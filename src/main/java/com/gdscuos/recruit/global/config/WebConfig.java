package com.gdscuos.recruit.global.config;

import com.gdscuos.recruit.global.util.LoginCheckInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "login")
public class WebConfig implements WebMvcConfigurer {

    private List<String> whiteListURLs = new ArrayList<>();

    public void setWhiteListURLs(List<String> whiteListURLs) {
        this.whiteListURLs = whiteListURLs;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns(whiteListURLs.toArray(new String[0]))
                .order(1);
    }
}