import model.IImage;
import model.IImageState;
import model.ImageImpl;
import model.transformations.FilterTransformation;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import java.awt.image.Kernel;

/**
 * A test class for testing the FilterTransformation class.
 */
public class FilterTransformationTest {

  /**
   * Test the FilterTransformation by applying a custom kernel to a sample input image and verifying
   * the result.
   */
  @Test
  public void testFilterTransformation() {
    // Create a sample input image
    int width = 3;
    int height = 3;
    IImage sourceImage = new ImageImpl(width, height);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        sourceImage.setPixel(i, j, 2, 2, 2);
      }
    }
    // call the filtertransformation
    float[] matrix = {
        0, 0, 10,
        0, 1, 0,
        0, 0, 0
    };
    Kernel filterKernel = new Kernel(3, 3, matrix);
    FilterTransformation filterTransformation = new FilterTransformation(filterKernel);
    IImageState filterImage = filterTransformation.run(sourceImage);
    assertEquals(filterImage.getBlueChannel(1, 1), 22);
  }
}

