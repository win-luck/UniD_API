package com.springmvc.unid.repository;

import com.springmvc.unid.domain.Login;
import com.springmvc.unid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {
    // 로그인 시도
    Optional<Login> findByIdAndPw(String id, String pw);
}
