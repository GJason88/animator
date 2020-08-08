package cs3500.animator.model;

import java.util.List;

/**
 * An interface to represent the Model of a single Excellence Animation. It is used for storing,
 * accessing and manipulating an Animation's Shapes and their motions for use in the Controller and
 * View of an Animation.
 */
public interface IModel {

  /**
   * Gets the list of shapes within this animation.
   *
   * @return a list of {@link Shape} objects that are being animated.
   */
  List<Shape> getShapes();

  /**
   * Adds a shape to the animation's list of shapes to be animated.
   *
   * @param s the shape to add to the animation.
   * @throws IllegalArgumentException if the given shape is null
   */
  void addShape(Shape s) throws IllegalArgumentException;

  /**
   * Adds a motion to the list of motions for a particular shape.
   *
   * @param m the motion to add
   * @param s the shape to add the motion to
   * @throws IllegalArgumentException if the given motion or shape is null or the shape does not
   *                                  exist in the animation.
   *                                  if the adding of a motion causes the animation to contain an
   *                                  inconsistency.
   */
  void addMotion(IMotion m, Shape s) throws IllegalArgumentException;

  /**
   * Removes a motion from the list of motions for a particular shape.
   *
   * @param m the motion to remove
   * @param s the shape to remove the motion from
   * @throws IllegalArgumentException if the given motion or shape is null or the shape does not
   *                                  contain the motion or the shape does not exist in the
   *                                  animation
   *                                  if the removal of a motion causes the animation to contain an
   *                                  inconsistency.
   */
  void removeMotion(IMotion m, Shape s) throws IllegalArgumentException;

  /**
   * Removes a shape from an animation's list of shapes.
   *
   * @param shameName                 the name of the shape to remove
   * @throws IllegalArgumentException if the given shape is null
   */
  void removeShape(String shameName) throws IllegalArgumentException;

  /**
   * Gets the minimum x value for the animation canvas.
   *
   * @return the minimum x value for the animation canvas.
   */
  int getCanvasX();

  /**
   * Gets the minimum y value for the animation canvas.
   *
   * @return the minimum y value for the animation canvas.
   */
  int getCanvasY();

  /**
   * Gets the width of the canvas for this animation.
   *
   * @return the width of the canvas in pixels.
   */
  int getCanvasWidth();

  /**
   * Gets the height of the canvas for this animation.
   *
   * @return the height of the canvas in pixels.
   */
  int getCanvasHeight();

  /**
   * Gets the list of motions for a given shape in this animation.
   * @param s the given shape to get the motions for
   * @return a List of Motion of the given shape
   * @throws IllegalArgumentException if the given shape is null or does not exist in this
   *                                  animation
   */
  List<IMotion> getMotionsForShape(Shape s) throws IllegalArgumentException;

  /**
   * Get keyframes for the shape with the given name.
   *
   * @param shapeName the name of the shape to get keyframes for
   * @return the list of Keyframes for the shape with the given name
   */
  List<Keyframe> getKeyframesForShape(String shapeName) throws IllegalArgumentException;

  /**
   * Gets the shape with the given name in this animation.
   *
   * @param shapeName the name of the shape to find
   * @return the Shape with the given name
   * @throws IllegalArgumentException if the given shape name is null, or there is no shape in
   *                                  this animation with the given name
   */
  Shape getShapeWithName(String shapeName) throws IllegalArgumentException;

}
