package com.springmvc.unid.controller;

import com.springmvc.unid.controller.dto.TeamDto;
import com.springmvc.unid.controller.dto.UserDto;
import com.springmvc.unid.controller.dto.request.*;
import com.springmvc.unid.controller.dto.response.NotifyDto;
import com.springmvc.unid.service.NotifyService;
import com.springmvc.unid.service.TeamService;
import com.springmvc.unid.service.UserService;
import com.springmvc.unid.util.api.ApiResponse;
import com.springmvc.unid.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TeamService teamService;
    private final NotifyService notifyService;

    // 로그인 V
    @PostMapping("/api/users/login")
    public ApiResponse<Long> login(@RequestBody RequestLoginDto requestLoginDto){
        Long id = userService.login(requestLoginDto.getLoginId(), requestLoginDto.getPw());
        if(id == null) return ApiResponse.fail(ResponseCode.USER_LOGIN_FAILED);
        return ApiResponse.success(id, ResponseCode.SIGNIN_SUCCESS.getMessage());
    }

    // 회원가입(+중복 user 방지) V
    @PostMapping("/api/users")
    public ApiResponse<Long> join(@RequestBody RequestCreateUserDto userDto){
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

    // user가 특정 팀에 가입
    @PostMapping("/api/users/{id}/teams")
    public ApiResponse<Long> joinTeam(@PathVariable("id") Long id, @RequestBody RequestJoinTeamDto requestJoinTeamDto){
        teamService.joinTeam(id, requestJoinTeamDto.getTeamId());
        return ApiResponse.success(requestJoinTeamDto.getTeamId(), ResponseCode.TEAM_JOIN_SUCCESS.getMessage());
    }

    // user가 현재 소속된 팀 조회
    @GetMapping("/api/users/{id}/teams")
    public ApiResponse<List<TeamDto>> getTeamList(@PathVariable("id") Long id){
        List<TeamDto> teamDtoList = teamService.findTeamByUser(userService.findOne(id));
        return ApiResponse.success(teamDtoList, ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // user가 현재 소속된 팀에서 탈퇴 (팀장이 아닌 팀원만 가능)
    @PostMapping("/api/users/{id}/teams/leave")
    public ApiResponse<Long> leaveTeam(@PathVariable("id") Long id, @RequestBody RequestExitTeamDto requestExitTeamDto){
        teamService.leaveTeam(id, requestExitTeamDto.getTeamId());
        return ApiResponse.success(requestExitTeamDto.getTeamId(), ResponseCode.TEAM_LEAVE_SUCCESS.getMessage());
    }

    // user가 자신이 받은 알림을 조회
    @GetMapping("/api/users/{id}/notifies")
    public ApiResponse<Long> notify(@PathVariable("id") Long id){
        List<NotifyDto> notifyDtoList = notifyService.findAllByUser(userService.findOne(id));
        return ApiResponse.success(1L, ResponseCode.NOTIFY_READ_SUCCESS.getMessage());
    }

    // user가 자신이 보낸 알림을 조회
    @GetMapping("/api/users/{id}/notifies/sent")
    public ApiResponse<Long> notifySent(@PathVariable("id") Long id){
        List<NotifyDto> notifyDtoList = notifyService.findAllBySender(userService.findOne(id));
        return ApiResponse.success(1L, ResponseCode.NOTIFY_READ_SUCCESS.getMessage());
    }

    // user가 자신이 받은 알림을 삭제
    @PostMapping("/api/users/{id}/notifies")
    public ApiResponse<Long> deleteMyNotify(@PathVariable("id") Long id, @RequestBody RequestDeleteNotifyDto requestDeleteNotifyDto){
        notifyService.deleteNotify(id, requestDeleteNotifyDto.getNotifyId());
        return ApiResponse.success(1L, ResponseCode.NOTIFY_DELETE_SUCCESS.getMessage());
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
