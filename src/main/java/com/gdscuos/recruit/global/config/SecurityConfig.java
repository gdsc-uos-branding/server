package com.gdscuos.recruit.global.config;

import com.gdscuos.recruit.global.auth.service.AuthService;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthService authService;

    public SecurityConfig(AuthService authService) {
        this.authService = authService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll() // 모든 사용자가 접근 가능하게 허용, 로그아웃 후 새로고침 시 바로 소셜 로그인이 가능한 화면이 뜨는 것을 막기 위함
                .anyRequest().authenticated()
                .and()
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessHandler(
                                (request, response, authentication) -> response.setStatus(
                                        HttpServletResponse.SC_OK))
                        .logoutUrl("/api/logout") // logout url 명시
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .oauth2Login()
                .defaultSuccessUrl("/api/home")
                .userInfoEndpoint() //userInfoEndpoint()은 로그인 성공 후 사용자 정보를 가져올 때의 설정을 담당
                .userService(
                        authService); // userService()은 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
        return http.build();
    }

    // cors 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000/**");
        configuration.addAllowedOrigin("http://localhost:5173/**");
        configuration.addAllowedOrigin("https://admin-gdsc-uos.vercel.app/**");
        configuration.addAllowedOrigin("https://recruit-gdsc-uos.vercel.app/**");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
