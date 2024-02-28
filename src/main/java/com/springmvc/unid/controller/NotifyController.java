package com.springmvc.unid.controller;

import com.springmvc.unid.controller.dto.request.CreateNotifyDto;
import com.springmvc.unid.controller.dto.response.ResponseNotifyDto;
import com.springmvc.unid.service.NotifyService;
import com.springmvc.unid.util.api.ApiResponse;
import com.springmvc.unid.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifies")
public class NotifyController {

    private final NotifyService notifyService;

    // 전체 알림 조회
    @GetMapping
    public ApiResponse<List<ResponseNotifyDto>> getNotifyList() {
        return ApiResponse.success(notifyService.findAll(),ResponseCode.NOTIFY_READ_SUCCESS.getMessage());
    }

    // user가 알림을 생성하여 특정 user(들)에게 전송
    @PostMapping
    public ApiResponse<Long> notifySend(@RequestBody CreateNotifyDto notifyDto) {
        return ApiResponse.success(notifyService.createAndSendNotices(notifyDto), ResponseCode.NOTIFY_CREATE_SUCCESS.getMessage());
    }
}
