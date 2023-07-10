package com.springmvc.unid.service;

import com.springmvc.unid.domain.Notify;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.domain.UserNotify;
import com.springmvc.unid.repository.NotifyRepository;
import com.springmvc.unid.repository.UserNotifyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotifyService {

    private final NotifyRepository notifyRepository;
    private final UserNotifyRepository userNotifyRepository;

    // 알림 생성
    @Transactional
    public Long create(Notify notify){
        notifyRepository.save(notify);
        return notify.getId();
    }

    // 알림 삭제
    @Transactional
    public void delete(Long notifyId){
        notifyRepository.deleteById(notifyId);
    }

    // 특정 user가 받은 모든 알림 조회
    public List<Notify> findAllByUserId(User user){
        List<UserNotify> userNotifies = userNotifyRepository.findByUser(user);
        List<Notify> notifies = new ArrayList<>();
        for(UserNotify userNotify : userNotifies){
            notifies.add(userNotify.getNotify());
        }
        if(notifies.isEmpty()) throw new IllegalStateException("존재하지 않는 알림입니다."); // Errorcode - NOT_FOUND_NOTIFY
        return notifies;
    }

    // user에게 알림을 전송
    @Transactional
    public void sendNotify(User user, Notify notify){
        UserNotify userNotify = UserNotify.createUserNotify(user, notify, LocalDate.now());
        userNotify.setUser(user);
        userNotify.setNotify(notify);
        if(userNotifyRepository.findByUserAndNotify(user, notify).isPresent()) throw new IllegalStateException("이미 존재하는 알림입니다."); // Errorcode - DUPLICATED_NOTIFY
        userNotifyRepository.save(userNotify);
    }

}
