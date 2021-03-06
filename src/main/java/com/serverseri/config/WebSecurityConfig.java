package com.serverseri.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.serverseri.core.constants.Constants;
import com.serverseri.security.AfterLoggedInService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private AfterLoggedInService loginSuccessHandler;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    .authorizeRequests() //Manage what pages allow to whom
    .antMatchers(Constants.RESOURCES, Constants.REGISTRATION,Constants.FORWARD_SLASH,Constants.DEV,Constants.TEST,"/about","/contact","/signup",Constants.FORGOT_PASSWORD,
        Constants.RESET_PASSWD, Constants.AJAX_SEND_PASSWD_RESET_LINK, Constants.AJAX_RESET_PASSWD).permitAll()
    .antMatchers(Constants.ADMIN_URL).hasRole(Constants.ADMIN)
    .antMatchers("/dashboard/**").hasRole(Constants.USER)
    .anyRequest().authenticated()
    .and()
    .formLogin() //User Login handler
    .loginPage("/login")
    .permitAll().successHandler(loginSuccessHandler) //Sucessful login handler.
    .and()
    .logout()
    .permitAll();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /*public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }*/
}