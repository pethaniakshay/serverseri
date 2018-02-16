package com.serverseri.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.serverseri.core.constants.Constants;
import com.serverseri.model.Server;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SshServiceImpl implements SshService{

  @Autowired
  ServerLogService serverLogService;

  @Override
  public boolean checkConnection(Server server){
    try{
      JSch jsch = getJch();
      Session session = jsch.getSession(server.getUserName(), server.getHostName());
      session.setPassword(server.getPassword());
      java.util.Properties config = new java.util.Properties();
      config.put("StrictHostKeyChecking", "no");
      session.setConfig(config);
      session.connect();
      if(session.isConnected()){
        session.disconnect();
        serverLogService.log(server, Constants.SERVER_PING, "Pinged");
        return true;
      }
    } catch(Exception e){
      log.error("Error: ",e);
      return false;
    }
    return false;
  }

  private boolean connectSession(Session session) {
    try {
      session.connect();
    }
    catch(Exception e) {
      log.error("Error While connectiong the session: " ,e);
      return false;
    }
    return true;
  }

  private boolean disconnectSession(Session session) {
    try {
      session.disconnect();
    }
    catch(Exception e) {
      log.error("Error While disconnectiong the session: " ,e);
      return false;
    }
    return true;
  }

  @Override
  public int executeCommand(Server server, String command) {
    try {
      String output = "";
      Session session = getSession(server);
      log.debug("Session: " + session.isConnected());
      connectSession(session);
      log.debug("Session: " + session.isConnected());
      ChannelExec channel = getExecChannel(session);
      log.debug("Channel: " + channel.isConnected());
      channel.setCommand(command);
      log.debug("Command Set");
      InputStream in=channel.getInputStream();
      channel.setInputStream(System.in);
      channel.connect();
      byte[] tmp=new byte[1024];
      log.debug("Place A");
      while(true){
        while(in.available()>0){
          int i=in.read(tmp, 0, 1024);
          if(i<0)break;
          log.debug(new String(tmp, 0, i));
          output = output + new String(tmp, 0, i);
        }
        if(channel.isClosed()){
          if(in.available()>0) continue;
          log.debug("exit-status: "+channel.getExitStatus());
          in.close();
          break;
        }
      }
      channel.disconnect();
      disconnectSession(session);
    }
    catch(Exception e) {
      log.error("In executing command: ", e);
      return 1;
    }
    return 0;
  }

  private ChannelExec getExecChannel(Session session) {
    try {
      return (ChannelExec)session.openChannel("exec");
    } catch (JSchException e) {
      log.error("Error while opening exec channel: ", e);
      return null;
    }
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
      log.info("Session Obejct created for: "+ server.getHostName());
    }
    catch(Exception e){
      log.error("Error: ", e);
      e.printStackTrace();
    }
    return session;
  }
}
