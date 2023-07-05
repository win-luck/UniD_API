package com.springmvc.unid.service;

import com.springmvc.unid.domain.Notify;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.domain.userNotify;
import com.springmvc.unid.dto.NotifyDto;
import com.springmvc.unid.dto.UserDto;
import com.springmvc.unid.repository.NotifyRepository;
import com.springmvc.unid.repository.UserNotifyRepository;
import com.springmvc.unid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserNotifyService {

    private final UserNotifyRepository userNotifyRepository;
    private final UserRepository userRepository;
    private final NotifyRepository notifyRepository;

    // user가 수신한 모든 알림 조회(최신이 먼저 오도록)
    List<NotifyDto> findByUserOrderByNotifyDesc(UserDto userDto){
        User user = userRepository.findById(userDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + userDto.getUserId()));
        List<userNotify> notifyList = userNotifyRepository.findByUserOrderByNotifyDesc(user);
        List<NotifyDto> notifyDtoList = new ArrayList<>();
        for(userNotify userNotify : notifyList){
            notifyDtoList.add(new NotifyDto(userNotify.getNotify()));
        }
        return notifyDtoList;
    }

    // 특정 알림 user에게 전달
    void saveUserNotify(UserDto userDto, NotifyDto notifyDto){
        User user = userRepository.findById(userDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + userDto.getUserId()));
        Notify notify = notifyRepository.findById(notifyDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 알림이 없습니다. id=" + notifyDto.getId()));
        userNotifyRepository.save(new userNotify(user, notify));
    }

    // user의 특정 알림 삭제
    void deleteUsersNotify(UserDto userDto, NotifyDto notifyDto){
        User user = userRepository.findById(userDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + userDto.getUserId()));
        Notify notify = notifyRepository.findById(notifyDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 알림이 없습니다. id=" + notifyDto.getId()));
        userNotifyRepository.deleteByUserAndNotify(user, notify);
    }
}
