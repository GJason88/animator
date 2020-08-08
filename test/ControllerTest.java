
import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.IController;
import cs3500.animator.controller.SVGController;
import cs3500.animator.controller.TextController;
import cs3500.animator.controller.VisualController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ShapeAnimation;
import cs3500.animator.view.IView;
import cs3500.animator.view.VisualView;
import java.awt.Color;
import java.awt.Shape;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link cs3500.animator.controller.AbstractController}: Ensures both the correct
 * inputs from a model and correct outputs from given inputs to SVG and Textual Views.
 */
public class ControllerTest {

  private IView visualView;
  private IView confirmInputsView;
  private StringBuilder log;
  private IModel model;
  private IController controller;
  // Use specific class name for testing purposes
  private VisualController featureController;


  private Keyframe k4;


  private cs3500.animator.model.Shape rect;


  @Before
  public void initial() {

    Keyframe k1 = new Keyframe(0, 10, 10, 0, 0, 255, 50, 50);
    Keyframe k2 = new Keyframe(25, 100, 100, 255, 255, 0, 100, 100);
    Keyframe k3 = new Keyframe(65, 100, 100, 0, 255, 0, 150, 150);

    // changes position from (10,10) to (100,100) from tick 0 to 20
    IMotion m1 = new Motion(k1, k2);
    // changes size from 50x50 to 100x100 and color from blue to green from tick 25 to 65
    IMotion m2 = new Motion(k2, k3);

    List<IMotion> rectMotions = new ArrayList<>(Arrays.asList(m1, m2));
    List<IMotion> ovalMotions = new ArrayList<>(Arrays.asList(m1, m2));

    this.rect = new Rectangle("rect", rectMotions);
    cs3500.animator.model.Shape oval = new Oval("oval", ovalMotions);

    List<cs3500.animator.model.Shape> shapes = new ArrayList<>(Arrays.asList(rect, oval));
    model = new ShapeAnimation(shapes, 0, 0, 500, 500);

    visualView = new VisualView();
    this.log = new StringBuilder();
    this.confirmInputsView = new ConfirmInputs(log, new ArrayList<>(), new ArrayList<>(),
        new ArrayList<>(), new VisualController(visualView, model));
    this.featureController = new VisualController(confirmInputsView, model);


  }

  @Test
  public void testCreateTextViewInputs() throws IOException {

    controller = new TextController(confirmInputsView, model);
    controller.createTextView(new FileWriter("testLocation.txt"));

    // inputs to printTextView()
    assertEquals("canvas 0 0 500 500\n"
            + "shape rect Rectangle\n"
            + "motion rect 0 10 10 50 50 0 0 255 25 100 100 100 100 255 255 0\n"
            + "motion rect 25 100 100 100 100 255 255 0 65 100 100 150 150 0 255 0\n"
            + "\n"
            + "shape oval Oval\n"
            + "motion oval 0 10 10 50 50 0 0 255 25 100 100 100 100 255 255 0\n"
            + "motion oval 25 100 100 100 100 255 255 0 65 100 100 150 150 0 255 0",
        log.toString());
  }

  @Test
  public void testCreateSVGInputs() throws IOException {
    controller = new SVGController(confirmInputsView, model);
    controller.createSVG(new FileWriter("testLocation.svg"));

    // inputs to generateSVG()
    assertEquals(
        "<svg width=\"500\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "\n"
            + "<rect id=\"rect\" x=\"10\" y=\"10\" width=\"50\" height=\"50\" fill=\"rgb(0,0,0)\" "
            + "visibility=\"visible\" opacity=\"0\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1ms\" attributeName=\"opacity\" "
            + "from=\"0\" to=\"1\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"25000ms\" attributeName=\"x\" "
            + "from=\"10\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"25000ms\" attributeName=\"y\" "
            + "from=\"10\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"25000ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"25000ms\" "
            + "attributeName=\"height\" from=\"50\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"25000ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(255,255,0)\" "
            + "fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"25000ms\" dur=\"40000ms\" "
            + "attributeName=\"width\" from=\"100\" to=\"150\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"25000ms\" dur=\"40000ms\" "
            + "attributeName=\"height\" from=\"100\" to=\"150\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"25000ms\" dur=\"40000ms\" "
            + "attributeName=\"fill\" from=\"rgb(255,255,0)\" to=\"rgb(0,255,0)\" "
            + "fill=\"freeze\" />\n"
            + "</rect>\n"
            + "\n"
            + "<ellipse id=\"oval\" cx=\"35\" cy=\"35\" rx=\"25\" ry=\"25\" fill=\"rgb(0,0,0)\""
            + " visibility=\"visible\" opacity=\"0\">\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1ms\" attributeName=\"opacity\" "
            + "from=\"0\" to=\"1\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"25000ms\" attributeName=\"cx\""
            + " from=\"35\" to=\"150\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"25000ms\" attributeName=\"cy\" "
            + "from=\"35\" to=\"150\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"25000ms\" attributeName=\"rx\" "
            + "from=\"25\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"25000ms\" attributeName=\"ry\" "
            + "from=\"25\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"25000ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(255,255,0)\""
            + " fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"25000ms\" dur=\"40000ms\" "
            + "attributeName=\"rx\" from=\"50\" to=\"75\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"25000ms\" dur=\"40000ms\" "
            + "attributeName=\"ry\" from=\"50\" to=\"75\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"25000ms\" dur=\"40000ms\" "
            + "attributeName=\"fill\" from=\"rgb(255,255,0)\" to=\"rgb(0,255,0)\" "
            + "fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "\n"
            + "</svg>",
        log.toString());
  }

  @Test
  public void testStartAnimationInputs() {
    ArrayList<Shape> currentShapesLog = new ArrayList<>();
    ArrayList<Color> currentColorsLog = new ArrayList<>();
    this.confirmInputsView = new ConfirmInputs(this.log, currentShapesLog, currentColorsLog,
        new ArrayList<>(), new VisualController(visualView, model));
    controller = new MockControllerForVisualInputsTest(confirmInputsView, model, 10);

    controller.startAnimation();

    // inputs to setCanvasSize()
    assertEquals("0,0,500,500", this.log.toString());

    // interpolated Shape and Color inputs to addShapes()
    assertEquals(2,
        currentShapesLog.size());

    assertEquals(2,
        currentColorsLog.size());
  }

  @Test
  public void testSetTempo() {
    controller = new VisualController(visualView, model);
    assertEquals(1, controller.getTempo());
    controller.setTempo(15);
    assertEquals(15, controller.getTempo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroTempo() {
    controller = new VisualController(visualView, model);
    controller.setTempo(0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTempo() {
    IView view = new VisualView();
    controller = new VisualController(visualView, model);
    controller.setTempo(-1);

  }

  @Test
  public void setSpeedTest() {
    assertEquals(1, this.featureController.getTempo());
    this.confirmInputsView.initializeSpeedChanger(60);
    this.featureController.setSpeed(60);
    assertEquals("60", this.log.toString());
    assertEquals(60, this.featureController.getTempo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setSpeedTestNegative() {
    this.featureController.setSpeed(-5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void setSpeedTestZero() {
    this.featureController.setSpeed(0);
  }

  @Test
  public void addToKeyFrameGUIListTest() {
    List<String> keyframeStringLog = new ArrayList<>();
    this.confirmInputsView = new ConfirmInputs(this.log, new ArrayList<>(), new ArrayList<>(),
        keyframeStringLog,
        featureController);
    this.featureController = new VisualController(this.confirmInputsView, this.model);
    this.featureController.addToKeyFrameGUIList("rect");
    assertEquals(new ArrayList<String>(
            Arrays.asList("Tick: 0 X: 10 Y: 10 Width: 50 Height: 50 Red: 0 Green: 0 Blue: 255",
                "Tick: 25 X: 100 Y: 100 Width: 100 Height: 100 Red: 255 Green: 255 Blue: 0",
                "Tick: 65 X: 100 Y: 100 Width: 150 Height: 150 Red: 0 Green: 255 Blue: 0")),
        keyframeStringLog);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddToKeyframeGUIListNullName() {
    this.featureController.addToKeyFrameGUIList(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKeyframeNullName() {
    this.featureController.deleteKeyframe(0, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNewKeyframeNullName() {
    this.featureController.addNewKeyframe(50, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditKeyframeNullName() {
    this.featureController.editKeyframe(0, 1, 2, 3, 4, 5, 6, 7, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeNullName() {
    this.featureController.addNewShape(null, "Rectangle");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpdateKeyframeEditorValuesNullName() {
    this.featureController.updateKeyframeEditorValues(0, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveShapeNullName() {
    this.featureController.removeShape(null);
  }

  @Test
  public void deleteKeyframeTest() {
    List<String> keyframeStringLog = new ArrayList<>();
    this.confirmInputsView = new ConfirmInputs(this.log, new ArrayList<>(), new ArrayList<>(),
        keyframeStringLog,
        featureController);
    this.featureController = new VisualController(this.confirmInputsView, this.model);
    assertEquals(3, this.rect.getKeyframes().size());

    this.featureController.deleteKeyframe(0, "rect");
    assertEquals(2, this.rect.getKeyframes().size());

  }

  @Test
  public void addNewKeyframeTest() {
    List<String> keyframeStringLog = new ArrayList<>();
    this.confirmInputsView = new ConfirmInputs(this.log, new ArrayList<>(), new ArrayList<>(),
        keyframeStringLog,
        featureController);
    this.featureController = new VisualController(this.confirmInputsView, this.model);
    assertEquals(3, this.rect.getKeyframes().size());

    this.featureController.addNewKeyframe(30, "rect");
    assertEquals(4, this.rect.getKeyframes().size());
  }

  @Test
  public void editKeyframeTest() {
    List<String> keyframeStringLog = new ArrayList<>();
    this.confirmInputsView = new ConfirmInputs(this.log, new ArrayList<>(), new ArrayList<>(),
        keyframeStringLog,
        featureController);
    this.featureController = new VisualController(this.confirmInputsView, this.model);

    assertEquals(0, this.rect.getKeyframes().get(0).getTick());
    assertEquals(10, this.rect.getKeyframes().get(0).getX());
    assertEquals(10, this.rect.getKeyframes().get(0).getY());
    assertEquals(0, this.rect.getKeyframes().get(0).getR());
    assertEquals(0, this.rect.getKeyframes().get(0).getG());
    assertEquals(255, this.rect.getKeyframes().get(0).getB());
    assertEquals(50, this.rect.getKeyframes().get(0).getHeight());
    assertEquals(50, this.rect.getKeyframes().get(0).getWidth());

    this.featureController.editKeyframe(0, 1, 2, 3, 4, 5, 6, 7, "rect");
    assertEquals(0, this.rect.getKeyframes().get(0).getTick());
    assertEquals(1, this.rect.getKeyframes().get(0).getX());
    assertEquals(2, this.rect.getKeyframes().get(0).getY());
    assertEquals(3, this.rect.getKeyframes().get(0).getR());
    assertEquals(4, this.rect.getKeyframes().get(0).getG());
    assertEquals(5, this.rect.getKeyframes().get(0).getB());
    assertEquals(6, this.rect.getKeyframes().get(0).getHeight());
    assertEquals(7, this.rect.getKeyframes().get(0).getWidth());


  }

  @Test
  public void updateKeyframeEditorValuesTest() {
    this.featureController.updateKeyframeEditorValues(0, "rect");
    assertEquals("10,10,0,0,255,50,50", this.log.toString());

  }

  @Test
  public void addNewShapeTest() {
    assertEquals(2, this.model.getShapes().size());
    this.featureController.addNewShape("testShape", "Rectangle");
    assertEquals(new ArrayList<Motion>(), this.model.getShapeWithName("testShape").getKeyframes());
    assertEquals(3, this.model.getShapes().size());
  }

  @Test
  public void removeShapeTest() {
    assertEquals(2, this.model.getShapes().size());
    this.featureController.removeShape("rect");
    assertEquals(1, this.model.getShapes().size());
  }

  @Test
  public void initializeScrubberTest() {
    this.featureController.initializeScrubber();
    assertEquals("65", this.log.toString());
  }

  @Test
  public void updateScrubberTest() {
    this.confirmInputsView.updateScrubber(100);
    assertEquals("100", this.log.toString());
  }
}
