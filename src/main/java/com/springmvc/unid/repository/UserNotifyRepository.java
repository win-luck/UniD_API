package com.springmvc.unid.repository;

import com.springmvc.unid.domain.Notify;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.domain.UserNotify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserNotifyRepository extends JpaRepository<UserNotify, Long> {

    // 알림을 받은 user 조회
    List<UserNotify> findByNotify(Notify notify);

    // user가 받은 알림 조회
    List<UserNotify> findByUser(User user);

    // 특정 user에게 특정 알림 전송 - save(UserNotify userNotify);

    // user가 알림 삭제 - deleteById(Long id);

    // 특정 유저가 알림을 이미 받았는지 확인하기 위한 조회
    Optional<UserNotify> findByUserAndNotify(User user, Notify notify);
}
