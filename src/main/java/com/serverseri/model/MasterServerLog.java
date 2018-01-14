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
@Table(name="master_server_log")
@Getter
@Setter
public class MasterServerLog {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "master_log_id")
  private Long mstServerLogId;

  @Column(name="log_description")
  private String logDescription;
}
