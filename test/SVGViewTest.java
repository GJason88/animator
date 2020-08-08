import static org.junit.Assert.assertEquals;

import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;

/**
 * Class for testing the functionality of SVGViews. Checks that the unsupported methods are properly
 * suppressed and the method for generating XML-based SVG code or any given text to a file or the
 * console correctly.
 */
public class SVGViewTest {

  private final IView view = new SVGView();

  @Test
  public void testCreateSVG() throws FileNotFoundException {
    String file = "SVGViewTest.svg";
    StringBuilder output = new StringBuilder();
    view.generateSVG(file, "test for\nsvg\nview");
    Scanner s = new Scanner(new File(file));

    while (s.hasNextLine()) {
      output.append(s.nextLine());
      if (s.hasNextLine()) {
        output.append("\n");
      }
    }

    assertEquals("test for\nsvg\nview", output.toString());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testCreateTextView() {
    view.printTextView("test", "test.txt");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddShapes() {
    view.addShapes(new ArrayList<>(), new ArrayList<>());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testSetCanvasSize() {
    view.setCanvasSize(0, 0, 500, 500);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testRefresh() {
    view.refresh();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testMakeVisible() {
    view.makeVisible();
  }
}
