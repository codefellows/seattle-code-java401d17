package com.reytest.salmonCookiesD17.repositories;

import com.reytest.salmonCookiesD17.models.SalmonCookieStore;
import org.springframework.data.jpa.repository.JpaRepository;

// 4. Make a repository for the data object
public interface SalmonCookiesStoreRepository extends JpaRepository<SalmonCookieStore, Long> {
  //5. MAGIC that we make happen with a specific function name
  public SalmonCookieStore findByName(String name);
}
