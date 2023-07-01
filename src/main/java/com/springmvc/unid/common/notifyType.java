package com.springmvc.unid.common;

public enum notifyType {
    application, // 팀원 -> 팀장, 지원서 신청
    approval, // 팀장 -> 팀원, 지원 승인(팀 합류 허용)
    reject, // 팀장 -> 팀원, 지원 거절
    out, // 팀원, 프로젝트 탈퇴
    delete // 팀장, 프로젝트 종료
}
