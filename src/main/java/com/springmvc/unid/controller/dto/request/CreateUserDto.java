package com.springmvc.unid.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserDto { // 회원가입 요청

    private String loginId;
    private String pw;
    private String name;
    private String university;
    private String major;
    private String link;
}
