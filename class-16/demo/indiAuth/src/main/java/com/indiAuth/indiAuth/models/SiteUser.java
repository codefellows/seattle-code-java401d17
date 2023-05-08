package com.indiAuth.indiAuth.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

// Step 1A: CheatSheet 1: Create a site user (not named "user")
// Step 4C: CheatSheet 4: implement UserDetails and its methods to help get the user details for spring sec
@Entity
public class SiteUser implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;

  String username;
  String password;
  LocalDate dateCreated;

  public SiteUser() {}

  public SiteUser(String username, String password, LocalDate dateCreated) {
    this.username = username;
    this.password = password;
    this.dateCreated = dateCreated;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public LocalDate getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(LocalDate dateCreated) {
    this.dateCreated = dateCreated;
  }

  @Override
  public String toString() {
    return "SiteUser{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", password='" + password + '\'' +
      ", dateCreated=" + dateCreated +
      '}';
  }
}
