package com.springmvc.unid.controller.dto;

import com.springmvc.unid.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private Long userId;
    private String loginId;
    private String name;
    private String university;
    private String major;
    private String link;

    public UserDto(User user) {
        this.userId = user.getId();
        this.loginId = user.getLoginId();
        this.name = user.getName();
        this.university = user.getUniversity();
        this.major = user.getMajor();
        this.link = user.getLink();
    }
}
