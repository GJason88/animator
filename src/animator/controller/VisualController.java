package cs3500.animator.controller;

import cs3500.animator.model.IModel;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Oval;
import cs3500.animator.view.IView;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

/**
 * A controller used to send data from the model to visual-based views. This includes {@link
 * cs3500.animator.view.VisualView} and {@link cs3500.animator.view.EditorView}. This controller
 * uses a timer to determine the rate at which it sends data to the view. Every tick, it finds out
 * what shapes have motions that are occurring, and creates a list of {@link Shape} objects and
 * Color objects which are send to the visual/editor view to display.
 */
public class VisualController extends AbstractController implements IFeatures {

  private int tick;
  private Timer timer;
  private int lastTick;

  private boolean isLooping;
  private boolean isPlaying;

  /**
   * Constructs a visual controller using the given view and model. Defaults to tempo being 1,
   * isLooping to false, and isPlaying to false.
   *
   * @param view  the view to display data on
   * @param model the model to get data from
   */
  public VisualController(IView view, IModel model) {
    super(view, model);

    this.tick = 0;
    this.tempo = 1;
    this.lastTick = this.findLastTick();
    this.isLooping = false;
    this.isPlaying = false;
  }

  /**
   * Initializes the view by setting the canvas size to the model's width and height. Also takes
   * into accound the X and Y offset. Finally, it makes the view visible.
   */
  public void initializeAnimation() {
    this.view.setCanvasSize(
        this.model.getCanvasX(), this.model.getCanvasY(),
        this.model.getCanvasWidth(), this.model.getCanvasHeight());
    this.view.makeVisible();
  }

  /**
   * Starts the animation off with a new timer that sends shape and color data to the view at a
   * particular tempo. The tick is incremented at the tempo rate, and the list of shapes for the
   * controller is also updated each tick in case a new shape is added while the animation is
   * running.
   */
  public void startAnimation() {
    this.isPlaying = true;
    this.timer = new Timer();
    this.timer.scheduleAtFixedRate(
        new TimerTask() {
          @Override
          public void run() {
            if (tick > lastTick) {
              if (isLooping) {
                tick = 0;
              } else {
                return;
              }
            }
            shapes = model.getShapes();
            findShapesToDraw();
            view.updateScrubber(tick);
            tick++;
          }
        },
        0, (long) 1000.0 / this.tempo);
  }


  /**
   * Method to get the shapes from the model and determine which motions within the shapes are
   * occurring at the current tick and sends them along with their color to the view to be drawn.
   */
  private void findShapesToDraw() {
    List<Shape> currentShapes = new ArrayList<>();
    List<Color> currentColors = new ArrayList<>();
    List<Integer> interpolatedFields;
    String shapeType;
    Map<String, Function<List<Integer>, Shape>> shapeTypes = new HashMap<>();

    // Creates a java.awt graphical shape based on what class we are looking at (Rectangle or Oval)
    shapeTypes.put("Rectangle",
        (mp) -> new Rectangle(mp.get(0), mp.get(1), mp.get(2), mp.get(3)));
    shapeTypes.put("Oval",
        (mp) -> new Ellipse2D.Double(mp.get(0), mp.get(1), mp.get(2), mp.get(3)) {
        });
    for (cs3500.animator.model.Shape s : this.model.getShapes()) {
      shapeType = s.getClass().getSimpleName();
      for (IMotion m : s
          .getMotions()) {
        if (this.tick >= m.getStartingKeyframe().getTick() && this.tick <= m.getEndingKeyframe()
            .getTick()) {
          interpolatedFields =
              new ArrayList<>(Arrays.asList(
                  this.interpolate(
                      m.getStartingKeyframe().getTick(), m.getEndingKeyframe().getTick(),
                      m.getStartingKeyframe().getX(), m.getEndingKeyframe().getX())
                      - this.model.getCanvasX(),
                  this.interpolate(
                      m.getStartingKeyframe().getTick(), m.getEndingKeyframe().getTick(),
                      m.getStartingKeyframe().getY(), m.getEndingKeyframe().getY())
                      - this.model.getCanvasY(),
                  this.interpolate(
                      m.getStartingKeyframe().getTick(), m.getEndingKeyframe().getTick(),
                      m.getStartingKeyframe().getWidth(), m.getEndingKeyframe().getWidth()),
                  this.interpolate(
                      m.getStartingKeyframe().getTick(), m.getEndingKeyframe().getTick(),
                      m.getStartingKeyframe().getHeight(), m.getEndingKeyframe().getHeight()))
              );

          // adds the shapes to be drawn at the current tick to a list that is to be passed to the
          // view.
          currentShapes.add(shapeTypes.get(shapeType).apply(interpolatedFields));
          currentColors.add(
              new Color(
                  this.interpolate(
                      m.getStartingKeyframe().getTick(), m.getEndingKeyframe().getTick(),
                      m.getStartingKeyframe().getR(), m.getEndingKeyframe().getR()),
                  this.interpolate(
                      m.getStartingKeyframe().getTick(), m.getEndingKeyframe().getTick(),
                      m.getStartingKeyframe().getG(), m.getEndingKeyframe().getG()),
                  this.interpolate(
                      m.getStartingKeyframe().getTick(), m.getEndingKeyframe().getTick(),
                      m.getStartingKeyframe().getB(), m.getEndingKeyframe().getB())));
        }
      }
    }
    this.view.addShapes(currentShapes, currentColors);
    this.view.refresh();
  }

  /**
   * Interpolates the "between" values of a given field based on the current tick and the starting
   * and ending ticks of the motion of a Shape.
   *
   * @param startTick  the starting tick of the motion
   * @param endTick    the ending tick of the motion
   * @param startValue the starting value of the field in the motion
   * @param endValue   the ending value of the field in the motion
   * @return the interpolated value of the field in the motion.
   */
  private int interpolate(int startTick, int endTick, int startValue, int endValue) {
    return (int) Math.round(startValue * ((double) (endTick - this.tick) / (endTick - startTick))
        + endValue * ((double) (this.tick - startTick) / (endTick - startTick)));
  }


  /**
   * Finds the ending tick of the last motion that occurs in this animation.
   *
   * @return the ending tick of the last motion that happens in this IModel Animation.
   */
  private int findLastTick() {
    int endingTick;
    int lastTick = 0;

    for (cs3500.animator.model.Shape s : this.shapes) {
      for (IMotion m : s.getMotions()) {
        endingTick = m.getEndingKeyframe().getTick();
        if (endingTick > lastTick) {
          lastTick = endingTick;
        }
      }
    }
    return lastTick;
  }

  @Override
  public void setTempo(int t) throws IllegalArgumentException {
    if (t < 1) {
      throw new IllegalArgumentException("Tempo must be positive.");
    }
    this.tempo = t;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }


  /**
   * Starts the animation.
   */
  @Override
  public void playAnimation() {
    this.startAnimation();
  }

  /**
   * Stops the animation by canceling the timer.
   */
  @Override
  public void pauseAnimation() {
    this.isPlaying = false;
    this.timer.cancel();
  }

  /**
   * Restarts the animation by setting the tick back to 0, and canceling the timer.
   */
  @Override
  public void restartAnimation() {
    this.tick = 0;
    this.view.updateScrubber(0);
    this.isPlaying = false;
    this.timer.cancel();
  }

  /**
   * Resumes the animation from where it was last paused.
   */
  @Override
  public void resumeAnimation() {
    if (!isPlaying) {
      this.startAnimation();

    }
  }

  /**
   * Toggles on and off the looping behavior of the editor view.
   */
  @Override
  public void toggleLooping() {
    this.isLooping = !isLooping;
  }

  /**
   * Sets the speed of the animation that is viewed in the editor view. Cancels the current timer to
   * create a new one using the new speed.
   *
   * @param speed the new speed in ticks per second.
   * @throws IllegalArgumentException if the speed is less than 1.
   */
  @Override
  public void setSpeed(int speed) throws IllegalArgumentException {
    if (speed < 1) {
      throw new IllegalArgumentException("New speed must be greater than 0");
    }
    this.tempo = speed;
    if (isPlaying) {
      this.timer.cancel();
      startAnimation();
    }
  }

  @Override
  public void addFeaturesToView() {
    this.view.addFeatures(this);
  }


  @Override
  public void createSpeedChanger() {
    this.view.initializeSpeedChanger(this.tempo);
  }


  @Override
  public void addToShapeGUIList() {
    List<String> stringsToAdd = new ArrayList<>();

    for (cs3500.animator.model.Shape s : this.model.getShapes()) {
      stringsToAdd.add(s.getName());
    }

    this.view.initializeShapeList(stringsToAdd);
  }

  @Override
  public void addToKeyFrameGUIList(String shapeName) throws IllegalArgumentException {
    if (shapeName == null) {
      throw new IllegalArgumentException("Given shape name cannot be null");
    }
    List<String> stringsToAdd = new ArrayList<>();
    for (Keyframe k : this.model.getKeyframesForShape(shapeName)) {
      stringsToAdd.add(k.toString());
    }

    this.view.initializeKeyframeList(stringsToAdd);
  }

  @Override
  public void deleteKeyframe(int selectedKeyframeTick, String selectedShapeName) {
    this.model.getShapeWithName(selectedShapeName).deleteKeyframe(selectedKeyframeTick);
    // Update the last tick in case we deleted the last keyframe
    if (selectedKeyframeTick == this.lastTick) {
      this.lastTick = findLastTick();
    }
  }

  @Override
  public void addNewKeyframe(int tickToAddAt, String selectedShapeName) {
    if (selectedShapeName == null) {
      throw new IllegalArgumentException("Given shape name cannot be null");
    }
    try {
      this.model.getShapeWithName(selectedShapeName).addKeyframe(tickToAddAt);

    } catch (IllegalArgumentException e) {
      view.makePopupError("There is already a keyframe at this tick.");
    }
    // Update the last tick to now be the new tick if it happens after the old last tick.
    if (this.lastTick < tickToAddAt) {
      this.lastTick = tickToAddAt;
    }


  }

  @Override
  public void editKeyframe(int tick, int x, int y, int r, int g, int b, int height, int width,
      String selectedShapeName) {

    this.model.getShapeWithName(selectedShapeName)
        .editKeyframe(tick, new Keyframe(tick, x, y, r, g, b, height, width));

  }

  @Override
  public void updateKeyframeEditorValues(int tick, String selectedShapeName) {
    for (Keyframe k : this.model.getKeyframesForShape(selectedShapeName)) {
      if (k.getTick() == tick) {
        this.view.updateKeyframeEditorGUI(k.getX(), k.getY(), k.getR(), k.getG(), k.getB(),
            k.getHeight(), k.getWidth());
        break;
      }
    }
  }

  @Override
  public void addNewShape(String name, String shapeType) {
    if (name == null || shapeType == null) {
      throw new IllegalArgumentException("Given shape name or type cannot be null");
    }
    switch (shapeType) {
      case "Rectangle":
        this.model.addShape(new cs3500.animator.model.Rectangle(name, new ArrayList<>()));
        break;
      case "Ellipse":
        this.model.addShape(new Oval(name, new ArrayList<>()));
        break;
      default:
        this.view.makePopupError("Not a valid shape type");
    }
  }

  @Override
  public void removeShape(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Given shape name cannot be null");
    }
    this.model.removeShape(name);
  }

  @Override
  public void initializeScrubber() {
    this.view.initializeScrubber(this.lastTick);
  }

  @Override
  public void updateTick(int scrubberTick) {
    this.tick = scrubberTick;
    findShapesToDraw();
  }

}

