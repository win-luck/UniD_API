package com.springmvc.unid.controller.dto.request;

import com.springmvc.unid.domain.Notify;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RequestCreateNotifyDto의 용도
 * 알림 생성 요청 객체 (request)
 */
@Data
@NoArgsConstructor
public class RequestCreateNotifyDto {

    private Long type; // 알림의 종류
    private String sender; // 발신자의 id
    private String teamName; // 발신자가 지원한(소속된) 팀
    private String contents; // 알림(지원서, 가입승인, 가입거절, 탈퇴, 종료) 내용
    private String link; // 지원자(승인한 팀장)이 첨부한 링크

    public RequestCreateNotifyDto(Notify notify) {
        this.type = notify.getType();
        this.sender = notify.getUser().getName();
        this.teamName = notify.getTeam().getName();
        this.contents = notify.getContents();
        this.link = notify.getLink();
    }
}
