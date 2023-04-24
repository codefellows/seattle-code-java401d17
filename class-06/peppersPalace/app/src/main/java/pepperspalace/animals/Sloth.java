package pepperspalace.animals;

import pepperspalace.parents.Animal;

public class Sloth extends Animal {
  private float nailLength;

  public Sloth(String name, int numLegs, int age, float nailLength) {
    super(name, numLegs, age);
    this.nailLength = nailLength;
  }

  public void move() {
    System.out.println("After 6 hours, your sloth, " + this.getName() + " has climbed 4 feet");
  }

  public void makeNoise() {
    System.out.println("Yaaaaawwwwnnnnn");
  }

  public float getNailLength() {
    return nailLength;
  }

  public void setNailLength(float nailLength) {
    this.nailLength = nailLength;
  }
}
