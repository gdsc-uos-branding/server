package com.gdscuos.recruit.global.auth.api;

import com.gdscuos.recruit.global.auth.dto.UpdateIntroAboutDTO;
import com.gdscuos.recruit.global.auth.dto.UpdateIntroActivityDTO;
import com.gdscuos.recruit.global.auth.dto.UpdateIntroTargetDTO;
import com.gdscuos.recruit.global.auth.dto.UserDTO;
import com.gdscuos.recruit.global.auth.service.AdminService;
import com.gdscuos.recruit.global.common.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    /*
        리드 멤버만 사용할 수 있는 기능입니다.
     */

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 유저 리스트 반환
    @GetMapping("/userList")
    public ResponseEntity<List<UserDTO>> showUserList(HttpServletRequest request) {

        return ResponseEntity.ok().body(adminService.getUserList(request));
    }

    // 유저의 권한 변경 (지원자|멤버|코어|리드)
    @PatchMapping("/role/{userId}")
    public ResponseEntity<Void> changeUserRole(HttpServletRequest request, @PathVariable Long userId, Role role) {

        adminService.changeUserRole(request, userId, role);
        return ResponseEntity.ok().build();
    }

    // 회원탈퇴
    @DeleteMapping("/withdrawal/{userId}")
    public ResponseEntity<Void> deleteUser(HttpServletRequest request, @PathVariable Long userId) {

        adminService.deleteUser(request, userId);
        return ResponseEntity.ok().build();
    }

    // 특정 팀의 소개 변경
    @PatchMapping("/introduction/about")
    public ResponseEntity<Void> changeTeamAbout(HttpServletRequest request, UpdateIntroAboutDTO updateIntroAboutDTO) {

        adminService.changeTeamAbout(request, updateIntroAboutDTO);
        return ResponseEntity.ok().build();
    }

    // 특정 팀의 활동 내용 변경
    @PatchMapping("/introduction/activity")
    public ResponseEntity<Void> changeTeamActivity(HttpServletRequest request, UpdateIntroActivityDTO updateIntroActivityDTO) {

        adminService.changeTeamActivity(request, updateIntroActivityDTO);
        return ResponseEntity.ok().build();
    }

    // 특정 팀의 인재상 변경
    @PatchMapping("/introduction/target")
    public ResponseEntity<Void> changeTeamTarget(HttpServletRequest request, UpdateIntroTargetDTO updateIntroTargetDTO) {

        adminService.changeTeamTarget(request, updateIntroTargetDTO);
        return ResponseEntity.ok().build();
    }
}