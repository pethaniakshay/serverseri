package com.serverseri.validator;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.serverseri.model.User;
import com.serverseri.service.UserService;

@Component
public class SignUpFormValidator {

  private static final Logger logger = LoggerFactory.getLogger(SignUpFormValidator.class);

  private UserService userService;

  public String validate(User userForm) {

    String validity = "error";

    String emailRegex = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
    String userNameRegex = "^[a-zA-Z0-9._-]{3,}$";

    if(userForm.getEmail() !=null && !userForm.getEmail().isEmpty()) {

      if(!Pattern.matches(emailRegex, userForm.getEmail())) {
        return "Invalid Email Address.";
      }

      User ifExist = userService.findByEmail(userForm.getEmail());

      if(ifExist != null) {
        return "Email Address Already Exist.";
      }
    }
    else {
      return "Please Enter the Email Address.";
    }

    if(userForm.getEmail() != null && !userForm.getEmail().isEmpty()) {
      //Here we treat username as the Full name so no need to validate it fot now.
      /*if(userForm.getUsername().length() < 4) {
        return "Username Must "
      }*/
    }
    else {
      return "Please Enter the Username.";
    }

    /*if() {

    }*/



    return validity;
  }

}
