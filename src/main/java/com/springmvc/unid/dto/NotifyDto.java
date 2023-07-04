package com.springmvc.unid.dto;

import com.springmvc.unid.domain.Notify;
import lombok.Data;

@Data
public class NotifyDto {
    private final Long id;
    private final Long type;
    private final String senderName;
    private final String senderTeam;
    private final String senderContent;
    private final String link;

    public NotifyDto(Notify notify) {
        this.id = notify.getNotifyId();
        this.type = notify.getType();
        this.senderName = notify.getUser().getName();
        this.senderTeam = notify.getTeam().getName();
        this.senderContent = notify.getContents();
        this.link = notify.getLink();
    }
}
