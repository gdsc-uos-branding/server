package com.gdscuos.recruit.global.config;

import com.gdscuos.recruit.global.auth.service.AuthService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "security")
public class SecurityConfig {

    private final AuthService authService;

    private List<String> whiteListURLs;

    public SecurityConfig(AuthService authService, List<String> whiteListURLs) {
        this.authService = authService;
        this.whiteListURLs = whiteListURLs;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(whiteListURLs.toArray(new String[0])).permitAll() // 모든 사용자가 접근 가능하게 허용, 로그아웃 후 새로고침 시 바로 소셜 로그인이 가능한 화면이 뜨는 것을 막기 위함
                .anyRequest().authenticated()
                .and()
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
                        .logoutUrl("/api/logout") // logout url 명시
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .oauth2Login()
                .defaultSuccessUrl("/api/home")
                .userInfoEndpoint() //userInfoEndpoint()은 로그인 성공 후 사용자 정보를 가져올 때의 설정을 담당
                .userService(authService); // userService()은 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
        return http.build();
    }

    public void setWhiteListURLs(List<String> whiteListURLs) {
        this.whiteListURLs = whiteListURLs;
    }
}