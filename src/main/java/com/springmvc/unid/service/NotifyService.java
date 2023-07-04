package com.springmvc.unid.service;

import com.springmvc.unid.domain.Notify;
import com.springmvc.unid.repository.NotifyRepository;
import com.springmvc.unid.repository.UserNotifyRepository;
import com.springmvc.unid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotifyService {

    private final NotifyRepository notifyRepository;

    // 1. 알림 조회
    public Notify findById(Long Id) {
        return notifyRepository.findById(Id).orElse(null);
    }

    // 2. 알림 생성
    public Notify save(Notify notify) {
        return notifyRepository.save(notify);
    }

    // 3. 알림 삭제
    void deleteById(Long Id) {
        notifyRepository.deleteById(Id);
    }
}
