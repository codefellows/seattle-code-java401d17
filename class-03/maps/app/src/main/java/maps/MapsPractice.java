package maps;

import java.util.ArrayList;
// import java.util.Date;

// impost static package (used in JUnit tests quite a bit)
import static java.lang.System.*;

public class MapsPractice {
  private static ArrayList<String> testArrayList = new ArrayList<>();

  int[] myIntArray = new int[8];


  public static void something() {
    out.println("some stuff");
  }

  java.util.Date myDate = new java.util.Date();


  private void somethingElse() {
    out.println("Some other stuff");
  }
}
