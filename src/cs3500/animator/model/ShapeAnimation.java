package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Representing an animation on a list of shapes. An animation consists of a list of shapes, each of
 * which has a list of motions that occur to them. Animations also contain information on the canvas
 * size, and the minimum x and y values that elements can be shown at.
 *
 * <p>Invariants - Motions in an animation can not overlap or conflict with each other. This means
 * that there cannot be two motions changing color, size, or position on the same shape at the same
 * time. - This is enforced by the {@code overlaps} methods which checks that if two motions occur
 * within overlapping time periods, that they do not both attempt to modify the same field. - A
 * motion must also pick up with the same state that the motion chronologically before it ended. -
 * For example, if one motion ends leaving Shape A as red, the next movement that occurs on Shape A
 * must start with it being red. - This is enforced when we check that if the motion that occurs
 * after a given motion starts in the same state the previous motion left the shape in using the
 * getSameState method. The {@code checkForBadOverlaps} method also helps here.</p>
 */
public class ShapeAnimation implements IModel {

  private final List<Shape> shapes;
  private final int canvasX;
  private final int canvasY;
  private final int canvasWidth;
  private final int canvasHeight;

  /**
   * Creates an animation using a provided list of shapes, and canvas specifications.
   * empty list of shapes
   *
   * @param shapes       the list of shapes to animate.
   * @param canvasX      the minimum x value that objects can be at during the animation.
   * @param canvasY      the minimum y value that objects can be at during the animation.
   * @param canvasWidth  the width of the canvas for display the animation visually.
   * @param canvasHeight the height of the canvas for displaying the canvas visually.
   * @throws IllegalArgumentException if a shape's motion does not start in the state that the
   *                                  motion that began before it left off. if two motion overlap
   *                                  timewise with another motion and attempts to modify the same
   *                                  fields of the same shape
   * @throws IllegalArgumentException if the provided list of shapes is null if the canvas width or
   *                                  height are less less than 1 if the provided x and y values are
   *                                  larger than the provided width and height
   */
  public ShapeAnimation(List<Shape> shapes, int canvasX, int canvasY, int canvasWidth,
      int canvasHeight) throws IllegalArgumentException {
    if (shapes == null) {
      throw new IllegalArgumentException("List of shapes cannot be null");
    }
    if (canvasWidth < 1 || canvasHeight < 1) {
      throw new IllegalArgumentException("Canvas width and height must be positive");
    }
    if (canvasX > canvasWidth || canvasY > canvasHeight) {
      throw new IllegalArgumentException("Minimum x and y values must be less than "
          + "the width and height of the canvas");
    }

    this.canvasX = canvasX;
    this.canvasY = canvasY;
    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;

    this.shapes = shapes;
    checkConstraints();
  }

  @Override
  public ArrayList<Shape> getShapes() {
    return new ArrayList<>(this.shapes);
  }

  @Override
  public void addShape(Shape s) {
    if (s == null) {
      throw new IllegalArgumentException("Shape must not be null");
    }
    this.shapes.add(s);
  }

  @Override
  public void removeShape(Shape s) {
    if (s == null) {
      throw new IllegalArgumentException("Shape must not be null");
    }
    if (!this.shapes.contains(s)) {
      throw new IllegalArgumentException("The given shape is not in this animation");
    }
    this.shapes.remove(s);
  }

  @Override
  public int getCanvasX() {
    return this.canvasX;
  }

  @Override
  public int getCanvasY() {
    return this.canvasY;
  }

  @Override
  public int getCanvasWidth() {
    return this.canvasWidth;
  }

  @Override
  public int getCanvasHeight() {
    return this.canvasHeight;
  }

  @Override
  public List<Motion> getMotionsForShape(Shape s) throws IllegalArgumentException {
    if (!this.shapes.contains(s) || s == null) {
      throw new IllegalArgumentException("Given shape must exist in this animation.");
    }
    return s.getMotions();
  }

  @Override
  public void addMotion(Motion m, Shape s) {
    if (m == null || s == null) {
      throw new IllegalArgumentException("Motion and shape must not be null");
    }

    if (!this.shapes.contains(s)) {
      throw new IllegalArgumentException("The provided shape does not exist in this animation");
    }

    s.addMotion(m);
    this.checkConstraints();
  }

  @Override
  public void removeMotion(Motion m, Shape s) {
    if (m == null || s == null) {
      throw new IllegalArgumentException("Motion and shape must not be null");
    }
    if (this.shapes.contains(s) && s.getMotions().contains(m)) {
      s.removeMotion(m);
      this.checkConstraints();
    } else {
      throw new IllegalArgumentException("The given shape does not exist, or does not have the"
          + " given motion in its list of motions");
    }
  }


  /**
   * Makes sure that the list of motions for a particular shape are valid. - Motions in an animation
   * can not overlap or conflict with each other. This means that there cannot be two motions
   * changing color, size, or position on the same shape at the same time. - A motion must also pick
   * up with the same state that the motion chronologically before it ended. For example, if one
   * motion ends leaving Shape A as red, the next movement that occurs on Shape A must start with it
   * being red. - Motions on a shape do not necessarily have to come one after another. For example,
   * if a motion on Oval B ends at tick 20, it is ok for the next motion that occurs on Oval B to
   * take place at tick 30, as long as it starts with the same shape state that the first motion
   * ended with.
   *
   * @throws IllegalArgumentException if a motion does not start in the state that the motion that
   *                                  began before it left off. if a motion overlaps timewise with
   *                                  another motion and attempts to modify the same fields of the
   *                                  same shape.
   */
  public void checkConstraints() {
    List<Motion> motionsForShape;
    Motion m;
    Motion next;

    // Look at every shape
    for (Shape s : this.shapes) {
      // Retrieve a chronologically sorted list of motions for this shape
      motionsForShape = s.getMotions();

      // Make sure there is at least one motion for this shape
      if (motionsForShape.size() > 0) {

        // Go through the list of motions for this shape
        for (int i = 0; i < motionsForShape.size(); i++) {
          m = motionsForShape.get(i);
          // To see if we are at the last shape
          if (i + 1 < motionsForShape.size()) {
            next = motionsForShape.get(i + 1);

            // Make sure any overlapping motions are valid
            checkForBadOverlaps(m, next);
          }
        }
      }
    }
  }


  /**
   * Checks to make sure that the ending states and starting states of sequential movements are
   * consistent with each other.
   *
   * @param m    the current motion.
   * @param next the motion that comes next.
   * @throws IllegalArgumentException if a motion does not start in the state that the motion that
   *                                  began before it left off. if a motion overlaps timewise with
   *                                  another motion and attempts to modify the same field of the
   *                                  same shape
   */
  private void checkForBadOverlaps(Motion m, Motion next) {
    // Making sure that the motion that the next motion starts where this one left off
    if (m.getEndingTick() <= next.getStartingTick() && !(m
        .isConsistent(next))) {
      throw new IllegalArgumentException(
          "One motion must start in the same state that the one before it left off");

      // Checking if the two motions occur within the same time frame and try to modify the
      // same field.
    } else if (m.overlaps(next)) {
      throw new IllegalArgumentException("Motions cannot overlap");
    }
  }

  /**
   * An implementation of the {@link AnimationBuilder} interface, used for creating animations from
   * files.
   */
  public static class AnimationBuilderImpl implements AnimationBuilder {

    private List<Shape> shapes;
    private int canvasX;
    private int canvasY;
    private int canvasWidth;
    private int canvasHeight;
    private Map<String, Function<String, Shape>> shapeMap;

    /**
     * Create an animation builder with an empty list of shapes. ShapeMap provides a way to create
     * new shapes. X and Y default to 0, width and height defaults to 1.
     */
    public AnimationBuilderImpl() {
      this.shapeMap = new HashMap<>();
      this.shapes = new ArrayList<>();
      this.canvasX = 0;
      this.canvasY = 0;
      this.canvasWidth = 1;
      this.canvasHeight = 1;

      this.shapeMap.put("rectangle", (name) -> new Rectangle(name, new ArrayList<>()));
      this.shapeMap.put("ellipse", (name) -> new Oval(name, new ArrayList<>()));
    }

    @Override
    public Object build() {
      return new ShapeAnimation(shapes, canvasX, canvasY, canvasWidth, canvasHeight);
    }

    @Override
    public AnimationBuilder setBounds(int x, int y, int width, int height) {
      this.canvasX = x;
      this.canvasY = y;
      this.canvasWidth = width;
      this.canvasHeight = height;

      return this;
    }


    @Override
    public AnimationBuilder declareShape(String name, String type) {
      Shape s = this.shapeMap.get(type).apply(name);
      this.shapes.add(s);

      return this;
    }

    @Override
    public AnimationBuilder addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1,
        int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      for (Shape s : this.shapes) {
        if (s.getName().equals(name)) {
          s.addMotion(new Motion(t1, x1, y1, r1, g1, b1, h1, w1, t2, x2, y2, r2, g2, b2, h2, w2));
        }
      }

      return this;
    }

    @Override
    public AnimationBuilder addKeyframe(String name, int t, int x, int y, int w, int h, int r,
        int g,
        int b) {
      return this;
    }
  }

}