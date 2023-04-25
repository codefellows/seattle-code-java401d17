package pepperspalace.animal;

public class Ocelot extends Animal implements Domesticated{
  private String pattern;

  // Constructor
  public Ocelot(String name, int numLegs, int age, String pattern) {
    super(name, numLegs, age);
    this.pattern = pattern;
  }

  // Methods
  public String pounce() {
    return this.getName() + " has jumped!";
  }

  // Interfaced Methods
  @Override
  public String sit() {
    return this.getName() + " has sat and now they want a treat";
  }

  @Override
  public String backFlip() {
    return this.getName() + " is nimble and did a perfect tuck";
  }

  @Override
  public String rollOver() {
    return this.getName() + " rolled right over";
  }

  // Getters and Setters
  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }
}
