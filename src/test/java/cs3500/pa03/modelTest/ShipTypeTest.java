package cs3500.pa03.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.ShipType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests for the enum ShipType.
 */
public class ShipTypeTest {
  ShipType carrier;
  ShipType battleship;
  ShipType destroyer;
  ShipType submarine;

  /**
   * Set up examples before tests for ShipType
   */
  @BeforeEach
  public void setup() {
    this.carrier = ShipType.CARRIER;
    this.battleship = ShipType.BATTLESHIP;
    this.destroyer = ShipType.DESTROYER;
    this.submarine = ShipType.SUBMARINE;
  }

  /**
   * Test the method returnLength in the enum ShipType
   */
  @Test
  public void testReturnLength() {
    assertEquals(carrier.returnLength(), 6);
    assertEquals(battleship.returnLength(), 5);
    assertEquals(destroyer.returnLength(), 4);
    assertEquals(submarine.returnLength(), 3);
  }
}
