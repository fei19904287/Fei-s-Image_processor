package controller.commands;

import controller.io.IImageSaver;
import controller.io.PNGImagerSaver;
import model.IImageState;
import model.ImageDatabase;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class handle the savePNG command passed from the controller.
 */
public class SavePNGCommand implements ICommand {

  /**
   * this method execute the command to save a png file.
   *
   * @param scanner the input command string
   * @param model   the image-database
   * @throws FileNotFoundException when couldn't find file
   */
  @Override
  public void run(Scanner scanner, ImageDatabase model) throws FileNotFoundException {
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
    IImageSaver imageSaver = new PNGImagerSaver(savePath, imageToBeSaved, output);
    imageSaver.run();
  }
}
