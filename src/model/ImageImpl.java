package model;

/**
 * Implementation of the {@link IImage} interface to represent an image.
 */
public class ImageImpl implements IImage {
  private final int width;
  private final int height;
  private final IPixel[][] data;

  /**
   * Constructs a new image with the specified height and width.
   *
   * @param height The height of the image.
   * @param width  The width of the image.
   * @throws IllegalArgumentException If the height or width is not a positive value.
   */
  public ImageImpl(int height, int width) throws IllegalArgumentException {
    this.width = width;
    this.height = height;
    this.data = new Pixel[height][width];
  }

  /**
   * Get the width of the image.
   *
   * @return The width of the image.
   */
  @Override
  public int getWidth() {
    return width;
  }

  /**
   * Get the height of the image.
   *
   * @return The height of the image.
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * Get the red channel value of the pixel at the specified (x, y) position.
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @return The red channel value of the pixel.
   * @throws IllegalArgumentException If the provided (x, y) coordinates are out of bounds.
   */
  @Override
  public int getRedChannel(int x, int y) throws IllegalArgumentException {
    if (x < 0 || x >= this.height || y < 0 || y >= this.width) {
      throw new IllegalArgumentException("x or y out of bound");
    }
    return this.data[x][y].getR();
  }

  /**
   * Get the green channel value of the pixel at the specified (x, y) position.
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @return The green channel value of the pixel.
   * @throws IllegalArgumentException If the provided (x, y) coordinates are out of bounds.
   */
  @Override
  public int getGreenChannel(int x, int y) {
    if (x < 0 || x >= this.height || y < 0 || y >= this.width) {
      throw new IllegalArgumentException("x or y out of bound");
    }
    return this.data[x][y].getG();
  }

  /**
   * Get the blue channel value of the pixel at the specified (x, y) position.
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @return The blue channel value of the pixel.
   * @throws IllegalArgumentException If the provided (x, y) coordinates are out of bounds.
   */
  @Override
  public int getBlueChannel(int x, int y) {
    if (x < 0 || x >= this.height || y < 0 || y >= this.width) {
      throw new IllegalArgumentException("x or y out of bound");
    }
    return this.data[x][y].getB();
  }

  /**
   * Set the pixel at the specified (x, y) position with the given RGB values.
   *
   * @param x The x-coordinate of the pixel.
   * @param y The y-coordinate of the pixel.
   * @param r The red channel value of the pixel.
   * @param g The green channel value of the pixel.
   * @param b The blue channel value of the pixel.
   * @throws IllegalArgumentException If the provided (x, y) coordinates are out of bounds.
   */
  @Override
  public void setPixel(int x, int y, int r, int g, int b) {
    if (x < 0 || x >= this.height || y < 0 || y >= this.width) {
      throw new IllegalArgumentException("x or y out of bound");
    }
    this.data[x][y] = new Pixel(r, g, b);
  }

  @Override
  public int[] getPixel(int x, int y) {
    return new int[]{
        getRedChannel(x, y), // Red component
        getGreenChannel(x, y),  // Green component
        getBlueChannel(x, y)          // Blue component
    };
  }
}
