package com.serverseri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverseri.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

  VerificationToken findByTokenCode(String tokenCode);

  VerificationToken findByTokenId(long tokenId);
}
