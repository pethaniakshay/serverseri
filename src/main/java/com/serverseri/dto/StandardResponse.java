package com.serverseri.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StandardResponse {
  private String response;
  private String message;
}
