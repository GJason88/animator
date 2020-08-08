package cs3500.animator.controller;

import cs3500.animator.model.IModel;
import cs3500.animator.view.IView;
import java.io.IOException;

/**
 * A controller that deals with sending data to an SVG view. Takes data from a model and converts it
 * to a formatted string representing an SVG animation to be sent to the SVG view.
 */
public class SVGController extends AbstractController {

  /**
   * Constructs an SVG controller using the provided model and view. Uses the {@link
   * AbstractController} constructor.
   *
   * @param view  the view to send data to
   * @param model the model to get data from
   */
  public SVGController(IView view, IModel model) {
    super(view, model);
  }

  /**
   * Gets the formatted SVG animation text for each shape in an animation as well as each shape's
   * motions. Also defines the canvas size. Builds a string with this data which is sent to a SVG
   * view to either output to System.out, or an SVG file.
   *
   * @param a the appendable for the SVG description to be written to.
   * @throws IOException if unable to write to the appendable for whatever reason
   */
  @Override
  public void createSVG(Appendable a) throws IOException {
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

    this.view.generateSVG(a, svg.toString());
  }

  @Override
  public void setTempo(int t) throws IllegalArgumentException {
    if (t < 1) {
      throw new IllegalArgumentException("Tempo must be positive.");
    }
    this.tempo = t;
  }
}
