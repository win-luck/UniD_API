package com.springmvc.unid.util.api;

public class ApiHeader {
    private final int resultCode;
    private final String codeName;

    public ApiHeader(int resultCode, String codeName) {
        this.resultCode = resultCode;
        this.codeName = codeName;
    }

    public int getResultCode() {
        return resultCode;
    }

    public String getCodeName() {
        return codeName;
    }
}
