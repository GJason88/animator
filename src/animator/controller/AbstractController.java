package cs3500.animator.controller;

import cs3500.animator.model.IModel;
import cs3500.animator.model.Shape;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualView;
import java.io.IOException;
import java.util.List;

/**
 * An abstract class that suppresses methods for all of the controllers. This means that controllers
 * only need to override the methods they support. This class also provides a factory for creating
 * the right controller depending on what view is provided.
 */
public class AbstractController implements IController {


  protected IModel model;
  protected final IView view;
  protected List<Shape> shapes;
  protected int tempo; // in ticks per second


  /**
   * Constructs a controller using the provided view and model. The list of shapes starts as empty,
   * and the tempo defaults to 1 tick per second.
   *
   * @param view  the view display data on.
   * @param model the model to get animation data from.
   */
  public AbstractController(IView view, IModel model) {
    this.model = model;
    this.view = view;
    this.shapes = this.model.getShapes();
    this.tempo = 1;
  }


  @Override
  public void createSVG(Appendable a) throws IOException {
    throw new UnsupportedOperationException("Not supported for text, visual, or editor views");
  }

  @Override
  public void createTextView(Appendable a) throws IOException {
    throw new UnsupportedOperationException("Not supported for SVG, visual, or editor views");
  }

  @Override
  public void startAnimation() {
    throw new UnsupportedOperationException("Not supported for SVG, or text views");
  }

  @Override
  public void setTempo(int t) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Not supported for text views");

  }

  @Override
  public int getTempo() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Not supported for text or SVG views");
  }


  @Override
  public void addFeaturesToView() {
    throw new UnsupportedOperationException("Not supported for SVG or text views");

  }

  @Override
  public void initializeAnimation() {
    throw new UnsupportedOperationException("Not supported for SVG or text views");
  }


  /**
   * Static method for creating a controller factory.
   *
   * @return a new ControllerFactory
   */
  public static ControllerFactory createControllerFactory() {
    return new ControllerFactory();
  }

  /**
   * A factory class for creating a controller using a provided model and view type. Can produce
   * text, SVG, or visual controllers.
   */
  public static class ControllerFactory {

    /**
     * Creates a controller using a provided model and a string to know what type of view to use.
     * Visual and edit views both use a visual controller, as they display shapes in a similar way.
     *
     * @param model    the model to supply data for the produced controller.
     * @param viewType the type of view that this controller will send data to. Should be either
     *                 text, svg, visual, or edit.
     * @return a new controller using the provided model, that is for sending data to the specified
     *         viewType.
     */
    public IController create(IModel model, String viewType) {

      switch (viewType) {
        case "text":
          return new TextController(new TextView(), model);
        case "svg":
          return new SVGController(new SVGView(), model);
        case "visual":
          return new VisualController(new VisualView(), model);
        case "edit":
          return new VisualController(new EditorView(), model);
        default:
          return null;
      }
    }
  }
}
