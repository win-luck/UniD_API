package com.springmvc.unid.controller;

import com.springmvc.unid.controller.dto.response.ResponseTeamDto;
import com.springmvc.unid.controller.dto.response.ResponseUserDto;
import com.springmvc.unid.controller.dto.request.*;
import com.springmvc.unid.controller.dto.response.ResponseNotifyDto;
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
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final TeamService teamService;
    private final NotifyService notifyService;

    // 로그인 (더미 데이터)
    @PostMapping("/login")
    public ApiResponse<Long> login(@RequestBody LoginDto loginDto){
        return ApiResponse.success(userService.login(loginDto.getLoginId(), loginDto.getPw()), ResponseCode.SIGNIN_SUCCESS.getMessage());
    }

    // 회원가입
    @PostMapping("/create")
    public ApiResponse<Long> join(@RequestBody CreateUserDto userDto){
        return ApiResponse.success(userService.join(userDto), ResponseCode.SIGNUP_SUCCESS.getMessage());
    }

    // 전체 회원 조회
    @GetMapping
    public ApiResponse<List<ResponseUserDto>> findAll(){
        return ApiResponse.success(userService.findUsers(), ResponseCode.USER_READ_SUCCESS.getMessage());
    }

    // 특정 회원 정보 조회
    @GetMapping("/{userId}")
    public ApiResponse<ResponseUserDto> find(@PathVariable Long userId){
        return ApiResponse.success(userService.findOne(userId), ResponseCode.USER_READ_SUCCESS.getMessage());
    }

    // 회원정보 수정
    @PutMapping("/{userId}")
    public ApiResponse<Void> update(@PathVariable Long userId, @RequestBody ResponseUserDto responseUserDto){
        userService.update(userId, responseUserDto.getName(), responseUserDto.getUniversity(), responseUserDto.getMajor(), responseUserDto.getLink());
        return ApiResponse.success(null, ResponseCode.USER_UPDATE_SUCCESS.getMessage());
    }

    // 회원 탈퇴
    @DeleteMapping("/{userId}")
    public ApiResponse<Void> delete(@PathVariable Long userId){
        userService.delete(userId);
        return ApiResponse.success(null, ResponseCode.USER_DELETE_SUCCESS.getMessage());
    }

    // user가 현재 소속된 팀 조회
    @GetMapping("/{userId}/teams")
    public ApiResponse<List<ResponseTeamDto>> getTeamList(@PathVariable Long userId){
        return ApiResponse.success(userService.findTeamsByUserId(userId), ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // user가 팀장인 팀 조회
    @GetMapping("/{userId}/teams/leader")
    public ApiResponse<List<ResponseTeamDto>> getLeaderTeamList(@PathVariable Long userId) {
        return ApiResponse.success(teamService.findTeamByLeader(userService.findOne(userId).getName()), ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // user가 현재 소속된 팀에서 탈퇴 (팀장이 아닌 팀원만 가능)
    @DeleteMapping("/{userId}/teams/leave")
    public ApiResponse<Void> leaveTeam(@PathVariable Long userId, @RequestBody ExitTeamDto exitTeamDto){
        teamService.leaveTeam(userId, exitTeamDto.getTeamId());
        return ApiResponse.success(null, ResponseCode.TEAM_LEAVE_SUCCESS.getMessage());
    }

    // user가 자신이 받은 알림을 조회
    @GetMapping("/{userId}/notifies")
    public ApiResponse<List<ResponseNotifyDto>> notify(@PathVariable Long userId){
        return ApiResponse.success(notifyService.findAllByUser(userService.findOne(userId).getUserId()), ResponseCode.NOTIFY_READ_SUCCESS.getMessage());
    }

    // user가 자신이 보낸 알림을 조회
    @GetMapping("/{userId}/notifies/send")
    public ApiResponse<List<ResponseNotifyDto>> notifySent(@PathVariable Long userId){
        return ApiResponse.success(notifyService.findAllBySender(userId), ResponseCode.NOTIFY_READ_SUCCESS.getMessage());
    }

    // user가 자신이 받은 알림을 삭제
    @DeleteMapping("/{userId}/notifies")
    public ApiResponse<Long> deleteMyNotify(@PathVariable Long userId, @RequestBody DeleteNotifyDto deleteNotifyDto){
        notifyService.deleteNotify(userId, deleteNotifyDto.getNotifyId());
        return ApiResponse.success(null, ResponseCode.NOTIFY_DELETE_SUCCESS.getMessage());
    }
}
