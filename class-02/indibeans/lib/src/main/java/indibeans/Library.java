/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package indibeans;

import java.util.ArrayList;

public class Library {
    public boolean someLibraryMethod() {
        return true;
    }

    public int returnPassedInNumber(int number) {
      return number;
    }

    public void myArrayIterator() {
      int[] myArray = new int[5];
      for(int i = 0; i < myArray.length; i++) {

      }

      ArrayList<Integer> myArrayList = new ArrayList<>();
      myArrayList.add(6);
      System.out.println(myArrayList.get(0));
    }
}
