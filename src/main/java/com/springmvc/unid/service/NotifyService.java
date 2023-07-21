package com.springmvc.unid.service;

import com.springmvc.unid.controller.dto.UserDto;
import com.springmvc.unid.controller.dto.NotifyDto;
import com.springmvc.unid.domain.Notify;
import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.domain.UserNotify;
import com.springmvc.unid.repository.NotifyRepository;
import com.springmvc.unid.repository.TeamRepository;
import com.springmvc.unid.repository.UserNotifyRepository;
import com.springmvc.unid.repository.UserRepository;
import com.springmvc.unid.util.exception.CustomException;
import com.springmvc.unid.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotifyService {

    private final NotifyRepository notifyRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final UserNotifyRepository userNotifyRepository;

    // 알림 생성 (및 전송)
    @Transactional
    public Long create(List<Long> receivedId, NotifyDto notifyDto) {
        User user = userRepository.findByName(notifyDto.getSender()).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        Team team = teamRepository.findByName(notifyDto.getTeamName()).orElseThrow(() -> new CustomException(ResponseCode.TEAM_NOT_FOUND));
        Notify notify = Notify.createNotify(notifyDto.getType(), user, team, notifyDto.getContents(), notifyDto.getLink());
        notifyRepository.save(notify);

        for (Long id : receivedId) {
            User receivedUser = userRepository.findById(id).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
            userNotifyRepository.save(UserNotify.createUserNotify(receivedUser, notify, LocalDate.now()));
        }
        return notify.getId();
    }

    // user가 받은 알림 삭제
    @Transactional
    public void deleteNotify(Long userId, Long notifyId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        Notify notify = notifyRepository.findById(notifyId).orElseThrow(() -> new CustomException(ResponseCode.NOTIFY_NOT_FOUND));
        UserNotify userNotify = userNotifyRepository.findByUserAndNotify(user, notify).orElseThrow(() -> new CustomException(ResponseCode.NOTIFY_NOT_FOUND));
        userNotifyRepository.delete(userNotify);
    }

    // 특정 user가 받은 모든 알림 조회
    public List<NotifyDto> findAllByUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getUserId()).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        List<UserNotify> userNotifies = userNotifyRepository.findByUser(user);
        return makeNotifyDtoListByUser(userNotifies);
    }

    // 특정 user가 보낸 모든 알림 조회
    public List<NotifyDto> findAllBySender(UserDto userDto) {
        User user = userRepository.findById(userDto.getUserId()).orElseThrow(() -> new RuntimeException("유저가 존재하지 않습니다."));
        List<Notify> notifies = notifyRepository.findByUser(user);
        return makeNotifyDtoList(notifies);
    }

    // user에게 알림을 전송
    @Transactional
    public void sendNotify(User user, Notify notify) {
        UserNotify userNotify = UserNotify.createUserNotify(user, notify, LocalDate.now());
        userNotifyRepository.save(userNotify);
    }

    // 전체 알림 조회
    public List<NotifyDto> findAll() {
        List<Notify> notifies = notifyRepository.findAll();
        return makeNotifyDtoList(notifies);
    }

    public static List<NotifyDto> makeNotifyDtoList(List<Notify> notifies) {
        return notifies.stream()
                .map(NotifyDto::new)
                .collect(Collectors.toList());
    }

    public static List<NotifyDto> makeNotifyDtoListByUser(List<UserNotify> userNotifies) {
        return userNotifies.stream()
                .map(UserNotify::getNotify)
                .map(NotifyDto::new)
                .collect(Collectors.toList());
    }
}
