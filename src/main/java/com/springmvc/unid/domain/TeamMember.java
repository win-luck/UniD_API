package com.springmvc.unid.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamMember { // Team과 User의 다대다 관계로 인해 생성된 테이블

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_member_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    private LocalDate joinDate;

    // 생성 메서드
    public static TeamMember createTeamMember(Team team, User user) {
        TeamMember teamMember = new TeamMember();
        teamMember.team = team;
        teamMember.user = user;
        return teamMember;
    }
}
