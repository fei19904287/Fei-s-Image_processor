import static org.junit.Assert.assertEquals;

import org.junit.Test;
import model.IImageState;
import model.ImageImpl;
import model.transformations.ITransformation;
import model.transformations.RedTransformation;

/**
 * a Junit test for the recTransformatioon.
 */
public class RedTransformationTest {

  /**
   * Test all channel equal to red.
   */
  @Test
  public void testRedTransformation() {
    // Create a sample colored image
    int width = 2;
    int height = 2;
    ImageImpl sourceImage = new ImageImpl(height, width);
    sourceImage.setPixel(0, 0, 255, 0, 0); // Red pixel
    sourceImage.setPixel(0, 1, 0, 255, 0); // Green pixel
    sourceImage.setPixel(1, 0, 0, 0, 255); // Blue pixel
    sourceImage.setPixel(1, 1, 255, 255, 0); // Yellow pixel
    // Set other pixels with random colors for testing
    // Create the transformation object
    ITransformation transformation = new RedTransformation();
    // Apply the transformation
    IImageState transformedImage = transformation.run(sourceImage);
    // Verify the result (check if the transformed image has only red channel values)
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int red = transformedImage.getRedChannel(row, col);
        int green = transformedImage.getGreenChannel(row, col);
        int blue = transformedImage.getBlueChannel(row, col);
        // All RGB values should have the same red value as the original image
        assertEquals(sourceImage.getRedChannel(row, col), red);
        // Green and blue channels should equal to red
        assertEquals(red, green);
        assertEquals(red, blue);
      }
    }
  }
}

