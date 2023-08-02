package cs3500.pa03.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.controller.CoordData;
import cs3500.pa03.model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the class CoordData
 */
public class CoordDataTest {
  Board basicBoard;
  CoordData origin;
  CoordData middleOfBoard;
  CoordData boardCorner;

  /**
   * Set up examples for CoordData tests
   */
  @BeforeEach
  public void setup() {
    basicBoard = new Board();
    basicBoard.setUpCoords(5, 5);

    origin = new CoordData(basicBoard.getCoord(0,0));
    middleOfBoard = new CoordData(basicBoard.getCoord(2,2));
    boardCorner = new CoordData(basicBoard.getCoord(4,4));
  }

  /**
   * Test the method readData in the class CoordData
   */
  @Test
  public void testReadData() {
    // test that data of the coord is returned correctly as a String.
    assertEquals(origin.readData(), "0,0");
    assertEquals(middleOfBoard.readData(), "2,2");
    assertEquals(boardCorner.readData(), "4,4");
  }
}
