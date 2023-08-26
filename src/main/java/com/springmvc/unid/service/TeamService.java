package com.springmvc.unid.service;

import com.springmvc.unid.controller.dto.request.CreateNotifyDto;
import com.springmvc.unid.controller.dto.request.CreateRequirementDto;
import com.springmvc.unid.controller.dto.request.UpdateTeamDto;
import com.springmvc.unid.controller.dto.response.ResponseRequirementDto;
import com.springmvc.unid.controller.dto.response.ResponseTeamDto;
import com.springmvc.unid.controller.dto.response.ResponseUserDto;
import com.springmvc.unid.controller.dto.request.CreateTeamDto;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final TeamMemberRepository teamMemberRepository;

    // 특정 팀 조회
    @Transactional(readOnly = true)
    public ResponseTeamDto findOne(Long teamId) {
        Team team = getTeamById(teamId);
        return new ResponseTeamDto(team);
    }

    // 모든 팀 조회
    @Transactional(readOnly = true)
    public List<ResponseTeamDto> findAll() {
        List<Team> teams = teamRepository.findAll();
        return makeTeamDtoList(teams);
    }

    // 특정 user가 팀장인 팀 조회
    @Transactional(readOnly = true)
    public List<ResponseTeamDto> findTeamByLeader(String userName) {
        List<Team> teams = teamRepository.findByUser(userRepository.findByName(userName).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND)));
        return makeTeamDtoList(teams);
    }

    // 특정 user의 대학 소속 팀 조회
    @Transactional(readOnly = true)
    public List<ResponseTeamDto> findTeamByUniv(String university) {
        List<Team> teams = teamRepository.findByUniversity(university);
        return makeTeamDtoList(teams);
    }

    // 팀 생성
    @Transactional
    public Long createTeam(CreateTeamDto teamDto) {
        Team team = Team.createTeam(teamDto.getName(), userRepository.findById(teamDto.getLeaderId()).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND)),
                teamDto.getOneLine(), teamDto.getDescription(), teamDto.getLink(), teamDto.getUniversity());
        validateDuplicateTeam(team.getName());
        team.setTeamLeader(team.getUser());
        teamRepository.save(team);
        teamMemberRepository.save(TeamMember.createTeamMember(team, team.getUser()));
        return team.getId();
    }

    // 중복 팀명 검증
    private void validateDuplicateTeam(String name) {
        if (teamRepository.existsByName(name)) throw new CustomException(ResponseCode.DUPLICATED_TEAM);
    }

    // 팀 정보 수정 (팀장만 가능)
    @Transactional
    public void updateTeamInfo(Long teamId, UpdateTeamDto teamDto) {
        Team team = getTeamById(teamId);
        if (!team.isLeader(teamDto.getUserId())) throw new CustomException(ResponseCode.NOT_TEAM_LEADER);
        team.updateTeam(teamDto.getName(), teamDto.getOneLine(), teamDto.getDescription(), teamDto.getUniversity(), teamDto.getLink());
        teamRepository.save(team);
    }

    // 팀 삭제 (팀장만 가능)
    @Transactional
    public void deleteTeam(Long teamId, Long leaderId) {
        Team team = getTeamById(teamId);
        if (team.isLeader(leaderId)) throw new CustomException(ResponseCode.NOT_TEAM_LEADER);
        teamRepository.delete(team);
    }

    // 특정 팀의 팀원 조회
    @Transactional(readOnly = true)
    public List<ResponseUserDto> findTeamMembers(Long teamId) {
        Team team = getTeamById(teamId);
        List<TeamMember> teamMembers = teamMemberRepository.findByTeam(team);
        return teamMembers.stream().map(m -> new ResponseUserDto(m.getUser())).collect(Collectors.toList());
    }

    // 특정 팀의 팀장 조회
    @Transactional(readOnly = true)
    public ResponseUserDto findTeamLeader(Long teamId) {
        Team team = getTeamById(teamId);
        return new ResponseUserDto(team.getUser());
    }

    // 팀에 특정 팀원 가입
    @Transactional
    public void joinTeam(Long userId, Long teamId) {
        User user = getUserById(userId);
        Team team = getTeamById(teamId);
        validateDuplicateTeamMember(user, team);
        teamMemberRepository.save(TeamMember.createTeamMember(team, user));
    }

    // 이미 팀에 가입되어 있는 팀원인지 검증
    private void validateDuplicateTeamMember(User user, Team team) {
        if (teamMemberRepository.existsByUserAndTeam(user, team))
            throw new CustomException(ResponseCode.DUPLICATED_TEAM_MEMBER);
    }

    // 팀에 특정 팀원 탈퇴 (팀장은 탈퇴 불가능)
    @Transactional
    public void leaveTeam(Long userId, Long teamId) {
        User user = getUserById(userId);
        Team team = getTeamById(teamId);
        if (team.isLeader(userId)) throw new CustomException(ResponseCode.TEAM_LEADER_CANNOT_LEAVE);
        teamMemberRepository.deleteByUserAndTeam(user, team);
    }

    // 특정 팀의 팀장 변경 (팀장만 가능)
    @Transactional
    public void setTeamLeader(Long leaderId, String nextLeaderName, Long teamId) {
        Team team = getTeamById(teamId);
        if (!team.isLeader(leaderId)) throw new CustomException(ResponseCode.NOT_TEAM_LEADER);
        User nextLeader = userRepository.findByName(nextLeaderName).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        team.setTeamLeader(nextLeader);
        teamRepository.save(team);
    }

    // 특정 팀의 구인 요구사항 추가 (팀장만 가능)
    @Transactional
    public Long addRequirement(Long leaderId, CreateRequirementDto requirementDto) {
        Requirement requirement = Requirement.createRequirement(requirementDto.getPosition(), requirementDto.getN(), requirementDto.getContents());
        Team team = requirement.getTeam();
        if (!team.isLeader(leaderId)) throw new CustomException(ResponseCode.NOT_TEAM_LEADER);
        team.addRequirement(requirement);
        teamRepository.save(team);
        return requirement.getId();
    }

    // 특정 팀의 구인 요구사항 제거 (팀장만 가능)
    @Transactional
    public void deleteRequirement(Long teamId, Long requirementId, Long userId) {
        Team team = getTeamById(teamId);
        if (!team.isLeader(userId)) throw new CustomException(ResponseCode.NOT_TEAM_LEADER);
        team.deleteRequirement(requirementId);
        teamRepository.save(team);
    }

    private Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new CustomException(ResponseCode.TEAM_NOT_FOUND));
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
    }

    private List<ResponseTeamDto> makeTeamDtoList(List<Team> teams) {
        return teams.stream()
                .map(ResponseTeamDto::new)
                .collect(Collectors.toList());
    }
}
