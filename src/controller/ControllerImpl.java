package controller;

import model.ImageDatabase;
import controller.commands.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * a controller class to take the input string,model,appendable,command map to execute the image
 * processing.
 */
public class ControllerImpl implements IController {
  private final Readable input;
  private final ImageDatabase model;
  private final Appendable appendable;
  private final Map<String, ICommand> commandMap;

  /**
   * to compose a contractor to handle the model behavior.
   *
   * @param input      the input commands string.
   * @param model      the image-database.
   * @param appendable to accumulate the message displayed
   * @param commandMap to store all the commands to be executed
   */
  public ControllerImpl(Readable input, ImageDatabase model, Appendable appendable,
                        Map<String, ICommand> commandMap) {
    this.input = Objects.requireNonNull(input);
    this.model = Objects.requireNonNull(model);
    this.appendable = Objects.requireNonNull(appendable);
    this.commandMap = new HashMap<String, ICommand>();
    this.commandMap.put("load", new LoadPPMCommand());
    this.commandMap.put("load-jpeg", new LoadOtherFormatCommand());
    this.commandMap.put("load-png", new LoadOtherFormatCommand());
    this.commandMap.put("save", new SavePPMCommand());
    this.commandMap.put("brighten", new BrightenCommand());
    this.commandMap.put("blur", new BlurCommand());
    this.commandMap.put("sharpen", new SharpenCommand());
    this.commandMap.put("greyscale", new GreyscaleCommand());
    this.commandMap.put("sepia", new SepiaCommand());
    this.commandMap.put("value-component", new GreyScaleValueCommand());
    this.commandMap.put("intensity-component", new GreyScaleIntensityCommand());
    this.commandMap.put("luma-component", new GreyScaleLumaCommand());
    this.commandMap.put("red-component", new VisualizeRedCommand());
    this.commandMap.put("green-component", new VisualizeGreenCommand());
    this.commandMap.put("blue-component", new VisualizeBlueCommand());
    this.commandMap.put("save-jpeg", new SaveJPEGCommand());
    this.commandMap.put("save-png", new SavePNGCommand());
  }

  /**
   * a helpter method to append message.
   *
   * @param message the message to be displayed
   */
  private void write(String message) {
    try {
      this.appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("writing to the appendable failed");
    }
  }

  /**
   * a controller method to execute the image processing.
   */
  @Override
  public void start() {
    Scanner scanner = new Scanner(this.input);
    System.out.print("Please enter a command: ");
    String command = scanner.next().toLowerCase();
    System.out.println("command: " + command);
    if (command.equalsIgnoreCase("quit")) {
      System.out.println("Quit，bye!");
      System.exit(0);
    }
    ICommand commandToRun = this.commandMap.getOrDefault(command, null);
    System.out.println("CTR: " + commandToRun);
    if (commandToRun == null) {
      write("Invalid command");
    }
    try {
      commandToRun.run(scanner, this.model);
    } catch (IllegalStateException e) {
      write(e.getMessage());
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
