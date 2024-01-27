import static org.junit.Assert.assertEquals;

import model.IImage;
import model.IImageState;
import model.ImageImpl;
import model.transformations.ColorTransformation;
import org.junit.Test;

/**
 * A JUnit test class to test the ColorTransformation class.
 */
public class TestColorTransformation {

  /**
   * Test the ColorTransformation class by applying a color transformation to an image.
   */
  @Test
  public void testColorTransformation() {
    // Create the input image state (You need to implement the IImageState interface for testing)
    IImageState sourceImage = createTestImageState();

    // Define the colordata for transformation (example values, you can adjust them as needed)
    float[] colordata = {2.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};

    // Create the ColorTransformation object
    ColorTransformation transformation = new ColorTransformation(colordata);

    // Apply the transformation
    IImageState transformedImage = transformation.run(sourceImage);

    // Validate the result (You need to implement appropriate methods in IImageState)
    assertEquals(20, transformedImage.getRedChannel(1, 1));
    assertEquals(10, transformedImage.getGreenChannel(1, 1));
    assertEquals(10, transformedImage.getBlueChannel(1, 1));
  }

  /**
   * Creates a test image state for the ColorTransformation test.
   *
   * @return An IImageState object representing the test image state.
   */
  private IImageState createTestImageState() {
    int width = 3;
    int height = 3;
    IImage sourceImage = new ImageImpl(width, height);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sourceImage.setPixel(i, j, 10, 1, 1);
      }
    }
    return sourceImage;
  }
}

