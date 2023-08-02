package cs3500.pa03.viewTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa03.view.MockAppendable;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests and examples for the class MockAppendable
 */
public class MockAppendableTest {
  MockAppendable mock;

  /**
   * Sets up examples before MockAppendable tests
   */
  @BeforeEach
  public void setup() {
    mock = new MockAppendable();
  }

  /**
   * Test the method append(CharSequence csq) in MockAppendable
   */
  @Test
  public void testAppendCharSequence() {
    assertThrows(IOException.class, () -> mock.append(
        "hi"), "Mock error thrown for character sequence");
  }


  /**
   * Test the method append(CharSequence csq, int start, int end) in MockAppendable
   */
  @Test
  public void testAppendCharSequenceWithStartEnd() {
    assertThrows(IOException.class, () -> mock.append(
        "long sentence here", 0, 5),
        "Mock error thrown for character sequence with start/end");
  }

  /**
   * Test the method append(char c) in MockAppendable
   */
  @Test
  public void testAppendChar() {
    assertThrows(IOException.class, () -> mock.append(
        'h'), "Mock error thrown for appending char");
  }
}
