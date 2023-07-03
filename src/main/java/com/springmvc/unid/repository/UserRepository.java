package com.springmvc.unid.repository;

import com.springmvc.unid.domain.Login;
import com.springmvc.unid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    // 1. id로 회원 조회
    Optional<User> findById(String userId);

    // 2. 회원 가입 - save(User user)가 내재되어 있음

    // 3. 회원 탈퇴
    void deleteById(String userId);

    // 4. 회원 정보 수정 - save(User user)가 내재되어 있음
}
