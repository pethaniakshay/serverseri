package com.serverseri.dao;

import com.serverseri.model.MasterServerLog;

public interface MasterServerLogDao {

  MasterServerLog findByLogId(Long id);

  MasterServerLog save(MasterServerLog masterServerLog);

}
