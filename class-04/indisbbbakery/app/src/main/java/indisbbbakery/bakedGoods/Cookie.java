package indisbbbakery.bakedGoods;

public class Cookie {
  // Fields
  private int calories;
  private int numChocoChips;
  private boolean isSugarFree;
  private boolean isSalted;
  private String flavor;

  // Constructors
  public Cookie(int calories, int numChocoChips, boolean isSugarFree, boolean isSalted, String flavor) {
    this.calories = calories;
    this.numChocoChips = numChocoChips;
    this.isSugarFree = isSugarFree;
    this.isSalted = isSalted;
    this.flavor = flavor;
  }

  public Cookie() {
    this.calories = 200;
    this.numChocoChips = 8;
    this.isSugarFree = false;
    this.isSalted = true;
    this.flavor = "Chocolate Chip";
  }


  // Getters and Setters
  public int getCalories() {
    return calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }

  public int getNumChocoChips() {
    return numChocoChips;
  }

  public void setNumChocoChips(int numChocoChips) {
    this.numChocoChips = numChocoChips;
  }

  public boolean isSugarFree() {
    return isSugarFree;
  }

  public void setSugarFree(boolean sugarFree) {
    isSugarFree = sugarFree;
  }

  public boolean isSalted() {
    return isSalted;
  }

  public void setSalted(boolean salted) {
    isSalted = salted;
  }

  public String getFlavor() {
    return flavor;
  }

  public void setFlavor(String flavor) {
    flavor = flavor;
  }
}
