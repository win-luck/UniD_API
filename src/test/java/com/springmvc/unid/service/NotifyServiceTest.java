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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class NotifyServiceTest {

    @Autowired NotifyService notifyService;
    @Autowired NotifyRepository notifyRepository;
    @Autowired UserNotifyRepository userNotifyRepository;
    @Autowired UserRepository userRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired TeamMemberRepository teamMemberRepository;

    @Test
    public void 알림생성() throws Exception {
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
        teamMemberRepository.save(teamMember1);

        Notify notify = Notify.createNotify(3L, user1, team, "죄송합니다", "");

        // when
        Long id = notifyService.create(notify);

        // then
        assertEquals(notify, notifyRepository.findById(id).orElse(null));
    }

    @Test
    public void 알림삭제() throws Exception {
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
        teamMemberRepository.save(teamMember1);

        Notify notify = Notify.createNotify(3L, user1, team, "죄송합니다", "");
        Long id = notifyService.create(notify);

        // when
        notifyService.delete(id);

        // then
        assertFalse(notifyRepository.findById(id).isPresent());
    }

    @Test
    public void user수신알림조회() throws Exception {
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        User user2 = User.createUser("LoginId2", "name2", "password2", "CAU", "CSE", "www.naver.com");User user3 = User.createUser("LoginId3", "name3", "password3", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);
        userRepository.save(user2);

        Notify notify1 = Notify.createNotify(3L, user2, null, "죄송합니다", "");
        Notify notify2 = Notify.createNotify(3L, user1, null, "아니에요 괜찮습니다.", "");
        Notify notify3 = Notify.createNotify(3L, user1, null, "아니에요 괜찮습니다.", "");
        notifyRepository.save(notify1);
        notifyRepository.save(notify2);
        notifyRepository.save(notify3);

        UserNotify userNotify1 = UserNotify.createUserNotify(user1, notify1, LocalDate.now());
        UserNotify userNotify2 = UserNotify.createUserNotify(user2, notify2, LocalDate.now());
        UserNotify userNotify3 = UserNotify.createUserNotify(user2, notify3, LocalDate.now());

        userNotifyRepository.save(userNotify1);
        userNotifyRepository.save(userNotify2);
        userNotifyRepository.save(userNotify3);

        // when
        List<Notify> userNotifies = notifyService.findAllByUserId(user1);
        List<Notify> userNotifies2 = notifyService.findAllByUserId(user2);
        assertEquals(1, userNotifies.size());
        assertEquals(2, userNotifies2.size());
    }

    @Test
    public void user에게알림전송() throws Exception {
        // given
        User user1 = User.createUser("LoginId1", "name1", "password1", "CAU", "CSE", "www.naver.com");
        User user2 = User.createUser("LoginId2", "name2", "password2", "CAU", "CSE", "www.naver.com");
        userRepository.save(user1);
        userRepository.save(user2);

        Team team = Team.createTeam("teamName", user1, "let's go", "we find the future", "CAU", "www.naver.com");
        teamRepository.save(team);

        TeamMember teamMember1 = TeamMember.createTeamMember(team, user1, LocalDate.now());
        teamMemberRepository.save(teamMember1);

        Notify notify = Notify.createNotify(3L, user1, team, "죄송합니다", "");
        notifyRepository.save(notify);

        // when
        notifyService.sendNotify(user2, notify);

        // then
        List<Notify> userNotifies = notifyService.findAllByUserId(user2);
        assertEquals(1, userNotifies.size());
    }
}
