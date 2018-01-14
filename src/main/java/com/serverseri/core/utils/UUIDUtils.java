package com.serverseri.core.utils;

import java.util.UUID;

public class UUIDUtils {

  public static String getUniqueToken() {
    return UUID.randomUUID().toString();
  }
}
