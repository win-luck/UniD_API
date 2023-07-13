package com.springmvc.unid.controller;

import com.springmvc.unid.controller.dto.request.RequestCreateNotifyDto;
import com.springmvc.unid.controller.dto.response.NotifyDto;
import com.springmvc.unid.service.NotifyService;
import com.springmvc.unid.util.api.ApiResponse;
import com.springmvc.unid.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotifyController {

    private final NotifyService notifyService;

    // 전체 알림 조회
    @GetMapping("/api/notifies")
    public ApiResponse<List<NotifyDto>> getNotifyList(){
        List<NotifyDto> notifyDtoList = notifyService.findAll();
        return ApiResponse.success(notifyDtoList, ResponseCode.NOTIFY_READ_SUCCESS.getMessage());
    }

    // user가 알림을 생성하여 특정 user(들) 에게 전송
    @PostMapping("/api/notifies")
    public ApiResponse<Long> notifySend(@RequestBody RequestCreateNotifyDto notifyDto){
        notifyService.create(notifyDto.getReceiverIds(), notifyDto.getNotifyDto());
        return ApiResponse.success(1L, ResponseCode.NOTIFY_CREATE_SUCCESS.getMessage());
    }

}
