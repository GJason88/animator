package cs3500.animator.controller;

import java.io.IOException;

/**
 * Interface to represent the controller of any IModel Simple Animations. Gets information necessary
 * for viewing the animation textually, visually, or through an XML-based SVG file from the model
 * and sends it to the view for the displaying of the animation.
 */
public interface IController {

  /**
   * Method to create an XML-based SVG file of an IModel Animation in the given directory and with
   * the given name by getting the Shape information from the model and sending the XML text of that
   * information to the view for the outputting of the svg file or to the console.
   *
   * @param a the location to write to, either System.out or an SVG file
   * @throws IOException                   if the appendable can't be written to for whatever
   *                                       reason.
   * @throws UnsupportedOperationException if called by a controller that does not support this
   *                                       operation (text, visual)
   */
  void createSVG(Appendable a) throws IOException, UnsupportedOperationException;

  /**
   * Method to create a textual view of an IModel Animation by getting the Shape information from
   * the model and sending a text description of the animation to the view to be displayed in the
   * console or text file.
   *
   * @param a the location to write to. Either System.out or a text file.
   * @throws IOException                   if unable to write to the appendable for whatever
   *                                       reason.
   * @throws UnsupportedOperationException if called by a controller that does not support this
   *                                       operation (SVG, visual)
   */
  void createTextView(Appendable a) throws IOException, UnsupportedOperationException;


  /**
   * Method to start a visual or editor view of an IModel Animation. Sets the view to be visible,
   * gets the canvas offset and size and sends them to the view to setup the Panel. For each tick,
   * the controller gets the shapes from the model and determines which motions within the shapes
   * are occurring at the current tick. It then sends them to the view to be drawn, refreshing the
   * view for every passing tick. Ticks increase at the rate of controller's tempo field in ticks
   * per second.
   *
   * @throws UnsupportedOperationException if called by a controller that does not support this
   *                                       operation (text, SVG)
   */
  void startAnimation() throws UnsupportedOperationException;

  /**
   * Sets the tempo of this animation to the given value.
   *
   * @param t the given tempo for this animation to be changed to (in ticks per second)
   * @throws IllegalArgumentException      if the given tempo is not positive
   * @throws UnsupportedOperationException if called by a controller that does not support this
   *                                       operation (text, SVG)
   */
  void setTempo(int t) throws IllegalArgumentException, UnsupportedOperationException;

  /**
   * Gets the tempo for the animation.
   *
   * @return the tempo for the animation
   * @throws UnsupportedOperationException if called by a controller that does not support this
   *                                       operation (text, SVG)
   */
  int getTempo() throws UnsupportedOperationException;

  /**
   * Adds a list of features to be used in the view. Uses the {@link IFeatures} interface.
   *
   * @throws UnsupportedOperationException if called by a controller that does not support this
   *                                       operation (text, SVG)
   */
  void addFeaturesToView() throws UnsupportedOperationException;

  /**
   * Initializes a visual controller by setting the visual/editor view's canvas size and offset, as
   * well as making it visible.
   *
   * @throws UnsupportedOperationException if called by a controller that does not support this
   *                                       operation (text, SVG)
   */
  void initializeAnimation() throws UnsupportedOperationException;


}
