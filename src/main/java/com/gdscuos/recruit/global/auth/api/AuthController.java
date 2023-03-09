package com.gdscuos.recruit.global.auth.api;

import com.gdscuos.recruit.global.auth.dto.UserDTO;
import com.gdscuos.recruit.global.util.SessionConst;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class AuthController {

    /*
        Spring Security에서 소셜 로그인 기능 제공
        url : {base_url}/oauth2/authorization/google
     */

    // 구글 소셜 로그인 성공시 리디렉션
    @GetMapping("/api/home")
    public ResponseEntity<UserDTO> home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) UserDTO userDTO) {

        // 세션을 가져와 회원을 반환합니다.
        return ResponseEntity.ok().body(userDTO);
    }

    // 유저가 로그인되어 있는 지 여부 확인
    @GetMapping("/api/isLogin")
    public ResponseEntity<Boolean> currentUser(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null)
            return ResponseEntity.ok().body(true);
        else
            return ResponseEntity.ok().body(false);
    }

    // 로그아웃
    @PostMapping("/api/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0); // 쿠키의 유효기간을 0으로 설정하여 쿠키를 삭제
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }
}