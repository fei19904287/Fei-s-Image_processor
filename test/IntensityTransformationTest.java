import model.IImageState;
import model.ImageImpl;
import model.transformations.IntensityTransformation;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * a Junit test for the indensityTransformation class.
 */
public class IntensityTransformationTest {

  /**
   * Test the transformed image pixel channel value equal to the original channel values average.
   */
  @Test
  public void testIntensityTransformation() {
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

    // Apply the IntensityTransformation
    IntensityTransformation intensityTransformation = new IntensityTransformation();
    IImageState transformedImage = intensityTransformation.run(sourceImage);

    // Assert the results
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int expectedAverage = (sourceImage.getRedChannel(row, col)
            + sourceImage.getGreenChannel(row, col)
            + sourceImage.getBlueChannel(row, col)) / 3;
        int actualAverage = transformedImage.getRedChannel(row, col);

        // Assert that the pixel values in the transformed image are equal to the
        // calculated averages.
        assertEquals(expectedAverage, actualAverage);
      }
    }
  }
}

