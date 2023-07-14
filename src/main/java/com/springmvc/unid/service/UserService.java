package com.springmvc.unid.service;

import com.springmvc.unid.controller.dto.TeamDto;
import com.springmvc.unid.controller.dto.UserDto;
import com.springmvc.unid.controller.dto.request.RequestCreateUserDto;
import com.springmvc.unid.domain.*;
import com.springmvc.unid.util.exception.CustomException;
import com.springmvc.unid.util.exception.ResponseCode;
import com.springmvc.unid.repository.TeamMemberRepository;
import com.springmvc.unid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TeamMemberRepository teamMemberRepository;

    // 로그인
    public Long login(String id, String password) {
        Optional<User> findUsers = userRepository.findByLoginIdAndPw(id, password);
        if (findUsers.isPresent()) {
            if (findUsers.get().getPw().equals(password)) {
                return findUsers.get().getId();
            } else {
                throw new CustomException(ResponseCode.USER_LOGIN_FAILED);
            }
        } else {
            throw new CustomException(ResponseCode.USER_NOT_FOUND);
        }
    }

    // user 가입
    @Transactional
    public Long join(RequestCreateUserDto userDto) {
        User user = User.createUser(userDto);
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    // 중복 user 검증
    private void validateDuplicateUser(User user) {
        Optional<User> findUserByLoginId = userRepository.findByLoginId(user.getLoginId());
        Optional<User> findUserByName = userRepository.findByName(user.getName());
        if (findUserByName.isPresent() || findUserByLoginId.isPresent()) {
            throw new CustomException(ResponseCode.DUPLICATED_USER);
        }
    }

    // user 탈퇴
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // user 정보 수정
    @Transactional
    public void update(Long id, String newName, String newUniv, String newMajor, String newLink) {
        User findUser = userRepository.findById(id).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        findUser.update(newName, newUniv, newMajor, newLink);
        userRepository.save(findUser);
    }

    // 전체 user 조회
    public List<UserDto> findUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(new UserDto(user));
        }
        return userDtos;
    }

    // 특정 user 조회
    public UserDto findOne(Long userId) {
        return new UserDto(userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND)));
    }

    // 특정 user가 소속된 팀 조회
    public List<TeamDto> findTeamsByUserId(Long userId) {
        List<TeamMember> teams = teamMemberRepository.findByUser(userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND)));
        List<TeamDto> teamDtos = new ArrayList<>();
        for (TeamMember team : teams) {
            teamDtos.add(new TeamDto(team.getTeam()));
        }
        return teamDtos;
    }

}
