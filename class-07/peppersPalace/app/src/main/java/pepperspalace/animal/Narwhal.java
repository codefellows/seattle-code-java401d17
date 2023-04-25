package pepperspalace.animal;

public class Narwhal extends Animal {
  private float hornLength;

//   constructor
  public Narwhal(String name, int numLegs, int age, float hornLength) {
    super(name, numLegs, age);
    this.hornLength = hornLength;
  }

//   method
  public String stab(){
    return "oh no " + this.getName() + " stabbed someone again";
  }


//   getter and setter
  public float getHornLength() {
    return hornLength;
  }

  public void setHornLength(float hornLength) {
    this.hornLength = hornLength;
  }
}
