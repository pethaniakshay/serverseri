package com.serverseri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverseri.model.TempDateTimeMappingTesting;

public interface TempDateTimeRepository extends JpaRepository<TempDateTimeMappingTesting, Long>{

  @Override
  TempDateTimeMappingTesting save(TempDateTimeMappingTesting tempObject);

}
