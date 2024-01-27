package model.transformations;

import model.IImage;
import model.IImageState;
import model.ImageImpl;

/**
 * A transformation class that converts an input image to greyscale using the luma method.
 * The luma method calculates the weighted sum of the RGB channels to represent the intensity of
 * each pixel.The resulting greyscale image will have the same dimensions as the original image.
 */
public class LumaTransformation implements ITransformation {

  /**
   * Applies the luma transformation to the input image state and returns the grayscale luma
   * transformed image.The luma method calculates the weighted sum of the red, green, and blue
   * channels of each pixel to determine its intensity. The resulting grayscale pixel has the
   * same value for each RGB channel.
   *
   * @param sourceImage The original image state to be transformed.
   * @return A new IImageState object representing the grayscale luma transformed image.
   */
  @Override
  public IImageState run(IImageState sourceImage) {
    IImage newImage = new ImageImpl(sourceImage.getHeight(), sourceImage.getWidth());
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int weightedSum =
            (int) (0.2126 * sourceImage.getRedChannel(row, col)
                + 0.7152 * sourceImage.getGreenChannel(row, col)
                + 0.0722 * sourceImage.getBlueChannel(row, col));
        newImage.setPixel(row, col, weightedSum, weightedSum, weightedSum);
      }
    }
    System.out.println("grey-scale luma has finished");
    return newImage;
  }
}
