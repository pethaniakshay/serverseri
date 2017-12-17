package com.serverseri.service;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.serverseri.model.Server;

@Service
public class SshServiceImpl implements SshService{
  private static final Logger logger = LoggerFactory.getLogger(SshServiceImpl.class);

  @Override
  public int executeCommand(Server server, String command) {
    try {
      String output = "";
      Session session = getSession(server);
      connectSession(session);
      ChannelExec channel = getExecChannel(session);
      channel.setCommand(command);
      InputStream in=channel.getInputStream();
      channel.setInputStream(System.in);
      channel.connect();
      byte[] tmp=new byte[1024];
      while(true){
        while(in.available()>0){
          int i=in.read(tmp, 0, 1024);
          if(i<0)break;
          System.out.print(new String(tmp, 0, i));
          output = output + new String(tmp, 0, i);
        }
        if(channel.isClosed()){
          if(in.available()>0) continue;
          System.out.println("exit-status: "+channel.getExitStatus());
          in.close();
          break;
        }
      }
      channel.disconnect();
      disconnectSession(session);
    }
    catch(Exception e) {
      logger.error("In executing command: ", e);
      return 1;
    }
    return 0;
  }

  private JSch getJch() {
    return new JSch();
  }

  public Session getSession(Server server) {
    Session session = null;
    try {
      JSch jsch = getJch();
      session = jsch.getSession(server.getUserName(), server.getHostName());
      session.setPassword(server.getPassword());
      java.util.Properties config = new java.util.Properties();
      config.put("StrictHostKeyChecking", "no");
      session.setConfig(config);
      logger.info("Session Obejct created for: "+ server.getHostName());
    }
    catch(Exception e){
      System.out.println("Error: + "+e);
      e.printStackTrace();
    }
    return session;
  }

  private boolean connectSession(Session session) {
    try {
      session.connect();
    }
    catch(Exception e) {
      logger.error("Error While connectiong the session: " ,e);
      return false;
    }
    return true;
  }

  private boolean disconnectSession(Session session) {
    try {
      session.disconnect();
    }
    catch(Exception e) {
      logger.error("Error While disconnectiong the session: " ,e);
      return false;
    }
    return true;
  }

  private ChannelExec getExecChannel(Session session) {
    try {
      return (ChannelExec)session.openChannel("exec");
    } catch (JSchException e) {
      logger.error("Error while opening exec channel: ", e);
      return null;
    }
  }
}
