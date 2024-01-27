package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The  My canvas class represent the image will be showed on it..
 */
public class MyCanvas extends JPanel implements IMyCanvas {
  private BufferedImage image;

  /**
   * Instantiates a new My canvas.
   *
   * @param image the image
   */
  public MyCanvas(BufferedImage image) {
    this.image = image;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      g.drawImage(image, 0, 0, this);
    }
  }

  /**
   * Render image.
   *
   * @param image the image
   */
  @Override
  public void renderImage(BufferedImage image) {
    this.image = image;
    repaint();
  }

  @Override
  public Dimension getPreferredSize() {
    //If an image is available, return its size as the preferred size
    if (image != null) {
      return new Dimension(image.getWidth(), image.getHeight());
    } else {
      // If no image is available, return a default size (e.g., 400x300)
      return new Dimension(500, 500);
    }
  }
}

