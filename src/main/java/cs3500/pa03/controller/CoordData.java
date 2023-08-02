package cs3500.pa03.controller;

import cs3500.pa03.model.Coord;

/**
 * Represents a Coord that a user inputted into the program.
 */
public class CoordData implements ManualUserInput {
  // Field holding a Coord.
  private final Coord coord;

  /**
   * Instantiates a piece of Coord input data.
   *
   * @param coord  The coordinate inputted by the user.
   */
  public CoordData(Coord coord) {
    this.coord = coord;
  }

  /**
   * Reads the data in this piece of Coord data and returns it as a String.
   *
   * @return String, the Coord in the format x,y.
   */
  public String readData() {
    return this.coord.getRow() + "," + this.coord.getColumn();
  }
}
