package cs3500.animator;

import cs3500.animator.controller.AbstractController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.IModel;
import cs3500.animator.model.ShapeAnimation;
import cs3500.animator.model.ShapeAnimation.AnimationBuilderImpl;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Main method for running Excellence animations. User provides the type of view they want, output
 * file location, and speed in ticks per second. Default speed is 1 tick per second.
 */
public class Excellence {

  /**
   * Run the animation using the given commands.
   *
   * @param args the array of string commands
   * @throws IOException if unable to read or write to the given file location.
   */
  public static void main(String[] args) throws IOException {

    int tempo = 15;
    String inputFile = "";
    Appendable outputFile = System.out;
    String viewType = "";
    int i = 0;
    while (i < args.length) {
      String next = args[i];
      switch (next) {
        case "-in":
          if (i + 1 < args.length) {
            inputFile = args[i + 1];
          }
          i = i + 2;
          break;

        case "-view":
          if (i + 1 < args.length) {
            viewType = args[i + 1];
          }
          i = i + 2;
          break;

        case "-out":
          if (i + 1 < args.length) {
            outputFile = new FileWriter(args[i + 1]);
          }
          i = i + 2;
          break;

        case "-speed":
          if (i + 1 < args.length) {
            tempo = Integer.parseInt(args[i + 1]);
          }
          i = i + 2;
          break;
        default:
          break;
      }
    }
    if (inputFile.equals("")) {
      JFrame frame = new JFrame();
      JOptionPane
          .showMessageDialog(frame, "Input file required", "Error",
              JOptionPane.ERROR_MESSAGE);
    }

    AnimationBuilder builder = new AnimationBuilderImpl();
    FileReader fr = new FileReader(new File(inputFile));
    ShapeAnimation fileAnimation =
        (ShapeAnimation) AnimationReader.parseFile(fr, builder);

    IModel model = new ShapeAnimation(fileAnimation.getShapes(),
        fileAnimation.getCanvasX(), fileAnimation.getCanvasY(),
        fileAnimation.getCanvasWidth(), fileAnimation.getCanvasHeight());

    IController controller = AbstractController.createControllerFactory().create(model, viewType);
    switch (viewType) {
      case "text":
        controller.createTextView(outputFile);

        break;
      case "svg":
        controller.setTempo(tempo);
        controller.createSVG(outputFile);

        break;
      case "visual":
        controller.setTempo(tempo);
        controller.initializeAnimation();
        controller.startAnimation();
        break;
      case "edit":
        controller.setTempo(tempo);

        controller.addFeaturesToView();
        controller.initializeAnimation();

        break;
      default:
        JFrame frame = new JFrame();
        JOptionPane
            .showMessageDialog(frame, "View type required", "Error",
                JOptionPane.ERROR_MESSAGE);
    }
  }

}


