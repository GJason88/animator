package cs3500.animator.controller;

import cs3500.animator.model.IModel;
import cs3500.animator.model.IMotion;
import cs3500.animator.view.IView;
import java.io.IOException;
import java.util.List;

/**
 * A controller that deals with sending data to a text view. Takes data from a model and converts it
 * to a string to be sent to the SVG view.
 */
public class TextController extends AbstractController {

  /**
   * Constructs a TextController using the provided view and model. Uses the {@link
   * AbstractController} constructor.
   *
   * @param view  the view to display data on
   * @param model the model to get data from
   */
  public TextController(IView view, IModel model) {
    super(view, model);
  }

  /**
   * Gets the text description for each shape in an animation as well as each shape's motions. Also
   * gets data on the canvas size and offset. Builds a string with this data which is sent to the
   * text view to output to either System.out or a text file.
   *
   * @param a the appendable for the text description to be written to.
   * @throws IOException if unable to write to the appendable for whatever reason.
   */
  @Override
  public void createTextView(Appendable a) throws IOException {
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
      List<IMotion> motionsForShape = s.getMotions();

      // Adds the description of each motion to the eventlog
      for (IMotion m : motionsForShape) {
        eventLog.append("motion ").append(s.getName()).append(" ");
        eventLog.append(m.generateDescription());
        eventLog.append("\n");
      }
      eventLog.append("\n");
    }

    this.view.printTextView(eventLog.toString().replaceAll("\n$", ""), a);
  }
}
