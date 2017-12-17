package com.serverseri.controllers;

import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.serverseri.model.User;
import com.serverseri.model.VerificationToken;
import com.serverseri.repository.VerificationTokenRepository;
import com.serverseri.service.SecurityService;
import com.serverseri.service.UserService;
import com.serverseri.service.mail.MailService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PublicController {

  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private ServletContext context;

  @Autowired
  private MailService mailService;

  @Autowired
  private VerificationTokenRepository verificationTokenRepository;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Model model) {
    log.debug("Real Path:" + context.getRealPath(""));
    log.debug(""+securityService.isUserLoggedIn());
    return "home";
  }

  @RequestMapping(value = "/about", method = RequestMethod.GET)
  public String about(Model model) {
    log.debug("About");
    return "about";
  }

  @RequestMapping(value = "/contact", method = RequestMethod.GET)
  public String contact(Model model) {
    log.debug("Contact Us");
    return "contact_us";
  }

  @RequestMapping(value ="/login", method = RequestMethod.GET)
  public String customLogin(Model model, String error, String logout) {

    log.debug("User Logged In:" + securityService.isUserLoggedIn());
    if(securityService.isUserLoggedIn()) {
      return "redirect:/dashboard";
    }

    if (error != null) {
      model.addAttribute("error", "Your username and password is invalid.");
      log.debug("Errors ....");
    }

    if (logout != null) {
      model.addAttribute("message", "You have been logged out successfully.");
      log.debug("Logout ....");
    }
    return "login";
  }

  @RequestMapping(value ="/signup", method = RequestMethod.GET)
  public String signup(Model model) {
    if(securityService.isUserLoggedIn()) {
      return "redirect:/dashboard";
    }
    model.addAttribute("hasError",true);
    return "signup";
  }

  @RequestMapping(value ="/signup", method = RequestMethod.POST)
  public String signup(@ModelAttribute User userForm, Model model,WebRequest request){
    String password = userForm.getPassword();
    userService.save(userForm);
    log.debug("Web Request context path: " + request.getContextPath());
    String token = UUID.randomUUID().toString();
    String body = request.getContextPath() + "/confrimatiion?token="+token;
    VerificationToken verificationToken = new VerificationToken();
    verificationToken.setTokenCode(token);
    verificationToken.setUser(userForm);
    verificationTokenRepository.saveAndFlush(verificationToken);
    mailService.sendMail("yoserverseri@gmail.com", userForm.getEmail(),"Please Confrim your registraion in 7 days.", body);
    log.info("Verifiaction mail sent for user: "+ userForm.getFullName());
    securityService.autologin(userForm.getEmail(), password);
    return "redirect:/dashboard";
  }
}
