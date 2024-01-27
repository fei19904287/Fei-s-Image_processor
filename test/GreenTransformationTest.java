import model.IImageState;
import model.ImageImpl;
import model.transformations.GreenTransformation;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * a Junit test for the GreenTransformation class.
 */
public class GreenTransformationTest {

  /**
   * test after the transformation all channel value equal to original green value.
   */
  @Test
  public void testGreenTransformation() {
    // Create a sample source image
    int width = 3;
    int height = 2;
    ImageImpl sourceImage = new ImageImpl(height, width);
    sourceImage.setPixel(0, 0, 100, 200, 50);
    sourceImage.setPixel(0, 1, 150, 75, 225);
    sourceImage.setPixel(0, 2, 50, 150, 250);
    sourceImage.setPixel(1, 0, 50, 50, 50);
    sourceImage.setPixel(1, 1, 200, 200, 200);
    sourceImage.setPixel(1, 2, 100, 150, 200);

    // Apply the GreenTransformation
    GreenTransformation greenTransformation = new GreenTransformation();
    IImageState transformedImage = greenTransformation.run(sourceImage);

    // Assert the results
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int expectedGreenValue = sourceImage.getGreenChannel(row, col);
        int actualGreenValue = transformedImage.getGreenChannel(row, col);

        // Assert that the green channel values in the transformed image are equal to the original
        // green channel values.
        assertEquals(expectedGreenValue, actualGreenValue);

        // Assert that the red and blue channel values in the transformed image are also green
        int actualRedValue = transformedImage.getRedChannel(row, col);
        assertEquals(expectedGreenValue, actualRedValue);

        int actualBlueValue = transformedImage.getBlueChannel(row, col);
        assertEquals(expectedGreenValue, actualBlueValue);
      }
    }
  }
}
