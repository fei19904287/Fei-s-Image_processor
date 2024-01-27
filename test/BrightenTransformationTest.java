import model.IImageState;
import model.ImageImpl;
import model.transformations.BrightenTransformation;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * a Junit test for the BrightenTransformation class.
 */
public class BrightenTransformationTest {

  /**
   * Test after the transformation, value incremented and within the range 0 to 255.
   */
  @Test
  public void testBrightenTransformation() {
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

    // Apply the BrightenTransformation with a brighten value of 50
    int brightenValue = 50;
    BrightenTransformation brightenTransformation = new BrightenTransformation(brightenValue);
    IImageState transformedImage = brightenTransformation.run(sourceImage);

    // Assert the results
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int expectedRedValue = clamp(sourceImage.getRedChannel(row, col) + brightenValue);
        int actualRedValue = transformedImage.getRedChannel(row, col);
        assertEquals(expectedRedValue, actualRedValue);

        int expectedGreenValue = clamp(sourceImage.getGreenChannel(row, col) + brightenValue);
        int actualGreenValue = transformedImage.getGreenChannel(row, col);
        assertEquals(expectedGreenValue, actualGreenValue);

        int expectedBlueValue = clamp(sourceImage.getBlueChannel(row, col) + brightenValue);
        int actualBlueValue = transformedImage.getBlueChannel(row, col);
        assertEquals(expectedBlueValue, actualBlueValue);
      }
    }
  }

  /**
   * Test after the transformation, value incremented and within the range 0 to 255.
   */
  @Test
  public void testBrightenTransformationNeg() {
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

    // Apply the BrightenTransformation with a brighten value of -50
    int brightenValue = -50;
    BrightenTransformation brightenTransformation = new BrightenTransformation(brightenValue);
    IImageState transformedImage = brightenTransformation.run(sourceImage);

    // Assert the results
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int expectedRedValue = clamp(sourceImage.getRedChannel(row, col) + brightenValue);
        int actualRedValue = transformedImage.getRedChannel(row, col);
        assertEquals(expectedRedValue, actualRedValue);

        int expectedGreenValue = clamp(sourceImage.getGreenChannel(row, col) + brightenValue);
        int actualGreenValue = transformedImage.getGreenChannel(row, col);
        assertEquals(expectedGreenValue, actualGreenValue);

        int expectedBlueValue = clamp(sourceImage.getBlueChannel(row, col) + brightenValue);
        int actualBlueValue = transformedImage.getBlueChannel(row, col);
        assertEquals(expectedBlueValue, actualBlueValue);
      }
    }
  }

  /**
   * a helper method to clamp the vlue in range.
   *
   * @param value the value to be processed.
   * @return a clammed value.
   */
  private int clamp(int value) {
    if (value < 0) {
      return 0;
    }
    if (value > 255) {
      return 255;
    }
    return value;
  }
}
