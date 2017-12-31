package com.serverseri.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.google.common.io.ByteStreams;
import com.serverseri.core.utils.EncrptBean;
import com.serverseri.core.utils.FreeMakerUtils;
import com.serverseri.model.User;
import com.serverseri.repository.UserRepository;
import com.serverseri.service.mail.MailService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/test")
@Slf4j
public class TestFeaturesController {

  @Autowired
  private ServletContext context;

  @Autowired
  private FreeMakerUtils freeMakerUtils;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MailService mailService;

  @RequestMapping(value = "/jsch")
  public String jschTest() {
    return "jsch_test";
  }

  @RequestMapping(value = "/mail" , method = RequestMethod.GET)
  public String mailTester(Model model) {
    mailService.sendMail("yoserverseri@gmail.com","patelaksh412@gmail.com","Test Mail","Hey this is the Asynchronous mail service of the serverseri.");
    return "sample_mail_sender";
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
    log.debug("File Name: " + fileName);
    //String fileName = "/sample_1.txt";
    File file = new File(filePath + "/" + fileName);
    InputStream targetStream = new FileInputStream(file);
    return ByteStreams.toByteArray(targetStream);
  }

  @RequestMapping(value ="/fm")
  public String freeMakerTesting(HttpServletRequest request, WebRequest webRequest) {
    Map<String,Object> model = new HashMap<>();
    model.put("empty", "Nothing");
    String message = freeMakerUtils.templateToString("test.ftl", model);
    log.debug(message);
    log.debug("Web Re: " + webRequest.getContextPath() + " * hR: " + request.getContextPath());
    return "about";
  }

  @RequestMapping(value = "/uuid")
  public String UUIDTesting() {
    String before = UUID.randomUUID().toString();
    String en = EncrptBean.encrypt(before);
    String dc = EncrptBean.decrypt(en);
    log.debug("Normal: "+ before);
    log.debug("Encrypted: " + en + " length: " + en.length());
    log.debug("Decrypted: " + dc);
    return "about";
  }

  @RequestMapping(value = "/repo")
  public String repoTesting() {
    List<User> users = userRepository.findAll();

    log.debug("User Count: "+ users.size());

    User user = userRepository.findByEmail("patelaksh412@gmail.com");
    if(user == null) {
      log.debug("User Is Null");
    }
    else {
      log.debug("User is not null");
    }

    Set<User> s = new HashSet<>();

    User u1 = new User();
    u1.setFullName("Akshay");
    User u2 = new User();
    u2.setFullName("Akshay");

    s.add(u2);
    s.add(u1);

    log.debug("User:" + s.size());

    return "about";
  }
}
