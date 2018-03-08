package com.serverseri.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverseri.dao.repository.MasterServerLogRepository;
import com.serverseri.model.MasterServerLog;

@Service
@Transactional
public class MasterServerLogDaoImpl extends AbstractDao implements MasterServerLogDao{

  @Autowired
  MasterServerLogRepository repository;

  @Override
  public MasterServerLog findByLogId(Long id) {
    return repository.findByLogId(id);
  }

  @Override
  public MasterServerLog save(MasterServerLog masterServerLog) {
    return repository.saveAndFlush(masterServerLog);
  }
}