package com.springmvc.unid.util.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 응답 코드와 응답 메시지를 정의한 enum 클래스
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
@Getter
public enum ResponseCode {

        /**
         * 400 Bad Request
         */
        BAD_REQUEST(HttpStatus.BAD_REQUEST, false, "잘못된 요청입니다."),
        NOT_TEAM_LEADER(HttpStatus.BAD_REQUEST, false,"팀장이 아닙니다."),
        USER_LOGIN_FAILED(HttpStatus.BAD_REQUEST, false,"로그인에 실패했습니다."),
        TEAM_LEADER_CANNOT_LEAVE(HttpStatus.BAD_REQUEST, false,"팀장은 팀에서 탈퇴할 수 없습니다."),

        /**
         * 404 NOT FOUND
         */
        USER_NOT_FOUND(HttpStatus.NOT_FOUND, false,"User Not Found"),
        TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Team Not Found"),
        NOTIFY_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Notify Not Found"),
        REQUIREMENT_NOT_FOUND(HttpStatus.NOT_FOUND, false,"Requirement Not Found"),

        /**
         * 409 CONFLICT
         */
        DUPLICATED_USER(HttpStatus.CONFLICT, false,"Duplicated User"),
        DUPLICATED_TEAM(HttpStatus.CONFLICT, false, "Duplicated Team"),
        DUPLICATED_NOTIFY(HttpStatus.CONFLICT, false, "Duplicated Notify"),
        DUPLICATED_TEAM_MEMBER(HttpStatus.CONFLICT, false, "Duplicated Team Member"),

        /**
         * 500 INTERNAL SERVER ERROR
         */
        INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false,"Server Error"),

        /**
         * 200 OK
         */
        SIGNIN_SUCCESS(HttpStatus.OK, true, "로그인 성공"),
        USER_READ_SUCCESS(HttpStatus.OK, true, "유저 조회 성공"),
        TEAM_READ_SUCCESS(HttpStatus.OK, true, "팀 조회 성공"),
        TEAM_JOIN_SUCCESS(HttpStatus.OK, true, "팀 가입 성공"),
        TEAM_MEMBER_READ_SUCCESS(HttpStatus.OK, true, "팀원 조회 성공"),
        REQUIREMENT_READ_SUCCESS(HttpStatus.OK, true, "요구사항 조회 성공"),

        NOTIFY_READ_SUCCESS(HttpStatus.OK, true, "알림 조회 성공"),
        NOTIFY_SEND_SUCCESS(HttpStatus.OK, true, "알림 전송 성공"),

        USER_DELETE_SUCCESS(HttpStatus.OK, true, "유저 탈퇴 성공"),
        TEAM_DELETE_SUCCESS(HttpStatus.OK, true, "팀 삭제 성공"),
        TEAM_LEAVE_SUCCESS(HttpStatus.OK, true, "팀 탈퇴 성공"),
        REQUIREMENT_DELETE_SUCCESS(HttpStatus.OK, true, "요구사항 삭제 성공"),
        NOTIFY_DELETE_SUCCESS(HttpStatus.OK, true, "알림 삭제 성공"),

        USER_UPDATE_SUCCESS(HttpStatus.OK, true, "유저 정보 수정 성공"),
        TEAM_UPDATE_SUCCESS(HttpStatus.OK, true, "팀 정보 수정 성공"),
        REQUIREMENT_UPDATE_SUCCESS(HttpStatus.OK, true, "요구사항 정보 수정 성공"),


        /**
         *  201 Created
         */
        SIGNUP_SUCCESS(HttpStatus.CREATED, true, "회원가입 성공"),
        TEAM_CREATE_SUCCESS(HttpStatus.CREATED, true, "팀 생성 성공"),
        NOTIFY_CREATE_SUCCESS(HttpStatus.CREATED, true, "알림 생성 성공"),
        REQUIREMENT_CREATE_SUCCESS(HttpStatus.CREATED, true, "요구사항 생성 성공"),

        /**
         * 204 No Content
         */
        LIST_EMPTY(HttpStatus.NO_CONTENT, true, "리스트가 비어있습니다.");

        private final HttpStatus httpStatus;
        private final Boolean success;
        private final String message;

        public int getHttpStatusCode() {
            return httpStatus.value();
        }
}
