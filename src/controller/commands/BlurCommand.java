package controller.commands;

import model.IImageState;
import model.ImageDatabase;
import model.transformations.FilterTransformation;
import model.transformations.ITransformation;

import java.awt.image.Kernel;
import java.util.Objects;
import java.util.Scanner;

/**
 * a blur command class to handel the input blur commands.
 */
public class BlurCommand implements ICommand {
  /**
   * to execute the blur command.
   *
   * @param scanner the input command string
   * @param model   the image-database
   */
  @Override
  public void run(Scanner scanner, ImageDatabase model) {
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
    float[] blurdata = {1.0f / 16, 1.0f / 8, 1.0f / 16, 1.0f / 8, 1.0f / 4, 1.0f / 8, 1.0f / 16,
        1.0f / 8, 1.0f / 16};
    Kernel strongBlurKernel = new Kernel(3, 3, blurdata);
    ITransformation filterTransformation = new FilterTransformation(strongBlurKernel);
    IImageState bluredImage = filterTransformation.run(sourceImage);
    model.add(destinationId, bluredImage);
  }
}

