package cs3500.pa03.view;

import cs3500.pa03.controller.GameController;
import java.io.IOException;


/**
 * Displays a game of BattleSalvo.
 */
public class BattleshipDisplay implements ElementDisplay {
  Appendable output;
  GameController controller;

  /**
   * Instantiates a Battleship display given a controller and output to append to.
   *
   * @param controller  The controller of the game
   * @param output  The output for display
   */
  public BattleshipDisplay(GameController controller, Appendable output) {
    this.controller = controller;
    this.output = output;
  }

  /**
   * Displays a board given a String representation of a board.
   *
   * @param boardView  A String representation of a board
   */
  @Override
  public void displayBoard(String boardView) {
    this.outputMessage(boardView);
  }

  /**
   * Signals to a user that they have inputted an invalid input.
   *
   * @param desiredInput  The desired input the control seeks.
   */
  @Override
  public void signalInvalidInput(String desiredInput) {
    this.outputMessage("You've entered an invalid input. "
        + "\nPlease enter " + desiredInput + " below!");
  }

  /**
   * Prompts a user for a specific input
   *
   * @param instructions  The instructions for the input
   */
  @Override
  public void promptUserInput(String instructions) {
    this.outputMessage("Enter " + instructions);
  }

  /**
   * Welcomes the user to a game of BattleSalvo
   */
  @Override
  public void welcomeUser() {
    this.outputMessage("Hello & Welcome to BattleSalvo!");
  }

  /**
   * Displays the ending of a game of BattleSalvo
   *
   * @param reason  The reason for the game ending.
   */
  @Override
  public void displayEnding(String reason) {
    this.outputMessage(reason);
  }

  /**
   * Displays a general game message/signal to the user
   *
   * @param message  The message to be shown
   */
  @Override
  public void displayGameMessage(String message) {
    this.outputMessage(message);
  }

  /**
   * Outputs a message and handles any exceptions
   *
   * @param message  The message needed to be appended to the output
   */
  private void outputMessage(String message) {
    try {
      this.output.append(message).append("\n");
    } catch (IOException e) {
      System.err.println("Unable to append to output.");
      throw new RuntimeException(e);
    }
  }

  /**
   * Displays a divider to the user.
   */
  @Override
  public void displayDivider() {
    this.outputMessage("------------------------------------------------------------------------");
  }

}
