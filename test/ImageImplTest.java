import static org.junit.Assert.assertEquals;

import model.ImageImpl;
import org.junit.Test;

/**
 * a Junit test for the ImageImpl class.
 */
public class ImageImplTest {

  /**
   * Test constructor and get width get Height.
   */
  @Test
  public void testImageCreation() {
    int width = 4;
    int height = 3;
    ImageImpl image = new ImageImpl(height, width);
    assertEquals(width, image.getWidth());
    assertEquals(height, image.getHeight());
  }

  /**
   * test getchannel out of range.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetRedChannelOutOfBounds() {
    ImageImpl image = new ImageImpl(3, 3);
    // The image is 3x3, so the valid range for x and y is 0 to 2
    image.getRedChannel(3, 3);
  }

  /**
   * test set and get pixel.
   */
  @Test
  public void testSetAndGetPixel() {
    ImageImpl image = new ImageImpl(2, 2);
    int x = 0;
    int y = 1;
    int r = 100;
    int g = 150;
    int b = 200;
    image.setPixel(x, y, r, g, b);
    assertEquals(r, image.getRedChannel(x, y));
    assertEquals(g, image.getGreenChannel(x, y));
    assertEquals(b, image.getBlueChannel(x, y));
  }

  /**
   * Test set pixel out of bound.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelOutOfBounds() {
    ImageImpl image = new ImageImpl(2, 2);
    // The image is 2x2, so the valid range for x and y is 0 to 1
    image.setPixel(2, 2, 100, 150, 200);
  }
}

