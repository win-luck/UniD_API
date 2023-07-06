package com.springmvc.unid.repository;

import com.springmvc.unid.domain.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotifyRepository extends JpaRepository<Notify, Long> {
    // 알림 생성 -> save(Notify notify)

    // 알림 조회
    Optional<Notify> findById(Long Id);

    // 알림 삭제
    void deleteById(Long Id);
}
