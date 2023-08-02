package cs3500.pa03.model;

/**
 * Represents a type of ship: carrier, battleship, destroyer, or submarine.
 */
public enum ShipType {
  CARRIER(6),
  BATTLESHIP(5),
  DESTROYER(4),
  SUBMARINE(3);

  // Represents the length of a type of ship.
  private final int length;

  /**
   * Given a length, creates a ship type.
   *
   * @param length  The length of the ship type.
   */
  ShipType(int length) {
    this.length = length;
  }

  /**
   * Returns the length of a type of ship.
   *
   * @return  The length of this type of ship.
   */
  public int returnLength() {
    return length;
  }

}
