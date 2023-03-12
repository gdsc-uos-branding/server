package com.gdscuos.recruit.global.config;

import com.gdscuos.recruit.global.util.LoginCheckInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "login")
public class WebConfig implements WebMvcConfigurer {

    private List<String> whiteListURLs;

    public WebConfig(List<String> whiteListURLs) {
        this.whiteListURLs = whiteListURLs;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()) // 인터셉터 등록
                .order(1) // 낮을 수록 먼저 호출
                .excludePathPatterns(whiteListURLs.toArray(new String[0])); // 인터셉터를 적용 안할 url 패턴
    }

    public void setWhiteListURLs(List<String> whiteListURLs) {
        this.whiteListURLs = whiteListURLs;
    }
}