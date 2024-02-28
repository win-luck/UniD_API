package com.springmvc.unid.controller.dto.request;

import com.springmvc.unid.common.NotifyType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateNotifyDto { // 알림 생성 요청

    private List<Long> receiverIds; // 알림의 수신자 id 목록
    private NotifyType type; // 알림의 종류
    private String sender; // 알림의 발신자
    private String contents; // 알림의 내용
    private String link; // 알림의 첨부 링크
}
