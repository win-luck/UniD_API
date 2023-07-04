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

    // 1. 특정 팀의 팀원을 조회
    Optional<teamMember> findByTeamAndUser(Team team, User user);

    // 2. 사용자가 소속된 팀 조회
    List<teamMember> findByUser(User user);

    // 3. 팀에 소속된 모든 팀원 조회
    List<teamMember> findByTeam(Team team);

}
