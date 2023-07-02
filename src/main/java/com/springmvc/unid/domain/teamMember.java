package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class teamMember { // Team과 User의 다대다 관계로 인해 생성된 테이블

    private Long id; // 자체 id - 기본키

    private String teamName; // 팀명 - 외래키

    private String userId; // 사용자 id - 외래키

    private LocalDate joinDate; // 팀 가입일

}
