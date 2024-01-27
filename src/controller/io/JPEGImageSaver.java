package controller.io;

import model.IImageState;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * This class is responsible for saving an image in JPEG format to a specified file path.
 */
public class JPEGImageSaver implements IImageSaver {
  private final String pathToSave;
  private final IImageState image;

  /**
   * Constructs a new JPEGImageSaver instance.
   *
   * @param pathToSave The file path where the JPEG image will be saved.
   * @param image      The IImageState representing the image to be saved.
   * @param output     The Appendable object to which status messages will be written.
   *                   It can be set to null if not needed.
   */
  public JPEGImageSaver(String pathToSave, IImageState image, Appendable output) {
    this.pathToSave = Objects.requireNonNull(pathToSave);
    this.image = Objects.requireNonNull(image);

  }

  /**
   * Saves the image to the specified file path in JPEG format.
   * It converts the image pixel data from the IImageState to a BufferedImage and writes it to a
   * file.
   */
  @Override
  public void run() {
    System.out.println("beginning of the save");
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    // Loop through each pixel and set the color in the BufferedImage
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int[] pixel = image.getPixel(y, x);
        int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
        bufferedImage.setRGB(x, y, rgb);
      }
    }
    try {
      File outputFile = new File(pathToSave);
      ImageIO.write(bufferedImage, "jpeg", outputFile);
      System.out.println("jpeg image saved successfully.");
    } catch (IOException e) {
      System.out.println("Error: Failed to save the jpeg file.");
      e.printStackTrace();
    }
  }
}

