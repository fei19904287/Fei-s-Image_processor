package model;

/**
 * an interface to represent the image model.
 */
public interface IImageDatabase {

  /**
   * to add the image to the map.
   *
   * @param id    the name of the image
   * @param image the image to be added
   */
  void add(String id, IImageState image);

  /**
   * the get the image by its id.
   *
   * @param id the name of the image
   * @return an image
   */
  IImageState get(String id);
}
