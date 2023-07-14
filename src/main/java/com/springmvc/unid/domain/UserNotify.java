package com.springmvc.unid.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter @Getter
@Table(name = "user_notify")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserNotify { // User와 Notify의 다대다 관계로 인해 생성된 테이블

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_notify_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 수신 유저

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notify_id")
    private Notify notify; // 알림

    private LocalDate notifyDate; // 알림 수신 시간

    // 생성 메서드
    public static UserNotify createUserNotify(User user, Notify notify, LocalDate notifyDate) {
        UserNotify userNotify = new UserNotify();
        userNotify.setUser(user);
        userNotify.setNotify(notify);
        userNotify.setNotifyDate(notifyDate);
        return userNotify;
    }
}