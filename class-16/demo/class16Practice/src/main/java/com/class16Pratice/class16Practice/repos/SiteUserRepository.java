package com.class16Pratice.class16Practice.repos;

import com.class16Pratice.class16Practice.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

// Step 1B: CheatSheet 1: Create user repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
  //Step 4B: create findbyUsername in order to help get a user's details
  public SiteUser findByUsername(String username);
}
