package com.reytest.salmonCookiesD17.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// To store this in the db
// 1. Add @Entity annotation
@Entity
public class SalmonCookieStore {
  // 2. Add @ID and @GeneratedValue annotations
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  //if more than 25 characters needed, use two annotations
  //@Lob
  //@Type(type = "org.hibernate.type.TextType")
  String name;
  Integer averageCookiesPerDay;

  //3. create a protected default constructor
  protected SalmonCookieStore() {}

  public SalmonCookieStore(String name, Integer averageCookiesPerDay) {
    this.name = name;
    this.averageCookiesPerDay = averageCookiesPerDay;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAverageCookiesPerDay() {
    return averageCookiesPerDay;
  }

  public void setAverageCookiesPerDay(Integer averageCookiesPerDay) {
    this.averageCookiesPerDay = averageCookiesPerDay;
  }
}
