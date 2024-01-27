package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple implementation of the {@link IImageDatabase} interface that stores images in a HashMap.
 */
public class ImageDatabase implements IImageDatabase {
  private final Map<String, IImageState> images;

  /**
   * Constructs an empty ImageDatabase.
   */
  public ImageDatabase() {
    this.images = new HashMap<String, IImageState>();
  }

  /**
   * Adds an image to the database with the specified ID.
   *
   * @param id    The unique identifier for the image.
   * @param image The {@link IImageState} object representing the image to be added.
   * @throws IllegalArgumentException If the provided ID or image is null.
   */
  @Override
  public void add(String id, IImageState image) {
    if (id == null || image == null) {
      throw new IllegalArgumentException("id or image is null");
    }
    this.images.put(id, image);

  }

  /**
   * Retrieves the image with the specified ID from the database.
   *
   * @param id The unique identifier of the image to retrieve.
   * @return The {@link IImageState} object representing the image with the given ID.
   * @throws NullPointerException If the provided ID is null.
   */
  @Override
  public IImageState get(String id) {
    Objects.requireNonNull(id);
    return this.images.get(id);
  }
}
