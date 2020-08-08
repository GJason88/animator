import cs3500.animator.view.IView;
import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for testing inputs that the controller gets from the model and sends to the view. Has
 * tests for inputs to SVG, Text, and Visual View methods.
 */
public class ConfirmInputs implements IView {
  private final StringBuilder log;
  private final ArrayList<Shape> currentShapes;
  private final ArrayList<Color> currentColors;

  /**
   * Constructor for confirming that inputs the controller receives from the model and sends to the
   * view are correct using a StringBuilder to store the inputs being received.
   *
   * @param log StringBuilder to store the inputs being received from the controller
   */
  public ConfirmInputs(StringBuilder log, ArrayList<Shape> currentShapes,
      ArrayList<Color> currentColors) {
    this.log = Objects.requireNonNull(log);
    this.currentShapes = currentShapes;
    this.currentColors = currentColors;

  }

  @Override
  public void generateSVG(String location, String svgText) {
    this.log.append(String.format("%s,%s", location, svgText));
  }

  @Override
  public void printTextView(String textDescription, String location) {
    this.log.append(String.format("%s,%s", textDescription, location));
  }

  @Override
  public void addShapes(List<Shape> currentShapes, List<Color> currentColors) {
    this.currentShapes.addAll(currentShapes);
    this.currentColors.addAll(currentColors);
  }

  @Override
  public void setCanvasSize(int x, int y, int width, int height) {
    this.log.append(String.format("%d,%d,%d,%d", x, y, width, height));

  }

  @Override
  public void refresh() throws UnsupportedOperationException {
    // no inputs to test so do nothing.
  }

  @Override
  public void makeVisible() throws UnsupportedOperationException {
    // no inputs to test so do nothing.
  }
}
