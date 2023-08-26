package com.springmvc.unid.repository;

import com.springmvc.unid.domain.Notify;
import com.springmvc.unid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotifyRepository extends JpaRepository<Notify, Long> {

    // 알림 조회
    Optional<Notify> findById(Long Id);

    // user가 보낸 알림 조회
    List<Notify> findByUser(User user);

    // 알림 삭제
    void deleteById(Long Id);
}
