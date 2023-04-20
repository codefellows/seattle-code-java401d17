package indisbbbakery.bakedGoods;

public class Cupcake {
  // Fields
  private int calories;
  private int numSprinkles;
  private boolean isSugarFree;
  private boolean hasPaper;
  private String flavor;
  private String frosting;

  // Constructor
  public Cupcake(int calories, int numSprinkles, boolean isSugarFree, boolean hasPaper, String flavor, String frosting) {
    this.calories = calories;
    this.numSprinkles = numSprinkles;
    this.isSugarFree = isSugarFree;
    this.hasPaper = hasPaper;
    this.flavor = flavor;
    this.frosting = frosting;
  }

  public Cupcake() {
    this.calories = 300;
    this.numSprinkles = 12;
    this.isSugarFree = false;
    this.hasPaper = true;
    this.flavor = "Vanilla";
    this.frosting = "Choclate";
  }

  // Getters and Setters
  public int getCalories() {
    return calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }

  public int getNumSprinkles() {
    return numSprinkles;
  }

  public void setNumSprinkles(int numSprinkles) {
    this.numSprinkles = numSprinkles;
  }

  public boolean isSugarFree() {
    return isSugarFree;
  }

  public void setSugarFree(boolean sugarFree) {
    isSugarFree = sugarFree;
  }

  public boolean isHasPaper() {
    return hasPaper;
  }

  public void setHasPaper(boolean hasPaper) {
    this.hasPaper = hasPaper;
  }

  public String getFlavor() {
    return flavor;
  }

  public void setFlavor(String flavor) {
    this.flavor = flavor;
  }

  public String getFrosting() {
    return frosting;
  }

  public void setFrosting(String frosting) {
    this.frosting = frosting;

  }
}
