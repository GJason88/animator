package cs3500.animator.view;

import cs3500.animator.controller.IFeatures;
import java.awt.Color;
import java.awt.Shape;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;

/**
 * Abstract class to represent an abstract view implementation of IView. Depicts the
 * different types of views that IView supports (Visual, Textual, and SVG). Allows for the usage
 * of a ViewFactory to produce the correct view based on command-line inputs in the main method.
 */
public class AbstractView extends JFrame implements IView {

  /**
   * Unsupported for SVGViews and VisualViews.
   *
   * @param textDescription the given text description to be printed to the console
   * @throws UnsupportedOperationException always for SVGViews and VisualViews.
   */
  @Override
  public void printTextView(String textDescription, Appendable a)
      throws UnsupportedOperationException, IOException {
    throw new UnsupportedOperationException("Unsupported for SVG Views and Visual Views.");
  }

  /**
   * Unsupported for TextViews and VisualViews.
   *
   * @param a        the appendable to write to.
   * @param svgText  the text that the SVG file will consist of
   * @throws UnsupportedOperationException always for TextViews and VisualViews.
   */
  @Override
  public void generateSVG(Appendable a, String svgText)
      throws UnsupportedOperationException, IOException {
    throw new UnsupportedOperationException("Unsupported for Textual View and Visual Views.");
  }

  /**
   * Unsupported for SVGViews and TextViews.
   *
   * @param currentShapes the given current shapes for this frame
   * @param currentColors the given current colors for the current shapes for this frame
   * @throws UnsupportedOperationException always for SVGViews and TextViews.
   */
  @Override
  public void addShapes(List<Shape> currentShapes, List<Color> currentColors)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Unsupported for SVG View and Textual Views.");
  }

  /**
   * Unsupported for SVGViews and TextViews.
   *
   * @param x      the given x offset for viewing the JFrame
   * @param y      the given y offset for viewing the JFrame
   * @param width  the given width for the Panel in the JFrame
   * @param height the given height for the Panel in the JFrame
   * @throws UnsupportedOperationException always for SVGViews and TextViews.
   */
  @Override
  public void setCanvasSize(int x, int y, int width, int height)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Unsupported for SVG View and Textual Views.");
  }

  /**
   * Unsupported for SVGViews and TextViews.
   *
   * @throws UnsupportedOperationException always for SVGViews and TextViews.
   */
  @Override
  public void refresh() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Unsupported for SVG View and Textual Views.");
  }

  /**
   * Unsupported for SVGViews and TextViews.
   *
   * @throws UnsupportedOperationException always for SVGViews and TextViews.
   */
  @Override
  public void makeVisible() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Unsupported for SVG View and Textual Views.");
  }

  // Methods used in the editor view
  /**
   * Unsupported for SVGViews, VisualViews, and TextViews.
   *
   * @throws UnsupportedOperationException always for SVGViews, VisualViews, and TextViews.
   */
  @Override
  public void addFeatures(IFeatures features) {
    throw new UnsupportedOperationException("Unsupported for SVG View, visual, and Textual Views.");
  }

  /**
   * Unsupported for SVGViews, VisualViews, and TextViews.
   *
   * @throws UnsupportedOperationException always for SVGViews, VisualViews, and TextViews.
   */
  @Override
  public void initializeSpeedChanger(int speed) {
    throw new UnsupportedOperationException("Unsupported for SVG View, visual, and Textual Views.");

  }

  /**
   * Unsupported for SVGViews, VisualViews, and TextViews.
   *
   * @throws UnsupportedOperationException always for SVGViews, VisualViews, and TextViews.
   */
  @Override
  public void initializeShapeList(List<String> shapes) {
    throw new UnsupportedOperationException("Unsupported for SVG View, visual, and Textual Views.");

  }

  /**
   * Unsupported for SVGViews, VisualViews, and TextViews.
   *
   * @throws UnsupportedOperationException always for SVGViews, VisualViews, and TextViews.
   */
  @Override
  public void initializeKeyframeList(List<String> keyframes) {
    throw new UnsupportedOperationException("Unsupported for SVG View, visual, and Textual Views.");

  }

  /**
   * Unsupported for SVGViews, VisualViews, and TextViews.
   *
   * @throws UnsupportedOperationException always for SVGViews, VisualViews, and TextViews.
   */
  @Override
  public void makePopupError(String message) {
    throw new UnsupportedOperationException("Unsupported for SVG View, visual, and Textual Views.");

  }

  /**
   * Unsupported for SVGViews, VisualViews, and TextViews.
   *
   * @throws UnsupportedOperationException always for SVGViews, VisualViews, and TextViews.
   */
  @Override
  public void updateKeyframeEditorGUI(int x, int y, int r, int g, int b, int height, int width) {
    throw new UnsupportedOperationException("Unsupported for SVG View, visual, and Textual Views.");
  }

}
