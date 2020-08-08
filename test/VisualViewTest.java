import cs3500.animator.view.IView;
import cs3500.animator.view.VisualView;
import org.junit.Test;

/**
 * Class for testing the functionality of VisualViews. Checks that the unsupported methods are
 * properly suppressed.
 */
public class VisualViewTest {

  private final IView view = new VisualView();

  @Test(expected = UnsupportedOperationException.class)
  public void testGenerateSVG() {
    view.generateSVG("test.svg", "test");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testCreateTextView() {
    view.printTextView("test", "test.txt");
  }
}
