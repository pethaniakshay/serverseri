package com.serverseri.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.io.ByteStreams;
import com.serverseri.core.utils.EncrptBean;
import com.serverseri.core.utils.FreeMakerUtils;
import com.serverseri.model.User;
import com.serverseri.repository.UserRepository;
import com.serverseri.service.UserService;

@Controller
@RequestMapping(value = "/test")
public class TestFeaturesController {

  private static final Logger logger = LoggerFactory.getLogger(TestFeaturesController.class);

  @Autowired
  private ServletContext context;

  @Autowired
  private FreeMakerUtils freeMakerUtils;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userservice;



  @RequestMapping(value = "/jsch")
  public String jschTest() {
    return "jsch_test";
  }


  @GetMapping(value = "/rtext" ,produces = MediaType.ALL_VALUE)
  public @ResponseBody byte[] renderTextFileinBrowser() throws Exception {
    String filePath = context.getRealPath("/WEB-INF/downloads");
    String fileName = "/sample_1.txt";
    File file = new File(filePath + fileName);
    InputStream targetStream = new FileInputStream(file);
    return ByteStreams.toByteArray(targetStream);
  }

  @GetMapping(value = "/rtext/{fileName:.+}" ,produces = MediaType.ALL_VALUE)
  public @ResponseBody byte[] renderTextFileinBrowserByName(@PathVariable("fileName") String fileName) throws Exception {
    String filePath = context.getRealPath("/WEB-INF/downloads");
    logger.debug("File Name: " + fileName);
    //String fileName = "/sample_1.txt";
    File file = new File(filePath + "/" + fileName);
    InputStream targetStream = new FileInputStream(file);
    return ByteStreams.toByteArray(targetStream);
  }

  @RequestMapping(value ="/fm")
  public String freeMakerTesting() {
    Map<String,Object> model = new HashMap<>();
    model.put("empty", "Nothing");
    String message = freeMakerUtils.templateToString("test.ftl", model);
    logger.debug(message);
    return "about";
  }

  @RequestMapping(value = "/uuid")
  public String UUIDTesting() {
    String before = UUID.randomUUID().toString();
    String en = EncrptBean.encrypt(before);
    String dc = EncrptBean.decrypt(en);
    logger.debug("Normal: "+ before);
    logger.debug("Encrypted: " + en + " length: " + en.length());
    logger.debug("Decrypted: " + dc);
    return "about";
  }

  @RequestMapping(value = "/repo")
  public String repoTesting() {
    List<User> users = userRepository.findAll();
    logger.debug("User Count: "+ users.size());

    User user = userRepository.findByEmail("patelaksh412@gmail.com");
    if(user == null) {
      logger.debug("User Is Null");
    }
    else {
      logger.debug("User is not null");
    }
    return "about";
  }
}
