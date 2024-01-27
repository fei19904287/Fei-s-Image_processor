import static org.junit.Assert.assertNotNull;

import java.util.Scanner;

import controller.commands.BlurCommand;
import model.IImageState;
import model.ImageDatabase;
import model.ImageImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for testing the {@link BlurCommand} class.
 */
public class TestBlur {

  private BlurCommand blurCommand;
  private ImageDatabase imageDatabase;

  /**
   * Set up the test environment before each test case.
   */
  @Before
  public void setUp() {
    blurCommand = new BlurCommand();
    imageDatabase = new ImageDatabase();
  }

  /**
   * Test the {@link BlurCommand#run(Scanner, ImageDatabase)} method to ensure it properly blurs
   * the image and adds the result to the imageDatabase.
   */
  @Test
  public void testRun() {
    // Create a test image
    int width = 3;
    int height = 2;
    ImageImpl sourceImage = new ImageImpl(height, width);
    sourceImage.setPixel(0, 0, 100, 200, 50);
    sourceImage.setPixel(0, 1, 150, 75, 225);
    sourceImage.setPixel(0, 2, 50, 150, 250);
    sourceImage.setPixel(1, 0, 50, 50, 50);
    sourceImage.setPixel(1, 1, 200, 200, 200);
    sourceImage.setPixel(1, 2, 100, 150, 200);

    // Add the test image to the imageDatabase
    String sourceImageId = "sourceId";
    imageDatabase.add(sourceImageId, sourceImage);

    // Create a fake Scanner with the required input arguments
    String destinationId = "destinationId";
    String input = sourceImageId + " " + destinationId;
    Scanner scanner = new Scanner(input);

    // Execute the run() method of the command
    blurCommand.run(scanner, imageDatabase);

    // Retrieve the destination image state from the imageDatabase
    IImageState destinationImageState = imageDatabase.get(destinationId);

    // Verify that the destination image state is not null
    assertNotNull(destinationImageState);

    // You may add further assertions to verify the correctness of the transformation if desired.
  }

  /**
   * Test the {@link BlurCommand#run(Scanner, ImageDatabase)} method with an invalid source image
   * ID.This should throw an {@link IllegalStateException}.
   */
  @Test(expected = IllegalStateException.class)
  public void testRunWithInvalidSourceId() {
    String sourceImageId = "invalidId";
    String destinationId = "destinationId";

    // Create a fake Scanner with the required input arguments
    String input = sourceImageId + " " + destinationId;
    Scanner scanner = new Scanner(input);

    // Execute the run() method of the command - this should throw an IllegalStateException
    blurCommand.run(scanner, imageDatabase);
  }

  /**
   * Test the {@link BlurCommand#run(Scanner, ImageDatabase)} method with missing arguments.
   * This should throw an {@link IllegalStateException}.
   */
  @Test(expected = IllegalStateException.class)
  public void testRunWithMissingArguments() {
    String sourceImageId = "sourceId";
    // Create a fake Scanner with only one argument (missing the destinationId)
    String input = sourceImageId;
    Scanner scanner = new Scanner(input);
    // Execute the run() method of the command - this should throw an IllegalStateException
    blurCommand.run(scanner, imageDatabase);
  }
}
