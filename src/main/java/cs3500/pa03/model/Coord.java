package cs3500.pa03.model;



/**
 * Represents a coordinate on a board.
 */
public abstract class Coord {
  protected int row;
  protected int column;
  protected boolean occupied;
  protected CoordState state;

  /**
   * Creates a new Coord object given its row, column, and if its occupied.
   *
   * @param row  Row of Coord
   * @param column  Column of Coord
   * @param occupied  If it is occupied by a ship
   */
  Coord(int row, int column, boolean occupied) {
    this.row = row;
    this.column = column;
    this.occupied = occupied;
  }

  /**
   * Returns the row of this Coord.
   *
   * @return an int, the row of this Coord.
   */
  public abstract int getRow();

  /**
   * Returns the column of this Coord.
   *
   * @return  an int, the column of this Coord.
   */
  public abstract int getColumn();

  /**
   * Returns the state of this Coord.
   *
   * @return the state of this Coord, either Hit, Missed, or Untouched.
   */
  public abstract CoordState getState();

  /**
   * Returns whether this Coord is occupied by a ship.
   *
   * @return  a boolean, whether this Coord is occupied
   */
  public abstract boolean isOccupied();

  /**
   * Sets this Coord as occupied by a ship.
   */
  public abstract void setOccupied();

  /**
   * Returns a representation of this Coord on a BattleSalvo board as a String.
   *
   * @return  A String representing this Coord and its state, to be placed on larger board
   *
   */
  public abstract String getBoardRepresentation();

  /**
   * Updates the state of this Coord after shot at/targeted by the
   * opponent.
   *
   * @param hit  Whether this Coord was hit or not (missed if not hit).
   */
  public abstract void updateAfterTargeted(boolean hit);
}
