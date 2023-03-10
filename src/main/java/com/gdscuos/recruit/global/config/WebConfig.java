package com.gdscuos.recruit.global.config;

import com.gdscuos.recruit.global.util.ApplicationProperties;
import com.gdscuos.recruit.global.util.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationProperties applicationProperties;

    public WebConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()) // 인터셉터 등록
                .order(1) // 낮을 수록 먼저 호출
                .excludePathPatterns(applicationProperties.getWhiteListURLs().toArray(new String[0])); // 인터셉터를 적용 안할 url 패턴
    }
}