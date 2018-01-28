package com.serverseri.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class CommonValidator {

  public static final String EMAIL_ADDRESS_REG_EX = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

  public static boolean emailAddressValidator(String mailId) {
    if(!Pattern.matches(EMAIL_ADDRESS_REG_EX, mailId)) {
      return false;
    }
    return true;
  }
}
