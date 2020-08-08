import static org.junit.Assert.assertEquals;

import cs3500.animator.view.IView;
import cs3500.animator.view.TextView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;

/**
 * Class for testing the functionality of TextViews. Checks that the unsupported methods are
 * properly suppressed and the method for printing text views to a file or the console print the
 * correct text.
 */
public class TextViewTest {

  private final IView view = new TextView();

  @Test
  public void testCreateTextView() throws IOException {
    String file = "TextViewTest.txt";
    StringBuilder output = new StringBuilder();
    view.printTextView("test\nfor text\nview", file);
    Scanner s = new Scanner(new File(file));

    while (s.hasNextLine()) {
      output.append(s.nextLine());
      if (s.hasNextLine()) {
        output.append("\n");
      }
    }

    assertEquals("test\nfor text\nview", output.toString());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGenerateSVG() {
    view.generateSVG("test.svg", "test");
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
