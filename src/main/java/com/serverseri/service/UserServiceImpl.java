package com.serverseri.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.serverseri.core.constants.Constants;
import com.serverseri.model.Actor;
import com.serverseri.model.Role;
import com.serverseri.model.User;
import com.serverseri.model.UserAccountStatusHistory;
import com.serverseri.model.UserAccountStatusHistoryDescription;
import com.serverseri.repository.ActorRepository;
import com.serverseri.repository.RoleRepository;
import com.serverseri.repository.UasHistoryDescriptionRepository;
import com.serverseri.repository.UserAccountStatusHistoryRepository;
import com.serverseri.repository.UserAccountStatusRepository;
import com.serverseri.repository.UserRepository;

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

  @SuppressWarnings("boxing")
  @Override
  public void save(User user) {
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
}