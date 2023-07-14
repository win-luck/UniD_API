package com.springmvc.unid.service;

import com.springmvc.unid.controller.dto.RequirementDto;
import com.springmvc.unid.controller.dto.TeamDto;
import com.springmvc.unid.controller.dto.UserDto;
import com.springmvc.unid.controller.dto.request.RequestCreateTeamDto;
import com.springmvc.unid.domain.Requirement;
import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.domain.TeamMember;
import com.springmvc.unid.repository.UserRepository;
import com.springmvc.unid.util.exception.CustomException;
import com.springmvc.unid.util.exception.ResponseCode;
import com.springmvc.unid.repository.TeamMemberRepository;
import com.springmvc.unid.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final TeamMemberRepository teamMemberRepository;

    public TeamDto findOne(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new CustomException(ResponseCode.TEAM_NOT_FOUND));
        return new TeamDto(team);
    }

    public List<TeamDto> findAll() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDto> teamDtos = new ArrayList<>();
        for (Team team : teams) {
            teamDtos.add(new TeamDto(team));
        }
        return teamDtos;
    }

    // user가 현재 소속된 팀 조회
    public List<TeamDto> findTeamByUser(UserDto userDto) {
        // List<TeamMember> TeamMembers = user.getTeamMemberList(); // 위와 아래의 차이 공부하기
        User user = userRepository.findByName(userDto.getName()).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        List<TeamMember> TeamMembers = teamMemberRepository.findByUser(user);
        List<TeamDto> teams = new ArrayList<>();
        for (TeamMember teamMember : TeamMembers) {
            teams.add(new TeamDto(teamMember.getTeam()));
        }
        return teams;
    }

    // user가 팀장인 팀 조회
    public List<TeamDto> findTeamByLeader(UserDto userDto) {
        List<Team> teams = teamRepository.findByUser(userRepository.findByName(userDto.getName()).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND)));
        List<TeamDto> teamsDtos = new ArrayList<>();
        for (Team team : teams) {
            teamsDtos.add(new TeamDto(team));
        }
        return teamsDtos;
    }

    // user의 대학 소속 팀 조회
    public List<TeamDto> findTeamByUniv(String university) {
        List<Team> teams= teamRepository.findByUniversity(university);
        List<TeamDto> teamsDtos = new ArrayList<>();
        for (Team team : teams) {
            teamsDtos.add(new TeamDto(team));
        }
        return teamsDtos;
    }

    // 팀 생성
    @Transactional
    public Long createTeam(RequestCreateTeamDto teamDto) {
        Team team = Team.createTeam(teamDto.getName(), userRepository.findByName(teamDto.getUser()).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND)),
                teamDto.getOneLine(), teamDto.getDescription(), teamDto.getLink(), teamDto.getUniversity());
        ValidateDuplicateTeam(team);
        team.setTeamLeader(team.getUser());
        teamRepository.save(team);
        teamMemberRepository.save(TeamMember.createTeamMember(team, team.getUser(), LocalDate.now()));
        return team.getId();
    }

    // 중복 팀명 검증
    public void ValidateDuplicateTeam(Team team) {
        Optional<Team> findTeam = teamRepository.findByName(team.getName());
        if (findTeam.isPresent()) {
            throw new CustomException(ResponseCode.DUPLICATED_TEAM);
        }
    }

    // 팀 정보 수정 (팀장만 가능)
    @Transactional
    public Long update(Long id, TeamDto teamDto, Long userId) {
        Team findTeam = teamRepository.findById(id).orElseThrow(() -> new CustomException(ResponseCode.TEAM_NOT_FOUND));
        if(!findTeam.getUser().getId().equals(userId)) throw new CustomException(ResponseCode.NOT_TEAM_LEADER);
        findTeam.updateTeam(teamDto);
        teamRepository.save(findTeam);
        return findTeam.getId();
    }

    // 팀 삭제 (팀장만 가능)
    @Transactional
    public void deleteTeam(Long teamId, Long leaderId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new CustomException(ResponseCode.TEAM_NOT_FOUND));
        if(!team.getUser().getId().equals(leaderId)) throw new CustomException(ResponseCode.NOT_TEAM_LEADER);
        teamRepository.delete(team);
    }

    // 특정 팀의 팀원 조회
    public List<UserDto> findTeamMember(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new CustomException(ResponseCode.TEAM_NOT_FOUND));
        List<TeamMember> teamMembers = teamMemberRepository.findByTeam(team);
        List<UserDto> userDtos = new ArrayList<>();
        for (TeamMember teamMember : teamMembers) {
            userDtos.add(new UserDto(teamMember.getUser()));
        }
        return userDtos;
    }

    // 특정 팀의 팀장 조회
    public UserDto findTeamLeader(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new CustomException(ResponseCode.TEAM_NOT_FOUND));
        return new UserDto(team.getUser());
    }

    // 팀에 팀원 가입
    @Transactional
    public void joinTeam(Long userId, Long teamId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new CustomException(ResponseCode.TEAM_NOT_FOUND));
        ValidateDuplicateTeamMember(user, team);
        TeamMember teamMember = TeamMember.createTeamMember(team, user, LocalDate.now());
        teamMemberRepository.save(teamMember);
    }

    // 이미 팀에 가입되어 있는 팀원인지 검증
    public void ValidateDuplicateTeamMember(User user, Team team) {
        List<TeamMember> findTeamMembers = teamMemberRepository.findByUser(user);
        for (TeamMember teamMember : findTeamMembers) {
            if (teamMember.getTeam().equals(team)) {
                throw new CustomException(ResponseCode.DUPLICATED_TEAM_MEMBER);
            }
        }
    }

    // 팀에 특정 팀원 탈퇴 (팀장은 탈퇴 불가능)
    @Transactional
    public void leaveTeam(Long userId, Long teamId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new CustomException(ResponseCode.TEAM_NOT_FOUND));
        if(team.getUser().equals(user)) throw new CustomException(ResponseCode.TEAM_LEADER_CANNOT_LEAVE);
        List<TeamMember> findTeamMembers = teamMemberRepository.findByUser(user);
        for (TeamMember teamMember : findTeamMembers) {
            if (teamMember.getTeam().equals(team)) {
                teamMemberRepository.delete(teamMember);
            }
        }
    }

    // 특정 팀의 팀장 변경 (팀장만 가능)
    @Transactional
    public void setTeamLeader(Long leaderId, Long nextId, Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new CustomException(ResponseCode.TEAM_NOT_FOUND));
        if(!team.getUser().getId().equals(leaderId)) throw new CustomException(ResponseCode.NOT_TEAM_LEADER);
        User nextLeader = userRepository.findById(nextId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        team.setUser(nextLeader);
        teamRepository.save(team);
    }

    // 특정 팀의 구인 요구사항 추가 (팀장만 가능)
    @Transactional
    public Long addRequirement(Long leaderId, RequirementDto requirementDto) {
        Requirement requirement = Requirement.createRequirement(requirementDto);
        Team team = requirement.getTeam();
        if (!team.getUser().getId().equals(leaderId)) {
            throw new CustomException(ResponseCode.NOT_TEAM_LEADER);
        }
        team.addRequirement(requirement);
        teamRepository.save(team);
        return requirement.getId();
    }

    // 특정 팀의 구인 요구사항 수정 (팀장만 가능)
    @Transactional
    public void updateRequirement(Long leaderId, Long reqId, RequirementDto requirementDto) {
        Team team = teamRepository.findById(requirementDto.getTeamId()).orElseThrow(() -> new CustomException(ResponseCode.TEAM_NOT_FOUND));
        if (!team.getUser().getId().equals(leaderId)) {
            throw new CustomException(ResponseCode.NOT_TEAM_LEADER);
        }
        team.modifyRequirement(reqId, requirementDto);
        teamRepository.save(team);
    }

    // 특정 팀의 구인 요구사항 제거 (팀장만 가능)
    @Transactional
    public void deleteRequirement(Long teamId, Long requirementId, Long userId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new CustomException(ResponseCode.TEAM_NOT_FOUND));
        if (!team.getUser().getId().equals(userId)) {
            throw new CustomException(ResponseCode.NOT_TEAM_LEADER);
        }
        team.deleteRequirement(requirementId);
        teamRepository.save(team);
    }

}
