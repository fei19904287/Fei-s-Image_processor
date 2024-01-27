package controller.commands;

import model.ImageDatabase;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * an interface to represent all the commands to be excuted.
 */
public interface ICommand {
  /**
   * to execute the command.
   *
   * @param scanner the input command string
   * @param model   the image-database
   * @throws FileNotFoundException when couldn't find the file
   */
  void run(Scanner scanner, ImageDatabase model) throws FileNotFoundException;
}
