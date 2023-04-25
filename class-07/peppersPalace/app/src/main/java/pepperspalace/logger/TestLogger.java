package pepperspalace.logger;

import java.util.ArrayList;
import java.util.List;

public class TestLogger implements Logger {
  public List<String> logs = new ArrayList<>();
  public void log(String message) {
    logs.add(message);
  }
}
