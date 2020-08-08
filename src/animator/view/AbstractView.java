package cs3500.animator.view;

import java.awt.Color;
import java.awt.Shape;
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
  public void printTextView(String textDescription, String directory)
      throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Unsupported for SVG Views and Visual Views.");
  }

  /**
   * Unsupported for TextViews and VisualViews.
   *
   * @param location the given location and name in which to create the SVG file or replace an
   *                 already existing file
   * @param svgText  the text that the SVG file will consist of
   * @throws UnsupportedOperationException always for TextViews and VisualViews.
   */
  @Override
  public void generateSVG(String location, String svgText)
      throws UnsupportedOperationException {
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

  /**
   * Creates a new ViewFactory for making a specific view type for the Excellence main() method.
   *
   * @return a new ViewFactory
   */
  public static ViewFactory createViewFactory() {
    return new ViewFactory();
  }

  /**
   * Factory class to create a TextView, VisualView, or SVGView based on command-line input in the
   * Excellence main() method.
   */
  public static class ViewFactory {

    /**
     * Creates a TextView, VisualView, or SVGView based on a given string that is the command-line
     * input in the main() method of Excellence.
     *
     * @param view the given view type as a command-line input
     * @return a new IView implementation based on the string given
     */
    public IView create(String view) {
      switch (view) {
        case "text":
          return new TextView();
        case "svg":
          return new SVGView();
        case "visual":
          return new VisualView();
        default:
          return null;
      }
    }
  }
}
