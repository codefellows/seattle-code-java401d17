package pepperspalace.animal;

public class Lion  extends Animal {
  private String maneColor;

//   Constructor
  public Lion(String name, int numLegs, int age, String maneColor) {
    super(name, numLegs, age);
    this.maneColor = maneColor;
  }

//   methods
  public String groom() {
    return "I'm all clean, time for a nap";
  }


//   Getter and setter
  public String getManeColor() {
    return maneColor;
  }

  public void setManeColor(String maneColor) {
    this.maneColor = maneColor;
  }
}
