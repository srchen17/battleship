package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents an AI Player in a game of Battle Salvo.
 */
public class AiPlayer implements Player {
  private final Board board;
  private final ArrayList<Coord> prevShots;

  /**
   * Instantiates an AI Player given a board to play BattleSalvo
   *
   * @param board  A board to hold coords/ships for BattleSalvo.
   */
  public AiPlayer(Board board) {
    this.board = board;
    this.prevShots = new ArrayList<>();
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return "ai";
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height the height of the board, range: [6, 15] inclusive
   * @param width the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    ArrayList<Ship> allShips = new ArrayList<>();
    allShips.addAll(
        this.board.setUpShips(ShipType.CARRIER, specifications.get(ShipType.CARRIER)));
    allShips.addAll(
        this.board.setUpShips(ShipType.BATTLESHIP, specifications.get(ShipType.BATTLESHIP)));
    allShips.addAll(
        this.board.setUpShips(ShipType.DESTROYER, specifications.get(ShipType.DESTROYER)));
    allShips.addAll(
        this.board.setUpShips(ShipType.SUBMARINE, specifications.get(ShipType.SUBMARINE)));
    return allShips;
  }


  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    ArrayList<Coord> shots = new ArrayList<>();
    int remainingShips = this.board.countRemainingShips();
    boolean allShotsGenerated = false;
    while (!allShotsGenerated) {
      Coord c = this.board.generateRandomCoord();
      boolean newCoord = true;
      for (Coord shotsCoord : prevShots) {
        if ((shotsCoord.getColumn() == c.getColumn())
            && (shotsCoord.getRow() == c.getRow())) {
          newCoord = false;
        }
      }
      if (newCoord) {
        shots.add(c);
        prevShots.add(c);
        if (shots.size() >= remainingShips) {
          allShotsGenerated = true;
        }
      }
    }
    return shots;
  }

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   *     ship on this board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return this.board.reportDamage(opponentShotsOnBoard);
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    // Not needed for PA03 (Slack).
  }
}
