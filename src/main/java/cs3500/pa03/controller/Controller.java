package cs3500.pa03.controller;

import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.ShipType;
import java.util.HashMap;

/**
 * Represents a controller in a game of BattleSalvo.
 */
public interface Controller {

  /**
   * Runs a game of BattleSalvo.
   */
  void runGame();

  /**
   * Welcomes the user by: showing a message in BattleSalvo and prompting them for
   * a valid width and height of a board.
   */
  void welcomeUser();

  /**
   * Prompts the user to enter a valid fleet for a game.
   *
   * @param height  The height of the board, used for max fleet size
   * @param width  The width of the board, used for max fleet size
   */
  void promptValidFleet(int height, int width);

  /**
   * Sets up the two players in BattleSalvo.
   *
   * @param height  The height of the board, to generate a player's ships and coords
   * @param width  The width of the board, to generate a player's ships and coords
   * @param specifications  The specifications of ship types in the players' fleets.
   */
  void setupPlayers(int height, int width,  HashMap<ShipType, Integer> specifications);

  /**
   * Runs the SalvoStage, where players exchange shots and view updated boards.
   */
  void salvoStage();

  /**
   * Ends a game of BattleSalvo and displays the ending to the user.
   *
   * @param result  The result of the game.
   */
  void endGame(GameResult result);
}
