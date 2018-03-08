package com.serverseri.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverseri.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {

  Actor findByActorId(long actorId);

}
