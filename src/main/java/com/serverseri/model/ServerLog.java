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
@Table(name="server_log")
@Getter
@Setter
public class ServerLog {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="server_log_id")
  private Long serverLogId;

  @OneToOne
  @JoinColumn(name = "server_id", referencedColumnName = "server_id")
  private Server server;

  @OneToOne
  @JoinColumn(name = "master_server_log_id", referencedColumnName = "master_log_id")
  private MasterServerLog mstServerLog;
}
