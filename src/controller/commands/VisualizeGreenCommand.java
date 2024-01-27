package controller.commands;

import model.IImageState;
import model.ImageDatabase;
import model.transformations.GreenTransformation;
import model.transformations.ITransformation;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * a visualizeGreen class to green the image.
 */
public class VisualizeGreenCommand implements ICommand {

  /**
   * to excute the visualizegreen command.
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
    ITransformation greenTransformation = new GreenTransformation();
    IImageState greenImage = greenTransformation.run(sourceImage);
    model.add(afterGreyScaleId, greenImage);
  }
}
