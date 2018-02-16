package com.serverseri.core.constants;

public class Constants {
  public static final String ROLE_ADMIN = "ROLE_ADMIN";
  public static final String ROLE_USER = "ROLE_USER";
  public static final long ROLE_ADMIN_ID = 2;
  public static final long ROLE_USER_ID = 1;

  public static final String STATUS_SUCCESS = "success";
  public static final String STATUS_ERROR = "error";
  public static final String STATUS_INVALID = "invalid";

  public static final String STATUS = "status";
  public static final String MESSAGE = "message";
  public static final String HAS_ERROR = "hasError";
  public static final String ERROR = "error";

  public static final String USER = "USER";
  public static final String ADMIN = "ADMIN";

  public static final int USER_ACCOUNT_STATUS_CREATED_ID = 1;
  public static final String USER_ACCOUNT_STATUS_CREATED = "Created";

  public static final int USER_ACCOUNT_STATUS_VERIFIED_ID = 2;
  public static final String USER_ACCOUNT_STATUS_VERIFIED = "Verified";

  public static final String FORWARD_SLASH = "/";
  public static final String TOKEN_VALUE_SPLITTER = "_<->_";

  public static final String APP_INTERNAL_DATE_TIME_FORMAT = "yyyy-MM-dd-HH-mm";


  //URL Static resources
  public static final String RESOURCES = "/resources/**";

  //URL Public
  public static final String FORGOT_PASSWORD = "/recovery";
  public static final String AJAX_SEND_PASSWD_RESET_LINK = "/ajax_send_passwd_rst_lnk";
  public static final String RESET_PASSWD = "/reset_password";
  public static final String REGISTRATION = "/registration";
  public static final String DEV = "/dev/**";
  public static final String TEST = "/test/**";
  public static final String ADMIN_URL = "/admin";
  public static final String AJAX_RESET_PASSWD ="/ajax_reset_passwd";

  //URL private
  public static final String ROOT_URL_FWD_SLASH ="/";
  public static final String ROOT_URL_BLANK ="";
  public static final String SERVER = "/server";

  //Masert Server Log Id
  public static final Long SERVER_CREATE = 1L;
  public static final Long SERVER_EXECUTE = 2L;
  public static final Long SERVER_DELETE = 3L;
  public static final Long SERVER_PING = 4L;

}
