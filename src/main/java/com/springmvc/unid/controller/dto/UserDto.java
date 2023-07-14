package com.springmvc.unid.controller.dto;

import com.springmvc.unid.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserDto의 용도
 * 1. 회원가입 Request/Response
 * 2. 로그인 성공 시 서버가 클라이언트에게 보내는 Response
 * 3. 회원정보 수정 Request/Response
 * 4. user 조회 결과 Response
 * 5. user가 받은 알림 조회를 위해 클라이언트가 서버에게 보내는 Request
 */
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
