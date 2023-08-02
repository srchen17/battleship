package cs3500.pa03.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.model.BattleshipCoord;
import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.CoordState;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests and examples for the class Board
 */
public class BoardTest {
  Board sixBySix;
  Board sevenByTen;
  Board completelyRandom;

  /**
   * Set up examples for the class Board
   */
  @BeforeEach
  public void setup() {
    sixBySix = new Board(new Random(0));
    sixBySix.setUpCoords(6,6);
    sixBySix.setUpShips(ShipType.CARRIER, 1);
    sixBySix.setUpShips(ShipType.BATTLESHIP, 1);
    sixBySix.setUpShips(ShipType.DESTROYER,1);
    sixBySix.setUpShips(ShipType.SUBMARINE, 1);

    sevenByTen = new Board(new Random(5));
    sevenByTen.setUpCoords(7,10);
    sevenByTen.setUpShips(ShipType.CARRIER, 1);
    sevenByTen.setUpShips(ShipType.BATTLESHIP, 2);
    sevenByTen.setUpShips(ShipType.DESTROYER,3);
    sevenByTen.setUpShips(ShipType.SUBMARINE, 3);

    completelyRandom = new Board();
    completelyRandom.setUpCoords(6,6);
    completelyRandom.setUpShips(ShipType.CARRIER, 1);
    completelyRandom.setUpShips(ShipType.BATTLESHIP, 1);
    completelyRandom.setUpShips(ShipType.DESTROYER,1);
    completelyRandom.setUpShips(ShipType.SUBMARINE, 1);
  }

  /**
   * Test the method setUpCoords: sets up proper amount of
   * coords
   */
  @Test
  public void testSetUpCoords() {
    // Uses Board render to verify tests, since displayBoard is well-tested.

    // test 6x6 board
    Board six = new Board();
    six.setUpCoords(6,6);
    // Visual representation of board:
    String boardView = six.getBoardStringRepresentation();
    // Check that representation contains 6 rows w/ 6 coords each:
    assertEquals(boardView, "+ + + + + +\n".repeat(6));

    // test 8x6 board
    Board eightBySix = new Board();
    eightBySix.setUpCoords(8,6);
    // Visual representation of board:
    boardView = eightBySix.getBoardStringRepresentation();
    // Check that representation contains 8 rows w/ 6 coords each:
    assertEquals(boardView, "+ + + + + +\n".repeat(8));

    // test 10x15 board
    Board tenByFiteen = new Board();
    tenByFiteen.setUpCoords(10,15);
    // Visual representation of board:
    boardView = tenByFiteen.getBoardStringRepresentation();
    // Check that representation contains 10 rows w/ 15 coords each:
    assertEquals(boardView, "+ + + + + + + + + + + + + + +\n".repeat(10));

    // test 15x15 board
    Board fifteenByFifteen = new Board();
    fifteenByFifteen.setUpCoords(15,15);
    // Visual representation of board:
    boardView = fifteenByFifteen.getBoardStringRepresentation();
    // Check that representation contains 15 rows w/ 15 coords each:
    assertEquals(boardView, "+ + + + + + + + + + + + + + +\n".repeat(15));
  }

  /**
   * Test the method setUpShips: test that it creates a list of ships
   * for 6x6 board with 1 1 1 1 ships
   */
  @Test
  public void testSetUpShipsSixBoard() {
    // 6x6 board, 1 1 1 1 ships
    Board six = new Board(new Random(5));
    six.setUpCoords(6,6);
    six.setUpShips(ShipType.CARRIER, 1);
    six.setUpShips(ShipType.BATTLESHIP, 1);
    six.setUpShips(ShipType.DESTROYER,1);
    six.setUpShips(ShipType.SUBMARINE, 1);
    // Visual representation of board:
    String boardView = six.getBoardStringRepresentation();
    // Check that representation contains 6 rows w/ 6 coords each:
    assertEquals(boardView,
        """
            + S S S S +
            S S S S S +
            S S S S S S
            + + + + + +
            S S S + + +
            + + + + + +
            """
    );
    assertEquals(six.countRemainingShips(), 4);
  }

  /**
   * Test the method setUpShips: test that it creates a list of ships
   * for 15x15 board with 4 5 3 3 ships
   */
  @Test
  public void testSetUpShipsFifteenBoard() {
    // 15x15 board, 4 5 3 3 ships
    Board fifteenByFifteen = new Board(new Random(7));
    fifteenByFifteen.setUpCoords(15,15);
    fifteenByFifteen.setUpShips(ShipType.CARRIER, 4);
    fifteenByFifteen.setUpShips(ShipType.BATTLESHIP, 5);
    fifteenByFifteen.setUpShips(ShipType.DESTROYER,3);
    fifteenByFifteen.setUpShips(ShipType.SUBMARINE, 3);
    // Visual representation of board:
    String boardView = fifteenByFifteen.getBoardStringRepresentation();
    // Check that representation contains 15 rows w/ 15 coords each:
    assertEquals(boardView,
        """
            + + + + + + + + + S S S S S +
            + + + + + + + + S + + + + S +
            + + + + + + + + S + + + + S +
            + S S S S S S + S + + + + S +
            + + S S S S + S S + S + + + +
            + + + + + + + S + + S + + + +
            S S S S S + + S + + S + + + +
            + + + + + + + S + + S S S S +
            + + + + + + + + + + S + + + +
            S S S S S S + + + + S + S + +
            + + + + + + S S S S S + S + +
            + + + + + + + + + + + + S + +
            + + + + + + + + + S S S S S S
            + + + + + + S S S S S + + + +
            + + + S S S S S + + + + + + +
            """
    );
    assertEquals(fifteenByFifteen.countRemainingShips(), 15);
  }


  /**
   * Test the method setUpShips: test that it creates a list of ships with
   * no overlaps & correct lengths for rectangle shaped W/H board
   */
  @Test
  public void testSetUpShipsRectangleBoards() {
    // 10x15 board, 1 1 1 1 ships
    Board tenByFiteen = new Board(new Random(6));
    tenByFiteen.setUpCoords(10,15);
    tenByFiteen.setUpShips(ShipType.CARRIER, 1);
    tenByFiteen.setUpShips(ShipType.BATTLESHIP, 1);
    tenByFiteen.setUpShips(ShipType.DESTROYER,1);
    tenByFiteen.setUpShips(ShipType.SUBMARINE, 1);
    // Visual representation of board:
    String boardView = tenByFiteen.getBoardStringRepresentation();
    // Check that representation contains 10 rows w/ 15 coords each:
    assertEquals(boardView, """
        + + + + + + + + + + + + + + +
        + + + + + + S S S S S S + S +
        + + + + + + S S S + + + + S +
        + + + + + + + + + + + + + S +
        + + + + + + + + + + + + + S +
        + + + + + + + + + + + + + + +
        + + + + + + + + + + + + + + +
        + + + + + + + + + + + + + + +
        + S S S S S + + + + + + + + +
        + + + + + + + + + + + + + + +
        """);
    assertEquals(tenByFiteen.countRemainingShips(), 4);
  }

  /**
   * Test the method setUpShips: test that it creates a list of ships with
   * no overlaps & correct lengths, with different numbers of ship types.
   */
  @Test
  public void testSetUpShips() {
    // 10x10 board, 1 1 1 1 ships
    Board ten = new Board(new Random(5));
    ten.setUpCoords(10,10);
    ten.setUpShips(ShipType.CARRIER, 1);
    ten.setUpShips(ShipType.BATTLESHIP, 1);
    ten.setUpShips(ShipType.DESTROYER,1);
    ten.setUpShips(ShipType.SUBMARINE, 1);

    // Visual representation of board:
    String boardView = ten.getBoardStringRepresentation();
    // Check that representation contains 6 rows w/ 6 coords each:
    assertEquals(boardView, """
        + + + + + + + + S +
        + S S S S S + + S +
        + + + + + + + + S +
        + + + + + + + + S +
        + S + + + + + + + +
        + S + + + + + + + +
        + S + + + + + + + +
        + S + + + + + S S S
        + S + + + + + + + +
        + S + + + + + + + +
        """);
    assertEquals(ten.countRemainingShips(), 4);

    // 10x10 board, 2 3 4 5 ships
    ten = new Board(new Random(7));
    ten.setUpCoords(10,10);
    ten.setUpShips(ShipType.CARRIER, 1);
    ten.setUpShips(ShipType.BATTLESHIP, 2);
    ten.setUpShips(ShipType.DESTROYER,3);
    ten.setUpShips(ShipType.SUBMARINE, 4);
    // Visual representation of board:
    boardView = ten.getBoardStringRepresentation();
    assertEquals(boardView, """
        + + + + + + S S S S
        + + + + + S S S + +
        + S S S S + + + + +
        + + + S S S + S S S
        S + + S S S S + + +
        S + + + + + + + + +
        S + + + S S S S S S
        S + + + + + + + + +
        S S S S S S S S S +
        + + + + + + + + + +
        """);
    assertEquals(ten.countRemainingShips(), 10);
  }


  /**
   * Test the method checkValidBoardSize: test that it validates
   * the correct board dimensions
   */
  @Test
  public void testCheckValidBoardSize() {
    // 10x14 is accepted
    assertTrue(sixBySix.checkValidBoardSize(10, 14));
    // 15x15 is accepted
    assertTrue(sixBySix.checkValidBoardSize(15, 15));
    // 6x6 is accepted
    assertTrue(sixBySix.checkValidBoardSize(6, 6));
    // 5x6 is not accepted
    assertFalse(sixBySix.checkValidBoardSize(5, 6));
    // 5x5 is not accepted
    assertFalse(sixBySix.checkValidBoardSize(5, 5));
    // 6x5 is not accepted
    assertFalse(sixBySix.checkValidBoardSize(6, 5));
    // 16x15 is not accepted
    assertFalse(sixBySix.checkValidBoardSize(16, 15));
    // 16x16 is not accepted
    assertFalse(sixBySix.checkValidBoardSize(5, 5));
    // 0x0 is not accepted
    assertFalse(sixBySix.checkValidBoardSize(0, 0));
    // 20x20 is not accepted
    assertFalse(sixBySix.checkValidBoardSize(0, 20));
    assertFalse(sixBySix.checkValidBoardSize(20, 0));
  }

  /**
   * Test the method checkValidBoardFleet: test that it validates
   * the correct fleet numbers
   */
  @Test
  public void testCheckValidBoardFleet() {
    // one of each boat
    assertTrue(sixBySix.checkValidBoardFleet(
        1,1,1,1, 4));
    assertTrue(sixBySix.checkValidBoardFleet(
        1,1,1,1, 5));
    // two of each boat
    assertTrue(sixBySix.checkValidBoardFleet(
        2,2,2,2, 8));

    // cannot be zero
    assertFalse(sixBySix.checkValidBoardFleet(
        0,2,2,2, 8));
    // cannot be zero
    assertFalse(sixBySix.checkValidBoardFleet(
        0,1,0,2, 8));
    assertFalse(sixBySix.checkValidBoardFleet(
        0,0,0,0, 8));
    // cannot be greater than max fleet size given
    assertFalse(sixBySix.checkValidBoardFleet(
        2,4,6,8, 5));
    // cannot be zero, and cannot be greater than max fleet size
    assertFalse(sixBySix.checkValidBoardFleet(
        0,0,0,0, -1));
  }

  /**
   * Test the method checkValidBoardShot: test that it validates
   * the correct shots
   */
  @Test
  public void testCheckValidBoardShot() {
    // 5x5
    assertTrue(sixBySix.checkValidBoardShot(
        5,5));
    // origin
    assertTrue(sixBySix.checkValidBoardShot(
        0,0));
    // 3x2
    assertTrue(sixBySix.checkValidBoardShot(
        3,2));

    assertFalse(sixBySix.checkValidBoardShot(
        6,6));
    assertFalse(sixBySix.checkValidBoardShot(
        8,8));
    assertFalse(sixBySix.checkValidBoardShot(
        -1,-1));
    assertFalse(sixBySix.checkValidBoardShot(
        -1, 7));
  }

  /**
   * Test the method getCoord
   */
  @Test
  public void testGetCoord() {
    Coord c = sixBySix.getCoord(0,0);
    // Check that correct coord was retrieved
    assertEquals(c.getRow(), 0);
    assertEquals(c.getColumn(), 0);

    c = sixBySix.getCoord(3,3);
    // Check that correct coord was retrieved
    assertEquals(c.getRow(), 3);
    assertEquals(c.getColumn(), 3);

    c = sixBySix.getCoord(1,4);
    // Check that correct coord was retrieved
    assertEquals(c.getRow(), 1);
    assertEquals(c.getColumn(), 4);
  }

  /**
   * Test the method getBoardStringRepresentation:
   * test that the board render is correct with ships
   */
  @Test
  public void testGetBoardStringRepresentation() {
    // test that board with no hits/misses just has +'s
    Board sevenBySeven = new Board(new Random(6));
    sevenBySeven.setUpCoords(7,7);
    assertEquals(sevenBySeven.getBoardStringRepresentation(),
        """
            + + + + + + +
            + + + + + + +
            + + + + + + +
            + + + + + + +
            + + + + + + +
            + + + + + + +
            + + + + + + +
            """
        );

    // test that board has expected 4 ships shown
    Board tenByTwelve = new Board(new Random(6));
    tenByTwelve.setUpCoords(10,12);
    tenByTwelve.setUpShips(ShipType.CARRIER, 1);
    tenByTwelve.setUpShips(ShipType.BATTLESHIP, 1);
    tenByTwelve.setUpShips(ShipType.DESTROYER,1);
    tenByTwelve.setUpShips(ShipType.SUBMARINE, 1);
    assertEquals(tenByTwelve.getBoardStringRepresentation(),
        """
            + + + + + + + + + + + +
            S S S S S S + + + + + +
            + + + S S S S + + + + +
            + + + + + + + + + + + +
            + + + + + S S S + + + +
            + + + + + + + + + + + +
            + + + + + + + + + + + +
            + + + + + + + + + + + +
            + S S S S S + + + + + +
            + + + + + + + + + + + +
            """
    );
  }

  /**
   * Test the method getBoardStringRepresentation:
   * test that the coord states are correct
   */
  @Test
  public void testGetBoardStringRepresentationCoords() {
    // No coords should have shown to be hit/missed:
    Board ten = new Board(new Random(6));
    ten.setUpCoords(10,10);
    assertEquals(ten.getBoardStringRepresentation(),
        """
            + + + + + + + + + +
            + + + + + + + + + +
            + + + + + + + + + +
            + + + + + + + + + +
            + + + + + + + + + +
            + + + + + + + + + +
            + + + + + + + + + +
            + + + + + + + + + +
            + + + + + + + + + +
            + + + + + + + + + +
            """
    );
    // (0,0), (5,5), (7,2) shown to be missed
    // (1,1), (2,2), (3,3) shown to be hit
    ten.getCoord(0, 0).updateAfterTargeted(false);
    ten.getCoord(5, 5).updateAfterTargeted(false);
    ten.getCoord(7, 2).updateAfterTargeted(false);
    ten.getCoord(1, 1).updateAfterTargeted(true);
    ten.getCoord(2, 2).updateAfterTargeted(true);
    ten.getCoord(3, 3).updateAfterTargeted(true);
    assertEquals(ten.getBoardStringRepresentation(),
        """
            M + + + + + + + + +
            + H + + + + + + + +
            + + H + + + + + + +
            + + + H + + + + + +
            + + + + + + + + + +
            + + + + + M + + + +
            + + + + + + + + + +
            + + M + + + + + + +
            + + + + + + + + + +
            + + + + + + + + + +
            """
    );
  }

  /**
   * Test the method getBoardStringRepresentation:
   * test that the coord states and ship placements together are correct
   */
  @Test
  public void testGetBoardRepShipsCoords() {
    // No coords should have shown to be hit/missed:
    Board ten = new Board(new Random(6));
    ten.setUpCoords(10,10);
    ten.setUpShips(ShipType.CARRIER, 2);
    ten.setUpShips(ShipType.BATTLESHIP, 2);
    ten.setUpShips(ShipType.DESTROYER,1);
    ten.setUpShips(ShipType.SUBMARINE, 1);
    // (0,0), (5,5), (7,2) shown to be missed
    // (1,1), (2,2), (3,3) shown to be hit
    ten.getCoord(0, 0).updateAfterTargeted(false);
    ten.getCoord(5, 5).updateAfterTargeted(false);
    ten.getCoord(7, 2).updateAfterTargeted(false);
    ten.getCoord(1, 1).updateAfterTargeted(true);
    ten.getCoord(2, 2).updateAfterTargeted(true);
    ten.getCoord(3, 3).updateAfterTargeted(true);
    assertEquals(ten.getBoardStringRepresentation(),
        """
            M + + + + + + + + +
            + H + + + + + + S +
            + S H S S S + + S +
            + + + H S S S + S +
            S + + S S S S S S +
            S + + + + M + + S +
            S + + + + + + + S +
            S + M + + + + + + +
            + S S S S S S + + +
            + + + + + + + + + +
            """
    );
  }

  /**
   * Test the method reportDamage in Board:
   * test that it updates Coord states
   */
  @Test
  public void testReportDamageCoordStates() {
    assertEquals(sixBySix.getBoardStringRepresentation(),
        """
            S + S + S +
            S + S S S +
            S + S S S +
            S + S S S +
            + + S + S +
            + + + + S +
            """);
    // Shoots 0 0, 0 1, 0 2:
    ArrayList<Coord> opponentShots =
        new ArrayList<>(Arrays.asList(
           new BattleshipCoord(0,0),
            new BattleshipCoord(0,1),
            new BattleshipCoord(0,2)));

    // Expected hits:
    ArrayList<Coord> hits =
        new ArrayList<>(Arrays.asList(
            sixBySix.getCoord(0,0),
            sixBySix.getCoord(0,2)));

    sixBySix.reportDamage(opponentShots);

    // Check that hits are updated to HIT state:
    for (Coord c : hits) {
      assertEquals(c.getState(), CoordState.HIT);
    }
    // Check that misses are updated to MISSED state:
      assertEquals(sixBySix.getCoord(0,1).getState(), CoordState.MISSED);
  }

  /**
   * Test the method reportDamage in Board:
   * test that correctly returns list of hits
   */
  @Test
  public void testReportDamageList() {
    assertEquals(sixBySix.getBoardStringRepresentation(),
        """
            S + S + S +
            S + S S S +
            S + S S S +
            S + S S S +
            + + S + S +
            + + + + S +
            """);
    // Shoots 0 0, 0 1, 0 2:
    ArrayList<Coord> opponentShots =
        new ArrayList<>(Arrays.asList(
            new BattleshipCoord(0,0),
            new BattleshipCoord(0,1),
            new BattleshipCoord(0,2)));

    // Expected hits:
    ArrayList<Coord> hits =
        new ArrayList<>(Arrays.asList(
            new BattleshipCoord(0,0),
            new BattleshipCoord(0,2)));

    List<Coord> methodResults = sixBySix.reportDamage(opponentShots);

    // Check that hits are the same as the list returned:
    for (int i = 0; i < 2; i++) {
      assertEquals(methodResults.get(i).getRow(),
          hits.get(i).getRow());
      assertEquals(methodResults.get(i).getColumn(),
          hits.get(i).getColumn());
    }
  }

  /**
   * Test the method generateRandomCoord in Board:
   * test that it generates a new random coord within the board bounds
   */
  @Test
  public void testGenerateRandomCoord() {
    assertTrue(sixBySix.generateRandomCoord().getRow() < 6);
    assertTrue(sixBySix.generateRandomCoord().getRow() >= 0);

    assertTrue(sixBySix.generateRandomCoord().getColumn() < 6);
    assertTrue(sixBySix.generateRandomCoord().getColumn() >= 0);

    assertTrue(sevenByTen.generateRandomCoord().getRow() < 7);
    assertTrue(sevenByTen.generateRandomCoord().getRow() >= 0);

    assertTrue(sevenByTen.generateRandomCoord().getColumn() < 10);
    assertTrue(sevenByTen.generateRandomCoord().getColumn() >= 0);

    // Use completely random example to prove:
    assertTrue(completelyRandom.generateRandomCoord().getRow() < 6);
    assertTrue(completelyRandom.generateRandomCoord().getRow() >= 0);

    assertTrue(completelyRandom.generateRandomCoord().getColumn() < 6);
    assertTrue(completelyRandom.generateRandomCoord().getColumn() >= 0);
  }

  /**
   * Test the method countUnguessedCoords in Board:
   * test that it correctly counts unguessed, untouched coords
   */
  @Test
  public void testCountUnguessedCoords() {
    // No guesses made, assert that all coords will be unguessed:
    assertEquals(sevenByTen.countUnguessedCoords(), 70);

    // Guess 18 Coords:
    ArrayList<Coord> opponentShots =
        new ArrayList<>(Arrays.asList(
            new BattleshipCoord(0,0), new BattleshipCoord(1,0),
            new BattleshipCoord(2,0), new BattleshipCoord(0,2),
            new BattleshipCoord(3,0), new BattleshipCoord(1,2),
            new BattleshipCoord(1,3), new BattleshipCoord(2,2),
            new BattleshipCoord(2,3), new BattleshipCoord(3,2),
            new BattleshipCoord(3,3), new BattleshipCoord(4,2),
            new BattleshipCoord(0,4), new BattleshipCoord(3,4),
            new BattleshipCoord(1,4), new BattleshipCoord(4,4),
            new BattleshipCoord(2,4), new BattleshipCoord(5,4)));
    sevenByTen.reportDamage(opponentShots);
    // 18 guesses made, assert that 58 coords will be counted unguessed:
    assertEquals(sevenByTen.countUnguessedCoords(), 52);
    // When all guessed, assert 0 coords are unguessed:
    opponentShots = new ArrayList<>();
    for (int row = 0; row < 7; row++) {
      for (int column = 0; column < 10; column ++) {
        opponentShots.add(sevenByTen.getCoord(row, column));
      }
    }
    sevenByTen.reportDamage(opponentShots);
    assertEquals(sevenByTen.countUnguessedCoords(), 0);
  }

  /**
   * Test the method countRemainingShips in Board:
   * test that it correctly counts remaining ships
   */
  @Test
  public void testCountRemainingShips() {
    assertEquals(sixBySix.getBoardStringRepresentation(),
        """
            S + S + S +
            S + S S S +
            S + S S S +
            S + S S S +
            + + S + S +
            + + + + S +
            """);
    // Shoots 0 0, 0 1, 0 2, all ships remain:
    ArrayList<Coord> opponentShots =
        new ArrayList<>(Arrays.asList(
            new BattleshipCoord(0,0),
            new BattleshipCoord(0,1),
            new BattleshipCoord(0,2)));
    sixBySix.reportDamage(opponentShots);
    assertEquals(sixBySix.countRemainingShips(), 4);
    // Shoots one ship, now should be 3 remaining:
    opponentShots =
        new ArrayList<>(Arrays.asList(
            new BattleshipCoord(0,0), new BattleshipCoord(1,0),
            new BattleshipCoord(2,0), new BattleshipCoord(3,0)));
    sixBySix.reportDamage(opponentShots);
    assertEquals(sixBySix.countRemainingShips(), 3);
    // Shoots all remaining ships
    opponentShots =
        new ArrayList<>(Arrays.asList(
            new BattleshipCoord(0,0), new BattleshipCoord(1,0),
            new BattleshipCoord(2,0), new BattleshipCoord(0,2),
            new BattleshipCoord(3,0), new BattleshipCoord(1,2),
            new BattleshipCoord(1,3), new BattleshipCoord(2,2),
            new BattleshipCoord(2,3), new BattleshipCoord(3,2),
            new BattleshipCoord(3,3), new BattleshipCoord(4,2),
            new BattleshipCoord(0,4), new BattleshipCoord(3,4),
            new BattleshipCoord(1,4), new BattleshipCoord(4,4),
            new BattleshipCoord(2,4), new BattleshipCoord(5,4)));
    sixBySix.reportDamage(opponentShots);
    assertEquals(sixBySix.countRemainingShips(), 0);
  }

  /**
   * Test the method gameEnded in Board:
   * test that it correctly determines whether all ships are sunk in board
   */
  @Test
  public void testGameEnded() {
    assertEquals(sixBySix.getBoardStringRepresentation(),
        """
            S + S + S +
            S + S S S +
            S + S S S +
            S + S S S +
            + + S + S +
            + + + + S +
            """);
    // Shoots 0 0, 0 1, 0 2:
    ArrayList<Coord> opponentShots =
        new ArrayList<>(Arrays.asList(
            new BattleshipCoord(0,0),
            new BattleshipCoord(0,1),
            new BattleshipCoord(0,2)));
    sixBySix.reportDamage(opponentShots);
    // When there are still ships, assert false that it has ended
    assertFalse(sixBySix.gameEnded());
    // Shoots all remaining ships
    opponentShots =
        new ArrayList<>(Arrays.asList(
            new BattleshipCoord(0,0), new BattleshipCoord(1,0),
            new BattleshipCoord(2,0), new BattleshipCoord(0,2),
            new BattleshipCoord(3,0), new BattleshipCoord(1,2),
            new BattleshipCoord(1,3), new BattleshipCoord(2,2),
            new BattleshipCoord(2,3), new BattleshipCoord(3,2),
            new BattleshipCoord(3,3), new BattleshipCoord(4,2),
            new BattleshipCoord(0,4), new BattleshipCoord(3,4),
            new BattleshipCoord(1,4), new BattleshipCoord(4,4),
            new BattleshipCoord(2,4), new BattleshipCoord(5,4)
            ));
    sixBySix.reportDamage(opponentShots);
    // When there are no ships, assert true that it has ended
    assertTrue(sixBySix.gameEnded());
  }
}
