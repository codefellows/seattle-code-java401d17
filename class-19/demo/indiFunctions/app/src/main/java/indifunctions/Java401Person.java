package indifunctions;

public class Java401Person implements Comparable<Java401Person> {
  String name;
  boolean isTired;
  int numOfPets;
  int numOfCoffees;
  public String[] petName;
  boolean isCurrentlyConfused;
  String hobby;
  boolean isSmart;

  public Java401Person(String name, boolean isTired, int numOfPets, int numOfCoffees, String[] petName, boolean isCurrentlyConfused, String hobby, boolean isSmart) {
    this.name = name;
    this.isTired = isTired;
    this.numOfPets = numOfPets;
    this.numOfCoffees = numOfCoffees;
    this.petName = petName;
    this.isCurrentlyConfused = isCurrentlyConfused;
    this.hobby = hobby;
    this.isSmart = isSmart;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public int compareTo(Java401Person o) {
    return this.name.compareTo(o.name);
  }
}
