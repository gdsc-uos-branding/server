package com.gdscuos.recruit.global.auth.service;

import com.gdscuos.recruit.global.auth.dto.UserDTO;
import com.gdscuos.recruit.global.auth.repository.UserRepository;
import com.gdscuos.recruit.global.common.Team;
import com.gdscuos.recruit.global.common.User;
import com.gdscuos.recruit.global.util.OAuthAttributes;
import com.gdscuos.recruit.global.util.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    // 구글 계정 정보 가져오는 과정
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oauth2User = delegate.loadUser(userRequest);

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(userNameAttributeName, oauth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        // 신규 세션 생성
        HttpSession session = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getSession();

        // 세션에 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, new UserDTO(user));
        log.info("세션에 저장될 회원 정보 : " + new UserDTO(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRole().getValue())), attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    // 로그인 진행 시, db 저장 혹은 갱신
    private User saveOrUpdate(OAuthAttributes attributes) {
        Optional<User> optionalUser = userRepository.findUserByEmail(attributes.getEmail());
        User user;

        if (optionalUser.isPresent()) { // 유저가 이미 존재하는 경우
            User entity = optionalUser.get();
            user = entity.update(attributes.getName(), attributes.getEmail());
        } else { // 유저가 존재하지 않는 경우
            user = attributes.toEntity();
        }

        user.setTeam(Team.COMMON); // 회원가입 시, 팀 초기값

        userRepository.save(user);

        return user;
    }
}