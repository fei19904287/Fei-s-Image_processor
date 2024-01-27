package model.transformations;

import model.IImage;
import model.IImageState;
import model.ImageImpl;

/**
 * A transformation that brightens an image by adding a specified value to each pixel's RGB
 * channels.
 */
public class BrightenTransformation implements ITransformation {
  private final int brightenValue;

  /**
   * Creates a BrightenTransformation with the specified brightenValue.
   *
   * @param brightenValue The value by which each RGB channel will be brightened.
   */
  public BrightenTransformation(int brightenValue) {
    this.brightenValue = brightenValue;
  }

  /**
   * Clamps the given value to ensure it stays within the range [0, 255].
   *
   * @param value The value to be clamped.
   * @return The clamped value.
   */
  private int clamp(int value) {
    if (value < 0) {
      return 0;
    }
    if (value > 255) {
      return 255;
    }
    return value;
  }

  /**
   * Applies the brightening transformation to the sourceImage and returns the resulting image.
   *
   * @param sourceImage The input image state to be brightened.
   * @return A new IImageState representing the brightened image.
   */
  @Override
  public IImageState run(IImageState sourceImage) {
    IImage newImage = new ImageImpl(sourceImage.getHeight(), sourceImage.getWidth());
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int newR = clamp(sourceImage.getRedChannel(row, col) + brightenValue);
        int newG = clamp(sourceImage.getGreenChannel(row, col) + brightenValue);
        int newB = clamp(sourceImage.getBlueChannel(row, col) + brightenValue);
        newImage.setPixel(row, col, newR, newG, newB);
      }
    }
    System.out.println("brighten has finished");
    return newImage;
  }
}
