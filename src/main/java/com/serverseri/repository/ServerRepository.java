package com.serverseri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverseri.model.Server;
import com.serverseri.model.User;

public interface ServerRepository extends JpaRepository<Server, Long>{

  /* (non-Javadoc)
   * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
   */
  @Override
  public List<Server> findAll();

  /**
   * @param serverId
   * @return
   */
  public Server findServerByServerId(Long serverId);

  /**
   * @param user
   * @return
   */
  public List<Server> findServerByUser(User user);

  /**
   * @param hostName
   * @return
   */
  public Server findServerByHostName(String hostName);
}
