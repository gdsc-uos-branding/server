package com.gdscuos.recruit.global.auth.service;

import com.gdscuos.recruit.domain.applicant.exception.IntroductionNotFoundException;
import com.gdscuos.recruit.global.auth.domain.Introduction;
import com.gdscuos.recruit.global.auth.dto.UpdateIntroAboutDTO;
import com.gdscuos.recruit.global.auth.dto.UpdateIntroActivityDTO;
import com.gdscuos.recruit.global.auth.dto.UpdateIntroTargetDTO;
import com.gdscuos.recruit.global.auth.dto.UserDTO;
import com.gdscuos.recruit.global.auth.repository.IntroductionRepository;
import com.gdscuos.recruit.global.auth.repository.UserRepository;
import com.gdscuos.recruit.global.common.Role;
import com.gdscuos.recruit.global.common.User;
import com.gdscuos.recruit.global.error.exception.AccessDeniedException;
import com.gdscuos.recruit.global.error.exception.EntityNotFoundException;
import com.gdscuos.recruit.global.error.exception.ErrorCode;
import com.gdscuos.recruit.global.util.SessionConst;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final IntroductionRepository introductionRepository;

    public AdminService(UserRepository userRepository, IntroductionRepository introductionRepository) {
        this.userRepository = userRepository;
        this.introductionRepository = introductionRepository;
    }

    // 유저 리스트 반환
    public List<UserDTO> getUserList(HttpServletRequest request) {

        isUserRoleLead(getLoginUser(request));
        return userRepository.findAll().stream().map(UserDTO::from).collect(Collectors.toList());
    }

    // 유저의 권한 변경
    public void changeUserRole(HttpServletRequest request, Long userId, Role role) {

        if (isUserRoleLead(getLoginUser(request))) {
            if (isUserExists(userId)) {
                User user = userRepository.findUserById(userId);
                user.setRole(role);

                userRepository.save(user);
            } else {
                throw new EntityNotFoundException("권한이 변경될 유저가 없습니다.");
            }
        }
    }

    // 유저 회원탈퇴
    public void deleteUser(HttpServletRequest request, Long userId) {

        if (isUserRoleLead(getLoginUser(request))) {
            if (isUserExists(userId)) {
                User user = userRepository.findUserById(userId);

                userRepository.delete(user);
            } else {
                throw new EntityNotFoundException("회원탈퇴할 유저 정보가 없습니다.");
            }
        }
    }

    // 특정 팀의 소개 수정
    public void changeTeamAbout(HttpServletRequest request, UpdateIntroAboutDTO updateIntroAboutDTO) {
        if (isUserRoleLead(getLoginUser(request))) {
            Introduction intro = introductionRepository.findIntroductionByTeam(updateIntroAboutDTO.getTeam());
            if (intro != null) {
                intro.setAbout(updateIntroAboutDTO.getAbout());
            } else {
                throw new IntroductionNotFoundException();
            }
        }
    }

    //  특정 팀의 활동 내용 수정
    public void changeTeamActivity(HttpServletRequest request, UpdateIntroActivityDTO updateIntroActivityDTO) {
        if (isUserRoleLead(getLoginUser(request))) {
            Introduction intro = introductionRepository.findIntroductionByTeam(updateIntroActivityDTO.getTeam());
            if (intro != null) {
                intro.setAbout(updateIntroActivityDTO.getActivity());
            } else {
                throw new IntroductionNotFoundException();
            }
        }
    }

    // 특정 팀의 인재상 수정
    public void changeTeamTarget(HttpServletRequest request, UpdateIntroTargetDTO updateIntroTargetDTO) {
        if (isUserRoleLead(getLoginUser(request))) {
            Introduction intro = introductionRepository.findIntroductionByTeam(updateIntroTargetDTO.getTeam());
            if (intro != null) {
                intro.setAbout(updateIntroTargetDTO.getTarget());
            } else {
                throw new IntroductionNotFoundException();
            }
        }
    }

    // db에 유저가 존재하는 지 확인
    private boolean isUserExists(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent();
    }

    // 유저의 권한이 Lead 인지 확인
    private boolean isUserRoleLead(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) UserDTO loginMember) {

        if (loginMember.getRole() != Role.LEAD) {
            throw new AccessDeniedException("권한이 필요한 기능입니다.", ErrorCode.USER_ACCESS_DENIED);
        }

        return true;
    }

    // HttpServletRequest 를 통해 세션에 저장된 userDTO 객체 반환
    private UserDTO getLoginUser(HttpServletRequest request) {

        return (UserDTO) request.getSession(false).getAttribute(SessionConst.LOGIN_MEMBER);
    }
}