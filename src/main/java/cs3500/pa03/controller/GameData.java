package cs3500.pa03.controller;


/**
 * Represents data about the game that is passed from the
 * model to the controller.
 */
public class GameData {

  // Whether the game is showing the shots
  private boolean showShots;

  /**
   * Instantiates a piece of game control data, with the
   * default as the game not yet ended and not currently showing shots.
   */
  public GameData() {
    this.showShots = false;
  }

  /**
   * Allows the controller to know when the game should show
   * exchanged shots, updates the game to be now showing hits/misses for shots.
   */
  public void showShots() {
    this.showShots = true;
  }

  /**
   * Returns whether the game should be currently showing shots.
   *
   * @return  boolean, Whether the game should be showing exchanged shots.
   */
  public boolean showingShots() {
    return this.showShots;
  }

}
