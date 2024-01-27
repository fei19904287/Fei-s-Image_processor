package controller.commands;

import model.IImageState;
import model.ImageDatabase;
import model.transformations.FilterTransformation;
import model.transformations.ITransformation;

import java.awt.image.Kernel;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * A sharpen command class to handle the sharpen command.
 */
public class SharpenCommand implements ICommand {

  /**
   * This method handles the sharpen command.
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
    float[] sharpenData = {-1.0f / 8, -1.0f / 8, -1.0f / 8, -1.0f / 8, -1.0f / 8,
        -1.0f / 8, 1.0f / 4, 1.0f / 4, 1.0f / 4, -1.0f / 8,
        -1.0f / 8, 1.0f / 4, 1.0f, 1.0f / 4, -1.0f / 8,
        -1.0f / 8, 1.0f / 4, 1.0f / 4, 1.0f / 4, -1.0f / 8,
        -1.0f / 8, -1.0f / 8, -1.0f / 8, -1.0f / 8, -1.0f / 8};
    Kernel sharpenKernal = new Kernel(5, 5, sharpenData);
    ITransformation filterTransformation = new FilterTransformation(sharpenKernal);
    IImageState sharpenedImage = filterTransformation.run(sourceImage);
    model.add(destinationId, sharpenedImage);
  }
}
