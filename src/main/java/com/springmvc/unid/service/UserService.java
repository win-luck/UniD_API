package com.springmvc.unid.service;

import com.springmvc.unid.domain.User;
import com.springmvc.unid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor // final 필드 또는 @NonNull 어노테이션이 지정된 필드만을(UserRepository) 대상으로 생성자를 생성
public class UserService {

    private final UserRepository userRepository;

    // 1. 회원 조회
    public User findById(String name) {
        return userRepository.findById(name).orElse(null);
    }

    // 2. 회원 가입
    public User save(User user) {
        return userRepository.save(user);
    }

    // 3. 회원 탈퇴
    void deleteById(String name) {
        userRepository.deleteById(name);
    }

    // 4. 회원 정보 수정
    public User update(User user) {
        return userRepository.save(user);
    }

    // 5. 로그인 (id, pw로 회원 조회)
    public User Login(String userId, String pw) {
        Optional<User> user = userRepository.findByUserIdAndPw(userId, pw);
        return user.orElse(null);
    }
}
