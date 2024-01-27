package model.transformations;

import model.IImage;
import model.IImageState;
import model.ImageImpl;

/**
 * This class represents a green transformation that converts an input image to grayscale by keeping
 * only the green channel.It implements the ITransformation interface.
 */
public class GreenTransformation implements ITransformation {

  /**
   * Applies the green transformation to the input image state and returns the transformed grayscale
   * image.
   *
   * @param sourceImage The original image state to be transformed.
   * @return A new IImageState object representing the grayscale transformed image with only the
   *     green channel.
   */
  @Override
  public IImageState run(IImageState sourceImage) {
    IImage newImage = new ImageImpl(sourceImage.getHeight(), sourceImage.getWidth());
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int newR = sourceImage.getGreenChannel(row, col);
        int newG = sourceImage.getGreenChannel(row, col);
        int newB = sourceImage.getGreenChannel(row, col);
        newImage.setPixel(row, col, newR, newG, newB);
      }
    }
    System.out.println("grey-scale value has finished");
    return newImage;
  }
}
