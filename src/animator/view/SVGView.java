package cs3500.animator.view;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to represent SVG Views which are views that are able generate an XML-based SVG file with a
 * given name and to a given directory to be opened in a browser for viewing an IModel
 * animation.
 */
public class SVGView extends AbstractView {


  @Override
  public void generateSVG(String location, String svgText) {
    try {
      FileWriter output = new FileWriter(location);
      output.write(svgText);
      output.close();
    } catch (IOException e) {
      System.out.println(svgText);
    }
  }

}
