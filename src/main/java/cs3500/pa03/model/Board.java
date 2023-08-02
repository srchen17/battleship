package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a board in a game of BattleSalvo.
 */
public class Board {
  private Coord[][] coords;
  private final ArrayList<Ship> fleet;
  private final Random rand;
  private int height;
  private int width;

  /**
   * Initializes a board with fields as null, to be updated later on.
   */
  public Board() {
    this(new Random());
  }

  /**
   * Initializes a board with fields as null, to be updated later on, and
   * receives a random object for testing.
   */
  public Board(Random rand) {
    this.coords = null;
    this.fleet = new ArrayList<>();
    this.rand = rand;
  }

  /**
   * Sets up the Coords of a BattleSalvo board, filling it with
   * new, default Coords.
   *
   * @param height  The height of the board
   * @param width  The width of the board
   */
  public void setUpCoords(int height, int width) {
    this.height = height;
    this.width = width;
    this.coords = new BattleshipCoord[height][width];
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        coords[r][c] = new BattleshipCoord(r, c);
      }
    }
  }

  /**
   * Sets up ships on this board, given the type of ship.
   *
   * @param type  The type of the ship
   * @param numberOfShips  The number of ships needed to be set up
   * @return  A list of the newly generated ships.
   */
  public List<Ship> setUpShips(ShipType type, int numberOfShips) {
    ArrayList<Ship> ships = new ArrayList<>();
    for (int i = 0; i < numberOfShips; i++) {
      ships.add(this.placeShip(type));
    }
    this.fleet.addAll(ships);
    return ships;
  }

  /**
   * Generates a Ship randomly on this board.
   *
   * @param type  The type of ship
   * @return  The newly generated Ship.
   */
  private Ship placeShip(ShipType type) {
    boolean foundValidLocation = false;
    ArrayList<Coord> coords = new ArrayList<>();
    int shipLength = type.returnLength();
    while (!foundValidLocation) {
      Coord randomCoord = this.generateRandomCoord();
      int row = randomCoord.getRow();
      int column =  randomCoord.getColumn();
      boolean horizontal = this.rand.nextBoolean();
      coords.clear();
      try {
        foundValidLocation = true;
        for (int i = 0; i < shipLength; i++) {
          Coord c = this.getCoord(row, column);
          if (horizontal) {
            column = column + 1;
          } else {
            row = row + 1;
          }
          if (c.isOccupied()) {
            foundValidLocation = false;
          } else {
            coords.add(c);
          }
        }
      } catch (IndexOutOfBoundsException e) {
        foundValidLocation = false;
      }
    }
    for (Coord c : coords) {
      c.setOccupied();
    }
    return new Ship(coords);
  }


  /**
   * Validates whether the given dimensions are a valid board size.
   *
   * @param height  The inputted height.
   * @param width  The inputted width.
   * @return  Whether they are valid.
   */
  public boolean checkValidBoardSize(int height, int width) {
    return (height <= 15) && (height >= 6)
        && (width <= 15) && (width >= 6);
  }

  /**
   * Validates whether the given fleet numbers for this board are valid.
   *
   * @param carriers  The inputted number of carriers
   * @param battleships The inputted number of battleships
   * @param destroyers The inputted number of destroyers
   * @param submarines The inputted number of submarines
   * @param smallerBoardDim  The smaller board dimension, maximum fleet size.
   * @return  Whether the given fleet is allowed.
   */
  public boolean checkValidBoardFleet(int carriers, int battleships, int destroyers, int submarines,
                                      int smallerBoardDim) {
    return (carriers >= 1)
        && (battleships >= 1)
        && (destroyers >= 1)
        && (submarines >= 1)
        && ((carriers + battleships + destroyers + submarines) <= smallerBoardDim);
  }

  /**
   * Whether the given shot on this board is valid.
   *
   * @param row  The row of the shot
   * @param column  The column of the shot
   * @return  Whether it is valid (in bounds)
   */
  public boolean checkValidBoardShot(int row, int column) {
    return (row < coords.length) && (row >= 0)
        && (column < coords[0].length) && (column >= 0);
  }

  /**
   * Gets the Coord of the board.
   *
   * @param row  The row of the desired Coord
   * @param column  The column of the desired Coord
   * @return  The desired Coord.
   */
  public Coord getCoord(int row, int column) {
    return this.coords[row][column];
  }

  /**
   * Returns a String representation of this board.
   *
   * @return  A String representation of the ships on this board.
   */
  public String getBoardStringRepresentation() {
    StringBuilder output = new StringBuilder();
    for (Coord[] row : this.coords) {
      for (Coord c : row) {
        if (!(c.getColumn() == width - 1)) {
          // if not last in row, append coord + space:
          output.append(c.getBoardRepresentation()).append(" ");
        }
        else { // if last in row, append coord + don't append space:
          output.append(c.getBoardRepresentation());
        }
      }
      output.append("\n");
    }
    return output.toString();
  }

  /**
   * Reports the damage done to the Board based on the opponent shots and updates
   * coord states.
   *
   * @param opponentShotsOnBoard  Shots made by opponent
   * @return  A list of Coords that were hit.
   */
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    // new array list of hits
    ArrayList<Coord> hits = new ArrayList<>();
    // check for each opponent shot:
    for (Coord opponentShot : opponentShotsOnBoard) {
      Coord targetedCoord = this.coords[opponentShot.getRow()][opponentShot.getColumn()];
      // add targeted coord to list if it is hit, and update
      // its state in this board.
      if (targetedCoord.isOccupied()) {
        targetedCoord.updateAfterTargeted(true);
        hits.add(targetedCoord);
      }
      else {
        targetedCoord.updateAfterTargeted(false);
      }
    }
    return hits;
  }


  /**
   * Generates a random coord on this board.
   *
   * @return  The random Coord
   */
  public Coord generateRandomCoord() {
    int row = this.rand.nextInt(height);
    int column = this.rand.nextInt(width);
    return new BattleshipCoord(row, column);
  }

  /**
   * Counts remaining ships on board
   *
   * @return  int, the number of remaining ships
   */
  public int countRemainingShips() {
    int count  = 0;
    for (Ship s : this.fleet) {
      if (s.notSunk()) {
        count = count + 1;
      }
    }
    return count;
  }


  /**
   * Counts the amount of unguessed Coords on this board
   *
   * @return  an int, a count of unguessed Coords
   */
  public int countUnguessedCoords() {
    int count = 0;
    for (Coord[] row : this.coords) {
      for (Coord c : row) {
        if (c.getState() == CoordState.UNTOUCHED) {
          count++;
        }
      }
    }
    return count;
  }

  /**
   * Whether this board has lost
   *
   * @return  boolean, if all the ships are sunk
   */
  public boolean gameEnded() {
    boolean allShipsSunk = true;
    for (Ship s : this.fleet) {
      if (s.notSunk()) {
        allShipsSunk = false;
      }
    }
    return allShipsSunk;
  }
}
