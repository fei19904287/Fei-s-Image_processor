package view;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * this class will handle all the events passed to the subscribers..
 */
public interface ViewListener {
  /**
   * Handle load jpeg event.
   *
   * @throws IOException the io exception
   */
  void handleLoadJPEGEvent() throws IOException;

  /**
   * Handle save jpeg event.
   *
   * @throws FileNotFoundException the file not found exception
   */
  void handleSaveJPEGEvent() throws FileNotFoundException;

  /**
   * Handle load png event.
   *
   * @throws IOException the io exception
   */
  void handleLoadPNGEvent() throws IOException;

  /**
   * Handle save png event.
   *
   * @throws FileNotFoundException the file not found exception
   */
  void handleSavePNGEvent() throws FileNotFoundException;

  /**
   * Handle sepia event.
   */
  void handleSepiaEvent();

  /**
   * Handle load ppm event.
   *
   * @throws FileNotFoundException the file not found exception
   */
  void handleLoadPPMEvent() throws FileNotFoundException;

  /**
   * Handle save ppm event.
   *
   * @throws FileNotFoundException the file not found exception
   */
  void handleSavePPMEvent() throws FileNotFoundException;

  /**
   * Handle brighten event.
   */
  void handleBrightenEvent();

  /**
   * Handle darken event.
   */
  void handleDarkenEvent();

  /**
   * Handle blurring event.
   */
  void handleBlurringEvent();

  /**
   * Handle sharpening event.
   */
  void handleSharpeningEvent();

  /**
   * Handle greyscale event.
   */
  void handleGreyscaleEvent();

  /**
   * Handle intensity event.
   */
  void handleIntensityEvent();

  /**
   * Handle value component event.
   */
  void handleValueComponentEvent();

  /**
   * Handle luma component event.
   */
  void handleLumaComponentEvent();

  /**
   * Handle red component event.
   */
  void handleRedComponentEvent();

  /**
   * Handle green component event.
   */
  void handleGreenComponentEvent();

  /**
   * Handle blue component event.
   */
  void handleBlueComponentEvent();
}
