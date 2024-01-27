import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


import controller.ControllerImpl;
import controller.IController;
import controller.commands.*;
import model.ImageDatabase;
import org.junit.Test;
import org.junit.Before;


import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * a Junit test for the commands and controller.
 */
public class TestCommandsandController {
  Scanner sc;
  Scanner sc1;
  ImageDatabase model;
  Appendable appendable;
  Map<String, ICommand> commandMap;

  /**
   * set up before each test.
   *
   * @throws FileNotFoundException when can't find file
   */
  @Before
  public void setUp() throws FileNotFoundException {
    appendable = new StringBuilder();
    model = new ImageDatabase();
    commandMap = new HashMap<>();
  }

  /**
   * Test constructor.
   */
  @Test
  public void testConstructor() {
    StringReader stringReader = new StringReader("load res/threebytwo.ppm 3by2");
    assertNull(model.get("3b2"));
    IController controller = new ControllerImpl(stringReader, model, appendable, commandMap);
    try {
      IController controller1 = new ControllerImpl(null, model, appendable, commandMap);
      fail("exception should have been thrown if parameter is null");
    } catch (NullPointerException e) {
      System.out.println("couldn't perform the action");

    }
    try {
      IController controller2 = new ControllerImpl(stringReader, null, appendable, commandMap);
      fail("exception should have been thrown if parameter is null");
    } catch (NullPointerException e) {
      System.out.println("couldn't perform the action");
    }
    try {
      IController controller3 = new ControllerImpl(stringReader, model, null, commandMap);
      fail("exception should have been thrown if parameter is null");
    } catch (NullPointerException e) {
      System.out.println("couldn't perform the action");
    }
  }

  /**
   * Test load commands.
   */
  @Test
  public void testLoad() {
    sc = new Scanner("res/threebytwo.ppm threebytwo");
    model = new ImageDatabase();
    LoadPPMCommand loadCommand = new LoadPPMCommand();
    assertNull(model.get("threebytwo"));
    appendable = new StringBuilder();

    try {
      loadCommand.run(sc, model);
    } catch (FileNotFoundException e) {
      System.out.println("couldn't perform the action");
    }
    assertNotNull(model.get("threebytwo"));
    assertEquals(2, model.get("threebytwo").getHeight());
    assertEquals(3, model.get("threebytwo").getWidth());
  }

  /**
   * Test commands.
   *
   * @throws FileNotFoundException when can't find file
   */
  @Test
  public void testCommands() throws FileNotFoundException {
    sc = new Scanner("res/threebytwo.ppm threebytwo");
    model = new ImageDatabase();
    LoadPPMCommand loadCommand = new LoadPPMCommand();
    SavePPMCommand savePPMCommand = new SavePPMCommand();
    assertNull(model.get("threebytwo"));
    appendable = new StringBuilder();

    try {
      loadCommand.run(sc, model);
    } catch (FileNotFoundException e) {
      System.out.println("couldn't perform the action");
    }
    assertNotNull(model.get("threebytwo"));
    assertEquals(2, model.get("threebytwo").getHeight());
    assertEquals(3, model.get("threebytwo").getWidth());

    sc1 = new Scanner("30 threebytwo brighten30");
    BrightenCommand brightenCommand = new BrightenCommand();
    assertNull(model.get("brighten30"));
    brightenCommand.run(sc1, model);
    assertNotNull(model.get("brighten30"));

    Scanner sc2 = new Scanner("threebytwo intensity3by2");
    GreyScaleIntensityCommand intensityCommand = new GreyScaleIntensityCommand();
    assertNull(model.get("intensity3by2"));
    intensityCommand.run(sc2, model);
    assertNotNull(model.get("intensity3by2"));

    Scanner sc3 = new Scanner("threebytwo luma3by2");
    GreyScaleLumaCommand lumaCommand = new GreyScaleLumaCommand();
    assertNull(model.get("luma3by2"));
    lumaCommand.run(sc3, model);
    assertNotNull(model.get("luma3by2"));

    Scanner sc4 = new Scanner("threebytwo value3by2");
    GreyScaleValueCommand valueCommand = new GreyScaleValueCommand();
    assertNull(model.get("value3by2"));
    valueCommand.run(sc4, model);
    assertNotNull(model.get("value3by2"));

    Scanner sc5 = new Scanner("threebytwo red3by2");
    VisualizeRedCommand redCommand = new VisualizeRedCommand();
    assertNull(model.get("red3by2"));
    redCommand.run(sc5, model);
    assertNotNull(model.get("red3by2"));

    Scanner sc6 = new Scanner("threebytwo green3by2");
    VisualizeGreenCommand greenCommand = new VisualizeGreenCommand();
    assertNull(model.get("green3by2"));
    greenCommand.run(sc6, model);
    assertNotNull(model.get("green3by2"));

    Scanner sc7 = new Scanner("threebytwo blue3by2");
    VisualizeBlueCommand blueCommand = new VisualizeBlueCommand();
    assertNull(model.get("blue3by2"));
    blueCommand.run(sc7, model);
    assertNotNull(model.get("blue3by2"));

    Scanner sc8 = new Scanner("res/blue3by2.ppm blue3by2");
    SavePPMCommand savePPMCommand1 = new SavePPMCommand();
    savePPMCommand1.run(sc8, model);
    Scanner sc9 = new Scanner("res/blue3by2.ppm blue3by2-overwrite");
    assertNull(model.get("blue3by2-overwrite"));
    loadCommand.run(sc9, model);
    assertNotNull(model.get("blue3by2-overwrite"));
  }

  /**
   * Test command exceptions.
   */
  @Test
  public void testCommandsExceptions() {
    try {
      sc1 = new Scanner("bri threebytwo brighten30");
      BrightenCommand brightenCommand = new BrightenCommand();
      assertNull(model.get("brighten30"));
      brightenCommand.run(sc1, model);
      fail();
    } catch (IllegalStateException e) {
      System.out.println("couldn't perform the action");
    }
    try {
      sc1 = new Scanner("10 2 brighten30");
      BrightenCommand brightenCommand = new BrightenCommand();
      assertNull(model.get("brighten30"));
      brightenCommand.run(sc1, model);
      fail();
    } catch (IllegalStateException e) {
      System.out.println("couldn't perform the action");
    }
    try {
      sc1 = new Scanner("bri threebytwo");
      BrightenCommand brightenCommand = new BrightenCommand();
      assertNull(model.get("brighten30"));
      brightenCommand.run(sc1, model);
      fail();
    } catch (IllegalStateException e) {
      System.out.println("couldn't perform the action");
    }
  }

  /**
   * Test load exceptions.
   */
  @Test(expected = RuntimeException.class)
  public void testloadException() {
    sc = new Scanner("res/notexsit.ppm threebytwo");
    model = new ImageDatabase();
    LoadPPMCommand loadCommand = new LoadPPMCommand();
    appendable = new StringBuilder();
    try {
      loadCommand.run(sc, model);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}

