package controller;

import controller.commands.*;
import model.IImageState;
import model.ImageDatabase;
import view.IView;
import view.View;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Objects;
import java.util.Scanner;

/**
 * The Controllerview class controls how the image GUI will be displayed to user.
 */
public class ControllerView implements IControllerView {
  private final ImageDatabase model;
  private final IView view;
  private String currentImageId;

  /**
   * Instantiates a new Controller view.
   *
   * @param model the model
   * @param view  the view
   */
  public ControllerView(ImageDatabase model, IView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.currentImageId = "";
    this.view.addViewListener(this);//TODO: Subscribe to the view's events.
  }

  @Override
  public void run() {
    view.setVisible(true);
  }

  @Override
  public void handleLoadJPEGEvent() throws IOException {
    System.out.println("clicked load JPEG:");
    JFileChooser fileChooser = new JFileChooser();
    // Set a file filter (optional, if you want to filter specific file types)
    javax.swing.filechooser.FileNameExtensionFilter filter =
        new javax.swing.filechooser.FileNameExtensionFilter("JPEG Images", "jpeg", "jpg");
    fileChooser.setFileFilter(filter);
    // Show the open dialog
    int returnVal = fileChooser.showOpenDialog(null);
    // Check if the user selected a file
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();
      String sourceImageId = selectedFile.getName();
      Scanner newScanner = new Scanner(filePath + " " + sourceImageId);
      ICommand loadOtherFormatCommand = new LoadOtherFormatCommand();
      loadOtherFormatCommand.run(newScanner, model);
      IImageState sourceImage = model.get(sourceImageId);
      int width = sourceImage.getWidth();
      int height = sourceImage.getHeight();
      BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      // Loop through each pixel and set the color in the BufferedImage
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int[] pixel = sourceImage.getPixel(y, x);
          int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
          bufferedImage.setRGB(x, y, rgb);
        }
      }
      view.renderImage(bufferedImage);
      view.renderHistogram(bufferedImage);
      currentImageId = sourceImageId;
      view.setVisible(true);
    }
  }

  @Override
  public void handleSaveJPEGEvent() throws FileNotFoundException {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      JFileChooser fileChooser = new JFileChooser();
      // Set the file chooser to save mode (rather than open mode)
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fileChooser.setDialogTitle("Save File"); // Optional: Set the title of the dialog
      javax.swing.filechooser.FileNameExtensionFilter filter =
          new javax.swing.filechooser.FileNameExtensionFilter("JPEG Images", "jpeg");
      fileChooser.setFileFilter(filter);
      // Show the save dialog and check if the user selected a file
      int returnVal = fileChooser.showSaveDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        // Get the selected file
        File selectedFile = fileChooser.getSelectedFile();
        String selectPath = selectedFile.getAbsolutePath();
        // Check if the file name already has the appropriate extension
        String extension = ".jpeg"; // or ".jpeg" depending on your filter
        if (!selectPath.toLowerCase().endsWith(extension)) {
          // Append the extension to the file path if it's missing
          selectPath += extension;
        }
        Scanner scanner = new Scanner(selectPath + " " + currentImageId);
        ICommand saveJPEGCommand = new SaveJPEGCommand();
        saveJPEGCommand.run(scanner, model);
        System.out.println("Selected path for saving: " + selectedFile.getAbsolutePath());
      } else {
        System.out.println("File saving was canceled.");
      }
    }
  }

  @Override
  public void handleLoadPPMEvent() throws FileNotFoundException {
    System.out.println("clicked load PPM");
    JFileChooser fileChooser = new JFileChooser();
    // Set a file filter (optional, if you want to filter specific file types)
    javax.swing.filechooser.FileNameExtensionFilter filter =
        new javax.swing.filechooser.FileNameExtensionFilter("PPM Images", "ppm");
    fileChooser.setFileFilter(filter);
    // Show the open dialog
    int returnVal = fileChooser.showOpenDialog(null);
    // Check if the user selected a file
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();
      String sourceImageId = selectedFile.getName();
      Scanner newScanner = new Scanner(filePath + " " + sourceImageId);
      ICommand loadppmCommand = new LoadPPMCommand();
      loadppmCommand.run(newScanner, model);
      IImageState sourceImage = model.get(sourceImageId);
      int width = sourceImage.getWidth();
      int height = sourceImage.getHeight();
      BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      // Loop through each pixel and set the color in the BufferedImage
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int[] pixel = sourceImage.getPixel(y, x);
          int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
          bufferedImage.setRGB(x, y, rgb);
        }
      }
      view.renderImage(bufferedImage);
      view.renderHistogram(bufferedImage);
      currentImageId = sourceImageId;
      view.setVisible(true);
    }
  }

  @Override
  public void handleSavePPMEvent() throws FileNotFoundException {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      JFileChooser fileChooser = new JFileChooser();
      // Set the file chooser to save mode (rather than open mode)
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fileChooser.setDialogTitle("Save File"); // Optional: Set the title of the dialog
      javax.swing.filechooser.FileNameExtensionFilter filter =
          new javax.swing.filechooser.FileNameExtensionFilter("PPM Images", "ppm");
      fileChooser.setFileFilter(filter);
      // Show the save dialog and check if the user selected a file
      int returnVal = fileChooser.showSaveDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        // Get the selected file
        File selectedFile = fileChooser.getSelectedFile();
        String selectPath = selectedFile.getAbsolutePath();
        // Check if the file name already has the appropriate extension
        String extension = ".ppm"; // or ".jpeg" depending on your filter
        if (!selectPath.toLowerCase().endsWith(extension)) {
          // Append the extension to the file path if it's missing
          selectPath += extension;
        }
        Scanner scanner = new Scanner(selectPath + " " + currentImageId);
        ICommand savePPMCommand = new SavePPMCommand();
        savePPMCommand.run(scanner, model);
        System.out.println("Selected path for saving ppm: " + selectedFile.getAbsolutePath());
      } else {
        System.out.println("File saving was canceled.");
      }
    }
  }

  @Override
  public void handleLoadPNGEvent() throws IOException {
    System.out.println("clicked load PNG:");
    JFileChooser fileChooser = new JFileChooser();
    // Set a file filter (optional, if you want to filter specific file types)
    javax.swing.filechooser.FileNameExtensionFilter filter =
        new javax.swing.filechooser.FileNameExtensionFilter("PNG Images", "PNG");
    fileChooser.setFileFilter(filter);
    // Show the open dialog
    int returnVal = fileChooser.showOpenDialog(null);
    // Check if the user selected a file
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String filePath = selectedFile.getAbsolutePath();
      String sourceImageId = selectedFile.getName();
      Scanner newScanner = new Scanner(filePath + " " + sourceImageId);
      ICommand loadOtherFormatCommand = new LoadOtherFormatCommand();
      loadOtherFormatCommand.run(newScanner, model);
      IImageState sourceImage = model.get(sourceImageId);
      int width = sourceImage.getWidth();
      int height = sourceImage.getHeight();
      BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      // Loop through each pixel and set the color in the BufferedImage
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          int[] pixel = sourceImage.getPixel(y, x);
          int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
          bufferedImage.setRGB(x, y, rgb);
        }
      }
      view.renderImage(bufferedImage);
      view.renderHistogram(bufferedImage);

      currentImageId = sourceImageId;
      view.setVisible(true);
    }
  }

  @Override
  public void handleSavePNGEvent() throws FileNotFoundException {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      JFileChooser fileChooser = new JFileChooser();
      // Set the file chooser to save mode (rather than open mode)
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fileChooser.setDialogTitle("Save File"); // Optional: Set the title of the dialog
      javax.swing.filechooser.FileNameExtensionFilter filter =
          new javax.swing.filechooser.FileNameExtensionFilter("PNG Images", "PNG");
      fileChooser.setFileFilter(filter);
      // Show the save dialog and check if the user selected a file
      int returnVal = fileChooser.showSaveDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        // Get the selected file
        File selectedFile = fileChooser.getSelectedFile();
        String selectPath = selectedFile.getAbsolutePath();
        // Check if the file name already has the appropriate extension
        String extension = ".png"; // or ".jpeg" depending on your filter
        if (!selectPath.toLowerCase().endsWith(extension)) {
          // Append the extension to the file path if it's missing
          selectPath += extension;
        }
        Scanner scanner = new Scanner(selectPath + " " + currentImageId);
        ICommand savePNGCommand = new SavePNGCommand();
        savePNGCommand.run(scanner, model);
        System.out.println("Selected path for saving: " + selectedFile.getAbsolutePath());
      } else {
        System.out.println("File saving was canceled.");
      }
    }
  }

  @Override
  public void handleSepiaEvent() {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      try {
        String imageIdBeingProcessed = currentImageId;
        String afterSepiaImageId = "sepia-" + imageIdBeingProcessed;
        Scanner scanner = new Scanner(imageIdBeingProcessed + " " + afterSepiaImageId);
        ICommand sepiaCommand = new SepiaCommand();
        sepiaCommand.run(scanner, model);
        IImageState sourceImage = model.get(afterSepiaImageId);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Loop through each pixel and set the color in the BufferedImage
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int[] pixel = sourceImage.getPixel(y, x);
            int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
            bufferedImage.setRGB(x, y, rgb);
          }
        }
        view.renderImage(bufferedImage);
        view.renderHistogram(bufferedImage);
        currentImageId = afterSepiaImageId;
        view.setVisible(true);
      } catch (FileNotFoundException ex) {
        ex.printStackTrace();
      }
      view.requestFrameFocus();
    }
  }

  @Override
  public void handleBrightenEvent() {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      try {
        String imageIdBeingProcessed = currentImageId;
        String afterBriantenImageId = "brighten-" + imageIdBeingProcessed;
        Scanner scanner = new Scanner("20 " + imageIdBeingProcessed + " " + afterBriantenImageId);
        ICommand brightenCommand = new BrightenCommand();
        brightenCommand.run(scanner, model);
        IImageState sourceImage = model.get(afterBriantenImageId);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Loop through each pixel and set the color in the BufferedImage
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int[] pixel = sourceImage.getPixel(y, x);
            int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
            bufferedImage.setRGB(x, y, rgb);
          }
        }
        view.renderImage(bufferedImage);
        view.renderHistogram(bufferedImage);
        this.currentImageId = afterBriantenImageId;
        view.setVisible(true);
      } catch (FileNotFoundException ex) {
        ex.printStackTrace();
      }
      view.requestFrameFocus();
    }
  }

  @Override
  public void handleDarkenEvent() {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      try {
        String afterDarkinImageId = "darken-" + currentImageId;
        Scanner scanner = new Scanner("-20 " + currentImageId + " " + afterDarkinImageId);
        ICommand brightenCommand = new BrightenCommand();
        brightenCommand.run(scanner, model);
        IImageState sourceImage = model.get(afterDarkinImageId);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Loop through each pixel and set the color in the BufferedImage
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int[] pixel = sourceImage.getPixel(y, x);
            int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
            bufferedImage.setRGB(x, y, rgb);
          }
        }
        view.renderImage(bufferedImage);
        view.renderHistogram(bufferedImage);
        this.currentImageId = afterDarkinImageId;
        view.setVisible(true);
        // Update the canvas with the new sepia image (you need to implement a method for this in the view)
      } catch (FileNotFoundException ex) {
        ex.printStackTrace();
      }
      view.requestFrameFocus();

    }
  }

  @Override
  public void handleBlurringEvent() {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      try {
        String imageIdBeingProcessed = currentImageId;
        String afterBlurringImageId = "blurring-" + imageIdBeingProcessed;
        Scanner scanner = new Scanner(imageIdBeingProcessed + " " + afterBlurringImageId);
        ICommand blurCommand = new BlurCommand();
        blurCommand.run(scanner, model);
        IImageState sourceImage = model.get(afterBlurringImageId);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Loop through each pixel and set the color in the BufferedImage
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int[] pixel = sourceImage.getPixel(y, x);
            int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
            bufferedImage.setRGB(x, y, rgb);
          }
        }
        view.renderImage(bufferedImage);
        view.renderHistogram(bufferedImage);
        this.currentImageId = afterBlurringImageId;
        view.setVisible(true);
      } catch (FileNotFoundException ex) {
        ex.printStackTrace();
      }
      view.requestFrameFocus();
    }
  }

  @Override
  public void handleSharpeningEvent() {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      try {
        String imageIdBeingProcessed = currentImageId;
        String afterSharpeningImageId = "sharpen-" + imageIdBeingProcessed;
        Scanner scanner = new Scanner(imageIdBeingProcessed + " " + afterSharpeningImageId);
        ICommand sharpenCommand = new SharpenCommand();
        sharpenCommand.run(scanner, model);
        IImageState sourceImage = model.get(afterSharpeningImageId);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Loop through each pixel and set the color in the BufferedImage
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int[] pixel = sourceImage.getPixel(y, x);
            int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
            bufferedImage.setRGB(x, y, rgb);
          }
        }
        view.renderImage(bufferedImage);
        view.renderHistogram(bufferedImage);
        this.currentImageId = afterSharpeningImageId;
        view.setVisible(true);
      } catch (FileNotFoundException ex) {
        // Handle the exception if needed
        ex.printStackTrace();
      }
      view.requestFrameFocus();
    }
  }

  @Override
  public void handleGreyscaleEvent() {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      try {
        String imageIdBeingProcessed = currentImageId;
        String afterGreyScaleImageId = "greyscale-" + imageIdBeingProcessed;
        Scanner scanner = new Scanner(imageIdBeingProcessed + " " + afterGreyScaleImageId);
        ICommand greyscaleCommand = new GreyscaleCommand();
        greyscaleCommand.run(scanner, model);
        IImageState sourceImage = model.get(afterGreyScaleImageId);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Loop through each pixel and set the color in the BufferedImage
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int[] pixel = sourceImage.getPixel(y, x);
            int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
            bufferedImage.setRGB(x, y, rgb);
          }
        }
        view.renderImage(bufferedImage);
        view.renderHistogram(bufferedImage);
        this.currentImageId = afterGreyScaleImageId;
        view.setVisible(true);
      } catch (FileNotFoundException ex) {
        ex.printStackTrace();
      }
      view.requestFrameFocus();
    }
  }

  @Override
  public void handleIntensityEvent() {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      try {
        String imageIdBeingProcessed = currentImageId;
        String afterIntensityImageId = "intensity-" + imageIdBeingProcessed;
        Scanner scanner = new Scanner(imageIdBeingProcessed + " " + afterIntensityImageId);
        ICommand intensityCommand = new GreyScaleIntensityCommand();
        intensityCommand.run(scanner, model);
        IImageState sourceImage = model.get(afterIntensityImageId);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Loop through each pixel and set the color in the BufferedImage
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int[] pixel = sourceImage.getPixel(y, x);
            int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
            bufferedImage.setRGB(x, y, rgb);
          }
        }
        view.renderImage(bufferedImage);
        view.renderHistogram(bufferedImage);
        this.currentImageId = afterIntensityImageId;
        view.setVisible(true);
      } catch (FileNotFoundException ex) {
        ex.printStackTrace();
      }
      view.requestFrameFocus();
    }
  }

  @Override
  public void handleValueComponentEvent() {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      try {
        String imageIdBeingProcessed = currentImageId;
        String afterValueComImageId = "value-" + imageIdBeingProcessed;
        Scanner scanner = new Scanner(imageIdBeingProcessed + " " + afterValueComImageId);
        ICommand valueCommand = new GreyScaleValueCommand();
        valueCommand.run(scanner, model);
        IImageState sourceImage = model.get(afterValueComImageId);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Loop through each pixel and set the color in the BufferedImage
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int[] pixel = sourceImage.getPixel(y, x);
            int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
            bufferedImage.setRGB(x, y, rgb);
          }
        }
        view.renderImage(bufferedImage);
        view.renderHistogram(bufferedImage);
        this.currentImageId = afterValueComImageId;
        view.setVisible(true);
      } catch (FileNotFoundException ex) {
        // Handle the exception if needed
        ex.printStackTrace();
      }
      view.requestFrameFocus();
    }
  }

  @Override
  public void handleLumaComponentEvent() {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      try {
        String imageIdBeingProcessed = currentImageId;
        String afterLumaImageId = "luma-" + imageIdBeingProcessed;
        Scanner scanner = new Scanner(imageIdBeingProcessed + " " + afterLumaImageId);
        ICommand lumaCommand = new GreyScaleLumaCommand();
        lumaCommand.run(scanner, model);
        IImageState sourceImage = model.get(afterLumaImageId);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Loop through each pixel and set the color in the BufferedImage
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int[] pixel = sourceImage.getPixel(y, x);
            int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
            bufferedImage.setRGB(x, y, rgb);
          }
        }
        view.renderImage(bufferedImage);
        view.renderHistogram(bufferedImage);
        this.currentImageId = afterLumaImageId;
        view.setVisible(true);
      } catch (FileNotFoundException ex) {
        // Handle the exception if needed
        ex.printStackTrace();
      }
      view.requestFrameFocus();
    }
  }

  @Override
  public void handleRedComponentEvent() {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      try {
        String imageIdBeingProcessed = currentImageId;
        String afterRedImageId = "red-" + imageIdBeingProcessed;
        Scanner scanner = new Scanner(imageIdBeingProcessed + " " + afterRedImageId);
        ICommand visualizeRedCommand = new VisualizeRedCommand();
        visualizeRedCommand.run(scanner, model);
        IImageState sourceImage = model.get(afterRedImageId);
        //convert sourceImage to buffed Image
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Loop through each pixel and set the color in the BufferedImage
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int[] pixel = sourceImage.getPixel(y, x);
            int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
            bufferedImage.setRGB(x, y, rgb);
          }
        }
        view.renderImage(bufferedImage);
        view.renderHistogram(bufferedImage);
        this.currentImageId = afterRedImageId;
        view.setVisible(true);
      } catch (FileNotFoundException ex) {
        ex.printStackTrace();
      }
      view.requestFrameFocus();
    }
  }

  @Override
  public void handleGreenComponentEvent() {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      try {
        String imageIdBeingProcessed = currentImageId;
        String afterGreenImageId = "green-" + imageIdBeingProcessed;
        Scanner scanner = new Scanner(imageIdBeingProcessed + " " + afterGreenImageId);
        ICommand visualizeGreenCommand = new VisualizeGreenCommand();
        visualizeGreenCommand.run(scanner, model);
        IImageState sourceImage = model.get(afterGreenImageId);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Loop through each pixel and set the color in the BufferedImage
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int[] pixel = sourceImage.getPixel(y, x);
            int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
            bufferedImage.setRGB(x, y, rgb);
          }
        }
        view.renderImage(bufferedImage);
        view.renderHistogram(bufferedImage);
        this.currentImageId = afterGreenImageId;
        view.setVisible(true);
      } catch (FileNotFoundException ex) {
        // Handle the exception if needed
        ex.printStackTrace();
      }
      view.requestFrameFocus();
    }
  }

  @Override
  public void handleBlueComponentEvent() {
    if (currentImageId.equals("")) {
      view.renderReminderMessage();
    } else {
      try {
        String imageIdBeingProcessed = currentImageId;
        String aftervisualizeBlueImageId = "blue-" + imageIdBeingProcessed;
        Scanner scanner = new Scanner(imageIdBeingProcessed + " " + aftervisualizeBlueImageId);
        ICommand visualizeBlueCommand = new VisualizeBlueCommand();
        visualizeBlueCommand.run(scanner, model);
        IImageState sourceImage = model.get(aftervisualizeBlueImageId);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Loop through each pixel and set the color in the BufferedImage
        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            int[] pixel = sourceImage.getPixel(y, x);
            int rgb = (pixel[0] << 16 | pixel[1] << 8 | pixel[2]);
            bufferedImage.setRGB(x, y, rgb);
          }
        }
        view.renderImage(bufferedImage);
        view.renderHistogram(bufferedImage);
        this.currentImageId = aftervisualizeBlueImageId;
        view.setVisible(true);
      } catch (FileNotFoundException ex) {
        // Handle the exception if needed
        ex.printStackTrace();
      }
      view.requestFrameFocus();
    }
  }

  public String getCurrentImageId(){
    return this.currentImageId;
  }
}
