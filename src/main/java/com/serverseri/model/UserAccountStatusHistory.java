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
@Table(name = "uas_history")
@Getter
@Setter
public class UserAccountStatusHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "uas_history_id")
  private Long uasHistoryId;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private User user;

  @OneToOne
  @JoinColumn(name = "uas_id", referencedColumnName = "user_account_status_id")
  private UserAccountStatus uas;

  @OneToOne
  @JoinColumn(name = "actor_id", referencedColumnName = "actor_id")
  private Actor actor;

  @OneToOne
  @JoinColumn(name = "description_id", referencedColumnName = "description_id")
  private UserAccountStatusHistoryDescription uasHistoryDesc;
}
