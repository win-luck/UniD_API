package com.springmvc.unid.util.api;


import com.springmvc.unid.util.exception.ResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    private ApiHeader header;
    private ApiBody body;

    private static final int SUCCESS = 200;

    public ApiResponse(ApiHeader header, ApiBody body) {
        this.header = header;
        this.body = body;
    }

    public ApiResponse(ApiHeader header){
        this.header = header;
    }

    public static <T> ApiResponse<T> success(T data){
        return new ApiResponse<T>(new ApiHeader(SUCCESS, "SUCCESS"), new ApiBody(data, null));
    }

    public static <T> ApiResponse<T> fail(ResponseCode responseCode){
        return new ApiResponse(new ApiHeader(responseCode.getHttpStatusCode(), responseCode.getMessage()), new ApiBody(null, responseCode.getMessage()));
    }
}
