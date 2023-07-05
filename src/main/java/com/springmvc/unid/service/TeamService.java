package com.springmvc.unid.service;

import com.springmvc.unid.domain.Team;
import com.springmvc.unid.domain.User;
import com.springmvc.unid.dto.TeamDto;
import com.springmvc.unid.repository.TeamRepository;
import com.springmvc.unid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    // 1. 팀 조회
    public TeamDto findById(TeamDto teamDto) {
        Team team = teamRepository.findById(teamDto.getName()).orElseThrow(
                () -> new IllegalArgumentException("해당 팀이 없습니다.")
        );
        return new TeamDto(team);
    }

    // 2. 팀 생성 -> save(Team team)이 내재되어 있음
    public TeamDto save(TeamDto teamDto) {
        Team team = DtoToEntity(teamDto);
        return new TeamDto(teamRepository.save(team));
    }

    // 3. 팀 삭제
    void deleteById(TeamDto teamDto) {
        teamRepository.deleteById(teamDto.getName());
    }

    // 4. 팀 수정 -> save(Team team)이 역할을 겸함
    public TeamDto update(TeamDto teamDto) {
        Team team = DtoToEntity(teamDto);
        return new TeamDto(teamRepository.save(team));
    }

    // 5. 특정 대학 소속 팀 조회
    public List<TeamDto> findTeamByUniversity(String university) {
        List<Team> teams = teamRepository.findTeamByUniversity(university);
        return teams.stream().map(TeamDto::new).collect(Collectors.toList());
    }

    private Team DtoToEntity(TeamDto teamDto) {
        User user = userRepository.findById(teamDto.getLeader()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        return new Team(teamDto.getName(), user, teamDto.getOneLine(), teamDto.getDescription(), teamDto.getUniversity(), teamDto.getLink(), teamDto.getRequirements());
    }
}
