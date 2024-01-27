package controller.io;

import model.IImageState;

import java.io.FileNotFoundException;

/**
 * an interface to load the image.
 */
public interface IImageLoader {

  /**
   * to execute the load the image.
   *
   * @return an image object
   * @throws FileNotFoundException when couldn't find the file
   */
  IImageState run() throws FileNotFoundException;
}
