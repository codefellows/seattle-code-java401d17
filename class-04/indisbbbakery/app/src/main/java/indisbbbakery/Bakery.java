package indisbbbakery;

import indisbbbakery.bakedGoods.Brownie;
import indisbbbakery.bakedGoods.Cookie;
import indisbbbakery.bakedGoods.Cupcake;

import java.util.ArrayList;

public class Bakery {
  // Fields
  private ArrayList<Cupcake> cupcakes;
  private ArrayList<Cookie> cookies;
  private ArrayList<Brownie> brownies;
  private boolean soldOut;
  private int price;
  private int revenue;

  public static final String OPEN_TIME = "0700";

  // Constructor
  public Bakery(int price, int reveue) {
    this.cupcakes = new ArrayList<>();
    this.brownies = new ArrayList<>();
    this.cookies = new ArrayList<>();
    this.soldOut = true;
    this.price = price;
    this.revenue = reveue;

    bake(12, 10, 12);
  }


  // Getters and setters
  public boolean isSoldOut() {
    return soldOut;
  }

  public void setSoldOut(boolean soldOut) {
    this.soldOut = soldOut;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getRevenue() {
    return revenue;
  }

  public void setRevenue(int revenue) {
    this.revenue = revenue;
  }

  public ArrayList<Cupcake> getCupcakes() {
    return cupcakes;
  }

  public void setCupcakes(ArrayList<Cupcake> cupcakes) {
    this.cupcakes = cupcakes;
  }

  public ArrayList<Cookie> getCookies() {
    return cookies;
  }

  public void setCookies(ArrayList<Cookie> cookies) {
    this.cookies = cookies;
  }

  public ArrayList<Brownie> getBrownies() {
    return brownies;
  }

  public void setBrownies(ArrayList<Brownie> brownies) {
    this.brownies = brownies;
  }

  // Methods
  public void makeSale(int numSold, int type) {
    revenue += (price * numSold);

    switch(type) {
      case 1:
        cookies.remove(cookies.size() - 1);
        System.out.println("We just sold a cookie!");
        break;
      case 2:
        brownies.remove(brownies.size() - 1);
        System.out.println("We just sold a brownie!");
        break;
      case 3:
        cupcakes.remove(cupcakes.size() - 1);
        System.out.println("We just sold a cupcake!");
        break;
      default:
        cupcakes.get(0).getCalories();
        System.out.println("Invalid option!");
        break;
    }

    System.out.println("We just made: " + (price * numSold));
    System.out.println("Total revenue: " + revenue);
  }

  private void bake(int numCookies, int numBrownies, int numCupcakes) {
    for(int i = 0; i < numCookies; i++) {
      cookies.add(new Cookie());
    }

    for(int i = 0; i < numBrownies; i++) {
      brownies.add(new Brownie());
    }

    for(int i = 0; i < numCupcakes; i++) {
      cupcakes.add(new Cupcake());
    }
  }

  public static void advertisement() {
    System.out.println("All bakeries in the Seattle area open at 6am!");
    System.out.println("Paid for the bakery fund of Seattle");
  }
}
