package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class teamMember { // Team과 User의 다대다 관계로 인해 생성된 테이블

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성
    private Long id; // 자체 id - 기본키

    @Column(name = "team_name", nullable = false)
    private String teamName; // 팀명 - 외래키

    @Column(name = "user_id", nullable = false)
    private String userId; // 사용자 id - 외래키

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate; // 팀 가입일

}
