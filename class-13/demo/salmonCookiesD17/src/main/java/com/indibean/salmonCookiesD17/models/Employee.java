package com.indibean.salmonCookiesD17.models;


import jakarta.persistence.*;

@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  long id;
  String name;
  Integer payPerHour;
  @ManyToOne
  SalmonCookieStore store;

  protected Employee() {}

  public Employee(String name, Integer payPerHour, SalmonCookieStore store) {
    this.name = name;
    this.payPerHour = payPerHour;
    this.store = store;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPayPerHour() {
    return payPerHour;
  }

  public void setPayPerHour(Integer payPerHour) {
    this.payPerHour = payPerHour;
  }

  @Override
  public String toString() {
    return "Employee{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", payPerHour=" + payPerHour +
      ", store=" + store +
      '}';
  }
}
