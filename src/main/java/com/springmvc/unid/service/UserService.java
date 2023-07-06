package com.springmvc.unid.service;

import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.TeamMember;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.repository.TeamMemberRepository;
import com.springmvc.unid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TeamMemberRepository teamMemberRepository;

    // 로그인
    public Long login(String id, String password) {
        Optional<User> findUsers = userRepository.findByLoginIdAndPw(id, password);
        if (findUsers.isPresent()) {
            if (findUsers.get().getPw().equals(password)) {
                return findUsers.get().getId();
            } else {
                throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new IllegalStateException("존재하지 않는 user입니다.");
        }
    }

    // user 가입
    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    // 중복 user 검증
    private void validateDuplicateUser(User user) {
        Optional<User> findUsers = userRepository.findById(user.getId());
        if (findUsers.isPresent()) {
            throw new IllegalStateException("이미 존재하는 user입니다.");
        }
    }

    // user 탈퇴
    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    // user 정보 수정
    @Transactional
    public void update(User user) {
        userRepository.save(user);
    }

    // 전체 user 조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    // 특정 user 조회
    public User findOne(Long userId) {
        Optional<User> findUsers = userRepository.findById(userId);
        if (findUsers.isPresent()) {
            return findUsers.get();
        } else {
            throw new IllegalStateException("존재하지 않는 user입니다.");
        }
    }

    // 특정 팀에 현재 소속된 user 조회
    public List<User> findUsersByTeam(Team team) {
        // List<TeamMember> teamMembers = team.getTeamMembersList(); // 이렇게 하면 안되는 이유는 teamMemberList가 lazy로딩이기 때문에 teamMemberList를 사용할 때마다 쿼리가 실행되기 때문이다.
        List<TeamMember> teamMembers = teamMemberRepository.findByTeam(team); // 이렇게 해야 쿼리가 한 번만 실행된다.
        List<User> users = new ArrayList<>();
        for (TeamMember teamMember : teamMembers) {
            users.add(teamMember.getUser());
        }
        return users;
    }

}
