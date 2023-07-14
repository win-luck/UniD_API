package com.springmvc.unid.controller.dto;

import com.springmvc.unid.domain.Notify;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotifyDto {

        private Long notifyId; // 알림의 id
        private Long type; // 알림의 종류
        private String sender; // 알림의 발신자
        private String teamName; // 알림의 발신자가 소속된 팀 이름
        private String contents; // 알림의 내용
        private String link; // 알림의 첨부 링크

        public NotifyDto(Notify notify) {
                this.notifyId = notify.getId();
                this.type = notify.getType();
                this.sender = notify.getUser().getName();
                this.teamName = notify.getTeam().getName();
                this.contents = notify.getContents();
                this.link = notify.getLink();
        }
}
