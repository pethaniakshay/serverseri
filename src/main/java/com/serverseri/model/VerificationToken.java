package com.serverseri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "verification_token")
@Getter
@Setter
public class VerificationToken {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "token_id")
  private Long tokenId;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private User user;

  @Column(name = "token_code")
  private String tokenCode;
}
