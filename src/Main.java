import controller.ControllerImpl;
import controller.ControllerView;
import controller.IController;
import controller.IControllerView;
import controller.commands.ICommand;
import model.ImageDatabase;
import view.View;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The entrance of the image processing.
 */
public class Main {
  /**
   * The entrance method the execute the program.
   *
   * @param args the command lines
   * @throws IOException when couldn't execute commands.
   */
  public static void main(String[] args) throws IOException {
    Readable input;
    Readable input2;
    ImageDatabase model;
    Appendable appendable;
    Map<String, ICommand> commandMap;
    appendable = new StringBuilder();
    model = new ImageDatabase();
    commandMap = new HashMap<>();
    View view = new View();
    IControllerView controllerView = new ControllerView(model, view);
    if (args.length == 0) {
      controllerView.run();
    }
    if (args.length > 0 && args[0].equalsIgnoreCase("-file")) {
      // If a script file is provided, execute the script and exit
      if (args.length < 2) {
        System.err.println("Missing script file name.");
        return;
      }
      String scriptFileName = args[1];
      input = new java.io.FileReader(scriptFileName);
      try (Scanner scanner = new Scanner(input)) {
        while (scanner.hasNextLine()) {
          String command = scanner.nextLine();
          Readable fileLineCommand = new StringReader(command);
          IController imageController = new ControllerImpl(fileLineCommand, model, appendable,
              commandMap);
          imageController.start();
        }
      }
      input2 = new java.io.InputStreamReader(System.in);
      while (!input2.toString().equalsIgnoreCase("quit")) {
        IController imageController = new ControllerImpl(input2, model, appendable, commandMap);
        imageController.start();
      }
      System.out.println("end of the main");
    }
    if (args.length == 1 && args[0].equals("-text")) {
      input2 = new java.io.InputStreamReader(System.in);
      while (!input2.toString().equalsIgnoreCase("quit")) {
        IController imageController = new ControllerImpl(input2, model, appendable, commandMap);
        imageController.start();
      }
      System.out.println("end of the main");
    }
  }
}
