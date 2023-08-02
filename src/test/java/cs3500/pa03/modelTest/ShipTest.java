package cs3500.pa03.modelTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests and examples for the Ship class
 */
public class ShipTest {
  Board basicBoard;
  Ship carrier;
  Ship battleship;
  Ship destroyer;
  Ship submarine;
  ArrayList<Coord> coords;

  /**
   * Set up initial Ship examples for tests
   */
  @BeforeEach
  public void setup() {
    basicBoard = new Board();
    basicBoard.setUpCoords(10, 10);
    // All coords occupied by ships should be set as occupied:
    coords =  new ArrayList<>(Arrays.asList(
        basicBoard.getCoord(0, 1), basicBoard.getCoord(0, 2),
        basicBoard.getCoord(0, 3), basicBoard.getCoord(0, 4),
        basicBoard.getCoord(0, 5), basicBoard.getCoord(0, 6),
        basicBoard.getCoord(1, 1), basicBoard.getCoord(1, 2),
        basicBoard.getCoord(1, 3), basicBoard.getCoord(1, 4),
        basicBoard.getCoord(1, 5), basicBoard.getCoord(5, 0),
        basicBoard.getCoord(6, 0), basicBoard.getCoord(7, 0),
        basicBoard.getCoord(8, 0), basicBoard.getCoord(5, 1),
        basicBoard.getCoord(6, 1), basicBoard.getCoord(7, 1)));
    for (Coord c : coords) {
      c.setOccupied();
    }
    // Initialize ships with coords:
    carrier = new Ship(
        new ArrayList<>(Arrays.asList(
        basicBoard.getCoord(0, 1), basicBoard.getCoord(0, 2),
        basicBoard.getCoord(0, 3), basicBoard.getCoord(0, 4),
        basicBoard.getCoord(0, 5), basicBoard.getCoord(0, 6))));
    battleship = new Ship(
        new ArrayList<>(Arrays.asList(
            basicBoard.getCoord(1, 1), basicBoard.getCoord(1, 2),
            basicBoard.getCoord(1, 3), basicBoard.getCoord(1, 4),
            basicBoard.getCoord(1, 5))));
    destroyer = new Ship(
        new ArrayList<>(Arrays.asList(
            basicBoard.getCoord(5, 0), basicBoard.getCoord(6, 0),
            basicBoard.getCoord(7, 0), basicBoard.getCoord(8, 0))));
    submarine = new Ship(
        new ArrayList<>(Arrays.asList(
            basicBoard.getCoord(5, 1), basicBoard.getCoord(6, 1),
            basicBoard.getCoord(7, 1))));
  }

  @Test
  public void testIsSunk() {
    // test that before any coords are hit, no ships are sunk.
    assertTrue(carrier.notSunk());
    assertTrue(battleship.notSunk());
    assertTrue(destroyer.notSunk());
    assertTrue(submarine.notSunk());

    // test that after some coords are hit, no ships are sunk.
    basicBoard.getCoord(0, 1).updateAfterTargeted(true);
    basicBoard.getCoord(0, 2).updateAfterTargeted(true);
    basicBoard.getCoord(1, 1).updateAfterTargeted(true);
    basicBoard.getCoord(1, 2).updateAfterTargeted(true);
    basicBoard.getCoord(5, 0).updateAfterTargeted(true);
    basicBoard.getCoord(5, 1).updateAfterTargeted(true);

    assertTrue(carrier.notSunk());
    assertTrue(battleship.notSunk());
    assertTrue(destroyer.notSunk());
    assertTrue(submarine.notSunk());

    // test that after all coords are hit, ships are sunk.
    for (Coord c : coords) {
      c.updateAfterTargeted(true);
    }
    assertFalse(carrier.notSunk());
    assertFalse(battleship.notSunk());
    assertFalse(destroyer.notSunk());
    assertFalse(submarine.notSunk());
  }
}
