package com.serverseri.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.serverseri.model.Server;

public class SshServiceImpl implements SshService{
  private static final Logger logger = LoggerFactory.getLogger(SshServiceImpl.class);

  @Override
  public int executeCommand(Server server, String command) {
    return 0;
  }

  private JSch getJch() {
    return new JSch();
  }

  public Session getSession(Server server, String host, String username, String password) {
    Session session = null;
    try {
      JSch jsch = getJch();
      session = jsch.getSession(username, host);
      session.setPassword(password);
      java.util.Properties config = new java.util.Properties();
      config.put("StrictHostKeyChecking", "no");
      session.setConfig(config);
    }
    catch(Exception e){
      System.out.println("Error: + "+e);
      e.printStackTrace();
    }
    return session;
  }
}
