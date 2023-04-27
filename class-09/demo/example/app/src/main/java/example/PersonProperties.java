package example;

public class PersonProperties {
  String name;
  String gender;
  String birth_year;

  public PersonProperties(String name, String gender, String birth_year) {
    this.name = name;
    this.gender = gender;
    this.birth_year = birth_year;
  }

  @Override
  public String toString() {
    return "PersonProperties{" +
      "name='" + name + '\'' +
      ", gender='" + gender + '\'' +
      ", birth_year='" + birth_year + '\'' +
      '}';
  }
}
