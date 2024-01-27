package model;

/**
 * an interface represent the pixel.
 */
public interface IPixel extends IPixelState {

  /**
   * to set the red value of the pixel by the given value.
   *
   * @param value the given value
   */
  void setR(int value);

  /**
   * to set the Green value of the pixel by the given value.
   *
   * @param value the given value
   */
  void setG(int value);

  /**
   * to set the Blue value of the pixel by the given value.
   *
   * @param value the given value
   */
  void setB(int value);
}
