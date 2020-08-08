package cs3500.animator.controller;

/**
 * An interface representing different features used by the VisualController to produce and editor
 * view.
 */
public interface IFeatures {

  /**
   * Starts the animation by starting a timer which sends the shapes to draw to the view at every
   * tick. Updates the list of shapes in the model every tick in case new shapes have been added.
   */
  void playAnimation();

  /**
   * Pauses the animation by canceling the current timer.
   */
  void pauseAnimation();

  /**
   * Restarts the animation. Sets the current tick back to 0, and cancels the current timer.
   */
  void restartAnimation();

  /**
   * Starts the animation playing from where it was last paused. Calls start animation if the
   * animation is currently not playing.
   */
  void resumeAnimation();

  /**
   * Turns on and off the looping functionality of an animation. If an animation is set to loop,
   * then the tick will be set back to 0 once the last tick in the animation is reached.
   */
  void toggleLooping();

  /**
   * Change the speed of the animation.
   *
   * @param speed the new speed in ticks per second.
   * @throws IllegalArgumentException if the new speed is not greater than 0
   */
  void setSpeed(int speed) throws IllegalArgumentException;

  /**
   * Creates a spinner that is used to change the speed of the animation. Initializes the spinner to
   * start at the speed of the animation, rather than 0.
   */
  void createSpeedChanger();

  /**
   * Adds shapes to the list of the animation's shapes that is displayed on the GUI.
   */
  void addToShapeGUIList();

  /**
   * Adds a keyframe to the list of keyframes for the selected shape that is dispalyed on the GUI.
   *
   * @param shapeName the name of the shape that is selected
   * @throws IllegalArgumentException if the provided shape name is null
   */
  void addToKeyFrameGUIList(String shapeName) throws IllegalArgumentException;

  /**
   * Deletes the keyframe at the given tick from a shape with the given name.
   *
   * @param selectedKeyframeTick the tick of the keyframe to delete
   * @param selectedShapeName    the name of the shape to delete the keyframe from
   * @throws IllegalArgumentException if the given shape name is null
   */
  void deleteKeyframe(int selectedKeyframeTick, String selectedShapeName)
      throws IllegalArgumentException;

  /**
   * Add a new keyframe at the given tick, for the shape that has the provided name.
   *
   * @param tickToAddAt       the tick to add a new keyframe at
   * @param selectedShapeName the name of the shape to add a keyframe to
   * @throws IllegalArgumentException if the provided shape name is null
   */
  void addNewKeyframe(int tickToAddAt, String selectedShapeName) throws IllegalArgumentException;

  /**
   * Edits the keyframe at the given tick for the shape with the provided name. Edits the keyframe
   * based off of the provided attribute values.
   *
   * @param tick              the tick of the keyframe to edit
   * @param x                 x value of the new keyframe
   * @param y                 y value of the new keyframe
   * @param r                 red value of the new keyframe
   * @param g                 green value of the new keyframe
   * @param b                 blue value of the new keyframe
   * @param height            height value of the new keyframe
   * @param width             width value value of the new keyframe
   * @param selectedShapeName name of the shape to edit a keyframe on
   * @throws IllegalArgumentException if the provided shape name is null
   */
  void editKeyframe(int tick, int x, int y, int r, int g, int b, int height, int width,
      String selectedShapeName) throws IllegalArgumentException;

  /**
   * Updates the GUI components that keep track of the values of the selected keyframe.
   *
   * @param tick              the tick of the selected keyframe
   * @param selectedShapeName the name of the currently selected shape
   * @throws IllegalArgumentException if the provided shape name is null
   */
  void updateKeyframeEditorValues(int tick, String selectedShapeName);

  /**
   * Adds a new shape to the model's list of shapes with the given name.
   * @param name        the name of the new shape to add
   * @param shapeType   the type of shape to add (rectangle or ellipse)
   * @throws IllegalArgumentException if either the given name, or shapeType is null
   */
  void addNewShape(String name, String shapeType);

  /**
   * Removes the shape with the given name from the model's list of shapes.
   * @param name the name of the shape to remove
   * @throws IllegalArgumentException if the given shape name is null
   */
  void removeShape(String name);

  /**
   * Initializes the scrubber for EditorViews by sending the view the last tick of the animation to
   * use as the maximum value of the scrubber.
   */
  void initializeScrubber();

  /**
   * Updates the tick of the animation based off the value of the EditorView's scrubber.
   *
   * @param scrubberTick the value of the scrubber in the editor view
   */
  void updateTick(int scrubberTick);
}
