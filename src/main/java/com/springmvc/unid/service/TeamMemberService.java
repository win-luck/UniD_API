package com.springmvc.unid.service;

import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.domain.teamMember;
import com.springmvc.unid.repository.TeamMemberRepository;
import com.springmvc.unid.repository.TeamRepository;
import com.springmvc.unid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    // 1. 특정 팀에 팀원이 가입
    public void addTeamMember(String teamName, String userId) {
        Team team = teamRepository.findById(teamName).orElseThrow(() -> new IllegalArgumentException("팀이 존재하지 않습니다."));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        teamMember teamMember = new teamMember();
        teamMember.setTeam(team);
        teamMember.setUser(user);
        teamMember.setJoinDate(LocalDate.now());
        teamMemberRepository.save(teamMember);
    }

    // 2. 특정 팀에서 팀원이 탈퇴
    public void deleteTeamMember(String teamName, String userId) {
        Team team = teamRepository.findById(teamName).orElseThrow(() -> new IllegalArgumentException("팀이 존재하지 않습니다."));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        teamMember teamMember = teamMemberRepository.findByTeamAndUser(team, user).orElseThrow(() -> new IllegalArgumentException("팀원이 존재하지 않습니다."));
        teamMemberRepository.delete(teamMember);
    }

    // 3. 사용자가 소속된 모든 팀 조회, 이때 사용자는 팀 객체에 접근 가능해야 함
    public List<Team> getTeamsByUser(String userId) {
        List<teamMember> teamMembers = teamMemberRepository.findByUser(userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다.")));
        return teamMembers.stream()
                .map(teamMember::getTeam)
                .collect(Collectors.toList());
    }

    // 4. 팀에 소속된 모든 팀원 조회
    public List<User> getUsersByTeam(String teamName) {
        List<teamMember> teamMembers = teamMemberRepository.findByTeam(teamRepository.findById(teamName).orElseThrow(() -> new IllegalArgumentException("팀이 존재하지 않습니다.")));
        return teamMembers.stream()
                .map(teamMember::getUser)
                .collect(Collectors.toList());
    }

}
