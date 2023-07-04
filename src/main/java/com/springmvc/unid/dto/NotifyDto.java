package com.springmvc.unid.dto;

import lombok.Data;

@Data
public class NotifyDto {
    private final Long id;
    private final Long type;
    private final String senderName;
    private final String senderTeam;
    private final String senderContent;
    private final String link;

    public NotifyDto(Long id, Long type, String senderName, String senderTeam, String senderContent, String link) {
        this.id = id;
        this.type = type;
        this.senderName = senderName;
        this.senderTeam = senderTeam;
        this.senderContent = senderContent;
        this.link = link;
    }
}
