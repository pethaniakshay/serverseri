package com.serverseri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actor")
public class Actor {

  private Long actorId;
  private String actorCode;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "actor_id")
  public String getActorCode() {
    return actorCode;
  }

  public void setActorCode(String actorCode) {
    this.actorCode = actorCode;
  }

  @Column(name = "actor_code")
  public Long getActorId() {
    return actorId;
  }

  public void setActorId(Long actorId) {
    this.actorId = actorId;
  }
}
