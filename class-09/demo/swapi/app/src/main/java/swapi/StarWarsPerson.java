package swapi;

public class StarWarsPerson {
  String message;
  StarWarsPersonResult result;

  public StarWarsPerson(String message, StarWarsPersonResult result) {
    this.message = message;
    this.result = result;
  }

  @Override
  public String toString() {
    return "StarWarsPerson{" +
      "message='" + message + '\'' +
      ", result=" + result +
      '}';
  }
}
