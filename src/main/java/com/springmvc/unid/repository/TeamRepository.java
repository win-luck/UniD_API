package com.springmvc.unid.repository;

import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    // 1. 팀 조회
    Optional<Team> findById(String name);

    // 2. 팀 생성 -> save(Team team)이 내재되어 있음

    // 3. 팀 삭제
    void deleteById(String name);

    // 4. 팀 수정 -> save(Team team)이 역할을 겸함

    // 5. 특정 대학 소속 팀 조회
    List<Team> findTeamByUniversity(String university);

    // 6. 특정 user가 팀장인 팀 조회
    List<Team> findTeamByUser(User user);
}
