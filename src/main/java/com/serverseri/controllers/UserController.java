
package com.serverseri.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.serverseri.model.VerificationToken;
import com.serverseri.repository.VerificationTokenRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {

  @Autowired
  VerificationTokenRepository verifiactionTokenRepository;

  @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
  public String welcome(Model model) {
    return "welcome";
  }

  @RequestMapping(value = "/confrimatiion", method = RequestMethod.GET)
  public String mailVerification(@RequestParam("token")String token) {
    if(token == null) {
      log.info("Invalid Token");
    }
    else {
      log.info("Token Code: "+ token);
      VerificationToken vToken = verifiactionTokenRepository.findByTokenCode(token);
      log.info("Token is for user:"+ vToken.getUser().getUserId());
    }
    return "new_user_verification";
  }
}