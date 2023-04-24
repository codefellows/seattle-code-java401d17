package pepperspalace.animals;

import pepperspalace.parents.Animal;

public class Ocelot extends Animal {
  // Properties/Fields
  private String pattern;

  // Constructor
  public Ocelot(String name, int numLegs, int age, String pattern) {
    super(name, numLegs, age);
    this.pattern = pattern;
  }

  public Ocelot() {
    super("basic", 4, 0);
    this.pattern = "base pattern";
  }

  // Methods
  @Override
  public String toString() {
    return "Ocelot{" +
      "pattern='" + pattern + '\'' +
      ", name='" + name + '\'' +
      ", numLegs=" + numLegs +
      ", age=" + age +
      '}';
  }

  public void pounce() {
    System.out.println("Your ocelot named " + this.name + " has pounced!");
  }

  @Override
  public void makeNoise() {
    // super.makeNoise();
    System.out.println("Meow!");
  }

  public void makeNoise(String somethingToSay) {
    this.makeNoise();
    System.out.println("And another thing " + somethingToSay + "!");
  }

  // Getters and Setters
  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }
}
