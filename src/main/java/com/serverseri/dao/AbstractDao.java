package com.serverseri.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

abstract class AbstractDao {

  @Autowired
  private EntityManager entityManager;

  protected Session getSession() {
    return entityManager.unwrap(Session.class);
  }

  public void persist(Object entity) {
    getSession().persist(entity);
  }

  public void update(Object entity) {
    getSession().update(entity);
  }

  public void delete(Object entity) {
    getSession().delete(entity);
  }

  public void save(Object entity) {
    getSession().save(entity);
  }

  public void saveOrUpdate(Object entity) {
    getSession().saveOrUpdate(entity);
  }
}
