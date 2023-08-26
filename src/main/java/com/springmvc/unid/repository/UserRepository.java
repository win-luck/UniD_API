package com.springmvc.unid.repository;

import com.springmvc.unid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // id로 회원 조회
    Optional<User> findById(Long Id);

    // name으로 회원 조회
    Optional<User> findByName(String name);

    // 회원 탈퇴
    void deleteById(Long id);

    // 회원 로그인에 필요
    Optional<User> findByLoginIdAndPw(String loginId, String pw);

    boolean existsByName(String name);

    boolean existsByLoginId(String loginId);
}
