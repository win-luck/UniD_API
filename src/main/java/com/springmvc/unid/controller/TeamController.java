package com.springmvc.unid.controller;

import com.springmvc.unid.controller.dto.response.ResponseTeamDto;
import com.springmvc.unid.controller.dto.response.ResponseUserDto;
import com.springmvc.unid.controller.dto.request.*;
import com.springmvc.unid.service.TeamService;
import com.springmvc.unid.util.api.ApiResponse;
import com.springmvc.unid.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    // 전체 팀 조회
    @GetMapping
    public ApiResponse<List<ResponseTeamDto>> getTeams() {
        return ApiResponse.success(teamService.findAll(), ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // 팀 생성 (+ 팀장으로 등록, 중복 팀 생성 방지 포함)
    @PostMapping
    public ApiResponse<Long> createTeam(@RequestBody CreateTeamDto teamDto) {
        return ApiResponse.success(teamService.createTeam(teamDto), ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // 특정 팀 정보 조회
    @GetMapping("/{teamId}")
    public ApiResponse<ResponseTeamDto> getTeamInfo(@PathVariable Long teamId) {
        return ApiResponse.success(teamService.findOne(teamId), ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // 특정 팀 정보 수정 (팀장만 가능)
    @PutMapping("/{teamId}")
    public ApiResponse<Void> updateTeam(@PathVariable Long teamId, @RequestBody UpdateTeamDto updateTeamDto) {
        teamService.updateTeamInfo(teamId, updateTeamDto);
        return ApiResponse.success(null, ResponseCode.TEAM_UPDATE_SUCCESS.getMessage());
    }

    // 특정 팀 삭제 (팀장만 가능)
    @DeleteMapping("/{teamId}")
    public ApiResponse<Void> deleteTeam(@PathVariable Long teamId, @RequestBody Long userId) {
        teamService.deleteTeam(teamId, userId);
        return ApiResponse.success(null, ResponseCode.TEAM_DELETE_SUCCESS.getMessage());
    }

    // 특정 팀에 user가 가입
    @PostMapping("{teamId}/join")
    public ApiResponse<Void> joinTeam(@PathVariable Long teamId, @RequestBody JoinTeamDto joinTeamDto){
        teamService.joinTeam(teamId, joinTeamDto.getTeamId());
        return ApiResponse.success(null, ResponseCode.TEAM_JOIN_SUCCESS.getMessage());
    }

    // 특정 팀의 팀장 조회
    @GetMapping("/{teamId}/leader")
    public ApiResponse<ResponseUserDto> getTeamLeader(@PathVariable Long teamId) {
        return ApiResponse.success(teamService.findTeamLeader(teamId), ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // 특정 팀의 팀원 조회
    @GetMapping("/{teamId}/members")
    public ApiResponse<List<ResponseUserDto>> getTeamMember(@PathVariable Long teamId) {
        return ApiResponse.success(teamService.findTeamMembers(teamId), ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // user의 대학의 팀 조회
    @GetMapping("/university/{university}")
    public ApiResponse<List<ResponseTeamDto>> getTeamListByUniv(@PathVariable String university) {
        return ApiResponse.success(teamService.findTeamByUniv(university), ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // 특정 팀의 팀장 변경 (팀장만 가능)
    @PutMapping("/{teamId}/leader")
    public ApiResponse<Void> changeLeader(@PathVariable Long teamId, @RequestBody UpdateTeamLeaderDto updateTeamLeaderDto) {
        teamService.setTeamLeader(updateTeamLeaderDto.getLeaderId(), updateTeamLeaderDto.getNextLeaderName(), teamId);
        return ApiResponse.success(null, ResponseCode.TEAM_UPDATE_SUCCESS.getMessage());
    }

    // 특정 팀의 구인 요구사항 추가 (팀장만 가능)
    @PostMapping("/{teamId}/requirement")
    public ApiResponse<Void> addRequirement(@PathVariable Long teamId, @RequestBody CreateRequirementDto requirementDto) {
        teamService.addRequirement(teamId, requirementDto);
        return ApiResponse.success(null, ResponseCode.REQUIREMENT_UPDATE_SUCCESS.getMessage());
    }

    // 특정 팀의 구인 요구사항 제거 (팀장만 가능)
    @DeleteMapping("/{teamId}/requirement")
    public ApiResponse<Void> deleteRequirement(@PathVariable Long teamId, @RequestBody DeleteRequirementDto deleteRequirementDto) {
        teamService.deleteRequirement(teamId, deleteRequirementDto.getRequirementId(), deleteRequirementDto.getUserId());
        return ApiResponse.success(null, ResponseCode.REQUIREMENT_DELETE_SUCCESS.getMessage());
    }
}
