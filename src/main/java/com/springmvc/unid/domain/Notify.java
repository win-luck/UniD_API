package com.springmvc.unid.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "notify")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notify {

    @Id @GeneratedValue
    @Column(name = "notify_id")
    private Long id;

    private Long type; // 알림의 종류

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 발신자의 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team; // 발신자가 지원한(소속된) 팀

    private String contents; // 알림(지원서, 가입승인, 가입거절, 탈퇴, 종료) 내용

    private String link; // 지원자(승인한 팀장)이 첨부한 링크

    @JsonIgnore // 클라이언트가 접근할 필요가 없는 정보
    @OneToMany(mappedBy = "notify")
    private List<UserNotify> userNotifies = new ArrayList<>(); // 이 알림을 수신한 사용자 명단

    // 생성 메서드
    public static Notify createNotify(Long type, User user, Team team, String contents, String link) {
        Notify notify = new Notify();
        notify.setType(type);
        notify.setUser(user);
        notify.setTeam(team);
        notify.setContents(contents);
        notify.setLink(link);
        return notify;
    }

}
