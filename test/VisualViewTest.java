import cs3500.animator.controller.VisualController;
import cs3500.animator.model.ShapeAnimation;
import cs3500.animator.view.IView;
import cs3500.animator.view.VisualView;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the functionality of VisualViews. Checks that the unsupported methods are
 * properly suppressed.
 */
public class VisualViewTest {
  private IView view;

  @Before
  public void init() {
    this.view = new VisualView();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGenerateSVG() throws IOException {
    view.generateSVG(new StringBuilder("test.svg"), "test");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testCreateTextView() throws IOException {
    view.printTextView("hello", System.out);
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
