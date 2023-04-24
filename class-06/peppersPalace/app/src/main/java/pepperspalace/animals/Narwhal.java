package pepperspalace.animals;

import pepperspalace.parents.Animal;

public class Narwhal extends Animal {
  private float hornLength;

  public Narwhal(String name, int numLegs, int age, float hornLength) {
    super(name, numLegs, age);
    this.hornLength = hornLength;
  }

  @Override
  public String toString() {
    return "Narwhal{" +
      "hornLength=" + hornLength +
      '}';
  }

  public String stab() {
    return "oh no, " + this.getName() + " has attacked someone again";
  }

  public void makeNoise() {
    System.out.println("I'm a unicorn whale");
  }

  public float getHornLength() {
    return hornLength;
  }

  public void setHornLength(float hornLength) {
    this.hornLength = hornLength;
  }
}
