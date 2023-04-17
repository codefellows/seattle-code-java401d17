import java.util.Random;
import java.util.Arrays;
import java.util.stream.IntStream;

// filename and class name MUST match
public class Basics {
  public static void main(String[] args) {
    System.out.println("Hello World!");

    int int1 = 5;
    long long1 = 100;
    byte byte1 = (byte)0xFF;
    short short1 = (short)0xFFFF;

    float float1 = 0.1f;
    float float2 = 0.2f;
    float float3 = float1 + float2;
    boolean booleanFloat = (float3 == 0.3f);
    System.out.println("booleanFloat: " + booleanFloat);
    System.out.println("float3: " + float3);

    double double1 = 0.1;
    double double2 = 0.2;
    double double3 = double1 + double2;
    boolean booleanDouble = (double3 == 0.3);  // false!
    System.out.println("booleanDouble: " + booleanDouble);
    System.out.println("double3: " + double3);

    char char1 = 'a';
    char char2 = 'b';
    System.out.println("char1: " + char1);
    System.out.println(char1 + char2);

    int maxInt = Integer.MAX_VALUE;
    System.out.println("Maximum integer is: " + maxInt);
    int minInt = Integer.MIN_VALUE;
    System.out.println("Mininum integer is: " + minInt);

    Random rand = new Random();
    Double DoubleRandom1 = rand.nextDouble();
    double doubleRandom2 = rand.nextDouble();
    System.out.println("DoubleRandom1: " + DoubleRandom1 + " doubleRandom2: " + doubleRandom2);

    Integer IntegerRandom2 = rand.nextInt(100);
    int intRandom1 = rand.nextInt();
    System.out.println("intRandom1: " + intRandom1 + " IntegerRandomRandom2: " + IntegerRandom2);

    int int2 = 20;
    String numberString1 = "30";
    System.out.println("int2 + numberString1: " + (int2 + numberString1));
    System.out.println("int2 + Integer.fromString(numberString1): " + (int2 + Integer.valueOf(numberString1)));

    String hello = "hello";
    String hel = "hel";
    String lo = "lo";

    System.out.println("\"hello\" == \"hel\" + \"lo\": " + ("hello" == ("hel" + "lo")));
    System.out.println("\"hello\" == hel + lo: " + ("hello" == (hel + lo)));
    System.out.println("hello == \"hel\" + \"lo\": " + (hello == ("hel" + "lo")));
    System.out.println("hello == hel + lo: " + (hello == (hel + lo)));

    System.out.println("\"hello\".equals(\"hel\" + \"lo\"): " + ("hello".equals("hel" + "lo")));
    System.out.println("\"hello\".equals(hel + lo): " + ("hello".equals(hel + lo)));
    System.out.println("hello.equals(\"hel\" + \"lo\"): " + (hello.equals("hel" + "lo")));
    System.out.println("hello.equals(hel + lo): " + (hello.equals(hel + lo)));

    int number1 = 7;
    int number2 = 10;
    if(number1 > number2) {
      // condition 1
      System.out.println(number2 + "is less than " + number1);
    } else if(number1 == number2) {
      System.out.println("The numbers are equal");
    } else {
      System.out.println(number2 + " is greater than " + number1);
    }

    int month = 8;
    String monthString;
    switch (month) {
      case 1: monthString = "January";
        break;
      case 2: monthString = "February";
        break;
      case 3: monthString = "March";
        break;
      case 4: monthString = "April";
        break;
      case 5: monthString = "May";
        break;
      case 6: monthString = "June";
        break;
      default: monthString = "Second half of the year";
        break;
    }

    System.out.println(monthString);

    int counter = 0;
    while(counter < 5) {
      System.out.println("counter: " + counter);
      counter++;
      if (counter == 3) {
        break;
      }
    }

    // int[] intArray = new int[3];
    int[] intArray = {1, 2, 6};

    for(int i = 0; i < intArray.length; i++) {
      System.out.println("intArray @ " + i + ":" + intArray[i]);
    }

    for(int currentInt : intArray) {
      System.out.println("intArray values :" + currentInt);
    }

    int mySum = sum(5, 10);
    System.out.println(mySum);
  }

  // Modifier Modifier ReturnType NameOfFunction(ParamType Param1Name, ParamType Param2Name, etc..) {//LOGIC}
  public static int sum(int a, int b) {
    int sum = a + b;
    return sum;
  }
}
