import cs3500.animator.controller.IFeatures;
import cs3500.animator.view.IView;
import java.awt.Color;
import java.awt.Shape;

import java.util.List;
import java.util.Objects;

/**
 * Class for testing inputs that the controller gets from the model and sends to the view. Has
 * tests for inputs to SVG, Text, and Visual View methods.
 */
public class ConfirmInputs implements IView {
  private final StringBuilder log;
  private final List<Shape> currentShapes;
  private final List<Color> currentColors;
  private final List<String> testStringList;
  private IFeatures testFeature;

  /**
   * Constructor for confirming that inputs the controller receives from the model and sends to the
   * view are correct using a StringBuilder to store the inputs being received.
   *
   * @param log StringBuilder to store the inputs being received from the controller
   */
  public ConfirmInputs(StringBuilder log, List<Shape> currentShapes,
      List<Color> currentColors, List<String> testStringList, IFeatures testFeature) {
    this.log = Objects.requireNonNull(log);
    this.currentShapes = currentShapes;
    this.currentColors = currentColors;
    this.testStringList = testStringList;
    this.testFeature = testFeature;

  }


  @Override
  public void generateSVG(Appendable a, String svgText) {
    this.log.append(String.format("%s", svgText));

  }

  @Override
  public void printTextView(String textDescription, Appendable a) {
    this.log.append(String.format("%s", textDescription));

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

  @Override
  public void addFeatures(IFeatures features) {
    this.testFeature = features;

  }

  @Override
  public void initializeSpeedChanger(int speed) {
    this.log.append(String.format("%d", speed));
  }

  @Override
  public void initializeShapeList(List<String> shapes) {
    this.testStringList.addAll(shapes);
  }

  @Override
  public void initializeKeyframeList(List<String> keyframes) {
    this.testStringList.addAll(keyframes);
  }

  @Override
  public void makePopupError(String message) {
    this.log.append(message);
  }

  @Override
  public void updateKeyframeEditorGUI(int x, int y, int r, int g, int b, int height, int width) {
    this.log.append(String.format("%d,%d,%d,%d,%d,%d,%d", x, y, r, g, b, height, width));
  }

  @Override
  public void initializeScrubber(int lastTick) {
    this.log.append(String.format("%d", lastTick));
  }

  @Override
  public void updateScrubber(int currentTick) {
    this.log.append(String.format("%d", currentTick));
  }


}
