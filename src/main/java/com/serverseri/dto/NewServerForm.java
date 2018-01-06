package com.serverseri.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewServerForm {
  private String serverName;
  private String hostName;
  private String userName;
  private String password;
}
