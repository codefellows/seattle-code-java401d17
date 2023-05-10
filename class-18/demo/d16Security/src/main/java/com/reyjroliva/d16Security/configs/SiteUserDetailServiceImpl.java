package com.reyjroliva.d16Security.configs;

import com.reyjroliva.d16Security.repositories.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// TODO: Step 3: UserDetailServiceImpl implements UserDetailService (make sure to use @Service)
@Service // Spring autodetects and loads
public class SiteUserDetailServiceImpl implements UserDetailsService {
  // TODO: Step 3a: Autowire SiteUserRepository
  @Autowired
  SiteUserRepository siteUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // add a sout!
    System.out.println("SITE USER DETAILS SERVICE CALLS FOR USER (FROM database)");
    return (UserDetails) siteUserRepository.findUserByUsername(username);
  }
}
