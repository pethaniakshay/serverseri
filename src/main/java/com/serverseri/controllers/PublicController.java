package com.serverseri.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.serverseri.model.User;
import com.serverseri.service.SecurityService;
import com.serverseri.service.UserService;
import com.serverseri.service.mail.MailService;
import com.serverseri.validator.SignUpFormValidator;

@Controller
public class PublicController {

  private static final Logger logger = LoggerFactory.getLogger(PublicController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private MailService mailService;

  @Autowired
  private SignUpFormValidator signUpFormValidator;

  @Autowired
  private ServletContext context;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Model model) {
    logger.debug("Real Path:" + context.getRealPath(""));
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
    return "login";
  }

  @RequestMapping(value ="/signup", method = RequestMethod.GET)
  public String signup(Model model) {
    logger.debug("Goig to display signup form");
    model.addAttribute("hasError",true);
    return "dev_signup";
  }

  @RequestMapping(value ="/signup", method = RequestMethod.POST)
  public String signup(@ModelAttribute User userForm, HttpServletRequest request, Model model){
    logger.debug("Going to save the signup form");

    /*if(!signUpFormValidator.validate(userForm)){
      model.addAttribute("hasError",true);
      model.addAttribute("error1","Some Error");
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
