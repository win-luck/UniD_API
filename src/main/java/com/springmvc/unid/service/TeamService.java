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
    public void createTeam(Team team) {
        ValidateDuplicateTeam(team);
        teamRepository.save(team);
    }

    // 중복 팀명 검증
    public void ValidateDuplicateTeam(Team team) {
        List<Team> findTeams = teamRepository.findByName(team.getName());
        if (!findTeams.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 팀입니다.");
        }
    }

    // 팀 정보 수정
    @Transactional
    public void updateTeam(Team team) {
        teamRepository.save(team);
    }

    // 팀 삭제
    @Transactional
    public void deleteTeam(Team team) {
        teamRepository.delete(team);
    }

    // 특정 팀의 팀장 변경
    @Transactional
    public void setTeamLeader(User user, Team team) {
        team.setTeamLeader(user);
    }

    // 특정 팀의 구인 요구사항 추가
    @Transactional
    public void addRequirement(Team team, Requirement requirement) {
        team.addRequirement(requirement);
    }

    // 특정 팀의 구인 요구사항 수정
    @Transactional
    public void updateRequirement(Team team, Requirement requirement) {
        team.modifyRequirement(requirement.getId(), requirement.getPosition(), requirement.getN(), requirement.getRequireContents());
    }

    // 특정 팀의 구인 요구사항 제거
    @Transactional
    public void removeRequirement(Team team, Requirement requirement) {
        team.removeRequirement(requirement.getId());
    }

    // user의 대학 소속 팀 조회
    public List<Team> findTeamByUniv(String university) {
        return teamRepository.findByUniversity(university);
    }

    // user가 팀장인 팀 조회
    public List<Team> findTeamByLeader(User user) {
        return teamRepository.findByUser(user);
    }

    // user가 현재 소속된 팀 조회
    public List<Team> findTeamByUser(User user) {
        // List<TeamMember> TeamMembers = user.getTeamMemberList(); // 위와 아래의 차이 공부하기
        List<TeamMember> TeamMembers = teamMemberRepository.findByUser(user);
        List<Team> teams = new ArrayList<>();
        for (TeamMember teamMember : TeamMembers) {
            teams.add(teamMember.getTeam());
        }
        return teams;
    }

}
