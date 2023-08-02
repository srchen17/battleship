package cs3500.pa03.view;

import java.io.IOException;

/**
 * Tests that failures are handled for writers
 */
public class MockAppendable implements Appendable {

//  /**
//   * Throws I/O Exception for testing with a message.
//   *
//   * @throws IOException To test that faillures are handled correctly.
//   */
//  private void throwInOut() throws IOException {
//    throw new IOException("Mock error throw");
//  }

  /**
   * Throws error for testing.
   *
   * @param csq The character sequence to append.  If {@code csq} is
   *            {@code null}, then the four characters {@code "null"} are
   *            appended to this Appendable.
   * @return a reference to this Appendable
   * @throws IOException when method is called in testing.
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Mock error thrown for character sequence");
  }

  /**
   *Throws error for testing.
   *
   * @param csq   The character sequence from which a subsequence will be
   *              appended.  If {@code csq} is {@code null}, then characters
   *              will be appended as if {@code csq} contained the four
   *              characters {@code "null"}.
   * @param start The index of the first character in the subsequence
   * @param end   The index of the character following the last character in the
   *              subsequence
   * @return a reference to this Appendable
   * @throws IOException when method is called in testing.
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Mock error thrown for character sequence with start/end");
  }


  /**
   * Throws error reliably for testing.
   *
   * @param c The character to append
   * @return a reference to this Appendable
   * @throws IOException when method is called in testing.
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Mock error thrown for appending char");
  }
}

