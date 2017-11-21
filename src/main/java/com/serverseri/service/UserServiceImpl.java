package com.serverseri.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.serverseri.core.constants.Constants;
import com.serverseri.model.Role;
import com.serverseri.model.User;
import com.serverseri.repository.RoleRepository;
import com.serverseri.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void save(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    Set<Role> roles = new HashSet<>();
    roles.add(roleRepository.findRoleById(Constants.ROLE_USER_ID));
    user.setRoles(roles);
    userRepository.save(user);
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}