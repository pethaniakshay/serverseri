package com.serverseri.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverseri.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

  Role findRoleByRoleId(Long roleId);
}