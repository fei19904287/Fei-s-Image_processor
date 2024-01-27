package controller.commands;

import model.ImageDatabase;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * a quit command to quit the input commands.
 */
public class QuitCommand implements ICommand {

  /**
   * to excute the quit command.
   *
   * @param scanner the input command string
   * @param model   the image-database
   * @throws FileNotFoundException when couldn't find the file.
   */
  @Override
  public void run(Scanner scanner, ImageDatabase model) throws FileNotFoundException {
    System.out.println("Quit,bye！");
  }
}
