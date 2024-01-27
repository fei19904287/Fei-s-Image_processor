import model.IImageState;
import model.ImageImpl;
import model.transformations.LumaTransformation;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * a Junit test for the lumaTransformation class.
 */
public class LumaTransformationTest {

  /**
   * test get correct lumavalue.
   */
  @Test
  public void testLumaTransformation() {
    // Create a sample colored image
    int width = 2;
    int height = 2;
    ImageImpl sourceImage = new ImageImpl(height, width);
    sourceImage.setPixel(0, 0, 255, 0, 0); // Red pixel
    sourceImage.setPixel(0, 1, 0, 255, 0); // Green pixel
    sourceImage.setPixel(1, 0, 0, 0, 255); // Blue pixel
    sourceImage.setPixel(1, 1, 255, 255, 0); // Yellow pixel
    // Create an instance of the LumaTransformation class
    LumaTransformation lumaTransformation = new LumaTransformation();

    // Apply the luma transformation to the source image
    IImageState newImage = lumaTransformation.run(sourceImage);

    // Assert that the new image is not null
    assertNotNull(newImage);

    // Assert the dimensions of the new image are the same as the source image
    assertEquals(sourceImage.getHeight(), newImage.getHeight());
    assertEquals(sourceImage.getWidth(), newImage.getWidth());

    // Assert the pixels in the new image are correctly transformed to greyscale
    assertEquals(54, newImage.getRedChannel(0, 0));
    assertEquals(182, newImage.getGreenChannel(0, 1));
    assertEquals(18, newImage.getBlueChannel(1, 0));
    assertEquals(236, newImage.getRedChannel(1, 1));
  }
}

