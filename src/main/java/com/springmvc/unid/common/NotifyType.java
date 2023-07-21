package com.springmvc.unid.common;

public enum NotifyType {
    Application, // 팀원후보 -> 팀장, 지원서 신청
    Approval, // 팀장 -> 팀원후보, 지원 승인(팀 합류 허용)
    Reject, // 팀장 -> 팀원후보, 지원 거절
    TeamOut, // 팀원 -> 자신을 제외한 모든 팀원, 프로젝트 탈퇴
    TeamDelete // 팀장 -> 자신을 제외한 모든 팀원, 프로젝트 종료
}
