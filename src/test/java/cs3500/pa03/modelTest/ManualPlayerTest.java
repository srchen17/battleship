package cs3500.pa03.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.controller.CoordData;
import cs3500.pa03.controller.GameData;
import cs3500.pa03.controller.ManualUserInput;
import cs3500.pa03.model.BattleshipCoord;
import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.ManualPlayer;
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
public class ManualPlayerTest {
  ManualPlayer console;
  ManualPlayer consoleTen;
  Board six;
  Board ten;
  ArrayList<ManualUserInput> inputSix;
  GameData gameDataSix;
  Board opponentSix;
  ArrayList<ManualUserInput> inputTen;
  GameData gameDataTen;
  Board opponentTen;

  /**
   * Sets up examples for ManualPlayer
   */
  @BeforeEach
  public void setup() {
    six = new Board(new Random(6));
    six.setUpCoords(6,6);
    inputSix = new ArrayList<>();
    gameDataSix = new GameData();
    opponentSix = new Board(new Random(6));
    opponentSix.setUpCoords(6,6);
    console = new ManualPlayer(six, inputSix, gameDataSix, opponentSix);
    ten = new Board(new Random(5));
    ten.setUpCoords(10,10);
    inputTen = new ArrayList<>();
    gameDataTen = new GameData();
    opponentTen = new Board(new Random(6));
    opponentTen.setUpCoords(10,10);
    consoleTen =  new ManualPlayer(ten, inputTen, gameDataTen, opponentTen);
  }

  /**
   * Test the method name in ManualPlayer
   */
  @Test
  public void testName() {
    assertEquals(console.name(), "GenericPlayer");
    assertEquals(consoleTen.name(), "GenericPlayer");
  }

  /**
   * Test the method setUp in ManualPlayer for a 6x6 board
   * with one of each ship
   */
  @Test
  public void testSetUp() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 1);
    map.put(ShipType.BATTLESHIP, 1);
    map.put(ShipType.SUBMARINE, 1);
    map.put(ShipType.DESTROYER, 1);
    List<Ship> ships = console.setup(6, 6, map);
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
   * Test the method setUp in ManualPlayer for a 10x10 board
   * with different amounts of ships
   */
  @Test
  public void testSetUpTen() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 2);
    map.put(ShipType.BATTLESHIP, 2);
    map.put(ShipType.SUBMARINE, 4);
    map.put(ShipType.DESTROYER, 1);
    List<Ship> ships = consoleTen.setup(10, 10, map);
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
   * Test the method takeShots for a 6x6 in ManualPlayer
   */
  @Test
  public void testTakeShotsSix() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 1);
    map.put(ShipType.BATTLESHIP, 1);
    map.put(ShipType.SUBMARINE, 1);
    map.put(ShipType.DESTROYER, 1);
    console.setup(6, 6, map);
    // add four shots to input
    inputSix.add(new CoordData(new BattleshipCoord(0, 0)));
    inputSix.add(new CoordData(new BattleshipCoord(1, 1)));
    inputSix.add(new CoordData(new BattleshipCoord(2, 2)));
    inputSix.add(new CoordData(new BattleshipCoord(3, 3)));
    // test that the number of shots is 4, because 4 ships.
    List<Coord> shots = console.takeShots();
    assertEquals(shots.size(), 4);
    // test that they are all different by rendering opponent board
    Board opponent = new Board();
    opponent.setUpCoords(6,6);
    opponent.reportDamage(shots);
    // test that the player took the 4 shots inputted:
    assertEquals(opponent.getBoardStringRepresentation(),
        """
            M + + + + +
            + M + + + +
            + + M + + +
            + + + M + +
            + + + + + +
            + + + + + +
            """);
  }

  /**
   * Test the method takeShots for a 10x10 in ManualPlayer
   */
  @Test
  public void testTakeShotsTen() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 2);
    map.put(ShipType.BATTLESHIP, 2);
    map.put(ShipType.SUBMARINE, 2);
    map.put(ShipType.DESTROYER, 2);
    consoleTen.setup(10, 10, map);
    inputTen.add(new CoordData(new BattleshipCoord(0, 0)));
    inputTen.add(new CoordData(new BattleshipCoord(1, 1)));
    inputTen.add(new CoordData(new BattleshipCoord(2, 2)));
    inputTen.add(new CoordData(new BattleshipCoord(3, 3)));
    inputTen.add(new CoordData(new BattleshipCoord(4, 4)));
    inputTen.add(new CoordData(new BattleshipCoord(5, 5)));
    inputTen.add(new CoordData(new BattleshipCoord(6, 6)));
    inputTen.add(new CoordData(new BattleshipCoord(7, 7)));
    // test that the number of shots is 8, because it has 8 ships.
    List<Coord> shots = consoleTen.takeShots();
    assertEquals(shots.size(), 8);
    // test that they are all different by rendering opponent board
    Board opponent = new Board();
    opponent.setUpCoords(10,10);
    opponent.reportDamage(shots);
    // test that the player took the 8 different shots inputted:
    assertEquals(opponent.getBoardStringRepresentation(),
        """
            M + + + + + + + + +
            + M + + + + + + + +
            + + M + + + + + + +
            + + + M + + + + + +
            + + + + M + + + + +
            + + + + + M + + + +
            + + + + + + M + + +
            + + + + + + + M + +
            + + + + + + + + + +
            + + + + + + + + + +
            """);
  }

  /**
   * Test the method reportDamage in ManualPlayer returns
   * correct hits on 6x6
   */
  @Test
  public void testReportDamage() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 1);
    map.put(ShipType.BATTLESHIP, 1);
    map.put(ShipType.SUBMARINE, 1);
    map.put(ShipType.DESTROYER, 1);
    console.setup(6, 6, map);
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
            new BattleshipCoord(3,0), new BattleshipCoord(3,1),
            new BattleshipCoord(3,2)));
    // Expected hits:
    ArrayList<Coord> hits =
        new ArrayList<>(List.of(
            new BattleshipCoord(3, 0)));
    List<Coord> methodResults = console.reportDamage(opponentShots);
    // Check that hits are the same as the list returned:
    for (int i = 0; i < 1; i++) {
      assertEquals(methodResults.get(i).getRow(),
          hits.get(i).getRow());
      assertEquals(methodResults.get(i).getColumn(),
          hits.get(i).getColumn());
    }
  }

  /**
   * Test the method reportDamage in ManualPlayer returns
   * correct hits on 10x10
   */
  @Test
  public void testReportDamageTen() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 2);
    map.put(ShipType.BATTLESHIP, 2);
    map.put(ShipType.SUBMARINE, 2);
    map.put(ShipType.DESTROYER, 1);
    consoleTen.setup(10, 10, map);
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
            new BattleshipCoord(1, 0), new BattleshipCoord(1, 1),
            new BattleshipCoord(1, 2), new BattleshipCoord(1, 3)));
    // Expected hits:
    ArrayList<Coord> hits =
        new ArrayList<>(Arrays.asList(new BattleshipCoord(1, 1),
            new BattleshipCoord(1, 2), new BattleshipCoord(1, 3)));
    List<Coord> methodResults = consoleTen.reportDamage(opponentShots);
    // Check that hits are the same as the list returned:
    for (int i = 0; i < 3; i++) {
      assertEquals(methodResults.get(i).getRow(),
          hits.get(i).getRow());
      assertEquals(methodResults.get(i).getColumn(),
          hits.get(i).getColumn());
    }
  }

  /**
   * Test the method successfulHits in ManualPlayer
   */
  @Test
  public void testSuccessfulHits() {
    LinkedHashMap<ShipType, Integer> map = new LinkedHashMap<>();
    map.put(ShipType.CARRIER, 1);
    map.put(ShipType.BATTLESHIP, 1);
    map.put(ShipType.SUBMARINE, 1);
    map.put(ShipType.DESTROYER, 1);
    console.setup(6, 6, map);
    // Shots fired
    // add four shots to input
    inputSix.add(new CoordData(new BattleshipCoord(0, 0)));
    inputSix.add(new CoordData(new BattleshipCoord(1, 1)));
    inputSix.add(new CoordData(new BattleshipCoord(2, 2)));
    inputSix.add(new CoordData(new BattleshipCoord(3, 3)));
    console.takeShots();
    inputSix = new ArrayList<>();

    // Shots that hit opponent ships:
    ArrayList<Coord> opponentShots =
        new ArrayList<>(Arrays.asList(
            new BattleshipCoord(0,0),
            new BattleshipCoord(1,1),
            new BattleshipCoord(2,2)));
    // test that show shots is false in gameData first
    assertFalse(gameDataSix.showingShots());
    // Calls successfulHits
    console.successfulHits(opponentShots);
    // Test that opponent board shows can render appropriate hits:
    assertEquals(opponentSix.getBoardStringRepresentation(),
        """
            H + + + + +
            + H + + + +
            + + H + + +
            + + + M + +
            + + + + + +
            + + + + + +
            """);
    // test that show shots is now turned true in gameData
    assertTrue(gameDataSix.showingShots());
  }

  /**
   * Test the method name in ManualPlayer
   */
  @Test
  public void testEndGame() {
    // No tests because does nothing in Manual Player.
    // So, nothing to test here.
  }
}
