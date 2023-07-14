package com.springmvc.unid.controller;

import com.springmvc.unid.controller.dto.RequirementDto;
import com.springmvc.unid.controller.dto.TeamDto;
import com.springmvc.unid.controller.dto.UserDto;
import com.springmvc.unid.controller.dto.request.*;
import com.springmvc.unid.service.TeamService;
import com.springmvc.unid.service.UserService;
import com.springmvc.unid.util.api.ApiResponse;
import com.springmvc.unid.util.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final UserService userService;

    // 팀 생성 (+ 팀장으로 등록, 중복 팀 생성 방지 포함)
    @PostMapping("/api/teams")
    public ApiResponse<Long> createTeam(@RequestBody RequestCreateTeamDto teamDto){
        Long id = teamService.createTeam(teamDto);
        return ApiResponse.success(id, ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // 전체 팀 조회
    @GetMapping("/api/teams")
    public ApiResponse<List<TeamDto>> getTeamList(){
        List<TeamDto> teamDtoList = teamService.findAll();
        return ApiResponse.success(teamDtoList, ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // 팀 정보 수정 (팀장만 가능)
    @PostMapping("/api/teams/{id}")
    public ApiResponse<Long> updateTeam(@PathVariable("id") Long id, @RequestBody RequestUpdateTeamDto requestUpdateTeamDto){
        Long uid = teamService.update(id, requestUpdateTeamDto.getTeamDto(), requestUpdateTeamDto.getUserId());
        if(uid == null) return ApiResponse.fail(ResponseCode.NOT_TEAM_LEADER); // 팀장 아님
        return ApiResponse.success(uid, ResponseCode.TEAM_UPDATE_SUCCESS.getMessage());
    }

    // 팀 삭제 (팀장만 가능)
    @DeleteMapping("/api/teams/{id}")
    public ApiResponse<Long> deleteTeam(@PathVariable("id") Long id, @RequestBody Long userId){
        teamService.deleteTeam(id, userId);
        return ApiResponse.success(1L, ResponseCode.TEAM_DELETE_SUCCESS.getMessage());
    }

    // 특정 팀 조회
    @GetMapping("/api/teams/{id}")
    public ApiResponse<TeamDto> getTeamInfo(@PathVariable("id") Long id){
        TeamDto teamDto = teamService.findOne(id);
        if(teamDto == null) return ApiResponse.fail(ResponseCode.TEAM_NOT_FOUND);
        return ApiResponse.success(teamDto, ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // 특정 팀의 팀장 조회
    @GetMapping("/api/teams/{id}/leader")
    public ApiResponse<UserDto> getTeamLeader(@PathVariable("id") Long id){
        UserDto teamLeader = teamService.findTeamLeader(id);
        return ApiResponse.success(teamLeader, ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // 특정 팀의 팀원 조회
    @GetMapping("/api/teams/{id}/members")
    public ApiResponse<List<UserDto>> getTeamMember(@PathVariable("id") Long id){
        List<UserDto> teamMembers = teamService.findTeamMember(id);
        return ApiResponse.success(teamMembers, ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // user의 대학의 팀 조회
    @GetMapping("/api/teams/univ")
    public ApiResponse<List<TeamDto>> getTeamListByUniv(@RequestBody RequestUnivMemberDto universityDto){
        List<TeamDto> teamDtoList = teamService.findTeamByUniv(universityDto.getUniversity());
        return ApiResponse.success(teamDtoList, ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // user가 팀장인 팀 조회
    @GetMapping("/api/users/{id}/teams/leader")
    public ApiResponse<List<TeamDto>> getLeaderTeamList(@PathVariable("id") Long id){
        List<TeamDto> teamDtoList = teamService.findTeamByLeader(userService.findOne(id));
        return ApiResponse.success(teamDtoList, ResponseCode.TEAM_READ_SUCCESS.getMessage());
    }

    // 특정 팀의 팀장 변경 (팀장만 가능)
    @PostMapping("/api/teams/{id}/leader")
    public ApiResponse<Long> changeLeader(@PathVariable("id") Long id, @RequestBody RequestUpdateTeamLeaderDto requestUpdateTeamLeaderDto){
        teamService.setTeamLeader(requestUpdateTeamLeaderDto.getLeaderId(), requestUpdateTeamLeaderDto.getNextId(), id);
        return ApiResponse.success(id, ResponseCode.TEAM_UPDATE_SUCCESS.getMessage());
    }

    // 특정 팀의 구인 요구사항 추가 (팀장만 가능)
    @PostMapping("/api/teams/{id}/require/add")
    public ApiResponse<Long> addRequire(@PathVariable("id") Long id, @RequestBody RequirementDto requirementDto){
        teamService.addRequirement(id, requirementDto);
        return ApiResponse.success(id, ResponseCode.REQUIREMENT_UPDATE_SUCCESS.getMessage());
    }

    // 특정 팀의 구인 요구사항 수정 (팀장만 가능)
    @PostMapping("/api/teams/{id}/require/update")
    public ApiResponse<Long> updateRequire(@PathVariable("id") Long id, @RequestBody RequirementDto requirementDto){
        teamService.updateRequirement(id, requirementDto.getRequirementId(), requirementDto);
        return ApiResponse.success(id, ResponseCode.REQUIREMENT_UPDATE_SUCCESS.getMessage());
    }

    // 특정 팀의 구인 요구사항 제거 (팀장만 가능)
    @PostMapping("/api/teams/{id}/require/delete")
    public ApiResponse<Long> deleteRequire(@PathVariable("id") Long id, @RequestBody RequestDeleteRequirementDto requestDeleteRequirementDto){
        teamService.deleteRequirement(id, requestDeleteRequirementDto.getRequirementId(), requestDeleteRequirementDto.getUserId());
        return ApiResponse.success(id, ResponseCode.REQUIREMENT_DELETE_SUCCESS.getMessage());
    }

}
