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
@Table(name = "actor")
@Getter
@Setter
public class Actor {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "actor_id")
  private Long actorId;

  @Column(name = "actor_code")
  private String actorCode;
}
