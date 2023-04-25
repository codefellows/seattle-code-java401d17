/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pepperspalace;

import org.junit.jupiter.api.Test;
import pepperspalace.logger.TestLogger;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }

    @Test void testMyTestLogger() {
      TestLogger testLogger = new TestLogger();
      App.doAThing("test from tester", testLogger);
      assertEquals("test from tester", testLogger.logs.get(0));
    }

    @Test void testExample() {
      int myint = 8;
      assertThrows(IllegalArgumentException.class, () -> {
        App.example(myint);
      });
    }
}
