package com.springmvc.unid.repository;

import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.TeamMember;
import com.springmvc.unid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    // 특정 user의 소속 팀 조회
    List<TeamMember> findByUserId(Long userId);

    // 특정 팀의 소속 user 조회
    List<TeamMember> findByTeam(Team team);

    // 특정 user 조회
    Optional<TeamMember> findByUserAndTeam(User user, Team team);

    // user가 팀에서 탈퇴
    void delete(TeamMember teamMember);

    void deleteByUserAndTeam(User user, Team team);

    boolean existsByUserAndTeam(User user, Team team);
}
