package cs3500.pa03.controller;

import cs3500.pa03.model.AiPlayer;
import cs3500.pa03.model.BattleshipCoord;
import cs3500.pa03.model.Board;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.ManualPlayer;
import cs3500.pa03.model.Player;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.view.BattleshipDisplay;
import cs3500.pa03.view.ElementDisplay;
import cs3500.pa03.view.InputReader;
import cs3500.pa03.view.InputScanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a controller to handle user input/output for a game of BattleSalvo.
 */
public class GameController implements Controller {
  ElementDisplay view;
  ArrayList<ManualUserInput> inputData;
  InputReader reader;
  Player consolePlayer;
  Player aiPlayer;
  Board consoleBoard;
  Board opponentBoard;
  Board aiBoard;
  GameData gameData;

  /**
   * Instantiates a game controller, given a readable to scan user input from.
   *
   * @param readable  A Readable to scan user input from.
   */
  public GameController(Readable readable) {
    this.reader = new InputScanner(readable);
    this.inputData = new ArrayList<>();
    this.consoleBoard = new Board();
    this.aiBoard = new Board();
    this.opponentBoard = new Board();
    this.gameData = new GameData();
    this.consolePlayer = new ManualPlayer(this.consoleBoard, this.inputData,
        this.gameData, this.opponentBoard);
    this.aiPlayer = new AiPlayer(this.aiBoard);
    this.view = new BattleshipDisplay(this, System.out);
  }

  /**
   * Runs a game of BattleSalvo.
   */
  @Override
  public void runGame() {
    this.welcomeUser();
  }

  /**
   * Welcomes the user by: showing a message in BattleSalvo and prompting them for
   * a valid width and height of a board.
   */
  @Override
  public void welcomeUser() {
    // welcome the user with a message:
    this.view.displayDivider();
    this.view.welcomeUser();
    // prompt height/width input:
    this.view.promptUserInput("an integer HEIGHT [6,15] "
        + "& an integer WIDTH [6,15]! (ex: 6 10)");
    this.view.displayDivider();
    int height = 0;
    int width = 0;
    // while hasn't received a valid board size, accept user input:
    while (!this.consoleBoard.checkValidBoardSize(height, width)) {
      try { // try parsing user input:
        height = Integer.parseInt(this.reader.readNextToken());
        width = Integer.parseInt(this.reader.readNextToken());
        if (!this.consoleBoard.checkValidBoardSize(height, width)) { // if invalid, signal user:
          this.view.signalInvalidInput("two integers from [6,15], inclusive");
          this.view.displayDivider();
        } else { // if valid, set up boards and prompt user for fleet:
          this.consoleBoard.setUpCoords(height, width);
          this.aiBoard.setUpCoords(height, width);
          this.opponentBoard.setUpCoords(height, width);
          this.view.displayDivider();
          this.promptValidFleet(height, width);
        }
      } catch (NumberFormatException e) { // if invalid, signal user and keep trying:
        this.view.signalInvalidInput("two valid INTEGERS");
        this.view.displayDivider();
      }
    }
  }

  /**
   * Prompts the user to enter a valid fleet for a game.
   *
   * @param height  The height of the board, used for max fleet size
   * @param width  The width of the board, used for max fleet size
   */
  @Override
  public void promptValidFleet(int height, int width) {
    int maxFleetSize = Math.min(height, width);
    this.view.promptUserInput(
        "your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n"
            + "Remember, your fleet may not exceed size "
            + maxFleetSize
            + ". \n(ex: 1 1 1 1)");
    this.view.displayDivider();
    int carriers, battleships, destroyers, submarines;
    // accept fleet specifications:
    boolean receivedValidInput = false;
    while (!receivedValidInput) {
      try { // try parsing user input into ship numbers:
        carriers = Integer.parseInt(this.reader.readNextToken());
        battleships = Integer.parseInt(this.reader.readNextToken());
        destroyers = Integer.parseInt(this.reader.readNextToken());
        submarines = Integer.parseInt(this.reader.readNextToken());
        receivedValidInput = this.consoleBoard.checkValidBoardFleet(
            carriers, battleships, destroyers, submarines, maxFleetSize);
        if (!receivedValidInput) { // if invalid, user is signaled and keeps trying:
          this.view.signalInvalidInput("four valid INTEGERS >=1, with "
            + "fleet size <= " + maxFleetSize);
          this.view.displayDivider();
        } else { // if valid: sets up players
          this.view.displayDivider();
          HashMap<ShipType, Integer> specifications = new HashMap<>();
          specifications.put(ShipType.CARRIER, carriers);
          specifications.put(ShipType.BATTLESHIP, battleships);
          specifications.put(ShipType.DESTROYER, destroyers);
          specifications.put(ShipType.SUBMARINE, submarines);
          this.setupPlayers(height, width, specifications);
        } // if invalid input, user is signaled and keeps trying:
      } catch (NumberFormatException e) {
        this.view.signalInvalidInput("four valid INTEGERS");
        this.view.displayDivider();
      }
    }
  }

  /**
   * Sets up the two players in BattleSalvo.
   *
   * @param height  The height of the board, to generate a player's ships and coords
   * @param width  The width of the board, to generate a player's ships and coords
   * @param specifications  The specifications of ship types in the players' fleets.
   */
  @Override
  public void setupPlayers(int height, int width,  HashMap<ShipType, Integer> specifications) {
    // set up players:
    this.consolePlayer.setup(height, width, specifications);
    this.aiPlayer.setup(height, width, specifications);
    // display boards:
    this.view.displayGameMessage("\nOPPONENT'S BOARD: ");
    this.view.displayBoard(this.opponentBoard.getBoardStringRepresentation());
    this.view.displayDivider();
    this.view.displayGameMessage("\nYOUR BOARD: ");
    String usersBoard = this.consoleBoard.getBoardStringRepresentation();
    this.view.displayBoard(usersBoard);
    this.view.displayDivider();
    // start salvo stage:
    this.salvoStage();
  }

  /**
   * Runs the SalvoStage, where players exchange shots and view updated boards.
   */
  @Override
  public void salvoStage() {
    // get number of shots to take
    int numberShots = this.consoleBoard.countRemainingShips();
    if (numberShots > this.aiBoard.countUnguessedCoords()) {
      numberShots = this.aiBoard.countUnguessedCoords();
    }
    // ai player takes shots:
    final List<Coord> aiPlayerShots = this.aiPlayer.takeShots();
    // user is prompted to take shots:
    this.view.promptUserInput(numberShots + " shots, row by column! (ex: 1 2)");
    this.acceptShotsFromUser(numberShots);
    // report damage to each other:
    this.consolePlayer.successfulHits(
        this.aiPlayer.reportDamage(
            this.consolePlayer.takeShots()));
    this.aiPlayer.successfulHits(
        this.consolePlayer.reportDamage(aiPlayerShots));
    this.showUpdatedBoards();
  }

  /**
   * Accepts shots from user.
   *
   * @param numberShots  The number of shots a user can take.
   */
  private void acceptShotsFromUser(int numberShots) {
    boolean shotsFound = false;
    while (!shotsFound) {
      int countShots = 0;
      boolean countingInputs = true;
      ArrayList<ManualUserInput> validShots = new ArrayList<>();
      this.inputData.clear();
      while (countingInputs) { // while has not received all inputs for shots:
        try { // try parsing user input:
          int row = Integer.parseInt(this.reader.readNextToken());
          int column = Integer.parseInt(this.reader.readNextToken());
          if (this.consoleBoard.checkValidBoardShot(row, column)) {
            // if valid, add to data that player can access:
            validShots.add(new CoordData(new BattleshipCoord(row, column)));
            countShots = countShots + 1;
            if (countShots == numberShots) {
              // if reached number of shots, then stop accepting inputs
              countingInputs = false;
              shotsFound = true;
              this.inputData.addAll(validShots);
            }
          } else { // if invalid, user is signaled, and keeps receiving inputs:
            this.view.signalInvalidInput(numberShots + " shots within board bounds");
            this.view.displayDivider();
            validShots.clear();
            countShots = 0;
          }
        } catch (NumberFormatException e) {
          countShots = 0;
          this.view.signalInvalidInput(numberShots + " shots in INTEGER form");
          this.view.displayDivider();
        }
      }
    }
  }

  /**
   * Shows updated boards and hits/misses/shots exchanged to user.
   */
  public void showUpdatedBoards() {
    if (this.gameData.showingShots()) {
      this.view.displayDivider();
      this.view.displayGameMessage("\nYOUR BOARD: ");
      this.view.displayBoard(this.consoleBoard.getBoardStringRepresentation());
      this.view.displayDivider();
      this.view.displayGameMessage("\nOPPONENT'S BOARD: ");
      this.view.displayBoard(this.opponentBoard.getBoardStringRepresentation());
    }
    this.view.displayDivider();
    this.checkIfGameEnded();
  }

  /**
   * Checks from Model whether the game has ended.
   */
  private void checkIfGameEnded(){
    if (this.aiBoard.gameEnded() && this.consoleBoard.gameEnded()) {
      this.endGame(GameResult.DRAW);
    } else {
      if (this.aiBoard.gameEnded()) { // if AI's ships are sunk, console wins
        this.endGame(GameResult.WIN);
      } else {
        if (this.consoleBoard.gameEnded()) { // if console's ships sunk, AI wins
          this.endGame(GameResult.LOSE);
        } else {
          this.salvoStage(); // if neither won, keep playing game
        }
      }
    }
  }

  /**
   * Ends a game of BattleSalvo and displays the ending to the user.
   *
   * @param result  The result of the game.
   */
  @Override
  public void endGame(GameResult result) {
    this.view.displayDivider();
    if (result == GameResult.WIN) {
      this.view.displayEnding("GAME ENDED: YOU BEAT AN 'AI', LET'S GO! :)");
      System.exit(0);
    } else {
      if (result == GameResult.LOSE) {
        this.view.displayEnding("GAME ENDED: YOU LOST :(");
        System.exit(0);
      } else {
        if (result == GameResult.DRAW) {
          this.view.displayEnding("GAME ENDED: IT WAS A TIE!");
          System.exit(0);
        }
      }
    }
  }
}
