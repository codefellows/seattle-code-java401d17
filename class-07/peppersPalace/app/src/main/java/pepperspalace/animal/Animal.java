package pepperspalace.animal;

public class Animal {
  private String name;
  private int numLegs;
  private int age;

  // Constructor
  public Animal(String name, int numLegs, int age) {
    this.name = name;
    this.numLegs = numLegs;
    this.age = age;
  }

  // Methods
  public String makeNoise() {
    return "I am an animal. aAAHHh!";
  }

  // Getters and Setters
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
}
