package view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 * An interface for the MyCanvas class to define the methods that need to be implemented.
 */
public interface IMyCanvas {

  /**
   * Render the given image on the canvas.
   *
   * @param image the image to render
   */
  void renderImage(BufferedImage image);
}

