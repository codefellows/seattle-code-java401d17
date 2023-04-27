package example;

public class ForismaticQuote {
  String quoteText;
  String quoteAuthor;
  String quoteLink;

  public ForismaticQuote(String quoteText, String quoteAuthor, String quoteLink) {
    this.quoteText = quoteText;
    this.quoteAuthor = quoteAuthor;
    this.quoteLink = quoteLink;
  }

  @Override
  public String toString() {
    return "ForismaticQuote{" +
      "quoteText='" + quoteText + '\'' +
      ", quoteAuthor='" + quoteAuthor + '\'' +
      ", quoteLink='" + quoteLink + '\'' +
      '}';
  }
}
