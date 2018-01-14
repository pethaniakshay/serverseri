package com.serverseri.model;

import java.sql.Timestamp;

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
@Table(name = "server")
@Getter
@Setter
public class Server {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "server_id")
  private Long serverId;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName="user_id")
  private User user;

  @Column(name = "server_name")
  private String serverName;

  @Column(name = "host_name")
  private String hostName;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "password")
  private String password;

  @Column(name = "created_date")
  private Timestamp createdDate;
}
