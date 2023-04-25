package pepperspalace.animal;

public class Sloth extends Animal implements Domesticated{
  private float nailLength;

//   Constructor
  public Sloth(String name, int numLegs, int age, float nailLength) {
    super(name, numLegs, age);
    this.nailLength = nailLength;
  }

//   method
  public String move() {
    return "on my way, I'll get there eventually";
  }

  // Interfaced Methods
  @Override
  public String sit() {
    return this.getName() + " was already sitting, they're a sloth";
  }

  @Override
  public String backFlip() {
    return "sure but its gonna take a while";
  }

  @Override
  public String rollOver() {
    return "No";
  }


//   getter and setter
  public float getNailLength() {
    return nailLength;
  }

  public void setNailLength(float nailLength) {
    this.nailLength = nailLength;
  }
}
