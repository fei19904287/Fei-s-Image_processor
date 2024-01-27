package view;

import controller.IControllerView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import java.util.List;

/**
 * a view class used to display the image processing interface.
 */
public class View extends JFrame implements ActionListener, IView {

  private MyCanvas canvas;
  private HistogramPanel histogramPanel;
  private BufferedImage image = null;
  private List<ViewListener> listenersToNotify;
  private final JPanel mainPanel;

  private final List<ViewListener> listnersToNotify;

  /**
   * Instantiates a new View.
   */
  public View() {
    super("Fei's Image Processer");
    setPreferredSize(new Dimension(1200, 700));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.listnersToNotify = new ArrayList<>();
    mainPanel = new JPanel(new BorderLayout());
    add(mainPanel);
    // Create a JScrollPane and add the canvas to it
    this.canvas = new MyCanvas(image);// Replace 'null' with your initial image
    this.histogramPanel = new HistogramPanel(image);
    JScrollPane scrollPane = new JScrollPane(canvas);
    JScrollPane hisScrollPane = new JScrollPane(histogramPanel);
    // Show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    JPanel hisPanel = new JPanel();
    // A border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Current Image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    imagePanel.add(scrollPane); // Add the scroll pane instead of the canvas directly
    hisPanel.setBorder(BorderFactory.createTitledBorder("Current Image Histogram"));
    hisPanel.setLayout(new GridLayout(1, 0, 10, 10));
    hisPanel.add(hisScrollPane); // Add the scroll pane instead of the canvas directly
    mainPanel.add(imagePanel, BorderLayout.CENTER);
    mainPanel.add(hisPanel, BorderLayout.SOUTH);


    // Create sub-panel for buttons with FlowLayout (or any other layout of your choice)
    JPanel buttonPanelEast = new JPanel();
    JPanel buttonPanelWest = new JPanel();
    buttonPanelEast = new JPanel(new GridLayout(0, 1));
    buttonPanelWest = new JPanel(new GridLayout(0, 1));

    // Create and add buttons to the button panel

    JButton loadPNGButton = new JButton("Load PNG");
    JButton loadPPMButton = new JButton("Load PPM");
    JButton loadJPEGButton = new JButton("Load JPEG");

    JButton savePNGButton = new JButton("SaveAsPNG");
    JButton savePPMButton = new JButton("SaveAsPPM");
    JButton saveJPEGButton = new JButton("SaveAsJPEG");

    JButton brightenButton = new JButton("Brightening");
    JButton darkenButton = new JButton("Darkening");
    JButton blurringButton = new JButton("Blurring");
    JButton sharpenButton = new JButton("Sharpening");
    JButton greyscaleButton = new JButton("GreyScale");
    JButton sepiaButton = new JButton("Sepia");
    JButton intensityButton = new JButton("Intensity");
    JButton valueButton = new JButton("Value-Component");
    JButton lumaButton = new JButton("Luma-Component");
    JButton redComponentButton = new JButton("Red-Component");
    JButton greenComponentButton = new JButton("Green-Component");
    JButton blueComponentButton = new JButton("Blue-Component");

    buttonPanelEast.add(loadPNGButton);
    buttonPanelEast.add(loadPPMButton);
    buttonPanelEast.add(loadJPEGButton);
    buttonPanelEast.add(savePPMButton);
    buttonPanelEast.add(savePNGButton);
    buttonPanelEast.add(saveJPEGButton);

    buttonPanelWest.add(brightenButton);
    buttonPanelWest.add(darkenButton);
    buttonPanelWest.add(blurringButton);
    buttonPanelWest.add(sharpenButton);
    buttonPanelWest.add(greyscaleButton);
    buttonPanelWest.add(sepiaButton);
    buttonPanelWest.add(intensityButton);
    buttonPanelWest.add(valueButton);
    buttonPanelWest.add(lumaButton);
    buttonPanelWest.add(redComponentButton);
    buttonPanelWest.add(greenComponentButton);
    buttonPanelWest.add(blueComponentButton);

    mainPanel.add(buttonPanelEast, BorderLayout.EAST);
    mainPanel.add(buttonPanelWest, BorderLayout.WEST);

    loadPPMButton.setActionCommand("loadPPM");
    loadPPMButton.addActionListener(this);
    savePPMButton.setActionCommand("savePPM");
    savePPMButton.addActionListener(this);

    loadJPEGButton.setActionCommand("loadJPEG");
    loadJPEGButton.addActionListener(this);
    saveJPEGButton.setActionCommand("saveJPEG");
    saveJPEGButton.addActionListener(this);

    loadPNGButton.setActionCommand("loadPNG");
    loadPNGButton.addActionListener(this);
    savePNGButton.setActionCommand("savePNG");
    savePNGButton.addActionListener(this);

    sepiaButton.setActionCommand("sepia");
    sepiaButton.addActionListener(this);

    brightenButton.setActionCommand("brighten");
    brightenButton.addActionListener(this);

    darkenButton.setActionCommand("darken");
    darkenButton.addActionListener(this);

    blurringButton.setActionCommand("blurring");
    blurringButton.addActionListener(this);

    sharpenButton.setActionCommand("sharpening");
    sharpenButton.addActionListener(this);

    greyscaleButton.setActionCommand("greyScaling");
    greyscaleButton.addActionListener(this);

    intensityButton.setActionCommand("intensity");
    intensityButton.addActionListener(this);

    valueButton.setActionCommand("value-component");
    valueButton.addActionListener(this);

    lumaButton.setActionCommand("luma-component");
    lumaButton.addActionListener(this);

    redComponentButton.setActionCommand("red-component");
    redComponentButton.addActionListener(this);

    greenComponentButton.setActionCommand("green-component");
    greenComponentButton.addActionListener(this);

    blueComponentButton.setActionCommand("blue-component");
    blueComponentButton.addActionListener(this);

    this.setFocusable(true);
    pack();
    setLocationRelativeTo(null);
  }



  /**
   * Add view listener.
   *
   * @param listener the listener
   */
  @Override
  public void addViewListener(IControllerView listener) {
    this.listnersToNotify.add(listener);
  }

  @Override
  public void addViewListener(ViewListener listener) {
    this.listnersToNotify.add(listener);
  }

  /**
   * Request frame focus.
   */
  @Override
  public void requestFrameFocus() {
    this.requestFocus();
  }

  private void emitLoadPPMEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleLoadPPMEvent();
    }
  }

  private void emitSavePPMEvent() throws FileNotFoundException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleSavePPMEvent();
    }
  }

  private void emitLoadJPEGEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleLoadJPEGEvent();
    }
  }

  private void emitLoadPNGEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleLoadPNGEvent();
    }
  }

  private void emitSaveJPEGEvent() throws FileNotFoundException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleSaveJPEGEvent();
    }
  }

  private void emitSavePNGEvent() throws FileNotFoundException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleSavePNGEvent();
    }
  }

  private void emitSepiaEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleSepiaEvent();
    }
  }

  private void emitBrightenEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleBrightenEvent();
    }
  }

  private void emitDarkeningEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleDarkenEvent();
    }
  }

  private void emitBlurringEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleBlurringEvent();
    }
  }

  private void emitSharpeningEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleSharpeningEvent();
    }
  }

  private void emitGreyscaleEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleGreyscaleEvent();
    }
  }

  private void emitIntensityEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleIntensityEvent();
    }
  }

  private void emitValueComponentEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleValueComponentEvent();
    }
  }

  private void emitLumaComponentEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleLumaComponentEvent();
    }
  }

  private void emitRedComponentEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleRedComponentEvent();
    }
  }

  private void emitGreenComponentEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleGreenComponentEvent();
    }
  }

  private void emitBlueComponentEvent() throws IOException {
    for (ViewListener listener : listnersToNotify) {
      listener.handleBlueComponentEvent();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    //Which button did we click?
    switch (e.getActionCommand()) {
      case "loadPPM":
        try {
          emitLoadPPMEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "savePPM":
        try {
          emitSavePPMEvent();
        } catch (FileNotFoundException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "loadJPEG":
        try {
          emitLoadJPEGEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "saveJPEG":
        try {
          emitSaveJPEGEvent();
        } catch (FileNotFoundException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "loadPNG":
        try {
          emitLoadPNGEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "savePNG":
        try {
          emitSavePNGEvent();
        } catch (FileNotFoundException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "sepia":
        try {
          emitSepiaEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "brighten":
        try {
          emitBrightenEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "darken":
        try {
          emitDarkeningEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "blurring":
        try {
          emitBlurringEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "sharpening":
        try {
          emitSharpeningEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "greyScaling":
        try {
          emitGreyscaleEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "intensity":
        try {
          emitIntensityEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "value-component":
        try {
          emitValueComponentEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "luma-component":
        try {
          emitLumaComponentEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "green-component":
        try {
          emitGreenComponentEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "red-component":
        try {
          emitRedComponentEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      case "blue-component":
        try {
          emitBlueComponentEvent();
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        break;
      default:
        throw new IllegalStateException("Unknown action command.");
    }
  }

  /**
   * Render image.
   *
   * @param image the image
   */
  public void renderImage(BufferedImage image) {
    this.image = image;
    canvas.renderImage(image);
  }

  /**
   * Render histogram.
   *
   * @param image the image
   */
  public void renderHistogram(BufferedImage image) {
    this.image = image;
    histogramPanel.renderHistogramData(image);
  }

  /**
   * Render reminder message.
   */
  public void renderReminderMessage() {
    JOptionPane.showMessageDialog(null, "hey,load file first", "Attention!",
        JOptionPane.INFORMATION_MESSAGE);
  }
}
