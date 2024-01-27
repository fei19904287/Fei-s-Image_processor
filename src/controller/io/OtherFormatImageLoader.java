package controller.io;

import model.IImageState;
import model.ImageImpl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * This class is responsible for loading an image from a file in a format other than BMP
 * and converting it into an IImageState object to be used in the model.
 */
public class OtherFormatImageLoader implements IImageLoader {
  private final String filepath;

  /**
   * Constructs a new OtherFormatImageLoader instance with the given file path.
   *
   * @param filepath The file path of the image to be loaded.
   */
  public OtherFormatImageLoader(String filepath) {
    this.filepath = Objects.requireNonNull(filepath);
  }

  /**
   * Loads the image from the specified file and converts it into an IImageState object.
   *
   * @return An IImageState object representing the loaded image.
   */
  @Override
  public IImageState run() {
    ImageImpl imageObj = null;
    try {
      File bmpFile = new File(filepath);
      BufferedImage image = ImageIO.read(bmpFile);
      if (image == null) {
        System.out.println("Error: Unable to read the " + filepath + " file.");
        System.exit(0);
      }
      // Get the image dimensions
      int width = image.getWidth();
      int height = image.getHeight();
      imageObj = new ImageImpl(height, width);
      // Loop through each pixel and print its value
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int rgb = image.getRGB(x, y);
          // Extract individual color components (red, green, blue, alpha) from the pixel value
          int red = (rgb >> 16) & 0xFF;
          int green = (rgb >> 8) & 0xFF;
          int blue = rgb & 0xFF;
          imageObj.setPixel(y, x, red, green, blue);
        }
      }
    } catch (IOException e) {
      System.out.println("Error: Failed to read the " + filepath + " file.");
    }
    return imageObj;
  }
}
