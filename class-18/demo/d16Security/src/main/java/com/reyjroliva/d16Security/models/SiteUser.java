package com.reyjroliva.d16Security.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// TODO: Step 1b: Make user model (NOT called "User"!)
@Entity
public class SiteUser implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

  private String username;
  private String password;
  private String firstName;
  private Date dateOfBirth;

  protected SiteUser() {
  }

  public SiteUser(String username, String password, String firstName, Date dateOfBirth) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.dateOfBirth = dateOfBirth;
  }

  // implement many to many!!
  @ManyToMany
  @JoinTable (
    name = "followers_to_followees",
    joinColumns = {@JoinColumn(name = "userWhoIsFollowing")},
    inverseJoinColumns = {@JoinColumn(name = "FollowedUser")}
  ) // takes a name, first join column, and inverse join column
  Set<SiteUser> usersIFollow = new HashSet<>();

  @ManyToMany(mappedBy = "usersIFollow")
  Set<SiteUser> usersWhoFollowMe = new HashSet<>();

  public Set<SiteUser> getUsersIFollow() {
    return usersIFollow;
  }

  public Set<SiteUser> getUsersWhoFollowMe() {
    return usersWhoFollowMe;
  }

  public SiteUser(String username, String password, String firstName) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
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
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  public String getPassword() {
    return password;
  }

  public String getFirstName() {
    return firstName;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }


}
