package cs3500.pa03.model;


/**
 * Represents a coordinate on a BattleSalvo board.
 */
public class BattleshipCoord extends Coord {
  // whether it is occupied by a ship or not.

  /**
   * Instantiates a BattleSalvo coordinate.
   *
   * @param row  The row of the Coord
   * @param column  The column of the Coord
   */
  public BattleshipCoord(int row, int column) {
    super(row, column, false);
    this.state = CoordState.UNTOUCHED;
  }

  /**
   * Returns whether this Coord is occupied by a ship.
   *
   * @return  a boolean, whether this Coord is occupied
   */
  @Override
  public boolean isOccupied() {
    return this.occupied;
  }

  /**
   * Sets this Coord as occupied by a ship.
   */
  @Override
  public void setOccupied() {
    this.occupied = true;
  }

  /**
   * Returns the state of this Coord.
   *
   * @return the state of this Coord, either Hit, Missed, or Untouched.
   */
  @Override
  public CoordState getState() {
    return this.state;
  }

  /**
   * Returns a representation of this Coord on a BattleSalvo board as a String.
   *
   * @return  A String representing this Coord and its state, to be placed on larger board.
   *
   */
  @Override
  public String getBoardRepresentation() {
    if (this.occupied && this.state == CoordState.UNTOUCHED) {
      return "S";
    } else {
      if (!this.occupied && this.state == CoordState.UNTOUCHED) {
        return "+";
      } else {
        return this.state.toString().substring(0, 1);
      }
    }
  }

  /**
   * Returns the row of this Coord.
   *
   * @return an int, the row of this Coord.
   */
  @Override
  public int getRow() {
    return this.row;
  }

  /**
   * Returns the column of this Coord.
   *
   * @return  an int, the column of this Coord.
   */
  @Override
  public int getColumn() {
    return this.column;
  }

  /**
   * Updates the state of an occupied Coord on a board given whether it was hit or missed.
   *
   * @param hit  Whether this Coord was hit or not (missed if not hit).
   */
  @Override
  public void updateAfterTargeted(boolean hit) {
    if (hit) {
      this.state = CoordState.HIT;
    } else {
      this.state = CoordState.MISSED;
    }
  }

}
