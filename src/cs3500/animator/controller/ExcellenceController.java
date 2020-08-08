package cs3500.animator.controller;

import cs3500.animator.model.IModel;
import cs3500.animator.model.Motion;
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
 * A class representing an implementation of IController that gets information necessary for viewing
 * the animation textually, visually, or through an XML-based SVG file from the model and sends it
 * to the view for the displaying of the animation.
 */
public class ExcellenceController implements IController {

  private final IModel model;
  private final IView view;
  public int tick;
  private int tempo; // in ticks per second
  private final Timer timer;
  private final int lastTick;
  private final List<cs3500.animator.model.Shape> shapes;

  /**
   * Constructor to construct an ExcellenceController with the given view and model, tick defaulted
   * to 0, tempo defaulted to 1 tick per second, shapes being the given model's shapes, lastTick
   * being the given model's final motion's ending tick, and timer being a new Timer as a means for
   * running through the ticks at the tempo rate.
   *
   * @param view  the given view to be used for this controller
   * @param model the given model to be used for this controller
   */
  public ExcellenceController(IView view, IModel model) {
    this.model = model;
    this.view = view;
    this.tick = 0;
    this.tempo = 1;
    this.shapes = this.model.getShapes();
    this.lastTick = this.findLastTick();
    this.timer = new Timer();

  }

  @Override
  public void createSVG(String location) {
    StringBuilder svg = new StringBuilder();

    svg.append("<svg width=\"")
        .append(this.model.getCanvasWidth())
        .append("\" height=\"")
        .append(this.model.getCanvasHeight())
        .append("\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n");

    // For each shape, create the shape to be animated, and then make the SVG code for each of its
    // animations
    for (cs3500.animator.model.Shape s : this.shapes) {
      svg.append(s.makeSVGHeader(this.model.getCanvasX(), this.model.getCanvasY())).append("\n")
          .append(s.makeSVGMotions(this.tempo, this.model.getCanvasX(), this.model.getCanvasY()))
          .append("\n\n");
    }
    svg.append("</svg>");

    this.view.generateSVG(location, svg.toString());
  }

  @Override
  public void createTextView(String location) {
    StringBuilder eventLog = new StringBuilder();

    eventLog.append("canvas ")
        .append(this.model.getCanvasX()).append(" ")
        .append(this.model.getCanvasY()).append(" ")
        .append(this.model.getCanvasWidth()).append(" ")
        .append(this.model.getCanvasHeight()).append("\n");

    for (cs3500.animator.model.Shape s : this.shapes) {
      // Adds the "header" text for each shape that has animations happening on it
      eventLog.append("shape")
          .append(" ")
          .append(s.getName())
          .append(" ")
          .append(s.getClass().getSimpleName())
          .append("\n");

      // gets a list of the motions for a shape
      List<Motion> motionsForShape = s.getMotions();

      // Adds the description of each motion to the eventlog
      for (Motion m : motionsForShape) {
        eventLog.append("motion ").append(s.getName()).append(" ");
        eventLog.append(m.generateDescription());
        eventLog.append("\n");
      }
      eventLog.append("\n");
    }
    this.view.printTextView(eventLog.toString().replaceAll("\n$", ""), location);
  }

  @Override
  public void startAnimation() {
    this.view.setCanvasSize(
        this.model.getCanvasX(), this.model.getCanvasY(),
        this.model.getCanvasWidth(), this.model.getCanvasHeight());
    this.view.makeVisible();
    this.timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (tick > lastTick) {
          return;
        }
        findShapesToDraw();
        view.refresh();
        tick++;
      }
    }, 0, (long) 1000.0 / this.tempo);
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
   * Finds the ending tick of the last motion that occurs in this animation.
   *
   * @return the ending tick of the last motion that happens in this IModel Animation.
   */
  private int findLastTick() {
    int endingTick;
    int lastTick = 0;

    for (cs3500.animator.model.Shape s : this.shapes) {
      for (Motion m : s.getMotions()) {
        endingTick = m.getEndingTick();
        if (endingTick > lastTick) {
          lastTick = endingTick;
        }
      }
    }
    return lastTick;
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

    for (cs3500.animator.model.Shape s : this.shapes) {
      shapeType = s.getClass().getSimpleName();
      for (Motion m : s.getMotions()) {
        if (this.tick >= m.getStartingTick() && this.tick <= m.getEndingTick()) {
          interpolatedFields =
              new ArrayList<>(Arrays.asList(
                  this.interpolate(
                      m.getStartingTick(), m.getEndingTick(), m.getStartX(), m.getEndX())
                      - this.model.getCanvasX(),
                  this.interpolate(
                      m.getStartingTick(), m.getEndingTick(), m.getStartY(), m.getEndY())
                      - this.model.getCanvasY(),
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

}
