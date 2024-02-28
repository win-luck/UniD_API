package com.springmvc.unid.domain;

import com.springmvc.unid.common.NotifyType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notify {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notify_id")
    private Long id;

    private NotifyType type; // 알림의 종류

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 발신자의 id

    private String contents; // 알림(지원서, 가입승인, 가입거절, 탈퇴, 종료) 내용

    private String link; // 지원자(승인한 팀장)이 첨부한 링크

    @OneToMany(mappedBy = "notify")
    private List<UserNotify> userNotifies = new ArrayList<>();

    // 생성 메서드
    public static Notify createNotify(NotifyType type, User user, String contents, String link) {
        Notify notify = new Notify();
        notify.type = type;
        notify.user = user;
        notify.contents = contents;
        notify.link = link;
        return notify;
    }
}
