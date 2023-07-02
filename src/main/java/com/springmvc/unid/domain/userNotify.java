package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
public class userNotify { // User와 Notify의 다대다 관계로 인해 생성된 테이블
    @Id
    private Long id; // 다대다 관계 기본키

    private String receiverId; // 수신자 id - user의 id 외래키

    private String notifierId; // 알림을 보낸 발신자의 id - notify의 sendId 외래키

    private LocalTime notifyDate; // 알림 전송 시간
}
