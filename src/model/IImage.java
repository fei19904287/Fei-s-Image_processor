package model;

/**
 * an image interface represents an image, it extends the imagestate interface.
 */
public interface IImage extends IImageState {
  /**
   * a method to update the pixel value.
   *
   * @param x the row of the pixel
   * @param y the col of the pixel
   * @param r the redvalue of the pixel
   * @param g the greenvalue of the pixel
   * @param b the bluevalue of the pixel
   */
  void setPixel(int x, int y, int r, int g, int b);
}
