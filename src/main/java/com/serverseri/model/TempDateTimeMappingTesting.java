package com.serverseri.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="temp_date_time_testing")
@Getter
@Setter
public class TempDateTimeMappingTesting {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name="id")
  private Long id;

  @Column(name="uname")
  private String uname;

  @Column(name="date_time")
  private Timestamp date;
}
