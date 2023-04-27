package swapi;

public class StarWarsPersonProperties {
  String name;
  String hair_color;
  String birth_year;
  String height;

  public StarWarsPersonProperties(String name, String hair_color, String birth_year, String height) {
    this.name = name;
    this.hair_color = hair_color;
    this.birth_year = birth_year;
    this.height = height;
  }

  @Override
  public String toString() {
    return "StarWarsPersonProperties{" +
      "name='" + name + '\'' +
      ", hair_color='" + hair_color + '\'' +
      ", birth_year='" + birth_year + '\'' +
      ", height='" + height + '\'' +
      '}';
  }
}
