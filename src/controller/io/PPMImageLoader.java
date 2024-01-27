package controller.io;

import model.IImageState;
import model.ImageImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * an image load class to load the image to the model.
 */
public class PPMImageLoader implements IImageLoader {
  private final String filepath;

  /**
   * to load the image from the givenpath and imageid.
   *
   * @param filepath      the sourceimage path
   */
  public PPMImageLoader(String filepath) {
    this.filepath = Objects.requireNonNull(filepath);
  }

  /**
   * to load the image to the model.
   *
   * @return an image object
   * @throws FileNotFoundException when couldn't find the source file
   */
  @Override
  public IImageState run() throws FileNotFoundException {
    ImageImpl image;
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filepath));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("file not found");
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    System.out.println(height);
    image = new ImageImpl(height, width);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        image.setPixel(row, col, r, g, b);
      }
    }
    return image;
  }
}
