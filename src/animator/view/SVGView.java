package cs3500.animator.view;

import java.io.IOException;

/**
 * Class to represent SVG Views which are views that are able generate an XML-based SVG file with a
 * given name and to a given directory to be opened in a browser for viewing an IModel
 * animation.
 */
public class SVGView extends AbstractView {


  @Override
  public void generateSVG(Appendable a, String svgText) throws IOException {
    try {
      a.append(svgText);

    }
    catch (IOException e) {
      throw new IOException("Unable to output SVG view to appendable");
    }
  }

}
