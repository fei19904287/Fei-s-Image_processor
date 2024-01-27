package model.transformations;

import model.IImage;
import model.IImageState;
import model.ImageImpl;

/**
 * a class to execute the greyscale blue logic.
 */
public class BlueTransformation implements ITransformation {

  /**
   * to execute the greyscale blue the image.
   *
   * @param sourceImage the image to be processed.
   * @return an greyscaled blue image.
   */
  @Override
  public IImageState run(IImageState sourceImage) {
    IImage newImage = new ImageImpl(sourceImage.getHeight(), sourceImage.getWidth());
    for (int row = 0; row < sourceImage.getHeight(); row++) {
      for (int col = 0; col < sourceImage.getWidth(); col++) {
        int newR = sourceImage.getBlueChannel(row, col);
        int newG = sourceImage.getBlueChannel(row, col);
        int newB = sourceImage.getBlueChannel(row, col);
        newImage.setPixel(row, col, newR, newG, newB);
      }
    }
    System.out.println("grey-scale value has finished");
    return newImage;
  }
}
