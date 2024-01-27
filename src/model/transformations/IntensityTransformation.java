package model.transformations;

import model.IImage;
import model.IImageState;
import model.ImageImpl;

/**
 * This class represents an intensity transformation that converts an input image to grayscale using
 * the luma method.The luma method calculates the grayscale value of each pixel as the average of
 * its red, green, and blue channels.It implements the ITransformation interface.
 */
public class IntensityTransformation implements ITransformation {

  /**
   * Applies the intensity transformation to the input image state and returns the grayscale luma
   * transformed image.
   *
   * @param sourceImage The original image state to be transformed.
   * @return A new IImageState object representing the grayscale luma transformed image.
   */
  @Override
  public IImageState run(IImageState sourceImage) {
    IImage newImage = new ImageImpl(sourceImage.getHeight(), sourceImage.getWidth());
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int sum = sourceImage.getRedChannel(row, col) + sourceImage.getGreenChannel(row,
            col) + sourceImage.getBlueChannel(row, col);
        int average = sum / 3;
        newImage.setPixel(row, col, average, average, average);
      }
    }
    System.out.println("grey-scale luma has finished");
    return newImage;
  }
}
