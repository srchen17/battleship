package cs3500.pa03.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.BattleshipCoord;
import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.Ship;
import cs3500.pa03.model.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests  and examples for the class AiPlayer
 */
public class AiPlayerTest {
  AiPlayer ai;
  Board six;
  AiPlayer aiTen;
  Board ten;

  /**
   * Sets up examples for AI
   */
  @BeforeEach
  public void setup() {
    six = new Board(new Random(6));
    six.setUpCoords(6,6);

    ai = new AiPlayer(six);

    ten = new Board(new Random(5));
    ten.setUpCoords(10,10);

    aiTen = new AiPlayer(ten);
  }

  /**
   * Test the method name in AiPLayerTest
   */
  @Test
  public void testName() {
    assertEquals(ai.name(), "ai");
    assertEquals(aiTen.name(), "ai");
  }

  /**
   * Test the method setUp in AiPLayerTest for a 6x6 board
   * with one of each ship
   */
  @Test
  public void testSetUp() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 1);
    map.put(ShipType.BATTLESHIP, 1);
    map.put(ShipType.SUBMARINE, 1);
    map.put(ShipType.DESTROYER, 1);
    List<Ship> ships = ai.setup(6, 6, map);
    // test that it returns all 4 ships
    assertEquals(ships.size(), 4);
    // test that the board is as it should be, with
    // 1 of each kind of ship:
    // (since getBoardStringRep has been tested, will use it
    // to confirm setUp results)
    assertEquals(six.getBoardStringRepresentation(),
        """
            + S S S S S
            S S S S S S
            S S S S S +
            S + + + + +
            S + + + + +
            + + + + + +
            """);
  }

  /**
   * Test the method setUp in AiPLayerTest for a 10x10 board
   * with different amounts of ships
   */
  @Test
  public void testSetUpTen() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 2);
    map.put(ShipType.BATTLESHIP, 2);
    map.put(ShipType.SUBMARINE, 4);
    map.put(ShipType.DESTROYER, 1);
    List<Ship> ships = aiTen.setup(10, 10, map);
    // test that it returns all 9 ships
    assertEquals(ships.size(), 9);
    // test that the board is as it should be, with
    // 2 carriers, 2 battleships, 4 subs, and 1 destroyer
    assertEquals(ten.getBoardStringRepresentation(),
        """
            + + + + + + + S S +
            + S S S S S S S S +
            + + + + + + + S S +
            + + + + S + + + S +
            + S + + S + + + S +
            + S + + S + + + S +
            + S + S S S + + S +
            + S + + + + + + S +
            + S S S S + + + S +
            + S S S S S + + S +
            """);
  }

  /**
   * Test the method takeShots for a 6x6 in AiPLayerTest
   */
  @Test
  public void testTakeShotsSix() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 1);
    map.put(ShipType.BATTLESHIP, 1);
    map.put(ShipType.SUBMARINE, 1);
    map.put(ShipType.DESTROYER, 1);
    ai.setup(6, 6, map);
    // test that the number of shots is 4, because 4 ships.
    List<Coord> shots = ai.takeShots();
    assertEquals(shots.size(), 4);
    // test that they are all different by rendering opponent board
    Board opponent = new Board();
    opponent.setUpCoords(6,6);
    opponent.reportDamage(shots);
    // test that the ai took 4 different shots:
    assertEquals(opponent.getBoardStringRepresentation(),
        """
            + + + + + +
            M + + + + +
            + + + + M +
            + + + + + +
            + + + M + +
            + + + + M +
            """);
  }

  /**
   * Test the method takeShots for a 10x10 in AiPLayerTest
   */
  @Test
  public void testTakeShotsTen() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 2);
    map.put(ShipType.BATTLESHIP, 2);
    map.put(ShipType.SUBMARINE, 2);
    map.put(ShipType.DESTROYER, 2);
    aiTen.setup(10, 10, map);
    // test that the number of shots is 8, because it has 8 ships.
    List<Coord> shots = aiTen.takeShots();
    assertEquals(shots.size(), 8);
    // test that they are all different by rendering opponent board
    Board opponent = new Board();
    opponent.setUpCoords(10,10);
    opponent.reportDamage(shots);
    // test that the ai took 8 different shots:
    assertEquals(opponent.getBoardStringRepresentation(),
        """
            + + + + + + + M + +
            + + + + + + + + + M
            + + + M + + + + + M
            + + + + + + + + + +
            + + + + + + + + + +
            + + M + + M + + + +
            + + + + + + + + + +
            + + + M + + + + + +
            + + M + + + + + + +
            + + + + + + + + + +
            """);
  }

  /**
   * Test the method reportDamage in AiPLayerTest returns
   * correct hits on 6x6
   */
  @Test
  public void testReportDamage() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 1);
    map.put(ShipType.BATTLESHIP, 1);
    map.put(ShipType.SUBMARINE, 1);
    map.put(ShipType.DESTROYER, 1);
    ai.setup(6, 6, map);
    assertEquals(six.getBoardStringRepresentation(),
        """
            + S S S S S
            S S S S S S
            S S S S S +
            S + + + + +
            S + + + + +
            + + + + + +
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
            new BattleshipCoord(0,1),
            new BattleshipCoord(0,2)));
    List<Coord> methodResults = ai.reportDamage(opponentShots);
    // Check that hits are the same as the list returned:
    for (int i = 0; i < 2; i++) {
      assertEquals(methodResults.get(i).getRow(),
          hits.get(i).getRow());
      assertEquals(methodResults.get(i).getColumn(),
          hits.get(i).getColumn());
    }
  }

  /**
   * Test the method reportDamage in AiPLayerTest returns
   * correct hits on 10x10
   */
  @Test
  public void testReportDamageTen() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 2);
    map.put(ShipType.BATTLESHIP, 2);
    map.put(ShipType.SUBMARINE, 2);
    map.put(ShipType.DESTROYER, 1);
    aiTen.setup(10, 10, map);
    assertEquals(ten.getBoardStringRepresentation(),
        """
            + + + + + + + + S +
            + S S S S S S + S +
            + + + + + + + + S +
            + + + + S + + + S +
            + S + + S + + + S +
            + S + + S + + + S +
            + S + S S S + + S +
            + S + + + + + + S +
            + S + + + + + + S +
            + S S S S S + + S +
            """);
    // Shoots coords:
    ArrayList<Coord> opponentShots =
        new ArrayList<>(Arrays.asList(
            new BattleshipCoord(1, 0),
            new BattleshipCoord(1, 1),
            new BattleshipCoord(1, 2)));
    // Expected hits:
    ArrayList<Coord> hits =
        new ArrayList<>(Arrays.asList(
            new BattleshipCoord(1, 1),
            new BattleshipCoord(1, 2)));
    List<Coord> methodResults = aiTen.reportDamage(opponentShots);
    // Check that hits are the same as the list returned:
    for (int i = 0; i < 2; i++) {
      assertEquals(methodResults.get(i).getRow(),
          hits.get(i).getRow());
      assertEquals(methodResults.get(i).getColumn(),
          hits.get(i).getColumn());
    }
  }

  /**
   * Test the method successfulHits in AiPLayerTest
   */
  @Test
  public void testSuccessfulHits() {
    // No tests because does nothing in AI Player.
    // So, nothing to test here.
  }

  /**
   * Test the method name in AiPLayerTest
   */
  @Test
  public void testEndGame() {
    // No tests because does nothing in AI Player.
    // So, nothing to test here.
  }
}
