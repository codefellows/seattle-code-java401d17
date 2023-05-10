package com.reyjroliva.d16Security.repositories;

import com.reyjroliva.d16Security.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: Step 1a: Set up SiteUser Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
  public SiteUser findUserByUsername(String username);
}
