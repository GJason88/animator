package cs3500.animator.model;

import java.util.List;

/**
 * Representing an oval with a name and a list of motions that occur on it.
 */
public class Oval extends AbstractShape {

  /**
   * Constructs an Oval at the given name and list of motions. Uses the {@link AbstractShape}
   * constructor.
   *
   * @param name    the name of the oval
   * @param motions the list of motions for this shape
   * @throws IllegalArgumentException if the given String name or list of motions is null.
   */
  public Oval(String name, List<Motion> motions) {
    super(name, motions);
  }


  /**
   * Creates a line of SVG code to define an ellipse using this shape's first motion as a starting
   * point. The ellipse begins as 100% opaque so that it is not visible until it has a motion
   * occurring on it.
   *
   * @return a string containing code to define this oval in terms of an SVG ellipse.
   */
  @Override
  public String makeSVGHeader(int offsetX, int offsetY) {
    StringBuilder result = new StringBuilder();
    if (this.getMotions().size() == 0) {
      result.append(String.format(
          "<ellipse id=\"%s\" cx=\"0\" cy=\"0\" rx=\"0\" ry=\"0\" "
              + "fill=\"rgb(0,0,0)\" visibility=\"visible\" opacity=\"0\">",
          this.getName()));
      return result.toString();
    }
    Motion firstMotion = this.getMotions().get(0);

    result.append(String.format(
        "<ellipse id=\"%s\" cx=\"%s\" cy=\"%s\" rx=\"%s\" ry=\"%s\" "
            + "fill=\"rgb(%s,%s,%s)\" visibility=\"visible\" opacity=\"0\">",
        this.getName(), firstMotion.getStartX() - offsetX + firstMotion.getStartWidth() / 2,
        firstMotion.getStartY() - offsetY + firstMotion.getStartHeight() / 2,
        firstMotion.getStartWidth() / 2, firstMotion.getStartHeight() / 2,
        firstMotion.getStartR(), firstMotion.getStartG(), firstMotion.getEndB()));

    return result.toString();
  }

  /**
   * Generates a string containing the SVG code that defines all of this shape's motions. Uses the
   * map from animateMap to know which strings to append to the result. Uses fill, cx, cy, rx, and
   * ry to animate the ellipse.
   *
   * @param tempo the tempo of the animation.
   * @return a string that has SVG code to create animations for all of this shape's motions.
   */
  @Override
  public String makeSVGMotions(int tempo, int offsetX, int offsetY) {
    StringBuilder result = new StringBuilder();
    boolean visible = false;
    for (Motion m : this.getMotions()) {
      if (!visible) {
        result.append(animateMap(tempo, offsetX, offsetY).get("show").apply("opacity", m));
        visible = true;
      }
      if (m.getStartX() != m.getEndX()) {
        result.append(animateMap(tempo, offsetX, offsetY).get("ellipseX").apply("cx", m));
      }
      if (m.getStartY() != m.getEndY()) {
        result.append(animateMap(tempo, offsetX, offsetY).get("ellipseY").apply("cy", m));
      }
      if (m.getStartWidth() != m.getEndWidth()) {
        result.append(animateMap(tempo, offsetX, offsetY).get("rWidth").apply("rx", m));
      }
      if (m.getStartHeight() != m.getEndHeight()) {
        result.append(animateMap(tempo, offsetX, offsetY).get("rHeight").apply("ry", m));
      }
      if (m.getStartR() != m.getEndR() || m.getStartG() != m.getEndG() || m.getStartB() != m
          .getEndB()) {
        result.append(animateMap(tempo, offsetX, offsetY).get("color").apply("fill", m));
      }


    }
    result.append("</ellipse>");
    return result.toString();
  }
}
