package view;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 * An interface for the HistogramPanel class to define the methods that need to be implemented.
 */
public interface IHistorgram {

  /**
   * Render histogram data on the panel.
   *
   * @param image the histogram image data to render
   */
  void renderHistogramData(BufferedImage image);
}

