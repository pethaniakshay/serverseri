package com.serverseri.core.utils;

import java.security.Key;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Service
public class EncrptBean {
  private final static Logger logger = LoggerFactory
      .getLogger(EncrptBean.class);

  @Value("${encrpt.key}")
  private String encrptKey;

  private static final String ALGORITHM = "AES";

  public static String KEY;

  @PostConstruct
  public void init(){
    KEY = encrptKey;
  }

  public static String encrypt(String value) {
    String encryptedValue64 = "";
    try {
      Key key = generateKey();
      Cipher cipher = Cipher.getInstance(EncrptBean.ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, key);
      byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
      encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
    } catch (Exception error) {
      logger.error("Error : " ,error);
    }
    if(encryptedValue64.contains("/")){
      encryptedValue64 = encryptedValue64.replaceAll("/", ":");
    }
    return encryptedValue64;
  }

  public static String decrypt(String value) {
    String decryptedValue = "";
    try {
      if(value.isEmpty()){
        return value;
      }
      Key key = generateKey();
      Cipher cipher = Cipher.getInstance(EncrptBean.ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, key);
      byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
      byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
      decryptedValue = new String(decryptedByteValue, "utf-8");
    } catch (Exception error) {
      logger.error("Error : "+ value ,error);
    }
    return decryptedValue;
  }

  private static Key generateKey() throws Exception {
    Key key = new SecretKeySpec(EncrptBean.KEY.getBytes(),
        EncrptBean.ALGORITHM);
    return key;
  }
}
