package cs3500.pa03.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.model.BattleshipCoord;
import cs3500.pa03.model.CoordState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests and examples for the class BattleshipCoordTest
 */
public class BattleshipCoordTest {
  BattleshipCoord origin;
  BattleshipCoord middle;
  BattleshipCoord corner;

  /**
   * Set up examples before each test
   */
  @BeforeEach
  public void setup() {
    // On a 6x6 Board
    origin = new BattleshipCoord(0,0 );
    middle = new BattleshipCoord(2,3);
    corner = new BattleshipCoord(5,5);
  }

  /**
   * Test the method setOccupied in the class BattleshipCoord for occupied + unoccupied coords
   */
  @Test
  public void testSetOccupied() {
    // show they are not occupied
    assertFalse(origin.isOccupied());
    assertFalse(middle.isOccupied());
    assertFalse(corner.isOccupied());

    // call setOccupied
    origin.setOccupied();
    middle.setOccupied();
    corner.setOccupied();

    // test that now they are occupied after calling method
    assertTrue(origin.isOccupied());
    assertTrue(middle.isOccupied());
    assertTrue(corner.isOccupied());
  }

  /**
   * Test the method isOccupied in the class BattleshipCoord for occupied + unoccupied coords
   */
  @Test
  public void testIsOccupied() {
    // sets origin, middle, corner as occupied, but 'unoccupied' coord
    // is unoccupied after being constructed:
    BattleshipCoord unoccupied = new BattleshipCoord(0,0);
    origin.setOccupied();
    middle.setOccupied();
    corner.setOccupied();
    // test that isOccupied prodcues accurate results:
    assertTrue(origin.isOccupied());
    assertTrue(middle.isOccupied());
    assertTrue(corner.isOccupied());
    assertFalse(unoccupied.isOccupied());
  }

  /**
   * Test the method getState in the class BattleshipCoord for hit/missed/untouched coords
   */
  @Test
  public void testGetState() {
    // origin, middle, and corner are untouched:
    assertEquals(origin.getState(), CoordState.UNTOUCHED);
    assertEquals(middle.getState(), CoordState.UNTOUCHED);
    assertEquals(corner.getState(), CoordState.UNTOUCHED);

    // set origin as hit
    origin.updateAfterTargeted(true);
    // set middle as missed
    middle.updateAfterTargeted(false);

    // test origin is hit, middle is missed, corner is untouched
    assertEquals(origin.getState(), CoordState.HIT);
    assertEquals(middle.getState(), CoordState.MISSED);
    assertEquals(corner.getState(), CoordState.UNTOUCHED);
  }


  /**
   * Test the method getBoardRepresentation in the class BattleshipCoord
   * for Coords of different states
   */
  @Test
  public void testGetBoardRepresentation() {
    // update origin as OCCUPIED with a ship
    origin.setOccupied();

    // check that since origin is OCCUPIED & UNTOUCHED, returns S
    assertEquals(origin.getBoardRepresentation(), "S");

    // set origin as hit
    origin.updateAfterTargeted(true);
    // set middle as missed
    middle.updateAfterTargeted(false);

    // origin is hit, middle is missed, corner is untouched
    // test that origin returns H, middle returns M, corner returns +
    assertEquals(origin.getBoardRepresentation(), "H");
    assertEquals(middle.getBoardRepresentation(), "M");
    assertEquals(corner.getBoardRepresentation(), "+");
  }

  /**
   * Test the method getRow in the class BattleshipCoord
   */
  @Test
  public void testGetRow() {
    assertEquals(origin.getRow(), 0);
    assertEquals(middle.getRow(), 2);
    assertEquals(corner.getRow(), 5);
  }


  /**
   * Test the method getColumn in the class BattleshipCoord
   */
  @Test
  public void testGetColumn() {
    assertEquals(origin.getColumn(), 0);
    assertEquals(middle.getColumn(), 3);
    assertEquals(corner.getColumn(), 5);
  }


  /**
   * Test the method updateAfterTargeted in the class BattleshipCoord
   * for updating hit and missed coords
   */
  @Test
  public void updateAfterTargeted() {
    // origin, middle, and corner begin as untouched when constructed

    // update origin as HIT, middle as MISSED
    origin.updateAfterTargeted(true);
    middle.updateAfterTargeted(false);

    // test that states are changed:
    // origin is hit, middle is missed, corner is untouched
    assertEquals(origin.getState(), CoordState.HIT);
    assertEquals(middle.getState(), CoordState.MISSED);
    assertEquals(corner.getState(), CoordState.UNTOUCHED);
  }
}
