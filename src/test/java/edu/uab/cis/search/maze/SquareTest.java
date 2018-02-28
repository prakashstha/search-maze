package edu.uab.cis.search.maze;

import org.junit.Assert;
import org.junit.Test;

public class SquareTest {

  @Test
  public void testGetters() {
    Square square = new Square(0, 6);
    Assert.assertEquals(0, square.getRow());
    Assert.assertEquals(6, square.getColumn());
  }

  @Test
  public void testHashingAndEquality() {
    Square square1 = new Square(7, 3);
    Square square2 = new Square(7, 3);
    Assert.assertEquals(square1, square2);
    Assert.assertEquals(square1.hashCode(), square2.hashCode());
    Assert.assertNotEquals(square1, null);
    Assert.assertNotEquals(square1, new Square(7, 2));
    Assert.assertNotEquals(square1, new Square(6, 3));
  }
}
