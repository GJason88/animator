import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.ExcellenceController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.ShapeAnimation;
import cs3500.animator.view.IView;
import cs3500.animator.view.VisualView;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link ExcellenceController}: Ensures both the correct inputs from a model and
 * correct outputs from given inputs to SVG and Textual Views.
 */
public class ControllerTest {

  private IView view;
  private IView visualView;
  private IModel model;
  private IController controller;
  // changes position from (10,10) to (100,100) from tick 0 to 20
  private final Motion m1 =
      new Motion(0, 10, 10, 0, 0, 255, 50, 50, 20, 100, 100, 255, 255, 0, 100, 100);
  // changes size from 50x50 to 100x100 and color from blue to green from tick 25 to 65
  private final Motion m2 =
      new Motion(25, 100, 100, 255, 255, 0, 100, 100, 65, 100, 100, 0, 255, 0, 150, 150);
  private final List<Motion> rectMotions = new ArrayList<>(
      Arrays.asList(this.m1, this.m2));
  private final List<Motion> ovalMotions = new ArrayList<>(
      Arrays.asList(this.m1, this.m2));
  private final cs3500.animator.model.Shape rect = new Rectangle("rect", this.rectMotions);
  private final cs3500.animator.model.Shape oval = new Oval("oval", this.ovalMotions);


  @Before
  public void initial() {
    List<cs3500.animator.model.Shape> shapes = new ArrayList<>(Arrays.asList(rect, oval));
    model = new ShapeAnimation(shapes, 0, 0, 500, 500);
    visualView = new VisualView();

  }

  @Test
  public void testCreateTextViewInputs() {
    StringBuilder log = new StringBuilder();
    view = new ConfirmInputs(log, new ArrayList<>(), new ArrayList<>());
    controller = new ExcellenceController(view, model);
    controller.createTextView("testLocation.txt");

    // inputs to printTextView()
    assertEquals("canvas 0 0 500 500\n"
            + "shape rect Rectangle\n"
            + "motion rect 0 10 10 50 50 0 0 255 20 100 100 100 100 255 255 0\n"
            + "motion rect 25 100 100 100 100 255 255 0 65 100 100 150 150 0 255 0\n"
            + "\n"
            + "shape oval Oval\n"
            + "motion oval 0 10 10 50 50 0 0 255 20 100 100 100 100 255 255 0\n"
            + "motion oval 25 100 100 100 100 255 255 0 65 100 100 150 150 0 255 0"
            + ",testLocation.txt",
        log.toString());
  }

  @Test
  public void testCreateSVGInputs() throws IOException {
    StringBuilder log = new StringBuilder();
    view = new ConfirmInputs(log, new ArrayList<>(), new ArrayList<>());
    controller = new ExcellenceController(view, model);
    controller.createSVG("testLocation.txt");

    // inputs to generateSVG()
    assertEquals("testLocation.txt,"
            + "<svg width=\"500\" height=\"500\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "\n"
            + "<rect id=\"rect\" x=\"10\" y=\"10\" width=\"50\" height=\"50\" fill=\"rgb(0,0,0)\" "
            + "visibility=\"visible\" opacity=\"0\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1ms\" attributeName=\"opacity\" "
            + "from=\"0\" to=\"1\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"x\" "
            + "from=\"10\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"y\" "
            + "from=\"10\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" "
            + "attributeName=\"width\" from=\"50\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" "
            + "attributeName=\"height\" from=\"50\" to=\"100\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"fill\" "
            + "from=\"rgb(0,0,255)\" to=\"rgb(255,255,0)\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"25000ms\" dur=\"40000ms\" "
            + "attributeName=\"width\" from=\"100\" to=\"150\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"25000ms\" dur=\"40000ms\" "
            + "attributeName=\"height\" from=\"100\" to=\"150\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"25000ms\" dur=\"40000ms\" "
            + "attributeName=\"fill\" from=\"rgb(255,255,0)\" to=\"rgb(0,255,0)\" "
            + "fill=\"freeze\" />\n"
            + "</rect>\n"
            + "\n"
            + "<ellipse id=\"oval\" cx=\"35\" cy=\"35\" rx=\"25\" ry=\"25\" fill=\"rgb(0,0,0)\" "
            + "visibility=\"visible\" opacity=\"0\">\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"1ms\" attributeName=\"opacity\" "
            + "from=\"0\" to=\"1\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"cx\" "
            + "from=\"35\" to=\"150\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"cy\" "
            + "from=\"35\" to=\"150\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"rx\" "
            + "from=\"25\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" attributeName=\"ry\" "
            + "from=\"25\" to=\"50\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0ms\" dur=\"20000ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(255,255,0)\" "
            + "fill=\"freeze\" />\n"
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
    StringBuilder log = new StringBuilder();
    ArrayList<Shape> currentShapesLog = new ArrayList<>();
    ArrayList<Color> currentColorsLog = new ArrayList<>();

    view = new ConfirmInputs(log, currentShapesLog, currentColorsLog);
    controller = new MockControllerForVisualInputsTest(view, model, 10);

    controller.startAnimation();

    // inputs to setCanvasSize()
    assertEquals("0,0,500,500", log.toString());

    // interpolated Shape and Color inputs to addShapes()
    assertEquals(new ArrayList<>(Arrays.asList(
        new java.awt.Rectangle(55, 55, 75, 75), new Ellipse2D.Double(55, 55, 75, 75))),
        currentShapesLog);

    assertEquals(new ArrayList<>(Arrays.asList(
        new Color(128, 128, 128), new Color(128, 128, 128))),
        currentColorsLog);
  }

  @Test
  public void testSetTempo() {
    controller = new ExcellenceController(visualView, model);
    assertEquals(1, controller.getTempo());
    controller.setTempo(15);
    assertEquals(15, controller.getTempo());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroTempo() {
    controller = new ExcellenceController(visualView, model);
    controller.setTempo(0);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTempo() {
    view = new VisualView();
    controller = new ExcellenceController(visualView, model);
    controller.setTempo(-1);

  }

}
