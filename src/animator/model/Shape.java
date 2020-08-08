package cs3500.animator.model;

import java.util.List;

/**
 * An interface for shapes that have movements in an animation.
 */
public interface Shape {

  /**
   * Gets the name of a shape.
   *
   * @return the string name of this shape
   */
  String getName();

  /**
   * Gets the list of motions that a shape has.
   *
   * @return the shape's list of motions
   */
  List<Motion> getMotions();

  /**
   * Adds a motion to a shape's list of motions.
   *
   * @param m the motion to add
   * @throws IllegalArgumentException if given motion is null
   */
  void addMotion(Motion m) throws IllegalArgumentException;

  /**
   * Removes a motion from a shape's list of motions.
   *
   * @param m the motion to remove
   * @throws IllegalArgumentException if given motion is null or is not contained in this shape
   */
  void removeMotion(Motion m) throws IllegalArgumentException;

  /**
   * Creates a line of SVG code to define a shape using this shape's first motion as a starting
   * point. The shape begins as 100% opaque so that it is not visible until it has a motion
   * occurring on it.
   *
   * @param offsetX the X offset of the canvas
   * @param offsetY the Y offset of the canvas
   * @return a string containing code to define a shape for an SVG animation.
   */
  String makeSVGHeader(int offsetX, int offsetY);

  /**
   * Generates a string containing the SVG code that defines all of a shape's motions.
   *
   * @param tempo   the tempo of the animation.
   * @param offsetX the X offset of the canvas
   * @param offsetY the Y offset of the canvas
   * @return a string that has SVG code to create animations for all of this shape's motions.
   */
  String makeSVGMotions(int tempo, int offsetX, int offsetY);
}
