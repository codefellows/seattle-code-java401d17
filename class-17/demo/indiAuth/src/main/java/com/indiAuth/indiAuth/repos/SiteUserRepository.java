package com.indiAuth.indiAuth.repos;

import com.indiAuth.indiAuth.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

// Step 1B: CheatSheet 1: create user repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
  // Step 4B: create a findByUsername() method in order to help get user details
  public SiteUser findByUsername(String username);
}
