/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package indibeans;


import java.util.ArrayList;

public class Library {
    public boolean someLibraryMethod() {
        return true;
    }

    // return the number that was passed in
    public int returnPassedNumber(int number) {
      // do whatever logic is expected after proving a failed test
      return number;
    }

    public int loopedThroughLists() {
      int[] myArray = new int[5];
      myArray[0] = 10;
      myArray[1] = 15;
      myArray[2] = 20;
      myArray[3] = 25;
      myArray[4] = 30;
      for(int i = 0; i < myArray.length; i++) {
        System.out.println(myArray[i]);
      }

      ArrayList<Integer> myArrayList = new ArrayList<>(5);
      myArrayList.add(8);
      myArrayList.add(12);
      myArrayList.add(13);
      myArrayList.add(20);
      myArrayList.add(26);
      for(int i = 0; i < myArrayList.size(); i++) {
        System.out.println(myArrayList.get(i));
      }

      return myArrayList.size();
    }
}
