package com.springmvc.unid.service;

import com.springmvc.unid.domain.Team;
import com.springmvc.unid.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    // 1. 팀 조회
    public Team findById(String Id) {
        return teamRepository.findById(Id).orElse(null);
    }

    // 2. 팀 생성 -> save(Team team)이 내재되어 있음
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    // 3. 팀 삭제
    void deleteById(String Id) {
        teamRepository.deleteById(Id);
    }

    // 4. 팀 수정 -> save(Team team)이 역할을 겸함
    public Team update(Team team) {
        return teamRepository.save(team);
    }

    // 5. 특정 대학 소속 팀 조회
    public List<Team> findTeamByUniversity(String university) {
        return teamRepository.findTeamByUniversity(university);
    }
}
