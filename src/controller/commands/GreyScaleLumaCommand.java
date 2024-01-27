package controller.commands;

import model.IImageState;
import model.ImageDatabase;
import model.transformations.ITransformation;
import model.transformations.LumaTransformation;

import java.util.Objects;
import java.util.Scanner;

/**
 * a luma command class to luma the image.
 */
public class GreyScaleLumaCommand implements ICommand {

  /**
   * to excute the luma command.
   *
   * @param scanner the input command string
   * @param model   the image-database
   */
  @Override
  public void run(Scanner scanner, ImageDatabase model) {
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
    ITransformation lumaTransformation = new LumaTransformation();
    IImageState lumaedImage = lumaTransformation.run(sourceImage);
    model.add(afterGreyScaleId, lumaedImage);
  }
}
