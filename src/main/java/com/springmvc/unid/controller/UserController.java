package com.springmvc.unid.controller;

import com.springmvc.unid.controller.dto.UserDto;
import com.springmvc.unid.controller.dto.request.RequestLoginDto;
import com.springmvc.unid.controller.dto.request.RequestNewUserDto;
import com.springmvc.unid.service.UserService;
import com.springmvc.unid.util.api.ApiResponse;
import com.springmvc.unid.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    // 로그인 V
    @PostMapping("/api/users/login")
    public ApiResponse<Long> login(@RequestBody RequestLoginDto requestLoginDto){
        Long id = userService.login(requestLoginDto.getLoginId(), requestLoginDto.getPw());
        if(id == null) return ApiResponse.fail(ResponseCode.USER_LOGIN_FAILED);
        return ApiResponse.success(id, ResponseCode.SIGNIN_SUCCESS.getMessage());
    }

    // 회원가입(+중복 user 방지) V
    @PostMapping("/api/users")
    public ApiResponse<Long> join(@RequestBody RequestNewUserDto userDto){
        Long id = userService.join(userDto);
        if(id == null) return ApiResponse.fail(ResponseCode.DUPLICATED_USER);
        return ApiResponse.success(id, ResponseCode.SIGNUP_SUCCESS.getMessage());
    }

    // 회원 탈퇴 V
    @DeleteMapping("/api/users/{id}")
    public ApiResponse<Long> delete(@PathVariable Long id){
        userService.delete(id);
        return ApiResponse.success(1L, ResponseCode.USER_DELETE_SUCCESS.getMessage());
    }

    // 회원 정보 수정 V
    @PostMapping("/api/users/{id}")
    public ApiResponse<Long> update(@PathVariable("id") Long id, @RequestBody UserDto userDto){
        userService.update(id, userDto.getName(), userDto.getUniversity(), userDto.getMajor(), userDto.getLink());
        return ApiResponse.success(1L, ResponseCode.USER_UPDATE_SUCCESS.getMessage());
    }

    // 회원 정보 조회 V
    @GetMapping("/api/users/{id}")
    public ApiResponse<UserDto> find(@PathVariable Long id){
        UserDto userDto = userService.findOne(id);
        return ApiResponse.success(userDto, ResponseCode.USER_READ_SUCCESS.getMessage());
    }

    // 전체 회원 조회 V
    @GetMapping("/api/users")
    public ApiResponse<List<UserDto>> findAll(){
        return ApiResponse.success(userService.findUsers(), ResponseCode.USER_READ_SUCCESS.getMessage());
    }

    /* 특정 대학에 소속된 회원 조회 : 필요성이 떨어져 보류
    @GetMapping("/api/users/{id}/")
    public ApiResponse<List<UserDto>> findByUniversity(@RequestBody String university){
        return ApiResponse.success(userService.findUsersByUniversity(university));
    }*/

    // 특정 알림을 받은 user 조회 : 마찬가지로 보류
}
