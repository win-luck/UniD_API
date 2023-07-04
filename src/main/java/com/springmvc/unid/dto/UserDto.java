package com.springmvc.unid.dto;

import lombok.Data;

@Data
public class UserDto {
    private final String userId;
    private final String name;
    private final String pw;
    private final String university;
    private final String major;
    private final String link;

    public UserDto(String userId, String name, String pw, String university, String major, String link) {
        this.userId = userId;
        this.name = name;
        this.pw = pw;
        this.university = university;
        this.major = major;
        this.link = link;
    }
}
