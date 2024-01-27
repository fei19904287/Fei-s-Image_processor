import controller.ControllerView;
import controller.IControllerView;
import model.ImageDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.IView;
import view.ViewListener;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MockView implements IView {
  protected BufferedImage renderedImage;
  protected BufferedImage renderedHistogram;
  protected StringBuilder log;

    public MockView(StringBuilder log) {
    this.log  = log;
  }

  @Override
  public void addViewListener(IControllerView controller) {
    // Not relevant for testing
  }

  @Override
  public void addViewListener(ViewListener listener) {
    // Not relevant for testing
  }

  @Override
  public void requestFrameFocus() {

  }

  @Override
  public void renderImage(BufferedImage image) {
    this.renderedImage = image;
  }

  @Override
  public void renderHistogram(BufferedImage histogram) {
    this.renderedHistogram = histogram;
  }

  @Override
  public void renderReminderMessage() {
    this.log.append("reminder message");
  }

  public String getlog() {
    return log.toString();
  }

  @Override
  public void setVisible(boolean b) {

  }
}

class MockImageDatabase extends ImageDatabase {
  // Implement the necessary methods for testing
  // You may use a simple HashMap or ArrayList to store images and their properties.
}

class ControllerViewTest {
  private ImageDatabase mockModel;
  private MockView mockView;
  private ControllerView controllerView;
  protected StringBuilder log = new StringBuilder();



  @BeforeEach
  void setUp() {
    mockModel = new MockImageDatabase();
    mockView = new MockView(log);
    controllerView = new ControllerView(mockModel, mockView);
  }

  @Test
  void handleLoadJPEGEvent_ShouldLoadImageAndRender() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    assertEquals("dog.jpeg",controllerView.getCurrentImageId());

    // Verify that the image is rendered on the view
    assertNotNull(mockView.renderedImage);

    // Verify that the histogram is rendered on the view
    assertNotNull(mockView.renderedHistogram);
  }

  // Test when save without load creates error message
  @Test
  void testDisplayMessage() throws IOException {
    controllerView.handleSaveJPEGEvent();
    assertEquals("reminder message",mockView.getlog());
  }

  // Test command actually goes to the saveJPEGEvent
  @Test
  void testhandleSaveJPEGEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleSaveJPEGEvent();
    assertEquals("reminder message",mockView.getlog());
  }

  //Test handle sepia
  @Test
  void testhandlleSepiaEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    controllerView.handleSepiaEvent();
    assertEquals("sepia-dog.jpeg",controllerView.getCurrentImageId());
  }
  //Test handle sepia
  @Test
  void testhandllebrightenEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    controllerView.handleBrightenEvent();
    assertEquals("sepia-dog.jpeg",controllerView.getCurrentImageId());
  }
  //Test handle sepia
  @Test
  void testhandleDarkenEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    controllerView.handleDarkenEvent();
    assertEquals("sepia-dog.jpeg",controllerView.getCurrentImageId());
  }
  //Test handle sepia
  @Test
  void testhandleBlurringEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    controllerView.handleBlurringEvent();
    assertEquals("blurring-dog.jpeg",controllerView.getCurrentImageId());
  }
  //Test handle sepia
  @Test
  void testhandleSharpeningEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    controllerView.handleSharpeningEvent();
    assertEquals("sharpen-dog.jpeg",controllerView.getCurrentImageId());
  }
  //Test handle sepia
  @Test
  void testhandleGreyscaleEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    controllerView.handleGreyscaleEvent();
    assertEquals("greyscale-dog.jpeg",controllerView.getCurrentImageId());
  }
  //Test handle sepia
  @Test
  void testhandleIntensityEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    controllerView.handleIntensityEvent();
    assertEquals("intensity-dog.jpeg",controllerView.getCurrentImageId());
  }
  //Test handle sepia
  @Test
  void handleValueComponentEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    controllerView.handleValueComponentEvent();
    assertEquals("value-dog.jpeg",controllerView.getCurrentImageId());
  }
  //Test handle sepia
  @Test
  void handleLumaComponentEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    controllerView.handleLumaComponentEvent();
    assertEquals("luma-dog.jpeg",controllerView.getCurrentImageId());
  }
  //Test handle sepia
  @Test
  void handleRedComponentEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    controllerView.handleRedComponentEvent();
    assertEquals("red-dog.jpeg",controllerView.getCurrentImageId());
  }
  //Test handle sepia
  @Test
  void handleGreenComponentEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    controllerView.handleGreenComponentEvent();
    assertEquals("green-dog.jpeg",controllerView.getCurrentImageId());
  }
  //Test handle sepia
  @Test
  void handleBlueComponentEvent() throws IOException {
    assertEquals("",controllerView.getCurrentImageId());
    assertEquals("",mockView.getlog());
    controllerView.handleLoadJPEGEvent();// select the res/ dog.jpeg file
    controllerView.handleBlueComponentEvent();
    assertEquals("blue-dog.jpeg",controllerView.getCurrentImageId());
  }

}

