package com.serverseri.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.io.ByteStreams;
import com.serverseri.model.Server;
import com.serverseri.repository.ServerRepository;
import com.serverseri.service.ServerService;
import com.serverseri.service.SshServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/dev")
@Slf4j
public class DevController {

  @Autowired
  private ServerService serverService;

  @Autowired
  private SshServiceImpl sshServiceImpl;

  @Autowired
  private ServerRepository serverRepo;

  @Value("busineess.scrips.path")
  private String scriptPath;

  @RequestMapping("/add_new")
  public String addServer(){

    log.debug("new server");
    return "dev_new_server";
  }

  @RequestMapping(value ="/exec_remote", method = RequestMethod.GET)
  public String executeRemoteScript(){
    return "test_execute_remote_script";
  }

  @RequestMapping(value ="/ajax_exec_remote", method = RequestMethod.POST)
  public @ResponseBody Map<String, Object> executeScript(@RequestParam("file") String file,HttpServletRequest request){
    Map<String ,Object> response = new HashMap<>();
    log.debug("File Name: " + file);

    String command = "bash <(curl -s http://mywebsite.com/myscript.txt)";
    Server server = serverRepo.findOne(1L);
    log.debug("Con Path: " + request.getContextPath());

    sshServiceImpl.executeCommand(server ,file);
    log.debug("Going to execute the script!Hurray");

    return response;
  }

  @RequestMapping(value ="/ajax_add_server_form", method = RequestMethod.POST)
  public @ResponseBody Map<String, Object> processServerForm(@RequestParam Map<String ,Object> newServerForm){
    log.debug(newServerForm.toString());
    Map<String ,Object> response = serverService.addNewServer(newServerForm);
    return response;
  }

  @GetMapping(value = "/rtext" ,produces = MediaType.ALL_VALUE)
  public @ResponseBody byte[] renderTextFileinBrowser(@RequestParam("token")String token) throws Exception {
    //String filePath = "/home/prinksh/Documents/scripts/";
    //String fileName = "/SHAA00.sh";
    File file = new File(scriptPath + token);
    InputStream targetStream = new FileInputStream(file);
    return ByteStreams.toByteArray(targetStream);
  }
}