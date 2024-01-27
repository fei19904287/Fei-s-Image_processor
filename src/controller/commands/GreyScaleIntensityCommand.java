package controller.commands;

import model.IImageState;
import model.ImageDatabase;
import model.transformations.ITransformation;
import model.transformations.IntensityTransformation;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * a command class to execute the intensity command.
 */
public class GreyScaleIntensityCommand implements ICommand {

  /**
   * to execute to commands to intensity the image.
   *
   * @param scanner a input command string
   * @param model   imagedabase
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
    ITransformation intensityTransformation = new IntensityTransformation();
    IImageState intensityImage = intensityTransformation.run(sourceImage);
    model.add(afterGreyScaleId, intensityImage);
  }
}
