package com.springmvc.unid.domain;

import com.springmvc.unid.common.Pair;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    private String name; // 팀명

    private String oneLine; // 팀 한줄설명

    private String description; // 팀 구체설명

    private String university; // 소속 대학

    private String major; // 소속 학과

    private HashMap<String, Pair> ReqAndCnt; // 팀원 모집 요건과 인원

    private ArrayList<String> links; // 팀 링크
}
