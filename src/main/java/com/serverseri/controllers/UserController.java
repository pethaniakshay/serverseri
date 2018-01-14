
package com.serverseri.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {

  @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
  public String welcome(Model model) {
    return "welcome";
  }
}