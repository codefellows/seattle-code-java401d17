package com.testAuth.recipeTest.repos;

import com.testAuth.recipeTest.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
  public SiteUser getSiteUserByUserName(String userName);
}
