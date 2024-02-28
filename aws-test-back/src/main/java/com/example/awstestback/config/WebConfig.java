package com.example.awstestback.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", HttpMethod.OPTIONS.name())
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true);
    }
}
