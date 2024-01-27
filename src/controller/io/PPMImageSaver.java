package controller.io;

import model.IImageState;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * an image saving class to save the image to the give path.
 */
public class PPMImageSaver implements IImageSaver {
  private final String pathToSave;
  private final IImageState image;
  private Appendable output;

  /**
   * an class to save the image to the given path.
   *
   * @param pathToSave an saving path to store the file
   * @param image      the source image
   * @param output     an appendable class
   */
  public PPMImageSaver(String pathToSave, IImageState image,
                       Appendable output
  ) {
    this.pathToSave = Objects.requireNonNull(pathToSave);
    this.image = Objects.requireNonNull(image);
    this.output = output;
  }

  private void write(String message) {
    try {
      this.output.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("write failed");
    }
  }

  /**
   * to excute the save command to save the ppm file.
   */
  @Override
  public void run() {
    System.out.println("beginning of the save");
    int width = image.getWidth();
    int height = image.getHeight();
    try {
      File file = new File(pathToSave);
      if (file.createNewFile()) {
        System.out.println("File created successfully.");
      } else {
        System.out.println("File already exists.");
      }
      FileWriter writer = new FileWriter(file);
      // Writing PPM header
      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write("255" + "\n");

      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          int r = image.getRedChannel(row, col);
          int g = image.getGreenChannel(row, col);
          int b = image.getBlueChannel(row, col);
          writer.write(r + " " + g + " " + b + " ");
        }
      }
      writer.close();
      System.out.println("PPM file '" + file + "' has been created.");
    } catch (IOException e) {
      System.out.println("An error occurred while writing to the file: " + e.getMessage());
    }
  }
}
