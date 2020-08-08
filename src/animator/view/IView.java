package cs3500.animator.view;

import java.awt.Color;
import java.awt.Shape;
import java.util.List;

/**
 * Interface to represent the views for an IModel Simple Animation. Consists of methods to show
 * the textual view, visual view per a JPanel, and converting the animation to an SVG file to be
 * viewed in a browser.
 */
public interface IView {

  /**
   * Generates an SVG file with a given location/name that is able to be opened in a
   * browser to view the animation visually. If directory is empty or invalid, prints to console.
   *
   * @param location the given location and name in which to create the SVG file or replace an
   *                 already existing file
   * @param svgText the text that the SVG file will consist of
   */
  void generateSVG(String location, String svgText);

  /**
   * Prints the given text description of an IModel Animation to the console or to the given
   * directory to be viewed as a textual view.
   *
   * @param textDescription the given text description to be printed to the console
   * @param location the given location for the textual view to go as a .txt file.
   *                  If directory is empty or invalid, prints to System.out
   */
  void printTextView(String textDescription, String location);

  /**
   * Adds the list of current shapes and the the list of the current colors for those shapes for
   * the current frame to the composing JPanel for visual view JFrames.
   *
   * @param currentShapes the given current shapes for this frame
   * @param currentColors the given current colors for the current shapes for this frame
   */
  void addShapes(List<Shape> currentShapes, List<Color> currentColors);

  /**
   * Sets the canvas size and x, y offset for the visual view JFrame that will contain the animation
   * to be viewed visually.
   *
   * @param x the given x offset for viewing the JFrame
   * @param y the given y offset for viewing the JFrame
   * @param width the given width for the Panel in the JFrame
   * @param height the given height for the Panel in the JFrame
   */
  void setCanvasSize(int x, int y, int width, int height);

  /**
   * Refreshes the JFrame by using JFrame's repaint() function to repaint the frame with the updated
   * features.
   */
  void refresh();

  /**
   * Makes the JFrame visible by using JFrame's setVisible() function for starting the animation.
   */
  void makeVisible();

}
