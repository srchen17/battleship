package cs3500.pa03.viewTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa03.controller.GameController;
import cs3500.pa03.view.BattleshipDisplay;
import cs3500.pa03.view.MockAppendable;
import java.io.StringReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests and examples for the class BattleshipDisplay
 */
public class BattleshipDisplayTest {
  BattleshipDisplay view;
  GameController controller;
  StringBuilder output;


  /**
   * Set up examples for tests for BattleshipDisplay
   */
  @BeforeEach
  public void setup() {
    output = new StringBuilder();
    controller = new GameController(
        new StringReader(""));
    view = new BattleshipDisplay(controller, output);
  }


  /**
   * Test the method displayBoard for displaying boards during
   * BattleSalvo games in the class BattleshipDisplay
   */
  @Test
  public void testDisplayGameBoard() {
    String boardView = """ 
        + M M M + +
        + + + H + +
        + + + H + +
        + + + H + +
        + + + + + +
        + + + + M +
        """;
    view.displayBoard(boardView); // Should display board with an extra line at the end:
    assertEquals(output.toString(),
        """
        + M M M + +
        + + + H + +
        + + + H + +
        + + + H + +
        + + + + + +
        + + + + M +
        
        """);
    boardView = """
        + + + + S +
        + M + H + +
        + S + + + +
        + + + H H +
        + M + S + +
        H + + + M +
        """;
    output.setLength(0);
    view.displayBoard(boardView); // Try displaying a board with ships, hits, and misses:
    assertEquals(output.toString(),
        """
        + + + + S +
        + M + H + +
        + S + + + +
        + + + H H +
        + M + S + +
        H + + + M +
        
        """);
  }


  /**
   * Test the method displayBoard for displaying opponent boards during
   * BattleSalvo games in the class BattleshipDisplay
   */
  @Test
  public void testDisplayOpponentBoard() {
    String boardView = """ 
        + + + + + +
        + + + + + +
        + + + + + +
        + + + + + +
        + + + + + +
        + + + + + +
        """;
    view.displayBoard(boardView); // Should display opponent board w/ no info known:
    assertEquals(output.toString(),
        """
        + + + + + +
        + + + + + +
        + + + + + +
        + + + + + +
        + + + + + +
        + + + + + +
    
        """);
    boardView = """
        + + + + + +
        + + + H + +
        + + H + + +
        + + + H + +
        + + + H + +
        + + M + + +
        """;
    output.setLength(0);
    view.displayBoard(boardView); // Try displaying opponent board with hits & misses mid-game:
    assertEquals(output.toString(),
        """
        + + + + + +
        + + + H + +
        + + H + + +
        + + + H + +
        + + + H + +
        + + M + + +
        
        """);
  }

  /**
   * Test the method signalInvalidInput for signaling
   * invalid characters
   */
  @Test
  public void testSignalInvalidInputCharacters() {
    String desiredInput = "four valid INTEGERS";
    output.setLength(0);
    view.signalInvalidInput(desiredInput); // Should display opponent board w/ no info known:
    assertEquals(output.toString(),
        "You've entered an invalid input. \nPlease enter four valid INTEGERS below!\n");
    output.setLength(0);
    desiredInput = "8 shots in INTEGER form";
    output.setLength(0);
    view.signalInvalidInput(desiredInput); // Should display opponent board w/ no info known:
    assertEquals(output.toString(),
        "You've entered an invalid input. \nPlease enter 8 shots in INTEGER form below!\n");
  }


  /**
   * Test the method signalInvalidInput for signaling
   * invalid data (ex: board params not within bounds)
   */
  @Test
  public void testSignalInvalidInputData() {
    String desiredInput = "two integers from [6,15], inclusive";
    output.setLength(0);
    view.signalInvalidInput(desiredInput); // Should display opponent board w/ no info known:
    assertEquals(output.toString(),
        "You've entered an invalid input. "
            + "\nPlease enter two integers from [6,15], inclusive below!\n");
    output.setLength(0);
    desiredInput = "four valid INTEGERS >=1, with fleet size <= 6";
    output.setLength(0);
    view.signalInvalidInput(desiredInput); // Should display opponent board w/ no info known:
    assertEquals(
        output.toString(), "You've entered an invalid input. "
            + "\nPlease enter four valid INTEGERS >=1, with fleet size <= 6 below!\n");
  }

  /**
   * Test the method promptUserInput for prompting
   * board w/h and number boats
   */
  @Test
  public void testPromptUserInputSetup() {
    String desiredInput = "an integer HEIGHT [6,15] "
        + "& an integer WIDTH [6,15]! (ex: 6 10)";
    view.promptUserInput(desiredInput);
    assertEquals(output.toString(),
        "Enter an integer HEIGHT [6,15] & an integer WIDTH [6,15]! (ex: 6 10)\n");

    desiredInput = "your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
        + "Remember, your fleet may not exceed size 8. \n(ex: 1 1 1 1)";
    output.setLength(0);
    view.promptUserInput(desiredInput);
    assertEquals(output.toString(),
        "Enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
            + "Remember, your fleet may not exceed size 8. \n(ex: 1 1 1 1)\n");
  }

  /**
   * Test the method promptUserInput for prompting
   * shots
   */
  @Test
  public void testPromptUserShots() {
    String desiredInput = "6 shots, row by column! (ex: 1 2)";
    view.promptUserInput(desiredInput);
    assertEquals(output.toString(),
        "Enter 6 shots, row by column! (ex: 1 2)\n");
    output.setLength(0);
    desiredInput = "10 shots, row by column! (ex: 1 2)";
    view.promptUserInput(desiredInput);
    assertEquals(output.toString(),
        "Enter 10 shots, row by column! (ex: 1 2)\n");
  }

  /**
   * Test the method welcomeUser for welcoming
   * a user with a message.
   */
  @Test
  public void testWelcomeUser() {
    view.welcomeUser();
    assertEquals(output.toString(),
        "Hello & Welcome to BattleSalvo!\n");
  }

  /**
   * Test the method welcomeUser for ending a game for
   * a user with a message.
   */
  @Test
  public void testDisplayEnding() {
    String reason = "You've Won! (You beat an 'AI', nice!)";
    view.displayEnding(reason);
    assertEquals(output.toString(),
        "You've Won! (You beat an 'AI', nice!)\n");

    output.setLength(0);
    reason = "You've TIED! (You tied with an 'AI', nice!)";
    view.displayEnding(reason);
    assertEquals(output.toString(),
        "You've TIED! (You tied with an 'AI', nice!)\n");

    output.setLength(0);
    reason = "You've Lost :(";
    view.displayEnding(reason);
    assertEquals(output.toString(),
        "You've Lost :(\n");
  }

  /**
   * Test the method displayGameMessage for displaying a general
   * game message / signal to the user,that may not be a prompt / invalid input.
   */
  @Test
  public void testDisplayGameMessage() {
    String message = "YOUR BOARD:";
    view.displayGameMessage(message);
    assertEquals(output.toString(),
        "YOUR BOARD:\n");

    output.setLength(0);
    message = "YOUR OPPONENT'S BOARD:";
    view.displayGameMessage(message);
    assertEquals(output.toString(),
        "YOUR OPPONENT'S BOARD:\n");
  }

  /**
   * Test the method displayMessage for catching an I/O error
   */
  @Test
  public void testDisplayMessageException() {
    MockAppendable mock = new MockAppendable();
    BattleshipDisplay errorView = new BattleshipDisplay(controller, mock);
    assertThrows(RuntimeException.class,
        () -> errorView.displayGameMessage(""), "Unable to append to output.");
  }

  /**
   * Test the method displayDivider for displaying a divider between
   * messages from the game
   */
  @Test
  public void testDisplayDivider() {
    output.setLength(0);
    view.displayDivider();
    assertEquals(output.toString(),
        "------------------------------------------------------------------------\n");
  }

}
