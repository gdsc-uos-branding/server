package com.gdscuos.recruit.global.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheckInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        HttpSession session = request.getSession(false); // 세션 가져옴

        try {
            if (session == null) { // 세션이 비어있다면, 401 에러를 뱉는다

                log.error("handleUnAuthorizedException");
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인 후, 사용해주세요.");
                return false;
            }
        } catch (IOException e) { // response.sendError()에서 error 발생하는 경우
            log.error("IOException", e);
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버에서 오류가 발생했습니다.");
            return false;
        }

        return true;
    }
}