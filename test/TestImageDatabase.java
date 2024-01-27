import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


import model.IImageState;
import model.ImageDatabase;
import model.ImageImpl;
import org.junit.Test;

/**
 * JUnit test class for the {@link ImageDatabase} class.
 */
public class TestImageDatabase {

  /**
   * Tests adding an image to the database and then retrieving it.
   */
  @Test
  public void testAddAndGetImage() {
    ImageDatabase imageDatabase = new ImageDatabase();
    IImageState image = createMockImageState();
    String id = "image001";
    imageDatabase.add(id, image);
    assertEquals(image, imageDatabase.get(id));
  }

  /**
   * Tests getting an image that does not exist in the database.
   */
  @Test
  public void testGetImageNotFound() {
    ImageDatabase imageDatabase = new ImageDatabase();
    String id = "nonexistentImage";

    assertNull(imageDatabase.get(id));
  }

  /**
   * Tests adding an image with a null ID, which should throw an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNullId() {
    ImageDatabase imageDatabase = new ImageDatabase();
    IImageState image = createMockImageState();

    imageDatabase.add(null, image);
  }

  /**
   * Tests adding a null image, which should throw an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNullImage() {
    ImageDatabase imageDatabase = new ImageDatabase();
    imageDatabase.add("image002", null);
  }

  /**
   * Mock implementation of IImageState for testing purposes.
   *
   * @return am image object
   */
  private IImageState createMockImageState() {
    return new ImageImpl(2, 3) {
      // Implement mock methods here or use a mock library like Mockito
    };
  }
}

