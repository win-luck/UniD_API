package com.springmvc.unid.service;

import com.springmvc.unid.domain.*;
import com.springmvc.unid.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TeamServiceTest {

    @Autowired TeamService teamService;
    @Autowired UserRepository userRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired TeamMemberRepository teamMemberRepository;

    @Test
    public void 팀생성(){
        // given
        User user1 = User.createUser("LoginId", "name", "password", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);
        Team team = Team.createTeam("teamName", user1, "let's go", "we find the future", "CAU", "www.naver.com");

        // when
        Long id = teamService.createTeam(team);

        // then
        assertEquals(team, teamRepository.findById(id).orElse(null));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_팀명_예외(){
        // given
        User user1 = User.createUser("LoginId", "name", "password", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);
        Team team = Team.createTeam("teamName", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        Team team2 = Team.createTeam("teamName", user1, "let's go", "we find the future", "CAU", "www.naver.com");

        // when
        teamService.createTeam(team);
        teamService.createTeam(team2); // 예외가 발생해야 한다.
    }

    @Test
    public void 팀정보수정(){
        // given
        User user1 = User.createUser("LoginId", "name", "password", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);

        Team team = Team.createTeam("teamName", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        teamRepository.save(team);

        // when
        teamService.update(team.getId(), "teamName2", "let's go2", "we find the future2", "www.kakao.com");

        // then
        assertEquals("teamName2", team.getName());
    }

    @Test
    public void 팀삭제(){
        // given
        User user1 = User.createUser("LoginId", "name", "password", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);

        Team team = Team.createTeam("team1", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        Team team2 = Team.createTeam("team2", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        teamRepository.save(team);
        teamRepository.save(team2);

        // when
        teamService.deleteTeam(team);

        // then
        List<Team> findTeam = teamRepository.findByName("team1");
        assertEquals(0, findTeam.size());
    }

    @Test
    public void 팀가입(){
        // given
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        User user2 = User.createUser("LoginId2", "name2", "password2", "CAU", "CSE", "www.naver.com");
        User user3 = User.createUser("LoginId3", "name3", "password3", "CAU", "CSE", "www.naver.com");
        User user4 = User.createUser("LoginId4", "name4", "password4", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        Team team = Team.createTeam("teamName", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        teamRepository.save(team);

        // when
        teamService.joinTeam(user2, team.getId());
        teamService.joinTeam(user3, team.getId());

        // then
        List<TeamMember> teamMembers = teamMemberRepository.findByTeam(team);
        List<User> users = new ArrayList<>();
        for(TeamMember teamMember : teamMembers){
            users.add(teamMember.getUser());
        }
        assertEquals(2, users.size());
    }

    @Test
    public void 팀탈퇴(){
        // given
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        User user2 = User.createUser("LoginId2", "name2", "password2", "CAU", "CSE", "www.naver.com");
        User user3 = User.createUser("LoginId3", "name3", "password3", "CAU", "CSE", "www.naver.com");
        User user4 = User.createUser("LoginId4", "name4", "password4", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        Team team = Team.createTeam("teamName", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        teamRepository.save(team);

        // when
        teamService.joinTeam(user2, team.getId());
        teamService.joinTeam(user3, team.getId());
        teamService.joinTeam(user4, team.getId());

        teamService.leaveTeam(user2, team.getId());
        teamService.leaveTeam(user3, team.getId());

        // then
        List<TeamMember> teamMembers = teamMemberRepository.findByTeam(team);
        List<User> users = new ArrayList<>();
        for(TeamMember teamMember : teamMembers){
            users.add(teamMember.getUser());
        }
        assertEquals(1, users.size());
    }

    @Test
    public void 팀장변경() {
        // given
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        User user2 = User.createUser("LoginId2", "name2", "password2", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);
        userRepository.save(user2);

        Team team = Team.createTeam("teamName", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        teamRepository.save(team);

        // when
        teamService.setTeamLeader(user2, team.getId());

        // then
        assertEquals(user2, team.getUser());
    }

    @Test
    public void 팀요구사항추가() {
        // given
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);
        Team team = Team.createTeam("teamName", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        teamRepository.save(team);

        // when
        Requirement re = Requirement.createRequirement("backend", team, 3L, "스프링 경험자");
        teamService.addRequirement(user1.getId(), re);

        // then
        assertEquals(1, team.getRequirementList().size());
    }

    @Test(expected = IllegalStateException.class)
    public void 팀장만_요구사항_접근_가능(){
        // given
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        User user2 = User.createUser("LoginId2", "name2", "password2", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);
        userRepository.save(user2);

        Team team = Team.createTeam("teamName", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        teamRepository.save(team);

        Requirement re = Requirement.createRequirement("backend", team, 3L, "스프링 경험자");
        teamService.addRequirement(user1.getId(), re);

        // when
        Requirement re2 = Requirement.createRequirement("AI", team, 1L, "머신러닝 우대");
        teamService.addRequirement(user2.getId(), re2); // 팀장이 아니므로 실패해야 함
    }

    @Test
    public void 팀요구사항수정() {
        // given
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);

        Team team = Team.createTeam("teamName", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        teamRepository.save(team);

        Requirement re = Requirement.createRequirement("backend", team, 3L, "스프링 경험자");
        teamService.addRequirement(user1.getId(), re);

        // when
        Requirement re2 = Requirement.createRequirement("AI", team, 1L, "머신러닝 우대");
        teamService.updateRequirement(user1.getId(), re.getId(), re2);

        // then
        assertEquals("AI", team.getOneRequirement(re.getId()).getPosition());
    }

    @Test
    public void 팀요구사항제거() {
        // given
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);

        Team team = Team.createTeam("teamName", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        teamRepository.save(team);

        Requirement re = Requirement.createRequirement("backend", team, 3L, "스프링 경험자");
        teamService.addRequirement(user1.getId(), re);

        // when
        teamService.removeRequirement(user1.getId(), re);

        // then
        assertEquals(0, team.getRequirementList().size());
    }

    @Test
    public void user소속대학팀조회(){
        // given
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);

        Team team1 = Team.createTeam("teamName1", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        Team team2 = Team.createTeam("teamName2", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        Team team3 = Team.createTeam("teamName3", user1, "let's go", "we find the future", "KU", "www.naver.com");
        Team team4 = Team.createTeam("teamName4", user1, "let's go", "we find the future", "KU", "www.naver.com");
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
        teamRepository.save(team4);

        // when
        List<Team> teams = teamService.findTeamByUniv("CAU");

        // then
        assertEquals(2, teams.size());
    }

    @Test
    public void user팀장인팀조회(){
        // given
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        User user2 = User.createUser("LoginId2", "name2", "password2", "CAU", "CSE", "www.naver.com");
        User user3 = User.createUser("LoginId3", "name3", "password3", "CAU", "CSE", "www.naver.com");
        User user4 = User.createUser("LoginId4", "name4", "password4", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        Team team1 = Team.createTeam("teamName1", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        Team team2 = Team.createTeam("teamName2", user2, "let's go", "we find the future", "CAU", "www.naver.com");
        Team team3 = Team.createTeam("teamName3", user3, "let's go", "we find the future", "KU", "www.naver.com");
        Team team4 = Team.createTeam("teamName4", user4, "let's go", "we find the future", "KU", "www.naver.com");
        teamRepository.save(team1);
        teamRepository.save(team2);
        teamRepository.save(team3);
        teamRepository.save(team4);

        // when
        List<Team> teams = teamService.findTeamByLeader(user1);

        // then
        assertEquals(1, teams.size());
    }

    @Test
    public void user소속된팀조회(){
        // given
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        User user2 = User.createUser("LoginId2", "name2", "password2", "CAU", "CSE", "www.naver.com");
        User user3 = User.createUser("LoginId3", "name3", "password3", "CAU", "CSE", "www.naver.com");
        User user4 = User.createUser("LoginId4", "name4", "password4", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        Team team1 = Team.createTeam("teamName1", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        Team team2 = Team.createTeam("teamName2", user2, "let's go", "we find the future", "CAU", "www.naver.com");
        teamRepository.save(team1);
        teamRepository.save(team2);

        teamMemberRepository.save(TeamMember.createTeamMember(team1, user1, LocalDate.now()));
        teamMemberRepository.save(TeamMember.createTeamMember(team1, user2, LocalDate.now()));
        teamMemberRepository.save(TeamMember.createTeamMember(team1, user3, LocalDate.now()));
        teamMemberRepository.save(TeamMember.createTeamMember(team2, user1, LocalDate.now()));

        // when
        List<Team> teams = teamService.findTeamByUser(user1);

        // then
        assertEquals( 2, teams.size());
    }
}
