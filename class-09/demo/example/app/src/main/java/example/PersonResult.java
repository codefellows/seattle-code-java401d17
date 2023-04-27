package example;

public class PersonResult {
  PersonProperties properties;

  public PersonResult(PersonProperties properties) {
    this.properties = properties;
  }

  @Override
  public String toString() {
    return "PersonResult{" +
      "properties=" + properties +
      '}';
  }
}
