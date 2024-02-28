package com.springmvc.unid.service;

import com.springmvc.unid.controller.dto.request.CreateNotifyDto;
import com.springmvc.unid.controller.dto.response.ResponseNotifyDto;
import com.springmvc.unid.domain.Notify;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.domain.UserNotify;
import com.springmvc.unid.repository.NotifyRepository;
import com.springmvc.unid.repository.UserNotifyRepository;
import com.springmvc.unid.repository.UserRepository;
import com.springmvc.unid.util.exception.CustomException;
import com.springmvc.unid.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotifyService {

    private final NotifyRepository notifyRepository;
    private final UserRepository userRepository;
    private final UserNotifyRepository userNotifyRepository;

    // 특정 user가 받은 모든 알림 조회
    @Transactional(readOnly = true)
    public List<ResponseNotifyDto> findAllByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        List<UserNotify> userNotifies = userNotifyRepository.findByUser(user);
        return makeNotifyDtoListByUser(userNotifies);
    }

    // user가 받은 알림 삭제
    @Transactional
    public void deleteNotify(Long userId, Long notifyId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        Notify notify = notifyRepository.findById(notifyId).orElseThrow(() -> new CustomException(ResponseCode.NOTIFY_NOT_FOUND));
        UserNotify userNotify = userNotifyRepository.findByUserAndNotify(user, notify).orElseThrow(() -> new CustomException(ResponseCode.NOTIFY_NOT_FOUND));
        userNotifyRepository.delete(userNotify);
    }

    // 특정 user가 보낸 모든 알림 조회
    @Transactional(readOnly = true)
    public List<ResponseNotifyDto> findAllBySender(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        List<Notify> notifies = notifyRepository.findByUser(user);
        return makeNotifyDtoList(notifies);
    }

    // 알림 생성 및 전송
    @Transactional
    public Long createAndSendNotices(CreateNotifyDto notifyDto) {
        User user = userRepository.findByName(notifyDto.getSender()).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        Notify notify = Notify.createNotify(notifyDto.getType(), user, notifyDto.getContents(), notifyDto.getLink());
        notifyRepository.save(notify); // 알림 생성

        for (Long id : notifyDto.getReceiverIds()) { // 알림 전송
            User receivedUser = userRepository.findById(id).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
            userNotifyRepository.save(UserNotify.createUserNotify(receivedUser, notify));
        }
        return notify.getId();
    }

    // 전체 알림 조회
    @Transactional(readOnly = true)
    public List<ResponseNotifyDto> findAll() {
        List<Notify> notifies = notifyRepository.findAll();
        return makeNotifyDtoList(notifies);
    }

    private List<ResponseNotifyDto> makeNotifyDtoList(List<Notify> notifies) {
        return notifies.stream()
                .map(ResponseNotifyDto::new)
                .collect(Collectors.toList());
    }

    private List<ResponseNotifyDto> makeNotifyDtoListByUser(List<UserNotify> userNotifies) {
        return userNotifies.stream()
                .map(ResponseNotifyDto::new)
                .collect(Collectors.toList());
    }
}
