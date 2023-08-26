package com.springmvc.unid.service;

import com.springmvc.unid.controller.dto.response.ResponseTeamDto;
import com.springmvc.unid.controller.dto.response.ResponseUserDto;
import com.springmvc.unid.controller.dto.request.CreateUserDto;
import com.springmvc.unid.domain.*;
import com.springmvc.unid.util.exception.CustomException;
import com.springmvc.unid.util.exception.ResponseCode;
import com.springmvc.unid.repository.TeamMemberRepository;
import com.springmvc.unid.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TeamMemberRepository teamMemberRepository;

    // 로그인
    @Transactional(readOnly = true)
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
    public Long join(CreateUserDto userDto) {
        User user = User.createUser(userDto.getLoginId(), userDto.getPw(), userDto.getName(), userDto.getUniversity(), userDto.getMajor(), userDto.getLink());
        validateDuplicateUser(user.getName(), user.getLoginId());
        return userRepository.save(user).getId();
    }

    // 중복 user 검증
    private void validateDuplicateUser(String name, String loginId) {
        if (userRepository.existsByName(name)) {
            throw new CustomException(ResponseCode.DUPLICATED_USER);
        }
        if (userRepository.existsByLoginId(loginId)) {
            throw new CustomException(ResponseCode.DUPLICATED_USER);
        }
    }

    // user 정보 수정
    @Transactional
    public void update(Long id, String newName, String newUniv, String newMajor, String newLink) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
        user.update(newName, newUniv, newMajor, newLink);
        userRepository.save(user);
    }

    // user 탈퇴
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    // 전체 user 조회
    @Transactional(readOnly = true)
    public List<ResponseUserDto> findUsers() {
        List<User> users = userRepository.findAll();
        return makeUserDtoList(users);
    }

    // 특정 user 조회
    @Transactional(readOnly = true)
    public ResponseUserDto findOne(Long userId) {
        return new ResponseUserDto(userRepository.findById(userId).orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND)));
    }

    // 특정 user가 소속된 팀 조회
    @Transactional(readOnly = true)
    public List<ResponseTeamDto> findTeamsByUserId(Long userId) {
        List<TeamMember> teams = teamMemberRepository.findByUserId(userId);
        return makeTeamDtoList(teams);
    }

    private List<ResponseUserDto> makeUserDtoList(List<User> users) {
        return users.stream()
                .map(ResponseUserDto::new)
                .collect(Collectors.toList());
    }

    private List<ResponseTeamDto> makeTeamDtoList(List<TeamMember> teams) {
        return teams.stream()
                .map(ResponseTeamDto::new)
                .collect(Collectors.toList());
    }
}
