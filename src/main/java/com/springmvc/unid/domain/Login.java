package com.springmvc.unid.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    @Id // pk
    @Column(nullable = false)
    private String id; // 사용자 id

    @Column(nullable = false)
    private String pw; // 비밀번호

}
