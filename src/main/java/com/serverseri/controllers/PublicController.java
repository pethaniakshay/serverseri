package com.serverseri.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.serverseri.core.constants.Constants;
import com.serverseri.model.User;
import com.serverseri.service.SecurityService;
import com.serverseri.service.UserService;
import com.serverseri.service.mail.MailService;
import com.serverseri.validator.SignUpFormValidator;
import com.serverseri.validator.UserValidator;

@Controller
public class PublicController {

  private static final Logger logger = LoggerFactory.getLogger(PublicController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private UserValidator userValidator;

  @Autowired
  private MailService mailService;

  @Autowired
  private SignUpFormValidator signUpFormValidator;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Model model) {
    return "home";
  }

  @RequestMapping(value = "/about", method = RequestMethod.GET)
  public String about(Model model) {
    logger.debug("About");
    
    
    return "about";
  }

  @RequestMapping(value = "/contact", method = RequestMethod.GET)
  public String contact(Model model) {
    logger.debug("Contact Us");
    return "contact_us";
  }

  //@RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Model model, String error, String logout) {
    if (error != null)
      model.addAttribute("error", "Your username and password is invalid.");

    if (logout != null)
      model.addAttribute("message", "You have been logged out successfully.");

    return "login";
  }

  @RequestMapping(value ="/login", method = RequestMethod.GET)
  public String customLogin(Model model, String error, String logout) {

    if (error != null) {
      model.addAttribute("error", "Your username and password is invalid.");
      logger.debug("Errors ....................");

    }

    if (logout != null) {
      model.addAttribute("message", "You have been logged out successfully.");
      logger.debug("Logout .....................");
    }


    logger.debug("************In Custom login page************");

    return "login";
  }


  @RequestMapping(value = "/registration", method = RequestMethod.GET)
  public String registration(Model model) {
    model.addAttribute("userForm", new User());

    return "registration";
  }

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
    userValidator.validate(userForm, bindingResult);
    if (bindingResult.hasErrors()) {
      return "registration";
    }
    userService.save(userForm);
    securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
    String loggedInUserRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().toString();

    if (loggedInUserRole.equals(Constants.ROLE_ADMIN)) {
      return "redirect:/admin";
    } else if (loggedInUserRole.equals(Constants.ROLE_USER)) {
      return "redirect:/dashboard";

    } else {
      return "redirect:/";
    }
  }

  @RequestMapping(value ="/signup", method = RequestMethod.GET)
  public String signup(Model model) {
    logger.debug("Goig to display signup form");
    return "dev_signup";
  }

  @RequestMapping(value ="/signup", method = RequestMethod.POST)
  public String signup(@ModelAttribute User userForm, HttpServletRequest request){
    logger.debug("Going to save the signup form");

    /*if(!signUpFormValidator.validate(userForm)){
      return "dev_signup";
    }*/

    userService.save(userForm);
    securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
    logger.debug("Redirecting to the Dashbaord");

    return "redirect:/dashboard";
  }

  @RequestMapping(value = "/mail" , method = RequestMethod.GET)
  public String mailTester(Model model) {
    mailService.sendMail("yoserverseri@gmail.com","patelaksh412@gmail.com","Test Mail","Hey this is the Asynchronous mail service of the serverseri.");
    return "sample_mail_sender";
  }
}
