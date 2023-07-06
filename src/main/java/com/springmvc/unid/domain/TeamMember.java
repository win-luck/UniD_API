package com.springmvc.unid.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter @Getter
@Table(name = "team_member")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamMember { // Team과 User의 다대다 관계로 인해 생성된 테이블

    @Id @GeneratedValue
    @Column(name = "team_member_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 사용자 id - 외래키

    private LocalDate joinDate; // 팀 가입일

    // 생성 메서드
    public static TeamMember createTeamMember(Team team, User user, LocalDate joinDate) {
        TeamMember teamMember = new TeamMember();
        teamMember.setTeam(team);
        teamMember.setUser(user);
        teamMember.setJoinDate(joinDate);
        return teamMember;
    }
}