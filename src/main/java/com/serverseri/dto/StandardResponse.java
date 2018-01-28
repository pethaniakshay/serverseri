package com.serverseri.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardResponse {
  private String response;
  private String message;
  private String status;
}
