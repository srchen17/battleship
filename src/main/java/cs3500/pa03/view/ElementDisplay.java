package cs3500.pa03.view;

/**
 * Displays the different elements of a game.
 */
public interface ElementDisplay {

  /**
   * Displays a board given a String representation of a board.
   *
   * @param boardView  A String representation of a board
   */
  void displayBoard(String boardView);


  /**
   * Signals to a user that they have inputted an invalid input.
   *
   * @param desiredInput  The desired input the control seeks.
   */
  void signalInvalidInput(String desiredInput);

  /**
   * Prompts a user for a specific input
   *
   * @param instructions  The instructions for the input
   */
  void promptUserInput(String instructions);

  /**
   * Welcomes the user to a game
   */
  void welcomeUser();

  /**
   * Displays the ending of a game
   *
   * @param reason  The reason for the game ending.
   */
  void displayEnding(String reason);

  /**
   * Displays a divider to the user.
   */
  void displayDivider();

  /**
   * Displays a general game message/signal to the user
   *
   * @param message  The message to be shown
   */
  void displayGameMessage(String message);

}
