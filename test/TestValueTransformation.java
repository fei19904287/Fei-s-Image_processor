import static org.junit.Assert.assertEquals;

import org.junit.Test;
import model.IImageState;
import model.ImageImpl;
import model.transformations.ITransformation;
import model.transformations.ValueTransformation;

/**
 * a Junite test for the valueTransformation.
 */
public class TestValueTransformation {

  /**
   * Test red,blue,green are the same.
   */
  @Test
  public void testValueTransformation() {
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
    ITransformation transformation = new ValueTransformation();
    // Apply the transformation
    IImageState transformedImage = transformation.run(sourceImage);
    // Verify the result (check if the transformed image is grayscale)
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int red = transformedImage.getRedChannel(row, col);
        int green = transformedImage.getGreenChannel(row, col);
        int blue = transformedImage.getBlueChannel(row, col);
        // All RGB values should be equal for grayscale
        assertEquals(red, green);
        assertEquals(green, blue);
        assertEquals(blue, red);
      }
    }
  }
}

