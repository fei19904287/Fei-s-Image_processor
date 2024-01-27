import controller.commands.SepiaCommand;
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
 * A test class for testing the SepiaCommand class.
 */
public class SepiaCommandTest {
  private ImageDatabase model;

  /**
   * set up before each test.
   */
  @Before
  public void setUp() {
    // Initialize or mock the ImageDatabase
    model = new ImageDatabase();
    // Add some test images to the model if needed
  }

  /**
   * Test the SepiaCommand with valid input, ensuring it runs successfully and the transformed
   * image is added to the model.
   */
  @Test
  public void testSepiaCommand_ValidInput_ShouldRunSuccessfully() {
    // Arrange
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
    SepiaCommand sepiaCommand = new SepiaCommand();
    String destinationId = "destinationImage";
    String input = sourceImageId + " " + destinationId;
    Scanner scanner = new Scanner(input);

    // Act & Assert
    try {
      sepiaCommand.run(scanner, model);
      // Check if the transformation was applied and the new image was added to the model
      assertNotNull(model.get(destinationId));
    } catch (FileNotFoundException e) {
      fail("FileNotFoundException should not be thrown in this test case.");
    }
  }

  /**
   * Test the SepiaCommand when the source image is missing, expecting an IllegalStateException to
   * be thrown.
   *
   * @throws FileNotFoundException The expected exception for this test case.
   */
  @Test(expected = IllegalStateException.class)
  public void testSepiaCommand_MissingSourceImage_ShouldThrowIllegalStateException()
      throws FileNotFoundException {
    SepiaCommand sepiaCommand = new SepiaCommand();
    String destinationId = "destinationImage";
    String input = destinationId;
    Scanner scanner = new Scanner(input);
    // Act & Assert
    sepiaCommand.run(scanner, model);
    // The method should throw an IllegalStateException due to missing source image
  }

  /**
   * Test the SepiaCommand when the destination image is missing, expecting an
   * IllegalStateException to be thrown.
   *
   * @throws FileNotFoundException The expected exception for this test case.
   */
  @Test(expected = IllegalStateException.class)
  public void testSepiaCommand_MissingDestinationImage_ShouldThrowIllegalStateException()
      throws FileNotFoundException {
    // Arrange
    SepiaCommand sepiaCommand = new SepiaCommand();
    String sourceImageId = "sourceImage";
    String input = sourceImageId;
    Scanner scanner = new Scanner(input);

    // Act & Assert
    sepiaCommand.run(scanner, model);
    // The method should throw an IllegalStateException due to missing destination image
  }

  /**
   * Test the SepiaCommand with an invalid source image, expecting an IllegalStateException to
   * be thrown.
   *
   * @throws FileNotFoundException The expected exception for this test case.
   */
  @Test(expected = IllegalStateException.class)
  public void testSepiaCommand_InvalidSourceImage_ShouldThrowIllegalStateException() throws
      FileNotFoundException {
    // Arrange
    SepiaCommand sepiaCommand = new SepiaCommand();
    String sourceImageId = "invalidSourceImage";
    String destinationId = "destinationImage";
    String input = sourceImageId + " " + destinationId;
    Scanner scanner = new Scanner(input);

    // Act & Assert
    sepiaCommand.run(scanner, model);
    // The method should throw an IllegalStateException due to invalid source image
  }
}

