package cs3500.pa03.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.CoordState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests and examples for the enum CoordState
 */
public class CoordStateTest {
  CoordState hit;
  CoordState missed;
  CoordState untouched;

  /**
   * Set up examples of CoordState before tests
   */
  @BeforeEach
  public void setup() {
    hit = CoordState.HIT;
    missed = CoordState.MISSED;
    untouched = CoordState.UNTOUCHED;
  }

  /**
   * Test the method value of in the enum CoordState
   */
  @Test
  public void testGameResultValueOf() {
    String hitString = "HIT";
    String missString = "MISSED";
    String untouchedString = "UNTOUCHED";

    assertEquals(CoordState.valueOf(hitString), CoordState.HIT);
    assertEquals(CoordState.valueOf(missString), CoordState.MISSED);
    assertEquals(CoordState.valueOf(untouchedString), CoordState.UNTOUCHED);
  }
}
