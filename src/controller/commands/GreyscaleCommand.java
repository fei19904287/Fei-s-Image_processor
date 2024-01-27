package controller.commands;

import model.IImageState;
import model.ImageDatabase;
import model.transformations.ColorTransformation;
import model.transformations.ITransformation;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * a GreyScale class that can apply the Greyscale effects on an image.
 */
public class GreyscaleCommand implements ICommand {

  /**
   * This method will excute the command passing from the constructor.
   *
   * @param scanner the input command string
   * @param model   the image-database
   * @throws FileNotFoundException when coudn't find file
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
    float[] colorData = {0.2126f, 0.7152f, 0.0722f, 0.2126f, 0.7152f, 0.0722f, 0.2126f, 0.7152f,
        0.0722f};
    ITransformation colorTransformation = new ColorTransformation(colorData);
    IImageState coloredImage = colorTransformation.run(sourceImage);
    model.add(destinationId, coloredImage);
  }
}
