package indisbbbakery.bakedGoods;

public class Brownie {
  // Fields
  private int calories;
  private boolean isSugarFree;
  private boolean isEdge;
  private String flavor;
  private String shape;

  // Constructors
  public Brownie(int calories, boolean isSugarFree, boolean isEdge, String flavor, String shape) {
    this.calories = calories;
    this.isSugarFree = isSugarFree;
    this.isEdge = isEdge;
    this.flavor = flavor;
    this.shape = shape;
  }

  public Brownie() {
    this.calories = 250;
    this.isSugarFree = false;
    this.isEdge = false;
    this.flavor = "Fudge";
    this.shape = "Rectangle";
  }

  // Getters and Setters
  public int getCalories() {
    return calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }

  public boolean isSugarFree() {
    return isSugarFree;
  }

  public void setSugarFree(boolean sugarFree) {
    isSugarFree = sugarFree;
  }

  public boolean isEdge() {
    return isEdge;
  }

  public void setEdge(boolean edge) {
    isEdge = edge;
  }

  public String getFlavor() {
    return flavor;
  }

  public void setFlavor(String flavor) {
    this.flavor = flavor;
  }

  public String getShape() {
    return shape;
  }

  public void setShape(String shape) {
    this.shape = shape;
  }
}
