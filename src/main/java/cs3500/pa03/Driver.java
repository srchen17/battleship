package cs3500.pa03;

import cs3500.pa03.controller.GameController;
import java.io.InputStreamReader;

/**
 * This is the main driver of this project - a BattleSalvo program.
 */
public class Driver {

  /**
   * Project entry point, runs a game of BattleSalvo.
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    GameController controller = new GameController(new InputStreamReader(System.in));
    controller.runGame();
  }
}