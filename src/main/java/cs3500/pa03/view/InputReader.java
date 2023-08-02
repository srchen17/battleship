package cs3500.pa03.view;

/**
 * Reads the input of a given source.
 */
public interface InputReader {

  /**
   * Reads the next token of a given source
   *
   * @return  The next token of a source, as a String (separated by spaces)
   */
  String readNextToken();
}
