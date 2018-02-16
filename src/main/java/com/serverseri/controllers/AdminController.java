package com.serverseri.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.serverseri.core.constants.Constants;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

  private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

  public static final String SETTINGS ="/settings";

  @RequestMapping(value = {Constants.ROOT_URL_FWD_SLASH,Constants.ROOT_URL_BLANK}, method = RequestMethod.GET)
  public String adminDashboard(Model model) {
    logger.debug("Admin Dashboard");
    return "admin_dashboard";
  }

  @GetMapping(SETTINGS)
  public String adminSetting(Model model) {
    logger.debug("Admin Dashboard");
    return "admin_user_management";
  }
}
