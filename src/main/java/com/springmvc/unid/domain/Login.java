package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {

    private String id; // 사용자 id - 기본키

    private String pw; // 비밀번호
}
