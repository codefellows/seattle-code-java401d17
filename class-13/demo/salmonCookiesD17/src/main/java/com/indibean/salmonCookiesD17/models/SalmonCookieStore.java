package com.indibean.salmonCookiesD17.models;

import jakarta.persistence.*;

import java.util.List;

// To store this in the DB
// 1. add @Entity annotation to data model
@Entity
public class SalmonCookieStore {
  // 2. add @Id and @GeneratedValue annotations
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  //if more than 25 characters needed, use two annotations
  //@Lob
  //@Type(type = "org.hibernate.type.TextType")
  String name;
  Integer averageCookiesPerDay;
  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
  List<Employee> employees;


  // 3. create a protected default constructor
  protected SalmonCookieStore() {}

  public SalmonCookieStore(String name, Integer averageCookiesPerDay) {
    this.name = name;
    this.averageCookiesPerDay = averageCookiesPerDay;
  }

  public SalmonCookieStore(long id, String name, Integer averageCookiesPerDay) {
    this.id = id;
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

  public List<Employee> getEmployees() {
    return employees;
  }

  @Override
  public String toString() {
    return "SalmonCookieStore{" +
      "name='" + name + '\'' +
      '}';
  }
}
