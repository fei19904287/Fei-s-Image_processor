import model.Pixel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test class for the Pixel class.
 */
public class PixelTest {

  /**
   * Test valid constructor.
   */
  @Test
  public void testValidPixelValues() {
    int r = 100;
    int g = 150;
    int b = 200;

    Pixel pixel = new Pixel(r, g, b);

    assertEquals(r, pixel.getR());
    assertEquals(g, pixel.getG());
    assertEquals(b, pixel.getB());
  }

  /**
   * Test Invalid pixel value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPixelValuesRed() {
    int r = 300; // Value out of bounds
    int g = 50;
    int b = 70;
    new Pixel(r, g, b);
  }

  /**
   * Test invalid pixel value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPixelValuesGreen() {
    int r = 50; // Value out of bounds
    int g = 300;
    int b = 70;
    new Pixel(r, g, b);
  }

  /**
   * Test invalid blue value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPixelValuesBlue() {
    int r = 30; // Value out of bounds
    int g = 50;
    int b = 300;

    new Pixel(r, g, b);
  }

  /**
   * Test invalid pixel valuered.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPixelValuesNegred() {
    int r = -30; // Value out of bounds
    int g = 50;
    int b = 70;

    new Pixel(r, g, b);
  }

  /**
   * Test neg value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPixelValuesNegGreen() {
    int r = 30; // Value out of bounds
    int g = -50;
    int b = 70;

    new Pixel(r, g, b);
  }

  /**
   * Test neg blue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPixelValuesNegblue() {
    int r = 30; // Value out of bounds
    int g = 50;
    int b = -70;

    new Pixel(r, g, b);
  }

  /**
   * Test set red.
   */
  @Test
  public void testSetR() {
    int r = 100;
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.setR(r);
    assertEquals(r, pixel.getR());
  }

  /**
   * est set green.
   */
  @Test
  public void testSetG() {
    int g = 150;
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.setG(g);
    assertEquals(g, pixel.getG());
  }

  /**
   * Test set blue.
   */
  @Test
  public void testSetB() {
    int b = 200;
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.setB(b);
    assertEquals(b, pixel.getB());
  }

  /**
   * Test invalid setR.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetR() {
    int r = 300; // Value out of bounds
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.setR(r);
  }

  /**
   * Test set invalid green.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetG() {
    int g = 300; // Value out of bounds
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.setG(g);
  }

  /**
   * Test set invalid blue.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSetB() {
    int b = 300; // Value out of bounds
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.setB(b);
  }

  /**
   * Test tostring.
   */
  @Test
  public void testToString() {
    Pixel pixel = new Pixel(10, 20, 30);
    assertEquals("10 20 30", pixel.toString());
  }
}
