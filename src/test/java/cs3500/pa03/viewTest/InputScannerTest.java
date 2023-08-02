package cs3500.pa03.viewTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.view.InputScanner;
import java.io.StringReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests and examples for the class InputScanner
 */
public class InputScannerTest {
  Readable stringReadable;
  InputScanner scanner;

  /**
   * Set up examples for InputScanner tests
   */
  @BeforeEach
  public void setup() {
    stringReadable = new StringReader("");
    scanner = new InputScanner(stringReadable);
  }

  /**
   * Test the readNextToken method will properly read user inputs separated
   * by spaces
   */
  @Test
  public void testReadNextToken() {
    assertEquals(scanner.readNextToken(), "");

    // Try with reading an int
    stringReadable = new StringReader("5");
    scanner = new InputScanner(stringReadable);
    assertEquals(scanner.readNextToken(), "5");

    // Try with reading an int with spaces
    stringReadable = new StringReader(" 5 ");
    scanner = new InputScanner(stringReadable);
    assertEquals(scanner.readNextToken(), "5");

    // Try with reading several ints with spaces
    stringReadable = new StringReader(" 5 7 8 \n91  ");

    scanner = new InputScanner(stringReadable);
    assertEquals(scanner.readNextToken(), "5");
    assertEquals(scanner.readNextToken(), "7");
    assertEquals(scanner.readNextToken(), "8");
    assertEquals(scanner.readNextToken(), "91");

    // Try with reading a word with spaces
    stringReadable = new StringReader(" hello!  ");

    scanner = new InputScanner(stringReadable);
    assertEquals(scanner.readNextToken(), "hello!");
  }
}
