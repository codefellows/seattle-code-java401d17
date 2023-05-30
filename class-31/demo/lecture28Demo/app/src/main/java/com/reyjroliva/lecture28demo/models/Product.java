package com.reyjroliva.lecture28demo.models;

import java.util.Date;

public class Product {
  private String name;
  private java.util.Date sellBy;
  private Float price;
  private ProductTypeEnum type;

  public Product(String name, Date sellBy, Float price, ProductTypeEnum type) {
    this.name = name;
    this.sellBy = sellBy;
    this.price = price;
    this.type = type;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getSellBy() {
    return sellBy;
  }

  public void setSellBy(Date sellBy) {
    this.sellBy = sellBy;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public ProductTypeEnum getType() {
    return type;
  }

  public void setType(ProductTypeEnum type) {
    this.type = type;
  }

  public enum ProductTypeEnum{
    // list values
    CLOTHES("Clothes"),
    ELECTRONICS("Electronics"),
    PERISHABLE_GOODS("Perishable Goods"),
    OFFICE_SUPPLIES("Office Supplies"),
    MISC("Miscellaneous");

    // property called ProductType
    private final String productType;

    ProductTypeEnum(String productType) {
      this.productType = productType;
    }
    // Method fromString
    public static ProductTypeEnum fromString(String possibleProductType){
      for (ProductTypeEnum type : ProductTypeEnum.values()) {
        if (type.productType.equals(possibleProductType)){
          return type;
        }
      }
      return null;
    }
    // toString method

    @Override
    public String toString() {
      return "ProductTypeEnum{" +
        "productType='" + productType + '\'' +
        '}';
    }
  }
}
