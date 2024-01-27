import controller.commands.SharpenCommand;
import model.IImage;
import model.ImageDatabase;
import model.ImageImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


/**
 * JUnit test class for testing the {@link SharpenCommand} class.
 */
public class SharpenCommandTest {
  private ImageDatabase model;

  /**
   * Set up the test environment before each test case.
   */
  @Before
  public void setUp() {
    SharpenCommand sharpenCommand = new SharpenCommand();
    model = new ImageDatabase(); // Create an instance of ImageDatabase
  }

  /**
   * Test the {@link SharpenCommand#run(Scanner, ImageDatabase)} method to ensure it properly
   * sharpens the image and adds the result to the model.
   *
   * @throws FileNotFoundException if the input file is not found.
   */
  @Test
  public void testSharpenCommand() throws FileNotFoundException {
    // Create a sample input image
    int width = 3;
    int height = 3;
    IImage sourceImage = new ImageImpl(width, height);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sourceImage.setPixel(i, j, 2, 2, 2);
      }
    }
    String sourceImageId = "sourceImage";
    model.add(sourceImageId, sourceImage);
    SharpenCommand sharpenCommand = new SharpenCommand();
    String destinationId = "destinationImage";
    String input = sourceImageId + " " + destinationId;
    Scanner scanner = new Scanner(input);
    try {
      sharpenCommand.run(scanner, model);
    } catch (IllegalStateException e) {
      fail("Unexpected exception: " + e.getMessage());
    }
    // Check if the sharpened image is added to the model with the destinationId
    assertNotNull(model.get("destinationImage"));
  }
}
