package com.serverseri.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//Configuration for the caseinsensitive mapping of the request mapping urls
@Configuration
public class UrlCaseInsensitiveConfig extends WebMvcConfigurerAdapter {
  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    AntPathMatcher matcher = new AntPathMatcher();
    matcher.setCaseSensitive(false);
    configurer.setPathMatcher(matcher);
  }
}