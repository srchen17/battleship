package cs3500.pa03.model;

import java.util.ArrayList;

/**
 * Represents a ship in a game of BattleSalvo.
 */
public class Ship {

  private final ArrayList<Coord> coords;

  /**
   * Instantiates a ship given a ship type and the coords it covers.
   *
   * @param coords  The coordinates it covers
   */
  public Ship(ArrayList<Coord> coords) {
    this.coords = coords;
  }

  /**
   * Returns whether this ship is sunk.
   *
   * @return a boolean, if all of this ship's coords have been hit.
   */
  public boolean notSunk() {
    boolean sunk = true;
    for (Coord c : coords) {
      if (!(c.getState() == CoordState.HIT)) {
        sunk = false;
      }
    }
    return !sunk;
  }

}
