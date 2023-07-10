package com.springmvc.unid.domain;

import com.springmvc.unid.controller.dto.RequirementDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter @Getter
@Table(name = "requirement")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Requirement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requirement_id")
    private Long id;

    private String position; // 구인 파트

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team; // Team과의 다대일 관계

    private Long n; // 구인 파트별 팀원 수

    private String requireContents; // 구체적인 구인 요구사항

    // 생성 메서드
    public static Requirement createRequirement(RequirementDto requirementDto) {
        Requirement requirement = new Requirement();
        requirement.setPosition(requirementDto.getPosition());
        requirement.setN(requirementDto.getN());
        requirement.setRequireContents(requirementDto.getRequireContents());
        return requirement;
    }

}
