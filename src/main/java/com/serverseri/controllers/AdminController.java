package com.serverseri.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

  private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

  @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
  public String adminDashboard(Model model) {
    logger.debug("Admin Dashboard");
    return "admin_dashboard";
  }

  @RequestMapping(value = {"/settings"}, method = RequestMethod.GET)
  public String adminSetting(Model model) {
    logger.debug("Admin Dashboard");
    return "admin_user_management";
  }
}
