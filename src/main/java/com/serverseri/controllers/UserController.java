
package com.serverseri.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dashboard")
public class UserController {
  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);


  @RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)

  public String welcome(Model model) {
    logger.debug("Welcome");
    return "welcome";
  }

  @RequestMapping(value = {"/",""} , method = RequestMethod.GET)
  public String dashboard(Model model) {
    logger.debug("User  Dashboard");
    return "dashboard";
  }
}