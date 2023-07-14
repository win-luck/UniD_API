package com.springmvc.unid.controller.dto.request;

import com.springmvc.unid.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestCreateUserDto { // 회원가입 요청

    private String loginId;
    private String pw;
    private String name;
    private String university;
    private String major;
    private String link;
}
