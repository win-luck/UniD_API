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
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserNotifyRepository userNotifyRepository;
    @Autowired
    NotifyRepository notifyRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TeamMemberRepository teamMemberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        User user = User.createUser("LoginId", "name", "password", "CAU", "CSE", "www.naver.com");

        // when
        Long id = userService.join(user);

        // then
        assertEquals(user, userRepository.findById(id).orElse(null));
    }

    @Test
    public void 로그인() throws Exception {
        //given
        User user = User.createUser("LoginId", "name", "password", "CAU", "CSE", "www.naver.com");
        userService.join(user);

        // when
        Long id = userService.login("LoginId", "password");

        // then
        assertEquals(user, userRepository.findById(id).orElse(null));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        User user1 = User.createUser("LoginId", "name", "password", "CAU", "CSE", "www.naver.com");
        User user2 = User.createUser("LoginId", "name", "password", "CAU", "CSE", "www.naver.com");

        //when
        userService.join(user1);
        userService.join(user2); //예외가 발생해야 한다!!!

        //then
        fail("예외가 발생해야 한다.");
    }

    @Test
    public void 회원정보수정() throws Exception {
        //given
        User user = User.createUser("LoginId", "name", "password", "CAU", "CSE", "www.naver.com");
        userService.join(user);

        // when
        userService.update(user.getId(), "newName", "newPassword", "KU", "EE", "www.kakao.com");

        // then
        assertEquals("newName", userRepository.findById(user.getId()).orElse(null).getName());
    }

    @Test
    public void 회원탈퇴() throws Exception {
        //given
        User user = User.createUser("LoginId", "name", "password", "CAU", "CSE", "www.naver.com");
        userService.join(user);

        // when
        userService.delete(user.getId());

        // then
        assertNull(userRepository.findById(user.getId()).orElse(null));
    }

    @Test
    public void 특정대학소속회원조회() throws Exception {
        //given
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        User user2 = User.createUser("LoginId2", "name2", "password2", "CAU", "CSE", "www.naver.com");
        User user3 = User.createUser("LoginId3", "name3", "password3", "CAU", "CSE", "www.naver.com");
        User user4 = User.createUser("LoginId4", "name4", "password4", "CAU", "CSE", "www.naver.com");
        User user5 = User.createUser("LoginId5", "name5", "password5", "KU", "CSE", "www.naver.com");
        User user6 = User.createUser("LoginId6", "name6", "password6", "KU", "CSE", "www.naver.com");
        User user7 = User.createUser("LoginId7", "name7", "password7", "KU", "CSE", "www.naver.com");
        User user8 = User.createUser("LoginId8", "name8", "password8", "KU", "CSE", "www.naver.com");
        User user15 = User.createUser("LoginId15", "name15", "password15", "KU", "CSE", "www.naver.com");

        List<User> KUmembers = new ArrayList<>();
        KUmembers.add(user5);
        KUmembers.add(user6);
        KUmembers.add(user7);
        KUmembers.add(user8);
        KUmembers.add(user15);

        // when
        userService.join(user1);
        userService.join(user2);
        userService.join(user3);
        userService.join(user4);
        userService.join(user5);
        userService.join(user6);
        userService.join(user7);
        userService.join(user8);
        userService.join(user15);

        // then
        assertEquals(KUmembers, userService.findUsersByUniversity("KU"));
    }

    @Test
    public void 특정팀소속회원조회() {
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

        TeamMember teamMember1 = TeamMember.createTeamMember(team, user1, LocalDate.now());
        TeamMember teamMember2 = TeamMember.createTeamMember(team, user2, LocalDate.now());
        teamMemberRepository.save(teamMember1);
        teamMemberRepository.save(teamMember2);

        List<User> teamMembers = new ArrayList<>();
        teamMembers.add(user1);
        teamMembers.add(user2);

        // when
        List<User> teamMembersDB = userService.findUsersByTeam(team);

        // then
        assertEquals(teamMembers, teamMembersDB);
    }

    @Test
    public void 특정알림수신회원조회() {
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

        Notify notify1 = Notify.createNotify(3L, user1, team, "안녕하세요 소통해요", "www.naver.com");
        notifyRepository.save(notify1);

        UserNotify userNotify1 = UserNotify.createUserNotify(user2, notify1, LocalDate.now());
        UserNotify userNotify2 = UserNotify.createUserNotify(user3, notify1, LocalDate.now());
        userNotifyRepository.save(userNotify1);
        userNotifyRepository.save(userNotify2);

        HashSet<String> notifyUsers = new HashSet<>();
        notifyUsers.add(user2.getLoginId());
        notifyUsers.add(user3.getLoginId());

        // when
        HashSet<String> notifyUsersDB = new HashSet<>();
        List<User> notifyUsersDBList = userService.findUsersByUserNotify(notify1);
        for (User user : notifyUsersDBList) {
            notifyUsersDB.add(user.getLoginId());
        }

        // then
        assertEquals(notifyUsers, notifyUsersDB);
        // 순서가 중요한게 아니라면 Set으로 둘의 내용물이 동일하다는 것을 판정하는 것도 좋을 것 같다.
    }

}
