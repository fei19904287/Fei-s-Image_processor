package model.transformations;

import model.IImage;
import model.IImageState;
import model.ImageImpl;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * This class represents a filter transformation that applies a convolution operation
 * using the provided kernel on an input image.
 * It implements the ITransformation interface.
 */
public class FilterTransformation implements ITransformation {
  private final Kernel filterkernel;

  /**
   * Constructs a FilterTransformation object with the specified kernel.
   *
   * @param filterkernel The Kernel representing the filter to be applied.
   */
  public FilterTransformation(Kernel filterkernel) {
    this.filterkernel = filterkernel;
  }

  /**
   * Applies the filter transformation to the input image state and returns the transformed image.
   *
   * @param sourceImage The original image state to be transformed.
   * @return A new IImageState object representing the transformed image.
   */
  @Override
  public IImageState run(IImageState sourceImage) {
    BufferedImageOp op = null;
    op = new ConvolveOp(filterkernel);
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    // Loop through each pixel and set the color in the BufferedImage
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] pixel = sourceImage.getPixel(y, x);
        int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
        bufferedImage.setRGB(x, y, rgb);
      }
    }
    BufferedImage bufferedImageDest = new BufferedImage(sourceImage.getWidth(),
        sourceImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    op.filter(bufferedImage, bufferedImageDest);
    IImage newImage = new ImageImpl(sourceImage.getHeight(), sourceImage.getWidth());
    for (int y = 0; y < sourceImage.getHeight(); y++) {
      for (int x = 0; x < sourceImage.getWidth(); x++) {
        int rgb = bufferedImageDest.getRGB(x, y);
        // Extract individual color components (red, green, blue, alpha) from the pixel value
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        newImage.setPixel(y, x, red, green, blue);
      }
    }
    return newImage;
  }
}
