package com.springmvc.unid.service;

import com.springmvc.unid.domain.Requirement;
import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.domain.TeamMember;
import com.springmvc.unid.repository.TeamMemberRepository;
import com.springmvc.unid.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    // 팀 생성
    @Transactional
    public Long createTeam(Team team) {
        ValidateDuplicateTeam(team);
        teamRepository.save(team);
        return team.getId();
    }

    // 중복 팀명 검증
    public void ValidateDuplicateTeam(Team team) {
        List<Team> findTeams = teamRepository.findByName(team.getName());
        if (!findTeams.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 팀입니다."); // Errorcode - DUPLICATED_TEAM
        }
    }

    // 팀 정보 수정
    @Transactional
    public void update(Long id, String newName, String newOneLine, String newDescription, String newLink) {
        Team findTeam = teamRepository.findById(id).orElse(null);
        if(findTeam == null) throw new IllegalStateException("존재하지 않는 팀입니다."); // Errorcode - TEAM_NOT_FOUND

        findTeam.setName(newName);
        findTeam.setOneLine(newOneLine);
        findTeam.setDescription(newDescription);
        findTeam.setLink(newLink);
        teamRepository.save(findTeam);
    }

    // 팀 삭제 (팀장만 가능)
    @Transactional
    public void deleteTeam(Team team, User leader) {
        if(!team.getUser().equals(leader)) throw new IllegalStateException("팀장만 가능합니다."); // Errorcode - NOT_TEAM_LEADER
        teamRepository.delete(team);
    }

    // 팀에 특정 팀원 가입
    @Transactional
    public void joinTeam(User user, Long teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        ValidateDuplicateTeamMember(user, team);
        TeamMember teamMember = TeamMember.createTeamMember(team, user , LocalDate.now());
        teamMemberRepository.save(teamMember);
    }

    // 이미 팀에 가입되어 있는 팀원인지 검증
    public void ValidateDuplicateTeamMember(User user, Team team) {
        List<TeamMember> findTeamMembers = teamMemberRepository.findByUser(user);
        for (TeamMember teamMember : findTeamMembers) {
            if (teamMember.getTeam().equals(team)) {
                throw new IllegalStateException("이미 팀에 가입되어 있는 팀원입니다.");
            }
        }
    }

    // 팀에 특정 팀원 탈퇴
    @Transactional
    public void leaveTeam(User user, Long teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        List<TeamMember> findTeamMembers = teamMemberRepository.findByUser(user);
        for (TeamMember teamMember : findTeamMembers) {
            if (teamMember.getTeam().equals(team)) {
                teamMemberRepository.delete(teamMember);
            }
        }
    }

    // 특정 팀의 팀장 변경 (팀장만 가능)
    @Transactional
    public void setTeamLeader(User leader, User user, Long teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);
        if(team == null) throw new IllegalStateException("존재하지 않는 팀입니다."); // Errorcode - TEAM_NOT_FOUND
        if(!team.getUser().equals(leader)) {
            throw new IllegalStateException("팀장만 가능합니다."); // Errorcode - NOT_TEAM_LEADER
        }
        team.setTeamLeader(user);
        teamRepository.save(team);
    }

    // 특정 팀의 구인 요구사항 추가 (팀장만 가능)
    @Transactional
    public void addRequirement(Long leaderId, Requirement requirement) {
        Team team = requirement.getTeam();
        if (!team.getUser().getId().equals(leaderId)) {
            throw new IllegalStateException("팀장만 가능합니다."); // Errorcode - NOT_TEAM_LEADER
        }
        team.addRequirement(requirement);
    }

    // 특정 팀의 구인 요구사항 수정 (팀장만 가능)
    @Transactional
    public void updateRequirement(Long leaderId, Long reqId, Requirement after) {
        // 팀장만 가능
        Team team = after.getTeam();
        if (!team.getUser().getId().equals(leaderId)) {
            throw new IllegalStateException("팀장만 가능합니다."); // Errorcode - NOT_TEAM_LEADER
        }
        Requirement before = team.getOneRequirement(reqId);
        team.modifyRequirement(before.getId(), after.getPosition(), after.getN(), after.getRequireContents());
    }

    // 특정 팀의 구인 요구사항 제거 (팀장만 가능)
    @Transactional
    public void removeRequirement(Long leaderId, Requirement requirement) {
        Team team = requirement.getTeam();
        if (!team.getUser().getId().equals(leaderId)) {
            throw new IllegalStateException("팀장만 가능합니다."); // Errorcode - NOT_TEAM_LEADER
        }
        team.removeRequirement(requirement.getId());
    }

    // user의 대학 소속 팀 조회
    public List<Team> findTeamByUniv(String university) {
        List<Team> teams = teamRepository.findByUniversity(university);
        if(teams.isEmpty()) throw new IllegalStateException("해당 대학에 소속된 팀이 없습니다."); // Errorcode - TEAM_NOT_FOUND
        return teams;
    }

    // user가 팀장인 팀 조회
    public List<Team> findTeamByLeader(User user) {
        List<Team> teams = teamRepository.findByUser(user);
        if(teams.isEmpty()) throw new IllegalStateException("팀장인 팀이 없습니다."); // Errorcode - TEAM_NOT_FOUND
        return teams;
    }

    // user가 현재 소속된 팀 조회
    public List<Team> findTeamByUser(User user) {
        // List<TeamMember> TeamMembers = user.getTeamMemberList(); // 위와 아래의 차이 공부하기
        List<TeamMember> TeamMembers = teamMemberRepository.findByUser(user);
        List<Team> teams = new ArrayList<>();
        for (TeamMember teamMember : TeamMembers) {
            teams.add(teamMember.getTeam());
        }
        if(teams.isEmpty()) throw new IllegalStateException("현재 소속된 팀이 없습니다."); // Errorcode - TEAM_NOT_FOUND
        return teams;
    }

}
