import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import controller.commands.GreyscaleCommand;
import model.IImage;
import model.ImageDatabase;
import model.ImageImpl;
import org.junit.Test;

import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * A JUnit test class to test the GreyscaleCommand class.
 */
public class TestGreyScale {

  /**
   * Test the GreyscaleCommand class with valid arguments, where an image is transformed to
   * greyscale.
   *
   * @throws FileNotFoundException If there is an issue with file handling.
   */
  @Test
  public void run_validArguments_imageTransformed() throws FileNotFoundException {
    // Create an ImageDatabase and add an image to it
    ImageDatabase model = new ImageDatabase();
    int width = 3;
    int height = 3;
    IImage sourceImage = new ImageImpl(width, height);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sourceImage.setPixel(i, j, 1, 1, 1);
      }
    }
    model.add("image1", sourceImage);
    // Create a GreyscaleCommand instance
    GreyscaleCommand greyscaleCommand = new GreyscaleCommand();
    // Prepare the input scanner with sourceImageId and destinationId
    Scanner scanner = new Scanner("image1 image2");
    try {
      // Execute the command
      greyscaleCommand.run(scanner, model);
    } catch (IllegalStateException e) {
      fail("Should not have thrown any exceptions.");
    }
    // Check if the destination image has been added to the model
    assertNotNull(model.get("image2"));
  }

  /**
   * Test the GreyscaleCommand class when the source image ID is missing, expecting an
   * IllegalStateException.
   *
   * @throws FileNotFoundException If there is an issue with file handling.
   */
  @Test(expected = IllegalStateException.class)
  public void run_missingSourceImageId_exceptionThrown() throws FileNotFoundException {
    // Create an ImageDatabase (no need to add an image to test the exception scenario)

    // Create a GreyscaleCommand instance
    GreyscaleCommand greyscaleCommand = new GreyscaleCommand();

    // Prepare the input scanner without sourceImageId
    Scanner scanner = new Scanner("image2");

    // Execute the command and expect an IllegalStateException to be thrown
    greyscaleCommand.run(scanner, new ImageDatabase());
  }

  /**
   * Test the GreyscaleCommand class when the destination ID is missing, expecting an
   * IllegalStateException.
   *
   * @throws FileNotFoundException If there is an issue with file handling.
   */
  @Test(expected = IllegalStateException.class)
  public void run_missingDestinationId_exceptionThrown() throws FileNotFoundException {
    // Create an ImageDatabase (no need to add an image to test the exception scenario)

    // Create a GreyscaleCommand instance
    GreyscaleCommand greyscaleCommand = new GreyscaleCommand();

    // Prepare the input scanner without destinationId
    Scanner scanner = new Scanner("image1");

    // Execute the command and expect an IllegalStateException to be thrown
    greyscaleCommand.run(scanner, new ImageDatabase());
  }

  /**
   * Test the GreyscaleCommand class when the source image is not found, expecting an
   * IllegalStateException.
   *
   * @throws FileNotFoundException If there is an issue with file handling.
   */
  @Test(expected = IllegalStateException.class)
  public void run_sourceImageNotFound_exceptionThrown() throws FileNotFoundException {
    // Create an ImageDatabase (no need to add an image to test the exception scenario)
    // Create a GreyscaleCommand instance
    GreyscaleCommand greyscaleCommand = new GreyscaleCommand();
    // Prepare the input scanner with an invalid sourceImageId
    Scanner scanner = new Scanner("invalidImageId image2");
    // Execute the command and expect an IllegalStateException to be thrown
    greyscaleCommand.run(scanner, new ImageDatabase());
  }
}

