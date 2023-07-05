package com.springmvc.unid.service;

import com.springmvc.unid.domain.Notify;
import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.dto.NotifyDto;
import com.springmvc.unid.repository.NotifyRepository;
import com.springmvc.unid.repository.TeamRepository;
import com.springmvc.unid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotifyService {

    private final NotifyRepository notifyRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    // 1. 알림 조회
    public NotifyDto findById(Long Id) {
        return new NotifyDto(notifyRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("해당 알림이 없습니다. id=" + Id)));
    }

    // 2. 알림 생성 : 클라이언트가 Dto를 전달함
    public NotifyDto save(NotifyDto notifyDto) {
        Notify notify = DtoToEntity(notifyDto);
        return new NotifyDto(notifyRepository.save(notify));
    }

    // 3. 알림 삭제
    void deleteById(Long Id) {
        notifyRepository.deleteById(Id);
    }

    private Notify DtoToEntity(NotifyDto notifyDto) {
        Team team = teamRepository.findById(notifyDto.getSenderName()).orElseThrow(() -> new IllegalArgumentException("해당 팀이 없습니다. id=" + notifyDto.getSenderName()));
        User user = userRepository.findById(notifyDto.getSenderName()).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + notifyDto.getSenderName()));
        return new Notify(notifyDto.getType(), user, team, notifyDto.getSenderContent(), notifyDto.getLink());
    }
}
