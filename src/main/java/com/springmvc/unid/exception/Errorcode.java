package com.springmvc.unid.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Errorcode {

        // 400 Bad Request 잘못된 요청
        BAD_REQUEST(404, "잘못된 문법으로 인하여 서버가 요청을 이해할 수 없음"),

        // 404 Not Found 리소스를 찾을 수 없음
        NOT_TEAM_LEADER(404, "Not Team Leader"),
        USER_NOT_FOUND(404, "User Not Found"),
        USER_LOGIN_FAILED(404, ""),
        TEAM_NOT_FOUND(404, "Team Not Found"),
        NOTIFY_NOT_FOUND(404, "Notify Not Found"),
        REQUIREMENT_NOT_FOUND(404, "Requirement Not Found"),

        // 409 Conflict 중복된 리소스
        DUPLICATED_USER(409, "Duplicated User"),
        DUPLICATED_TEAM(409, "Duplicated Team"),
        DUPLICATED_NOTIFY(409, "Duplicated Notify"),

        // 500 Internal Server Error
        INTERNAL_SERVER_ERROR(500, "Server Error");

        private final int httpCode;
        private final String message;
}
