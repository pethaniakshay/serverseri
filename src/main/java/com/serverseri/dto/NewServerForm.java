package com.serverseri.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewServerForm implements Serializable{
  private String serverName;
  private String hostName;
  private String userName;
  private String password;
}
