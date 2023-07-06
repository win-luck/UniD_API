package com.springmvc.unid.service;

import com.springmvc.unid.domain.User;
import com.springmvc.unid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService { // 7/7 00:17 작업 완료

    private final UserRepository userRepository;

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
        return userRepository.findOne(userId);
    }

}
