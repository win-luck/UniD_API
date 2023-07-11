package com.springmvc.unid.controller.dto.request;

import com.springmvc.unid.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestNewUserDto {
    private String loginId;
    private String pw;
    private String name;
    private String university;
    private String major;
    private String link;

    public RequestNewUserDto(User user) {
        this.loginId = user.getLoginId();
        this.pw = user.getPw();
        this.name = user.getName();
        this.university = user.getUniversity();
        this.major = user.getMajor();
        this.link = user.getLink();
    }
}
