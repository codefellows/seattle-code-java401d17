package com.reyjroliva.d16Security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// TODO: Step 5: Create a WebSecurityConfig
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  // TODO: wire up the SiteUserDetailsServiceImpl
  @Autowired
  SiteUserDetailServiceImpl siteUserDetailService;

  // passowrdEncoder bean
  @Bean
  public PasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }

  // configure AuthenticationManagerBuilder
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // BUILDER PATTERN Method().config().config.config()
    auth.userDetailsService(siteUserDetailService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors().disable() // CROSS ORIGIN RESOURCE SHARING
      .csrf().disable() // Stops people from other sites to come in and run scripts
      // REQUEST SECTION
      .authorizeRequests()
      .antMatchers("/", "/login", "/signup").permitAll()
      .anyRequest().authenticated()
      .and() // Seperator
      // LOGIN SECTION
      .formLogin()
      .loginPage("/login")
      // NOTE: LINE BELOW IS FOR LAB
      // .defaultSuccessUrl()
      .and()
      // LOGOUT SECTION
      .logout()
      .logoutSuccessUrl("/login");
  }
}
