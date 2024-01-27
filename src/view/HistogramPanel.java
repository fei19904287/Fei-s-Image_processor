package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The type Histogram panel.
 */
public class HistogramPanel extends JPanel implements IHistorgram {
  /**
   * The Image.
   */
  BufferedImage image;
  private int[] histogramData;

  /**
   * Instantiates a new Histogram panel.
   *
   * @param image the image
   */
  public HistogramPanel(BufferedImage image) {
    this.histogramData = calculateHistogram(image);
    ;
  }

  /**
   * Render histogram data.
   *
   * @param image the image
   */
  // Method to set the histogram data
  @Override
  public void renderHistogramData(BufferedImage image) {
    this.histogramData = calculateHistogram(image);
    repaint(); // Request a repaint to update the histogram
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (histogramData != null) {
      int histogramWidth = getWidth();
      int histogramHeight = getHeight();
      int binWidth = histogramWidth / histogramData.length;
      int maxFrequency = getMaxFrequency();
      double scaleFactor = (double) histogramHeight / maxFrequency;
      // Draw the histogram bars
      for (int i = 0; i < histogramData.length; i++) {
        int binHeight = (int) (histogramData[i] * scaleFactor);
        int x = i * binWidth;
        int y = histogramHeight - binHeight;
        g.setColor(Color.red);
        g.fillRect(x, y, binWidth, binHeight);
      }
    }
  }

  private int getMaxFrequency() {
    int maxFrequency = 0;
    for (int frequency : histogramData) {
      maxFrequency = Math.max(maxFrequency, frequency);
    }
    return maxFrequency;
  }

  @Override
  public Dimension getPreferredSize() {
    //If an image is available, return its size as the preferred size
    if (image != null) {
      return new Dimension(getWidth(), getHeight());
    } else {
      //If no image is available, return a default size (e.g., 400x300)
      return new Dimension(400, 200);
    }
  }

  private static int[] calculateHistogram(BufferedImage image) {
    int[] histogramData = new int[256];
    if (image == null) {
      return histogramData;
    }
    int width = image.getWidth();
    int height = image.getHeight();
    // Loop through each pixel in the image
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = image.getRGB(x, y);
        int grayscaleValue = getGrayscaleValue(rgb);
        histogramData[grayscaleValue]++;
      }
    }
    return histogramData;
  }

  private static int getGrayscaleValue(int rgb) {
    // Extract the red, green, and blue components from the RGB value
    int red = (rgb >> 16) & 0xFF;
    int green = (rgb >> 8) & 0xFF;
    int blue = rgb & 0xFF;
    // Calculate the grayscale value using the luminance formula
    // Y = 0.299R + 0.587G + 0.114B
    int grayscaleValue = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
    // Ensure the value is within [0, 255]
    grayscaleValue = Math.min(255, Math.max(0, grayscaleValue));
    return grayscaleValue;
  }
}
