package com.serverseri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_account_status")
@Getter
@Setter
public class UserAccountStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_account_status_id")
  private Long userAccountStatusId;

  @Column(name = "status_code")
  private String statusCode;
}
