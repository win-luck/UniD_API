package com.springmvc.unid.repository;

import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    // 팀 생성 및 수정 -> save(Team team)

    // 팀 조회
    Optional<Team> findById(Long id);

    // 팀 삭제
    void deleteById(Long id);

    // 특정 대학 소속 팀 조회
    List<Team> findByUniversity(String university);

    // 특정 팀장이 이끄는 팀들 조회
    List<Team> findByUser(User user);

    List<Team> findByName(String teamName);
}
