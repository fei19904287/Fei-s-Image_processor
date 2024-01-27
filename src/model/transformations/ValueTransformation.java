package model.transformations;

import model.IImage;
import model.IImageState;
import model.ImageImpl;

/**
 * This class represents a value transformation that converts an input image to grayscale using the
 * value method.The value method selects the maximum value among the RGB channels for each pixel to
 * represent its intensity.The resulting grayscale image will have the same dimensions as the
 * original image.It implements the ITransformation interface.
 */
public class ValueTransformation implements ITransformation {

  /**
   * Applies the value transformation to the input image state and returns the grayscale transformed
   * image using the value method.The value method selects the maximum value among the red, green,
   * and blue channels of each pixel to determine its intensity.The resulting grayscale pixel will
   * have the same maximum value for each RGB channel.
   *
   * @param sourceImage The original image state to be transformed.
   * @return A new IImageState object representing the grayscale transformed image using the
   *        value method.
   */
  @Override
  public IImageState run(IImageState sourceImage) {
    IImage newImage = new ImageImpl(sourceImage.getHeight(), sourceImage.getWidth());
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int max = Math.max(sourceImage.getRedChannel(row, col),
            Math.max(sourceImage.getGreenChannel(row, col),
                sourceImage.getBlueChannel(row, col)));
        newImage.setPixel(row, col, max, max, max);
      }
    }
    System.out.println("grey-scale value has finished");
    return newImage;
  }
}
