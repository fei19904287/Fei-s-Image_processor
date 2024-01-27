package controller.commands;

import model.IImageState;
import model.ImageDatabase;
import model.transformations.ColorTransformation;
import model.transformations.ITransformation;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * a sepia command class to handel the sepia command.
 */
public class SepiaCommand implements ICommand {

  /**
   * This method will execute the sepia command.
   *
   * @param scanner the input command string
   * @param model   the image-database
   * @throws FileNotFoundException when can't find file
   */
  @Override
  public void run(Scanner scanner, ImageDatabase model) throws FileNotFoundException {
    Objects.requireNonNull(model);
    Objects.requireNonNull(scanner);
    if (!scanner.hasNext()) {
      throw new IllegalStateException("second argument must be a source imageId");
    }
    String sourceImageId = scanner.next();
    if (!scanner.hasNext()) {
      throw new IllegalStateException("third argument must be an destination image Id");
    }
    String destinationId = scanner.next();
    IImageState sourceImage = model.get(sourceImageId);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified id doesn't exist");
    }
    float[] colorData = {0.393f, 0.769f, 0.189f, 0.349f, 0.686f, 0.168f, 0.272f, 0.534f, 0.131f};
    ITransformation colorTransformation = new ColorTransformation(colorData);
    IImageState coloredImage = colorTransformation.run(sourceImage);
    model.add(destinationId, coloredImage);
  }
}
