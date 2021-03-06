package com.serverseri.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import com.serverseri.core.constants.Constants;
import com.serverseri.core.utils.EncrptBean;
import com.serverseri.core.utils.StringUtility;
import com.serverseri.dao.repository.ActorRepository;
import com.serverseri.dao.repository.RoleRepository;
import com.serverseri.dao.repository.UasHistoryDescriptionRepository;
import com.serverseri.dao.repository.UserAccountStatusHistoryRepository;
import com.serverseri.dao.repository.UserAccountStatusRepository;
import com.serverseri.dao.repository.UserRepository;
import com.serverseri.dto.StandardResponse;
import com.serverseri.model.Actor;
import com.serverseri.model.Role;
import com.serverseri.model.User;
import com.serverseri.model.UserAccountStatusHistory;
import com.serverseri.model.UserAccountStatusHistoryDescription;
import com.serverseri.service.mail.MailService;
import com.serverseri.validator.CommonValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private UserAccountStatusRepository uasRepository;

  @Autowired
  private UasHistoryDescriptionRepository uasHisDescRepo;

  @Autowired
  private UserAccountStatusHistoryRepository uasHistoryRepository;

  @Autowired
  private ActorRepository actorRepository;

  @Autowired
  private MailService mailService;

  @Autowired
  private WebRequest webRequest;

  @SuppressWarnings("boxing")
  @Override
  public void saveNewUser(User user) {
    try {
      User tempUser = findByEmail(user.getEmail());
      if(tempUser == null) {
      }
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      Set<Role> roles = new HashSet<>();
      roles.add(roleRepository.findRoleByRoleId(Constants.ROLE_USER_ID));
      user.setRoles(roles);
      user.setUas(uasRepository.findByUserAccountStatusId(Constants.USER_ACCOUNT_STATUS_CREATED_ID));
      user =userRepository.save(user);
      log.info("New user saved successfully.");
      UserAccountStatusHistoryDescription desc = uasHisDescRepo.findByDescriptionId(1); //user created
      Actor actor = actorRepository.findByActorId(4);
      UserAccountStatusHistory history = new UserAccountStatusHistory();
      history.setActor(actor);
      history.setUas(uasRepository.findByUserAccountStatusId(Constants.USER_ACCOUNT_STATUS_CREATED_ID));
      history.setUasHistoryDesc(desc);
      history.setUser(user);
      uasHistoryRepository.save(history);
      log.info("Hsitory Entry Updated for User: "+ user.getFullName());
    } catch(Exception e) {
      log.error("Error: ", e);
    }
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @SuppressWarnings("boxing")
  @Override
  public Map<String,Object> processRegistration(User user){

    Map<String,Object> status = new HashMap<String,Object>();
    try {

      User tempUser = findByEmail(user.getEmail());
      if(tempUser != null) {
        status.put(Constants.STATUS, Constants.STATUS_ERROR);
        status.put(Constants.MESSAGE, "This email address is already in use with serverseri account.");
        log.info("This email address is already in use with serverseri account.");
        return status;
      }
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      Set<Role> roles = new HashSet<>();
      roles.add(roleRepository.findRoleByRoleId(Constants.ROLE_USER_ID));
      user.setRoles(roles);
      user.setUas(uasRepository.findByUserAccountStatusId(Constants.USER_ACCOUNT_STATUS_CREATED_ID));
      user =userRepository.save(user);
      log.info("New user saved successfully.");

      UserAccountStatusHistoryDescription desc = uasHisDescRepo.findByDescriptionId(1); //user created
      Actor actor = actorRepository.findByActorId(4);
      UserAccountStatusHistory history = new UserAccountStatusHistory();
      history.setActor(actor);
      history.setUas(uasRepository.findByUserAccountStatusId(Constants.USER_ACCOUNT_STATUS_CREATED_ID));
      history.setUasHistoryDesc(desc);
      history.setUser(user);
      uasHistoryRepository.save(history);
      log.info("Hsitory Entry Updated for User: "+ user.getFullName());
    } catch(Exception e) {
      log.error("Error: ", e);
      status.put(Constants.STATUS, Constants.STATUS_ERROR);
      status.put(Constants.MESSAGE, "An unkonwn error occur while creating the user.");
      return status;
    }
    status.put(Constants.STATUS, Constants.STATUS_SUCCESS);
    status.put(Constants.MESSAGE, "New account registraion successfull.");
    return status;
  }

  @Override
  public Map<String,Object> updateUserStatusToVerified(Long userId){
    Map<String,Object> status = new HashMap<String,Object>();
    return status;
  }

  @Override
  public StandardResponse sendPasswordVerifiactionLink(String mailId) {
    StandardResponse response = new StandardResponse();
    if(StringUtility.isEmptyOrNull(mailId)) {
      response.setStatus(Constants.STATUS_ERROR);
      response.setMessage("Mail address is empty or null");
      return response;
    }

    if(!CommonValidator.emailAddressValidator(mailId)) {
      response.setStatus(Constants.STATUS_ERROR);
      response.setMessage("Invalid Email Address");
      return response;
    }

    User user = userRepository.findByEmail(mailId);
    if(user == null) {
      response.setStatus(Constants.STATUS_ERROR);
      response.setMessage("No user account exist with provided email address.");
      return response;
    }
    //Generate Password Rest Code
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(Constants.APP_INTERNAL_DATE_TIME_FORMAT);
    ZonedDateTime startTimUTC = ZonedDateTime.now(ZoneOffset.UTC);
    log.debug("Current Time UTC: "+ startTimUTC.toString());
    String resetCode =  startTimUTC.format(timeFormatter)+"_<->_"+mailId;
    log.debug("::Reset Link: " + resetCode + "  ::  Web Request: ");
    resetCode = EncrptBean.encrypt(resetCode);
    String resetLink = webRequest.getContextPath() + Constants.FORWARD_SLASH + resetCode;
    //Send Mail to the user
    mailService.sendHTMLMail("yoserverseri@gmail.com", mailId, "Password Reset Link", resetLink);
    log.info("Password Reset Link is sent");
    response.setStatus(Constants.STATUS_SUCCESS);
    response.setMessage("Password Reset Link is sent.");
    return response;
  }

  @Override
  public StandardResponse verifyPasswordResetToken(String resetToken) {
    StandardResponse response = new StandardResponse();
    resetToken = EncrptBean.decrypt(resetToken);
    String[] tokenValues = resetToken.split(Constants.TOKEN_VALUE_SPLITTER);
    String time = tokenValues[0];
    String email = tokenValues[1];
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(Constants.APP_INTERNAL_DATE_TIME_FORMAT);
    ZonedDateTime generatedTime = LocalDateTime.parse(time, timeFormatter).atZone(ZoneOffset.UTC);
    ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
    long hours = ChronoUnit.HOURS.between(generatedTime, now);
    log.debug("Time: "+ generatedTime +  "   Email: "+ email);
    if(hours > 8) {
      //Return as the link expired;
      log.info("Linke Expired");
      response.setStatus(Constants.STATUS_SUCCESS);
      return response;
    }
    response.setStatus(Constants.STATUS_SUCCESS);
    response.setMessage("Password Reset Link is sent.");
    response.setPayLoadOne(EncrptBean.encrypt(email));
    return response;
  }

  @Override
  public StandardResponse resetPassword(String email, String password, String confirmPassword) {
    StandardResponse response = new StandardResponse();

    //check weather all the strings are empty or not
    if(StringUtility.isEmptyOrNull(email)) {
      response.setStatus(Constants.STATUS_ERROR);
      response.setMessage("Mail address is empty or null");
      return response;
    }

    if(StringUtility.isEmptyOrNull(password)) {
      response.setStatus(Constants.STATUS_ERROR);
      response.setMessage("Password Empty or Null.");
      return response;
    }

    if(StringUtility.isEmptyOrNull(confirmPassword)) {
      response.setStatus(Constants.STATUS_ERROR);
      response.setMessage("Confirm Password Empty or Null.");
      return response;
    }

    email = EncrptBean.decrypt(email);

    //check for the validity of the email
    if(!CommonValidator.emailAddressValidator(email)) {
      response.setStatus(Constants.STATUS_ERROR);
      response.setMessage("Invalid Email Address");
      return response;
    }

    //find user by the email address
    User user = findByEmail(email);

    if(user == null) {
      response.setStatus(Constants.STATUS_ERROR);
      response.setMessage("User Does not exist. Invalid Email Address.");
      return response;
    }

    //match the password and the confirm password
    if(!password.equals(confirmPassword)) {
      response.setStatus(Constants.STATUS_ERROR);
      response.setMessage("Passwords Do not match.");
      return response;
    }

    //TODO determine the password strength and validate the strenght as per application requirement.

    //TODO match with old password and do not allow to set the same passord.

    //write the password in the object and persist it
    user.setPassword(bCryptPasswordEncoder.encode(password));
    userRepository.save(user);
    log.info("Password Reset Successfully.");
    response.setStatus(Constants.STATUS_SUCCESS);
    response.setMessage("Password Reset Successfully.");
    return response;
  }
}