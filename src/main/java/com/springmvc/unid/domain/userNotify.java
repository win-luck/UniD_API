package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "user_notify")
public class userNotify { // User와 Notify의 다대다 관계로 인해 생성된 테이블
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 다대다 관계 기본키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 수신자의 user_id - user의 기본키가 외래키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notify_id", nullable = false)
    private Notify notify; // 알람의 notify_id - notify의 기본키가 외래키

    @Column(name = "notify_date", nullable = false)
    @CreatedDate
    private LocalDate notifyDate; // 알림 생성 시간

    public userNotify() {
    }

    public userNotify(User user, Notify notify) {
        this.user = user;
        this.notify = notify;
        this.notifyDate = LocalDate.now();
    }
}
