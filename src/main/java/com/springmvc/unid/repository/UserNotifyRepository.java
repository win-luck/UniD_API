package com.springmvc.unid.repository;

import com.springmvc.unid.domain.Notify;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.domain.UserNotify;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserNotifyRepository extends JpaRepository<UserNotify, Long> {

    // user가 받은 알림 조회
    List<UserNotify> findByUser(User user);

    // 특정 유저가 알림을 이미 받았는지 확인하기 위한 조회
    Optional<UserNotify> findByUserAndNotify(User user, Notify notify);
}
