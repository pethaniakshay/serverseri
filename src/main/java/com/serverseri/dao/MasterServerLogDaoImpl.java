package com.serverseri.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverseri.model.MasterServerLog;
import com.serverseri.repository.MasterServerLogRepository;

@Service
@Transactional
public class MasterServerLogDaoImpl implements MasterServerLogDao{

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
