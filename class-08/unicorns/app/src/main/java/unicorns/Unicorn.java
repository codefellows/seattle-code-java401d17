package unicorns;

import java.util.ArrayList;

public class Unicorn {
  Integer hooves;
  Integer hornLength;
  boolean isMajestic;
  ArrayList<String> colors;

  public Unicorn(Integer hooves, Integer hornLength, boolean isMajestic, ArrayList<String> colors) {
    this.hooves = hooves;
    this.hornLength = hornLength;
    this.isMajestic = isMajestic;
    this.colors = colors;
  }

  public void fly() {
    System.out.println("I'm flying");
  }

  public void stab() {
    System.out.println("I stab");
  }

  public void laserBeam() {
    System.out.println("pew pew pew!");
  }

  @Override
  public String toString() {
    return "Unicorn{" +
      "hooves=" + hooves +
      ", hornLength=" + hornLength +
      ", isMajestic=" + isMajestic +
      ", colors=" + colors +
      '}';
  }
}
