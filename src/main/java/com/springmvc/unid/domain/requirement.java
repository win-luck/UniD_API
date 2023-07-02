package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class requirement {
    private String position; // 구인 파트 - 기본키

    private String teamName; // 팀명 - Team에서 온 외래키, 기본키

    private Long n; // 구인 파트별 팀원 수

    private String requireContents; // 구인 요구사항
}
