import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.VisualController;
import cs3500.animator.model.ShapeAnimation;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the functionality of SVGViews. Checks that the unsupported methods are properly
 * suppressed and the method for generating XML-based SVG code or any given text to a file or the
 * console correctly.
 */
public class SVGViewTest {

  private IView view;

  @Before
  public void init() {
    this.view = new SVGView();
  }


  @Test
  public void testCreateSVGFile() throws IOException {

    // Specific type name used for testing purposes
    FileWriter a = new FileWriter("SVGViewTest.svg");

    this.view.generateSVG(a, "test for\nsvg\nview");
    a.flush();
    a.close();

    File f = new File("SVGViewTest.svg");
    Scanner s = new Scanner(f);

    StringBuilder output = new StringBuilder();
    while (s.hasNextLine()) {
      output.append(s.nextLine());
      if (s.hasNextLine()) {
        output.append("\n");
      }
    }

    assertEquals("test for\nsvg\nview", output.toString());
  }

  @Test
  public void testSVGViewV2() throws IOException {
    Appendable a = new StringBuilder();

    this.view.generateSVG(a, "test for\nsvg\nview");

    assertEquals("test for\nsvg\nview", a.toString());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testCreateTextView() throws IOException {
    this.view.printTextView("test", new FileWriter("test.txt"));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddShapes() {
    this.view.addShapes(new ArrayList<>(), new ArrayList<>());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetCanvasSize() {
    this.view.setCanvasSize(0, 0, 500, 500);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRefresh() {
    this.view.refresh();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testMakeVisible() {
    this.view.makeVisible();
  }

  @Test (expected = UnsupportedOperationException.class)
  public void testAddFeatures() {
    this.view.addFeatures(new VisualController(
        this.view, new ShapeAnimation(new ArrayList<>(), 1,1,1,1)));
  }

  @Test (expected = UnsupportedOperationException.class)
  public void testInitializeSpeedChanger() {
    this.view.initializeSpeedChanger(60);
  }

  @Test (expected = UnsupportedOperationException.class)
  public void testInitializeShapeList() {
    this.view.initializeShapeList(new ArrayList<>());
  }

  @Test (expected = UnsupportedOperationException.class)
  public void testInitializeKeyframeList() {
    this.view.initializeKeyframeList(new ArrayList<>());
  }

  @Test (expected = UnsupportedOperationException.class)
  public void testMakePopupError() {
    this.view.makePopupError("test");
  }

  @Test (expected = UnsupportedOperationException.class)
  public void testUpdateKeyframeEditorGUI() {
    this.view.updateKeyframeEditorGUI(1,2,3,4,5,6,7);
  }


}
