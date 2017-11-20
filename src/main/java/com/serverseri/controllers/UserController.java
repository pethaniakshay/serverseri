package com.serverseri.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.serverseri.repository.RoleRepository;
import com.serverseri.service.SecurityService;
import com.serverseri.service.UserService;
import com.serverseri.validator.UserValidator;

@Controller
public class UserController {
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private UserValidator userValidator;

  @RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
  public String welcome(Model model) {
    logger.debug("Welcome");
    return "welcome";
  }

  @RequestMapping(value = "/dashboard" , method = RequestMethod.GET)
  public String dashboard(Model model) {
    logger.debug("User  Dashboard");
    return "dashboard";
  }
}