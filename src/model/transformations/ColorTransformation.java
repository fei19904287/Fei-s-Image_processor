package model.transformations;

import model.IImage;
import model.IImageState;
import model.ImageImpl;


/**
 * This class represents a color transformation that can be applied to an image.
 * It implements the ITransformation interface.
 */
public class ColorTransformation implements ITransformation {
  private final float[] colordata;

  /**
   * Constructs a ColorTransformation object with the provided color data.
   *
   * @param colordata An array of float values representing the color transformation data.
   */
  public ColorTransformation(float[] colordata) {
    this.colordata = colordata;
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
   * Applies the color transformation to the input image state and returns the transformed image.
   *
   * @param sourceImage The original image state to be transformed.
   * @return A new IImageState object representing the transformed image.
   */
  @Override
  public IImageState run(IImageState sourceImage) {
    IImage newImage = new ImageImpl(sourceImage.getHeight(), sourceImage.getWidth());
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int redVal = sourceImage.getRedChannel(row, col);
        int greenVal = sourceImage.getRedChannel(row, col);
        int blueVal = sourceImage.getRedChannel(row, col);

        int newR =
            clamp((int) (redVal * colordata[0]) + (int) (redVal * colordata[1])
                + (int) (redVal * colordata[2]));
        int newG =
            clamp((int) (greenVal * colordata[3]) + (int) (greenVal * colordata[4])
                + (int) (greenVal * colordata[5]));

        int newB =
            clamp((int) (blueVal * colordata[6]) + (int) (blueVal * colordata[7])
                + (int) (blueVal * colordata[8]));

        newImage.setPixel(row, col, newR, newG, newB);
      }
    }
    System.out.println("color-image has finished");
    return newImage;
  }

}
