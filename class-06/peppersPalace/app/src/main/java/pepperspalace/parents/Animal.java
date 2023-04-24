package pepperspalace.parents;

public class Animal {
  protected String name;
  protected int numLegs;
  protected int age;

  public Animal(String name, int numLegs, int age) {
    this.name = name;
    this.numLegs = numLegs;
    this.age = age;
  }

  public void makeNoise() {
    System.out.println("I'm an animal!! Ahhh!");
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
}
