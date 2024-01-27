package controller.commands;

import model.IImageState;
import model.ImageDatabase;
import model.transformations.ITransformation;
import model.transformations.ValueTransformation;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * a value command class to value the image.
 */
public class GreyScaleValueCommand implements ICommand {

  /**
   * to execute the value command.
   *
   * @param scanner the input command string
   * @param model   the image-database
   * @throws FileNotFoundException when couldn't find the file
   */
  @Override
  public void run(Scanner scanner, ImageDatabase model) throws FileNotFoundException {
    Objects.requireNonNull(model);
    Objects.requireNonNull(scanner);
    if (!scanner.hasNext()) {
      throw new IllegalStateException("second argument must be a source image Id");
    }
    String sourceImageId = scanner.next();
    if (!scanner.hasNext()) {
      throw new IllegalStateException("third argument must be an destination image Id");
    }
    String afterGreyScaleId = scanner.next();
    IImageState sourceImage = model.get(sourceImageId);
    if (sourceImage == null) {
      throw new IllegalStateException("Image with specified id doesn't exist");
    }

    ITransformation valueTransformation = new ValueTransformation();
    IImageState valueImage = valueTransformation.run(sourceImage);
    model.add(afterGreyScaleId, valueImage);
  }
}
