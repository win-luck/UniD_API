package com.springmvc.unid.repository;

import com.springmvc.unid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 회원 가입 및 정보 수정 - save(User user)

    // id로 회원 조회
    Optional<User> findById(Long Id);

    // 회원 탈퇴
    void deleteById(Long id);

    // 회원 로그인에 필요
    Optional<User> findByLoginIdAndPw(String loginId, String pw);

}
