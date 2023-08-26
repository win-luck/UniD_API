package com.springmvc.unid.controller.dto.response;

import com.springmvc.unid.domain.User;
import lombok.Getter;

@Getter
public class ResponseUserDto {

    private Long userId;
    private String loginId;
    private String name;
    private String university;
    private String major;
    private String link;

    public ResponseUserDto(User user) {
        this.userId = user.getId();
        this.loginId = user.getLoginId();
        this.name = user.getName();
        this.university = user.getUniversity();
        this.major = user.getMajor();
        this.link = user.getLink();
    }
}
