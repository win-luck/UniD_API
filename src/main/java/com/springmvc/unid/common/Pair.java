package com.springmvc.unid.common;

import lombok.Getter;

@Getter
public class Pair { // Pair 객체 구현
    private final String X;
    private final Long Y;

    public Pair(String X, Long Y) {
        this.X = X;
        this.Y = Y;
    }
}
