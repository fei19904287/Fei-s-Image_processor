package model;

/**
 * an interface to represent the image state.
 */
public interface IImageState {

  /**
   * get the width of the image.
   *
   * @return the width of the image
   */
  int getWidth();

  /**
   * get the height of the image.
   *
   * @return the width of the image
   */
  int getHeight();

  /**
   * return the red vlaue of the pixel.
   *
   * @param x the row of the pixel
   * @param y the col of the pixel
   * @return the red value
   */
  int getRedChannel(int x, int y);

  /**
   * return the green vlaue of the pixel.
   *
   * @param x the row of the pixel
   * @param y the col of the pixel
   * @return the green value
   */
  int getGreenChannel(int x, int y);

  /**
   * return the blue value of the pixel.
   *
   * @param x the row of the pixel
   * @param y the col of the pixel
   * @return the blue value
   */
  int getBlueChannel(int x, int y);

  int[] getPixel(int x, int y);
}
