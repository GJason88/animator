import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.VisualController;
import cs3500.animator.model.ShapeAnimation;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the functionality of TextViews. Checks that the unsupported methods are
 * properly suppressed and the method for printing text views to a file or the console print the
 * correct text.
 */
public class TextViewTest {

  private IView view;


  @Before
  public void init() {
    this.view = new TextView();
  }

  @Test
  public void testCreateSVGFile() throws IOException {

    // Specific type name used for testing purposes
    FileWriter a = new FileWriter("TextTest.txt");

    this.view.printTextView("test for\ntext\nview", a);
    a.flush();
    a.close();

    File f = new File("TextTest.txt");
    Scanner s = new Scanner(f);

    StringBuilder output = new StringBuilder();
    while (s.hasNextLine()) {
      output.append(s.nextLine());
      if (s.hasNextLine()) {
        output.append("\n");
      }
    }

    assertEquals("test for\ntext\nview", output.toString());
  }

  @Test
  public void testTextViewV2() throws IOException {
    Appendable a = new StringBuilder();

    this.view.printTextView("abc123", a);

    assertEquals("abc123", a.toString());
  }


  @Test(expected = UnsupportedOperationException.class)
  public void testGenerateSVG() throws IOException {
    this.view.generateSVG(new FileWriter("test.txt"), "test");
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
