package model;

/**
 * A class representing a pixel with red, green, and blue color channels.
 * The values of the color channels are integers ranging from 0 to 255.
 */
public class Pixel implements IPixel {
  private int r;
  private int g;
  private int b;

  /**
   * Constructs a Pixel object with the specified values for red, green, and blue channels.
   *
   * @param r The red channel value (0 to 255).
   * @param g The green channel value (0 to 255).
   * @param b The blue channel value (0 to 255).
   * @throws IllegalArgumentException If any of the channel values are outside the valid range
   */
  public Pixel(int r, int g, int b) throws IllegalArgumentException {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("Pixel value out of bounds");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Sets the red channel value of the pixel.
   *
   * @param value The red channel value to be set (0 to 255).
   * @throws IllegalArgumentException If the provided value is outside the valid range (0 to 255).
   */
  @Override
  public void setR(int value) {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException("Channel value is not invalid");
    }
    this.r = value;
  }

  /**
   * Sets the green channel value of the pixel.
   *
   * @param value The green channel value to be set (0 to 255).
   * @throws IllegalArgumentException If the provided value is outside the valid range (0 to 255).
   */
  @Override
  public void setG(int value) {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException("Channel value is not invalid");
    }
    this.g = value;

  }

  /**
   * Sets the blue channel value of the pixel.
   *
   * @param value The blue channel value to be set (0 to 255).
   * @throws IllegalArgumentException If the provided value is outside the valid range (0 to 255).
   */
  @Override
  public void setB(int value) {
    if (value < 0 || value > 255) {
      throw new IllegalArgumentException("Channel value is not invalid");
    }
    this.b = value;

  }

  /**
   * Gets the red channel value of the pixel.
   *
   * @return The red channel value (0 to 255).
   */
  @Override
  public int getR() {
    return r;
  }

  /**
   * Gets the green channel value of the pixel.
   *
   * @return The green channel value (0 to 255).
   */
  @Override
  public int getG() {
    return g;
  }

  /**
   * Gets the blue channel value of the pixel.
   *
   * @return The blue channel value (0 to 255).
   */
  @Override
  public int getB() {
    return b;
  }

  /**
   * Returns a string representation of the pixel in the format "R G B".
   *
   * @return The string representation of the pixel.
   */
  @Override
  public String toString() {
    return this.r + " " + this.g + " " + this.b;
  }
}
