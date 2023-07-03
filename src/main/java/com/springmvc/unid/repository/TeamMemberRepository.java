package com.springmvc.unid.repository;

import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.domain.teamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamMemberRepository extends JpaRepository<teamMember, Long> {
    /*
    데이터 저장 및 업데이트:
    save(entity): 엔티티를 저장하거나 업데이트합니다.
    saveAll(entities): 여러 개의 엔티티를 저장하거나 업데이트합니다.

    데이터 삭제:
    delete(entity): 엔티티를 삭제합니다.
    deleteById(id): 주어진 ID에 해당하는 엔티티를 삭제합니다.
    deleteAll(): 모든 엔티티를 삭제합니다.
    deleteAll(entities): 주어진 엔티티 목록을 삭제합니다.

    데이터 조회:
    findById(id): 주어진 ID에 해당하는 엔티티를 조회합니다.
    findAll(): 모든 엔티티를 조회합니다.
    findAllById(ids): 주어진 ID 목록에 해당하는 엔티티들을 조회합니다.

    데이터 개수 조회:
    count(): 엔티티의 총 개수를 조회합니다.
    데이터 존재 여부 확인:
    existsById(id): 주어진 ID에 해당하는 엔티티의 존재 여부를 확인합니다.

    기타:
    flush(): 현재 영속성 컨텍스트에 있는 변경 사항을 데이터베이스에 즉시 반영합니다.
    refresh(entity): 영속성 컨텍스트의 엔티티를 데이터베이스로부터 다시 로드합니다.

    이러한 메서드들은 Spring Data JPA의 JpaRepository 인터페이스를 상속받아서 기본적으로 제공됩니다.
    */

    // teamName으로 team의 소속 팀원 찾기
    List<teamMember> findByTeam(Team team);

    // userId로 user의 소속 팀 찾기
    List<teamMember> findByUser(User user);

    // user가 특정 팀에서 탈퇴
    void deleteById(Long id); // id로
    void deleteByTeamAndUser(Team team, User user); // team와 user로 탈퇴

    // user가 특정 팀에 가입 -> save(teamMember teamMember)가 내재되어 있음
}
