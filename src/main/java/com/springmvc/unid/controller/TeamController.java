package com.springmvc.unid.controller;

import com.springmvc.unid.controller.dto.RequirementDto;
import com.springmvc.unid.controller.dto.TeamDto;
import com.springmvc.unid.controller.dto.UserDto;
import com.springmvc.unid.controller.dto.request.UpdateTeamLeaderRequestDto;
import com.springmvc.unid.controller.dto.request.UpdateTeamRequestDto;
import com.springmvc.unid.service.TeamService;
import com.springmvc.unid.service.UserService;
import com.springmvc.unid.util.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    /*private final TeamService teamService;
    private final UserService userService;

    // user가 현재 소속된 팀 리스트 조회
    @GetMapping("/api/teams/with/{id}")
    public ApiResponse<List<TeamDto>> getTeamList(@PathVariable("id") Long id){
        List<TeamDto> teamDtoList = teamService.findTeamByUser(userService.findOne(id));
        return ApiResponse.success(teamDtoList,);
    }

    // user가 현재 소속된 팀에서 탈퇴 (팀장이 아닌 팀원만 가능)
    @PostMapping("/api/teams/with/{id}/leav")
    public ApiResponse<Long> leaveTeam(@PathVariable("id") Long id, @RequestBody Long teamId){
        teamService.leaveTeam(id, teamId);
        return ApiResponse.success(teamId);
    }

    // 특정 팀에 user가 가입(+ 중복 가입 시도 방지 포함)
    @PostMapping("/api/teams/with/{id}/join")
    public ApiResponse<Long> joinTeam(@PathVariable("id") Long id, @RequestBody Long teamId){
        teamService.joinTeam(id, teamId);
        return ApiResponse.success(teamId);
    }

    // user가 팀장인 팀 조회
    @GetMapping("/api/teams/leadby/{id}")
    public ApiResponse<List<TeamDto>> getLeaderTeamList(@PathVariable("id") Long id){
        List<TeamDto> teamDtoList = teamService.findTeamByLeader(userService.findOne(id));
        return ApiResponse.success(teamDtoList);
    }

    // user의 대학의 팀 조회
    @GetMapping("/api/teams/univ")
    public ApiResponse<List<TeamDto>> getTeamListByUniv(@RequestBody String univ){
        List<TeamDto> teamDtoList = teamService.findTeamByUniv(univ);
        return ApiResponse.success(teamDtoList);
    }

    // 팀 생성 (+ 팀장으로 등록, 중복 팀 생성 방지 포함)
    @PostMapping("/api/teams")
    public ApiResponse<Long> createTeam(@RequestBody TeamDto teamDto){
        teamService.createTeam(teamDto);
        return ApiResponse.success(teamDto.getTeamId());
    }

    // 특정 팀 정보 조회
    @GetMapping("/api/teams/{id}")
    public ApiResponse<TeamDto> getTeamInfo(@PathVariable("id") Long id){
        TeamDto teamDto = teamService.findOne(id);
        return ApiResponse.success(teamDto);
    }

    // 특정 팀의 팀원 조회
    @GetMapping("/api/teams/{id}/teamMember")
    public ApiResponse<List<UserDto>> getTeamMember(@PathVariable("id") Long id){
        List<UserDto> teamMembers = teamService.findTeamMember(id);
        return ApiResponse.success(teamMembers);
    }

    // 팀 정보 수정 (팀장만 가능)
    @PostMapping("/api/teams/{id}")
    public ApiResponse<Long> updateTeam(@PathVariable("id") Long id, UpdateTeamRequestDto updateTeamRequestDto){
        teamService.update(id, updateTeamRequestDto.getTeamDto(), updateTeamRequestDto.getUserId());
        return ApiResponse.success(id);
    }

    // 팀 삭제 (팀장만 가능)
    @DeleteMapping("/api/teams/{id}")
    public ApiResponse<Long> deleteTeam(@PathVariable("id") Long id, @RequestBody Long userId){
        teamService.deleteTeam(id, userId);
        return ApiResponse.success(id);
    }

    // 특정 팀의 팀장 변경 (팀장만 가능)
    @PostMapping("/api/teams/{id}/leader")
    public ApiResponse<Long> changeLeader(@PathVariable("id") Long id, @RequestBody UpdateTeamLeaderRequestDto updateTeamLeaderRequestDto){
        teamService.setTeamLeader(updateTeamLeaderRequestDto.getLeaderId(), updateTeamLeaderRequestDto.getNextId(), id);
        return ApiResponse.success(id);
    }

    // 특정 팀의 구인 요구사항 추가 (팀장만 가능)
    @PostMapping("/api/teams/{id}/recruit")
    public ApiResponse<Long> addRequire(@PathVariable("id") Long id, @RequestBody RequirementDto requirementDto){
        teamService.addRequirement(id, requirementDto);
        return ApiResponse.success(id);
    }

    // 특정 팀의 구인 요구사항 수정 (팀장만 가능)
    @PostMapping("/api/teams/{id}/recruit/{recruitId}")
    public ApiResponse<Long> updateRequire(@PathVariable("id") Long id, @PathVariable("recruitId") Long recruitId, @RequestBody RequirementDto requirementDto){
        teamService.updateRequirement(id, recruitId, requirementDto);
        return ApiResponse.success(id);
    }

    // 특정 팀의 구인 요구사항 제거 (팀장만 가능)
    @DeleteMapping("/api/teams/{id}/recruit/{recruitId}")
    public ApiResponse<Long> deleteRequire(@PathVariable("id") Long id, @PathVariable("recruitId") Long recruitId, @RequestBody Long userId){
        teamService.deleteRequirement(id, recruitId, userId);
        return ApiResponse.success(id);
    }*/

}
