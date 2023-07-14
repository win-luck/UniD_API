package com.springmvc.unid.controller.dto.request;

import com.springmvc.unid.controller.dto.response.NotifyDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RequestCreateNotifyDto { // 알림 생성 요청
    private List<Long> receiverIds;
    private NotifyDto notifyDto;
}
