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
@Table(name = "uas_history_description")
@Getter
@Setter
public class UserAccountStatusHistoryDescription {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "description_id")
  private Long descriptionId;

  @Column(name = "description")
  private String description;
}
