package example;

public class RonQuote {
  String[] quote;

  public RonQuote(String[] quote) {
    this.quote = quote;
  }

  @Override
  public String toString() {
    return "RonQuote{" +
      "quote='" + quote[0] + '\'' +
      '}';
  }
}
