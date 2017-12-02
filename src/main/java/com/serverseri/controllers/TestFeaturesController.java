package com.serverseri.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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

@Controller
@RequestMapping(value = "/test")
public class TestFeaturesController {

  private static final Logger logger = LoggerFactory.getLogger(TestFeaturesController.class);

  @Autowired
  private ServletContext context;

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



}
