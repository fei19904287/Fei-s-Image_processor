package controller.commands;

import model.IImageState;
import model.ImageDatabase;
import model.transformations.BrightenTransformation;
import model.transformations.ITransformation;

import java.util.Objects;
import java.util.Scanner;

/**
 * a brighten class to brighten the image specified.
 */
public class BrightenCommand implements ICommand {

  /**
   * to excute the brighten command.
   *
   * @param scanner the input command string
   * @param model   the image-database
   */
  @Override
  public void run(Scanner scanner, ImageDatabase model) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(scanner);

    if (!scanner.hasNextInt()) {
      throw new IllegalStateException("Second argument must be an int.");
    }
    int value = scanner.nextInt();
    if (!scanner.hasNext()) {
      throw new IllegalStateException("Third argument must be a source image Id");
    }
    String sourceImageId = scanner.next();
    if (!scanner.hasNext()) {
      throw new IllegalStateException("Fourth argument must be an destination image Id");
    }
    String destinationId = scanner.next();

    IImageState sourceImage = model.get(sourceImageId);
    System.out.println("In bri command sourceImageId: "+sourceImageId);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified id doesn't exist");
    }
    ITransformation brightenTransformation = new BrightenTransformation(value);
    IImageState brightenedImage = brightenTransformation.run(sourceImage);
    // save it to the database(model)
    model.add(destinationId, brightenedImage);
  }
}
