package view;

import controller.IControllerView;

import java.awt.image.BufferedImage;

/**
 * An interface for the View class to define the methods that need to be implemented.
 */
public interface IView {

  void addViewListener(IControllerView controller);

  /**
   * Add view listener to the view.
   *
   * @param listener the listener to add
   */
  void addViewListener(ViewListener listener);

  /**
   * Request focus for the main frame.
   */
  void requestFrameFocus();

  /**
   * Render the given image on the view.
   *
   * @param image the image to render
   */
  void renderImage(BufferedImage image);

  /**
   * Render the histogram based on the histogram data.
   *
   * @param image the histogram bufferImage to render
   */
  void renderHistogram(BufferedImage image);

  /**
   * Display a reminder message on the view.
   */
  void renderReminderMessage();

  void setVisible(boolean b);

  // Other methods related to updating the UI components and handling user input can be added here.
}

