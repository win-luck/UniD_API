package com.springmvc.unid.dto;

import com.springmvc.unid.domain.User;
import lombok.Data;

@Data
public class UserDto {
    private final String userId;
    private final String name;
    private final String pw;
    private final String university;
    private final String major;
    private final String link;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.pw = user.getPw();
        this.university = user.getUniversity();
        this.major = user.getMajor();
        this.link = user.getLink();
    }
}
