package com.springmvc.unid.repository;

import com.springmvc.unid.domain.Notify;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.domain.userNotify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserNotifyRepository extends JpaRepository<userNotify, String> {
    // 특정 유저가 수신한 모든 알림 조회
    List<userNotify> findByUser(User user);

    // 특정 알림을 수신한 모든 유저 조회
    List<userNotify> findByNotify(Notify notify);

    // 특정 유저가 수신한 모든 알림 조회(최신이 먼저 오도록)
    List<userNotify> findByUserOrderByNotifyDesc(User user);

    // user가 특정 알림 삭제
    void deleteByUserAndNotify(User user, Notify notify);
}
