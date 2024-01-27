package controller.commands;

import controller.io.IImageLoader;
import controller.io.OtherFormatImageLoader;
import model.IImageState;
import model.ImageDatabase;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * An load command class to load other image formats command.
 */
public class LoadOtherFormatCommand implements ICommand {

  /**
   * This will execute the passing in command from controller.
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
      throw new IllegalStateException("second argument must be a source path");
    }
    String filePath = scanner.next();
    if (!scanner.hasNext()) {
      throw new IllegalStateException("third argument must be an source image Id");
    }
    String sourceImageId = scanner.next();
    IImageLoader loader = new OtherFormatImageLoader(filePath);
    IImageState sourceImage = loader.run();
    model.add(sourceImageId, sourceImage);
  }
}
