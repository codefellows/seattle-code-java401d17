package com.indibean.salmonCookiesD17.repository;

import com.indibean.salmonCookiesD17.models.SalmonCookieStore;
import org.springframework.data.jpa.repository.JpaRepository;

// 4. Make a repository interface for the data object
public interface SalmonCookieStoreRepository extends JpaRepository<SalmonCookieStore, Long> {
  // 5. MAGIC that we make happen with a specific function name
  public SalmonCookieStore findByName(String name);
  // default -> save(), delete(), update(), findAll()
}
