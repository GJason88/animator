import cs3500.animator.controller.IController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Shape;
import cs3500.animator.view.IView;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Mock Controller class for testing inputs given to the view at specific ticks without the
 * continuous timer running. This makes sure the interpolated values of the sent Shapes are correct
 * and the canvas offsets and size sent to the view are correct.
 */
public class MockControllerForVisualInputsTest implements IController {

  private final IModel model;
  private final IView view;
  public int tick;
  private final List<Shape> shapes;

  /**
   * Constructs a controller with the given view, model, and tick for testing purposes.
   *
   * @param view the given view for this controller
   * @param model the given model for this controller
   * @param tick the given tick to be at for testing
   */
  public MockControllerForVisualInputsTest(IView view, IModel model, int tick) {
    this.model = model;
    this.view = view;
    this.tick = tick;
    this.shapes = this.model.getShapes();

  }

  @Override
  public void createSVG(String location) {
    // Does nothing for testing
  }

  @Override
  public void createTextView(String location) {
    // Does nothing for testing
  }

  @Override
  public void startAnimation() {
    this.view.setCanvasSize(
        this.model.getCanvasX(), this.model.getCanvasY(),
        this.model.getCanvasWidth(), this.model.getCanvasHeight());
    findShapesToDraw();
  }

  @Override
  public void setTempo(int t) {
    // Does nothing for testing
  }

  @Override
  public int getTempo() {
    return 0;
    // Does nothing for testing
  }

  /**
   * Method to get the shapes from the model and determine which motions within the shapes are
   * occurring at the current tick and sends them along with their color to the view to be drawn.
   */
  private void findShapesToDraw() {
    List<java.awt.Shape> currentShapes = new ArrayList<>();
    List<Color> currentColors = new ArrayList<>();
    List<Integer> interpolatedFields;
    String shapeType;
    Map<String, Function<List<Integer>, java.awt.Shape>> shapeTypes = new HashMap<>();

    // Creates a java.awt graphical shape based on what class we are looking at (Rectangle or Oval)
    shapeTypes.put("Rectangle",
        (mp) -> new Rectangle(mp.get(0), mp.get(1), mp.get(2), mp.get(3)));
    shapeTypes.put("Oval",
        (mp) -> new Ellipse2D.Double(mp.get(0), mp.get(1), mp.get(2), mp.get(3)) {
        });

    for (cs3500.animator.model.Shape s : this.shapes) {
      shapeType = s.getClass().getSimpleName();
      for (Motion m : s.getMotions()) {
        if (this.tick >= m.getStartingTick() && this.tick <= m.getEndingTick()) {
          interpolatedFields =
              new ArrayList<>(Arrays.asList(
                  this.interpolate(
                      m.getStartingTick(), m.getEndingTick(), m.getStartX(), m.getEndX())
                      + this.model.getCanvasX(),
                  this.interpolate(
                      m.getStartingTick(), m.getEndingTick(), m.getStartY(), m.getEndY())
                      + this.model.getCanvasY(),
                  this.interpolate(
                      m.getStartingTick(), m.getEndingTick(), m.getStartWidth(), m.getEndWidth()),
                  this.interpolate(
                      m.getStartingTick(), m.getEndingTick(), m.getStartHeight(), m.getEndHeight()))
              );

          // adds the shapes to be drawn at the current tick to a list that is to be passed to the
          // view.
          currentShapes.add(shapeTypes.get(shapeType).apply(interpolatedFields));
          currentColors.add(
              new Color(
                  this.interpolate(
                      m.getStartingTick(), m.getEndingTick(), m.getStartR(), m.getEndR()),
                  this.interpolate(
                      m.getStartingTick(), m.getEndingTick(), m.getStartG(), m.getEndG()),
                  this.interpolate(
                      m.getStartingTick(), m.getEndingTick(), m.getStartB(), m.getEndB())));
        }
      }
    }
    this.view.addShapes(currentShapes, currentColors);
  }

  /**
   * Interpolates the "between" values of a given field based on the current tick and
   * the starting and ending ticks of the motion of a Shape.
   * @param startTick the starting tick of the motion
   * @param endTick the ending tick of the motion
   * @param startValue the starting value of the field in the motion
   * @param endValue the ending value of the field in the motion
   * @return the interpolated value of the field in the motion.
   */
  private int interpolate(int startTick, int endTick, int startValue, int endValue) {
    return (int) Math.round(startValue * ((double) (endTick - this.tick) / (endTick - startTick))
        + endValue * ((double) (this.tick - startTick) / (endTick - startTick)));
  }
}
