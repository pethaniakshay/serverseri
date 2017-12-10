package com.serverseri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_account_status")
public class UserAccountStatus {

  private Long userAccountStatusId;
  private String statusCode;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_account_status_id")
  public Long getUserAccountStatusId() {
    return userAccountStatusId;
  }

  public void setUserAccountStatusId(Long userAccountStatusId) {
    this.userAccountStatusId = userAccountStatusId;
  }

  @Column(name = "status_code")
  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }
}
