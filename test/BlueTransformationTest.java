import model.IImageState;
import model.ImageImpl;
import model.transformations.BlueTransformation;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;



/**
 * a Junit test class to test the BlueTransformation class.
 */
public class BlueTransformationTest {

  /**
   * Test blue transformation.
   */
  @Test
  public void testBlueTransformation() {
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

    // Apply the BlueTransformation
    BlueTransformation blueTransformation = new BlueTransformation();
    IImageState transformedImage = blueTransformation.run(sourceImage);

    // Assert the results
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int expectedBlueValue = sourceImage.getBlueChannel(row, col);
        int actualBlueValue = transformedImage.getBlueChannel(row, col);

        // Assert that the blue channel values in the transformed image are equal to the original
        // blue channel values
        assertEquals(expectedBlueValue, actualBlueValue);

        // Assert that the red and green channel values in the transformed image remain unchanged
        int actualRedValue = transformedImage.getRedChannel(row, col);
        assertEquals(expectedBlueValue, actualRedValue);

        int actualGreenValue = transformedImage.getGreenChannel(row, col);
        assertEquals(expectedBlueValue, actualGreenValue);
      }
    }
  }
}
