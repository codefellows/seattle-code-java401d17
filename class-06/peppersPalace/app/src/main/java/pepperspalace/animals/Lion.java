package pepperspalace.animals;

public class Lion {
  private String name;
  private int numLegs;
  private int age;
  private String maneColor;

  public Lion(String name, int numLegs, int age, String maneColor) {
    this.name = name;
    this.numLegs = numLegs;
    this.age = age;
    this.maneColor = maneColor;
  }

  public void groom() {
    System.out.println("finally, " + this.name + " is clean");
  }

  public void makeNoise() {
    System.out.println("ROOOAAARRR");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNumLegs() {
    return numLegs;
  }

  public void setNumLegs(int numLegs) {
    this.numLegs = numLegs;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getManeColor() {
    return maneColor;
  }

  public void setManeColor(String maneColor) {
    this.maneColor = maneColor;
  }
}
