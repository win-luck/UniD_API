package com.springmvc.unid.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "team_member")
public class teamMember { // Team과 User의 다대다 관계로 인해 생성된 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성
    private Long id; // 자체 id - 기본키

    @ManyToOne
    @JoinColumn(name = "team_name")
    private Team team; // 팀명 - 외래키

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 사용자 id - 외래키

    @Column(name = "join_date", nullable = false)
    @CreatedDate
    private LocalDate joinDate; // 팀 가입일
}
