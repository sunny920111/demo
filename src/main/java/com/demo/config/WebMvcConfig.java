package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  private static final long MAX_AGE_SECS = 3000;

  @Override
  public void addCorsMappings(CorsRegistry corsRegistry) {
    corsRegistry
        .addMapping("/**")
        .allowedOriginPatterns("*")
        .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
        .allowedHeaders("*")
        .exposedHeaders("Content-Disposition", "Update-Auth-Token")
        .allowCredentials(true)
        .maxAge(MAX_AGE_SECS);
  }
}
