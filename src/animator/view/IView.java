package cs3500.animator.view;

import cs3500.animator.controller.IFeatures;
import java.awt.Color;
import java.awt.Shape;
import java.io.IOException;
import java.util.List;

/**
 * Interface to represent the views for an IModel Simple Animation. Consists of methods to show
 * the textual view, visual view per a JPanel, and converting the animation to an SVG file to be
 * viewed in a browser.
 */
public interface IView {

  /**
   * Generates an SVG file with a given location/name that is able to be opened in a browser to view
   * the animation visually. If directory is empty or invalid, prints to console.
   *
   * @param a        the appendable to write to. Either System.out or an SVG file
   * @param svgText  the text that the SVG file will consist of
   * @throws IOException if unable to write to the given appendable for whatever reason.
   */
  void generateSVG(Appendable a, String svgText) throws IOException;

  /**
   * Prints the given text description of an IModel Animation to the console or to the given
   * directory to be viewed as a textual view.
   *
   * @param textDescription the given text description to be printed to the console
   * @param a        the appendable to write to. Either System.out, or a text file.
   * @throws IOException if unable to write to the given appendable for whatever reason.
   */
  void printTextView(String textDescription, Appendable a) throws IOException;

  /**
   * Adds the list of current shapes and the the list of the current colors for those shapes for the
   * current frame to the composing JPanel for visual view JFrames.
   *
   * @param currentShapes the given current shapes for this frame
   * @param currentColors the given current colors for the current shapes for this frame
   */
  void addShapes(List<Shape> currentShapes, List<Color> currentColors);

  /**
   * Sets the canvas size and x, y offset for the visual view JFrame that will contain the animation
   * to be viewed visually.
   *
   * @param x      the given x offset for viewing the JFrame
   * @param y      the given y offset for viewing the JFrame
   * @param width  the given width for the Panel in the JFrame
   * @param height the given height for the Panel in the JFrame
   * @throws IllegalArgumentException if the given width or height are negative
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

  /**
   * Method to add features to various Swing components on an EditorView (buttons, text fields,
   * selection lists, etc.). Utilizes callbacks to the controller to set the functionality of these
   * components.
   *
   * @param features the given features implementation that implements the functionality of the
   *                 callback methods.
   */
  void addFeatures(IFeatures features);

  /**
   * Method to initialize the JSpinner used to represent the speed of the animation to the speed
   * that is specified upon startup of an EditorView animation.
   *
   * @param speed the speed of the JSpinner to be initialized to
   */
  void initializeSpeedChanger(int speed);

  /**
   * Method to initialize the list of Shape that is contained within this animation. Works by
   * adding each Shape's name in the animation's list of Shape to the JList's content.
   *
   * @param shapes a list of all the shape names of all the shapes in this animation
   */
  void initializeShapeList(List<String> shapes);

  /**
   * Method to initialize the JList representing the list of keyframes for the current selected
   * Shape in the shape selection list. Works by adding each keyframe in the selected shape to the
   * JList's content.
   *
   * @param keyframes the given keyframe descriptions in which to initialize the GUI list
   */
  void initializeKeyframeList(List<String> keyframes);

  /**
   * Creates a pop-up error message that shows a given message. Used to notify the client when
   * there is an invalidity in the interactions.
   *
   * @param message The message to be shown in the error pop-up
   */
  void makePopupError(String message);

  /**
   * Updates a Keyframe to the specified x, y, color, height, and width values. Used for editing
   * selected Keyframes of a selected Shape to the given values in JSpinners.
   * @param x the x value of the keyframe to be changed to
   * @param y the y value of the keyframe to be changed to
   * @param r the r value of the color of the keyframe to be changed to
   * @param g the g value of the color of the keyframe to be changed to
   * @param b the b value of the color of the keyframe to be changed to
   * @param height the height of the keyframe to be changed to
   * @param width the width of the keyframe to be changed to
   */
  void updateKeyframeEditorGUI(int x, int y, int r, int g, int b, int height, int width);


}


