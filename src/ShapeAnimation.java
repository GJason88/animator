import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * Representing an animation on shapes. An animation is simply a list of motions that say the state
 * and time a shape starts in, and the state and time a shape ends in.
 *
 * <p>Invariants - Motions in an animation can not overlap or conflict with each other. This means
 *   that there cannot be two motions changing color, size, or position on the same shape at
 *   the same time.
 * - This is enforced by the {@code overlaps} methods which checks that if two motions occur within
 *   overlapping time periods, that they do not both attempt to modify the same field.
 * - A motion must also pick up with the same state that the motion chronologically before it ended.
 * - For example, if one motion ends leaving Shape A as red, the next movement that occurs on Shape
 *   A must start with it being red.
 * - This is enforced when we check that if the motion that occurs
 *   after a given motion starts in the same state the previous motion left the shape in using the
 *   getSameState method. The {@code checkForBadOverlaps} method also helps here.</p>
 */
public class ShapeAnimation implements Excellence {

  private ArrayList<Motion> motions;


  /**
   * Creates an animation using a list of motions. Ensures that the given list of motions is valid.
   *
   * @param motions the list of motions to animate
   * @throws IllegalArgumentException if the provided list of motions is empty.
   *                                  if a motion does not start in the state that the motion that
   *                                  began before it left off.
   *                                  if a motion ends the same time as another and does not leave
   *                                  the same shape in the same state
   *                                  if a motion ends at a different time than another and does
   *                                  leave the same shape in the same state.
   *                                  if two motion overlap timewise with another motion and
   *                                  attempts to modify the same fields of the same shape
   * @throws NullPointerException     if the provided list of motions is NULL
   *
   */
  public ShapeAnimation(ArrayList<Motion> motions) {
    Objects.requireNonNull(motions);
    if (motions.size() <= 0) {
      throw new IllegalArgumentException("Must give at least one motion to animate");
    }

    this.motions = motions;
    checkConstraints();
  }


  @Override
  public String getTextDescription() {
    StringBuilder eventLog = new StringBuilder();

    ArrayList<Shape> shapesBeingAnimated = getUniqueShapes();
    for (Shape s : shapesBeingAnimated) {
      // Adds the "header" test for each shape that has animations happening on it
      eventLog.append("shape")
          .append(" ")
          .append(s.getName())
          .append(" ")
          .append(s.getClass().getSimpleName())
          .append("\n");

      // gets a list of the motions for a shape
      ArrayList<Motion> motionsForShape = findMotionsForShape(s);

      // Adds the description of each motion to the eventlog
      for (Motion m : motionsForShape) {
        eventLog.append(m.generateDescription());
        eventLog.append("\n");
      }
      eventLog.append("\n");
    }
    return eventLog.toString().replaceAll("\n$", "");
  }

  /**
   * Gets a list of all the unique shapes that have at least one motion happening to them.
   *
   * @return an arrayList of {@link Shape} containing all of the unique shapes that are having a
   *         motion applied to them.
   */
  private ArrayList<Shape> getUniqueShapes() {

    ArrayList<Shape> uniqueShapes = new ArrayList<>();
    boolean contains;
    // Look at all of the motions in the animation
    for (Motion m : motions) {
      contains = false;
      // Look at the current list of uniqueShapes to see if it contains the name of the
      // starting shape of a motion
      for (Shape s : uniqueShapes) {
        if (s.getName().equals(m.getStartingShape().getName())) {
          contains = true;
        }
      }
      // Add a the shape to the list of unique shapes if it's not already in there
      if (!contains) {
        uniqueShapes.add(m.getStartingShape());
      }
    }

    return uniqueShapes;
  }


  /**
   * Makes sure that the list of motions for a particular shape are valid.
   * - Motions in an animation can not overlap or conflict with each other. This means that there
   * cannot be two motions changing color, size, or position on the same shape at the same time.
   * - A motion must also pick up with the same state that the motion chronologically before
   * it ended. For example, if one motion ends leaving Shape A as red, the next movement that occurs
   * on Shape A must start with it being red.
   * - Motions on a shape do not necessarily have to come one after another. For example,
   * if a motion on Oval B ends at tick 20, it is ok for the next motion that occurs on Oval B to
   * take place at tick 30, as long as it starts with the same shape state that the first motion
   * ended with.
   *
   * @throws IllegalArgumentException if a motion does not start in the state that the motion that
   *                                  began before it left off.
   *                                  if a motion ends at the same time as
   *                                  another and does not leave the same shape in the same state.
   *                                  if a motion ends at a different time than another
   *                                  and does leave the same shape in the same state.
   *                                  if a motion overlaps timewise with
   *                                  another motion and attempts to modify the same fields of the
   *                                  same shape.
   */
  private void checkConstraints() {
    ArrayList<Shape> uniqueShapes = getUniqueShapes();
    ArrayList<Motion> motionsForShape;
    Motion m;
    Motion next;

    // Look at every shape
    for (Shape s : uniqueShapes) {
      // Retrieve a chronologically sorted list of motions for this shape
      motionsForShape = findMotionsForShape(s);

      // Make sure there is at least one motion for this shape
      if (motionsForShape.size() > 1) {

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
   * Finds all of the motions that occur for a given shape, based off of its name, and makes a list
   * of them in chronological order based off of starting tick.
   *
   * @param s the shape to get a list of motions for
   * @return An arraylist of Motions that occur on the given shape in chronological order based off
   *         of the starting tick for each motion.
   * @throws NullPointerException if the provided shape is null
   */
  private ArrayList<Motion> findMotionsForShape(Shape s) {
    Objects.requireNonNull(s);
    ArrayList<Motion> motionsForShape = new ArrayList<Motion>();
    for (Motion m : motions) {
      if (m.getStartingShape().getName().equals(s.getName())) {
        motionsForShape.add(m);
      }
    }
    // Sort the motions for the given shape chronologically
    motionsForShape.sort(Comparator.comparingInt(Motion::getStartingTick));
    return motionsForShape;
  }

  /**
   * Checks to make sure that the ending states and starting states of sequential movements are
   * consistent with each other.
   *
   * @param m    the current motion.
   * @param next the motion that comes next.
   * @throws IllegalArgumentException if a motion does not start in the state that the motion that
   *                                  began before it left off.
   *                                  if a motion ends the same time as.
   *                                  another and does not leave the same shape in the same state
   *                                  if a motion ends at a different time than another and does
   *                                  leave a shape in the same state.
   *                                  if a motion overlaps timewise with
   *                                  another motion and attempts to modify the same field of the
   *                                  same shape
   */
  private void checkForBadOverlaps(Motion m, Motion next) {
    // Making sure that the motion that the next motion starts where this one left off
    if (m.getEndingTick() <= next.getStartingTick() && !(m.getEndingState()
        .isSameState(next.getStartingShape()))) {
      throw new IllegalArgumentException(
          "One motion must start in the same state that the one before it left off");

    }
    // If the next motion starts while the current motion is still going, but after the
    // current motion starts
    else if (next.getStartingTick() < m.getEndingTick() && next.getStartingTick() != m
        .getStartingTick()) {
      // If the current motion has change happening while it occurs
      if (!(m.getStartingShape().isSameState(m.getEndingState()))
          // Then the next motion should not be starting in the same state that m starts at
          && m.getStartingShape().isSameState(next.getStartingShape())) {
        throw new IllegalArgumentException(
            "A motion that starts during a motion that is already "
                + "running must start in a state consistent with where the animation currently is");
      }
    } else if (m.getEndingTick() == next.getEndingTick() && !(m.getEndingState()
        .isSameState(next.getEndingState()))) {
      throw new IllegalArgumentException(
          "If two motions end at the same time, their ending states should"
              + " be the same");
    } else if (m.getEndingTick() != next.getEndingTick() && (m.getEndingState()
        .isSameState(next.getEndingState()))) {
      throw new IllegalArgumentException(
          "If two motions end at different times, their ending states should"
              + " not be the same");
    } else if (m.overlaps(next)) {
      throw new IllegalArgumentException("Motions cannot overlap");
    }
  }

}
