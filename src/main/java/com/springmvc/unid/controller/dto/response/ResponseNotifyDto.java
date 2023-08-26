package com.springmvc.unid.controller.dto.response;

import com.springmvc.unid.common.NotifyType;
import com.springmvc.unid.domain.Notify;
import com.springmvc.unid.domain.UserNotify;
import lombok.Getter;

@Getter
public class ResponseNotifyDto {

    private Long notifyId; // 알림의 id
    private NotifyType type; // 알림의 종류
    private String sender; // 알림의 발신자
    private String contents; // 알림의 내용
    private String link; // 알림의 첨부 링크

    public ResponseNotifyDto(Notify notify) {
        this.notifyId = notify.getId();
        this.type = notify.getType();
        this.sender = notify.getUser().getName();
        this.contents = notify.getContents();
        this.link = notify.getLink();
    }

    public ResponseNotifyDto(UserNotify userNotify) {
        this.notifyId = userNotify.getNotify().getId();
        this.type = userNotify.getNotify().getType();
        this.sender = userNotify.getNotify().getUser().getName();
        this.contents = userNotify.getNotify().getContents();
        this.link = userNotify.getNotify().getLink();
    }
}
