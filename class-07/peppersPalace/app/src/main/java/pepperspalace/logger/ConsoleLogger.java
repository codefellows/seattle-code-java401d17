package pepperspalace.logger;

public class ConsoleLogger implements Logger {
  public void log(String message) {
    System.out.println(message);
  }
}
