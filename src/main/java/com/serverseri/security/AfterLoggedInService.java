package com.serverseri.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.serverseri.core.constants.Constants;

@Component
public class AfterLoggedInService implements AuthenticationSuccessHandler {

  private static final Logger logger = LoggerFactory.getLogger(AfterLoggedInService.class);

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    String loggedInUserRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().toString();

    logger.debug("Logged In User role: " + loggedInUserRole);

    if (loggedInUserRole.equals(Constants.ROLE_ADMIN)) {
      response.sendRedirect(request.getContextPath() + "/admin");
    }

    else if (loggedInUserRole.equals(Constants.ROLE_USER)) {
      response.sendRedirect(request.getContextPath() + "/dashboard");
    } else {
      response.sendRedirect(request.getContextPath() + "/");
    }
  }
}