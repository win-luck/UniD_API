package com.springmvc.unid.common;

public enum notifyType {
    application, // 팀원후보 -> 팀장, 지원서 신청
    approval, // 팀장 -> 팀원후보, 지원 승인(팀 합류 허용)
    reject, // 팀장 -> 팀원후보, 지원 거절
    teamOut, // 팀원 -> 자신을 제외한 모든 팀원, 프로젝트 탈퇴
    teamDelete // 팀장 -> 자신을 제외한 모든 팀원, 프로젝트 종료
}
