package com.class16Pratice.class16Practice.configs;

import com.class16Pratice.class16Practice.repos.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Step 4A: CheatSheet 3: create details service implementation and required methods
@Service
public class SiteUserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  SiteUserRepository siteUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // Step 4D: don't forget to finish this method!
    return (UserDetails) siteUserRepository.findByUsername(username);
  }
}
