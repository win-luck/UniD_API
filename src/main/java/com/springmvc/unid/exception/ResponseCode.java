package com.springmvc.unid.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ResponseCode {

        // 400 Bad Request 잘못된 요청
        BAD_REQUEST(HttpStatus.BAD_REQUEST, false, "잘못된 요청입니다."),
        NOT_TEAM_LEADER(HttpStatus.BAD_REQUEST, false,"팀장이 아닙니다."),
        USER_LOGIN_FAILED(HttpStatus.BAD_REQUEST, false,"로그인에 실패했습니다."),

        // 404 Not Found 리소스를 찾을 수 없음
        USER_NOT_FOUND(HttpStatus.NOT_FOUND, false,"User Not Found"),
        TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Team Not Found"),
        NOTIFY_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Notify Not Found"),
        REQUIREMENT_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Requirement Not Found"),

        // 409 Conflict 중복된 리소스
        DUPLICATED_USER(HttpStatus.CONFLICT, false,"Duplicated User"),
        DUPLICATED_TEAM(HttpStatus.CONFLICT, false, "Duplicated Team"),
        DUPLICATED_NOTIFY(HttpStatus.CONFLICT, false, "Duplicated Notify"),
        DUPLICATED_TEAM_MEMBER(HttpStatus.CONFLICT, false, "Duplicated Team Member"),

        // 500 Internal Server Error
        INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false,"Server Error");

        // 성공

        private final HttpStatus httpStatus;
        private final Boolean success;
        private final String message;
}
