package model.transformations;

import model.IImageState;

/**
 * an interface to represent all the transformations.
 */
public interface ITransformation {

  /**
   * to execute the transformation process.
   *
   * @param sourceImage the image to be processed.
   * @return an processed image.
   */
  IImageState run(IImageState sourceImage);
}
