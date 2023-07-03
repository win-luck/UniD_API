package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "requirement")
public class Requirement {
    @Id
    @Column(nullable = false)
    private String position; // 구인 파트 - 기본키

    @ManyToOne
    private Team team; // Team과의 다대일 관계

    @Column(nullable = false)
    private Long n; // 구인 파트별 팀원 수

    @Column(name = "require_contents", nullable = false)
    private String requireContents; // 구인 요구사항
}
