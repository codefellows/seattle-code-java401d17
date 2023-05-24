package com.reyjroliva.lecture28demo.models;

// TODO: Step 2-1: Make a data class
public class Product {
  private String name;

  public Product(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
