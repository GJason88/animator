package cs3500.animator.controller;

import java.io.IOException;

/**
 * Interface to represent the controller of any IModel Simple Animations. Gets information
 * necessary for viewing the animation textually, visually, or through an XML-based SVG file from
 * the model and sends it to the view for the displaying of the animation.
 */
public interface IController {

  /**
   * Method to create an XML-based SVG file of an IModel Animation in the given directory and
   * with the given name by getting the Shape information from the model and sending the XML text of
   * that information to the view for the outputting of the svg file or to the console.
   *
   * @param location the given directory and name for which the file will be placed or replaced
   * @throws IOException if the file exists but is a directory rather than a regular file, does not
   *                     exist but cannot be created, or cannot be opened for any other reason.
   */
  void createSVG(String location) throws IOException;

  /**
   * Method to create a textual view of an IModel Animation by getting the Shape information from
   * the model and sending a text description of the animation to the view to be displayed in the
   * console or text file.
   */
  void createTextView(String location);

  /**
   * Method to start a visual view of an IModel Animation by setting the VisualView to be visible,
   * getting the canvas offset and size and sending them to the view to set the Panel, and getting
   * the shapes from the model and until the last tick is hit, determines which motions within the
   * shapes are occurring at the current tick and sends them to the view to be drawn, refreshing the
   * view for every passing tick. Ticks increase at the rate of the given tempo per second.
   */
  void startAnimation();

  /**
   * Sets the tempo of this animation to the given value.
   *
   * @param t the given tempo for this animation to be changed to
   * @throws IllegalArgumentException if the given tempo is not positive
   */
  void setTempo(int t) throws IllegalArgumentException;

  /**
   * Gets the tempo of this animation.
   *
   * @return the tempo of this animation
   */
  int getTempo();

}
