package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@Table(name = "user_notify")
public class userNotify { // User와 Notify의 다대다 관계로 인해 생성된 테이블
    @Id
    @Column(nullable = false)
    private Long id; // 다대다 관계 기본키

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 수신자 id - user의 id 외래키

    @ManyToOne
    @JoinColumn(name = "send_id", nullable = false)
    private Notify notify; // 알림을 보낸 발신자의 id - notify의 sendId 외래키

    @Column(name = "notify_date", nullable = false)
    @CreatedBy
    private LocalTime notifyDate; // 알림 전송 시간
}