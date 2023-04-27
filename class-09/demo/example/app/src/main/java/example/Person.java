package example;

public class Person {
  String message;
  PersonResult result;

  public Person(String message, PersonResult result) {
    this.message = message;
    this.result = result;
  }

  @Override
  public String toString() {
    return "Person{" +
      " message=" + message +
      " result=" + result +
      '}';
  }
}
