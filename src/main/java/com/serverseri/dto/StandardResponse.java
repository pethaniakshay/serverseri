package com.serverseri.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardResponse implements Serializable{
  private String response;
  private String message;
  private String status;
  private String payLoadOne;
}
