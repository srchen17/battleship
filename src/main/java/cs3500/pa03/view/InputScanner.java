package cs3500.pa03.view;

import java.util.Scanner;

/**
 * Scans the input of a given source in the view of a game of BattleSalvo.
 */
public class InputScanner implements InputReader {
  Scanner scanner;

  /**
   * Instantiates an input scanner given a readable input source.
   *
   * @param readable  An input source
   */
  public InputScanner(Readable readable) {
    this.scanner = new Scanner(readable);
  }

  /**
   * Reads the next token of a given source
   *
   * @return  The next token of a source, as a String (separated by spaces)
   */
  @Override
  public String readNextToken() {
    StringBuilder output = new StringBuilder();
    if (this.scanner.hasNext()) {
      output.append(this.scanner.next());
    }
    return output.toString();
  }
}


