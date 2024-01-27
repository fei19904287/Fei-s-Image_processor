package controller.commands;

import controller.io.IImageSaver;
import controller.io.PPMImageSaver;
import model.IImageState;
import model.ImageDatabase;

import java.util.Objects;
import java.util.Scanner;

/**
 * a save command class to save the image to a ppm file.
 */
public class SavePPMCommand implements ICommand {

  /**
   * to execute the saving commands.
   *
   * @param scanner the input command string
   * @param model   the image-database
   */
  @Override
  public void run(Scanner scanner, ImageDatabase model) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(scanner);
    if (!scanner.hasNext()) {
      throw new IllegalStateException("second argument must be a destination path");
    }
    String savePath = scanner.next();
    if (!scanner.hasNext()) {
      throw new IllegalStateException("third argument must be an source imageId");
    }
    String toBeSavedImageId = scanner.next();
    Appendable output = new StringBuilder();
    IImageState imageToBeSaved = model.get(toBeSavedImageId);
    IImageSaver imageSaver = new PPMImageSaver(savePath, imageToBeSaved, output);
    imageSaver.run();
  }
}
