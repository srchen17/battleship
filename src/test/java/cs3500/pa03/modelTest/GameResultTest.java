package cs3500.pa03.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests and examples for the enum GameResult
 */
public class GameResultTest {
  GameResult win;
  GameResult draw;
  GameResult lose;

  /**
   * Set up examples of GameResult before tests
   */
  @BeforeEach
  public void setup() {
    win = GameResult.WIN;
    draw = GameResult.DRAW;
    lose = GameResult.LOSE;
  }

  /**
   * Test the method value of in the enum GameResult
   */
  @Test
  public void testGameResultValueOf() {
    String winString = "WIN";
    String drawString = "DRAW";
    String loseString = "LOSE";

    assertEquals(GameResult.valueOf(winString), GameResult.WIN);
    assertEquals(GameResult.valueOf(drawString), GameResult.DRAW);
    assertEquals(GameResult.valueOf(loseString), GameResult.LOSE);
  }
}
