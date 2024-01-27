package controller.commands;

import controller.io.IImageLoader;
import controller.io.PPMImageLoader;
import model.IImageState;
import model.ImageDatabase;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * a load command class to load the command.
 */
public class LoadPPMCommand implements ICommand {

  /**
   * to execute the load command.
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
      throw new IllegalStateException("second argument must be a source path");
    }
    String filePath = scanner.next();
    if (!scanner.hasNext()) {
      throw new IllegalStateException("thired argument must be an source image Id");
    }
    String sourceImageId = scanner.next();

    IImageLoader loader = new PPMImageLoader(filePath);
    IImageState sourceImage = loader.run();
    model.add(sourceImageId, sourceImage);
  }
}
