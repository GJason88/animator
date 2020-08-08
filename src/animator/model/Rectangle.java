package cs3500.animator.model;

import java.util.List;

/**
 * Representing a rectangle shape with a name and a list of motions that occur on it.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructs a Rectangle with the given name and list of motions. Uses the {@link AbstractShape}
   * constructor.
   *
   * @param name    the name of the rectangle
   * @param motions the list of motions for this shape
   * @throws IllegalArgumentException if the given String name or list of motions is null.
   */
  public Rectangle(String name, List<IMotion> motions) {
    super(name, motions);
  }

  /**
   * Creates a line of SVG code to define a rect using this shape's first motion as a starting
   * point. The rect begins as 100% opaque so that it is not visible until it has a motion occurring
   * on it.
   *
   * @return a string containing code to define this rectangle in terms of an SVG rect.
   */
  @Override
  public String makeSVGHeader(int offsetX, int offsetY) {
    StringBuilder result = new StringBuilder();
    if (this.getMotions().size() == 0) {
      result.append(String.format(
          "<rect id=\"%s\" x=\"0\" y=\"0\" width=\"0\" height=\"0\" "
              + "fill=\"rgb(0,0,0)\" visibility=\"visible\" opacity=\"0\">",
          this.getName()));
      return result.toString();
    }
    IMotion firstMotion = this.getMotions().get(0);

    result.append(String.format(
        "<rect id=\"%s\" x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\" "
            + "fill=\"rgb(%s,%s,%s)\" visibility=\"visible\" opacity=\"0\" >",
        this.getName(), firstMotion.getStartingKeyframe().getX() - offsetX,
        firstMotion.getStartingKeyframe().getY() - offsetY,
        firstMotion.getStartingKeyframe().getWidth(), firstMotion.getStartingKeyframe().getHeight(),
        firstMotion.getStartingKeyframe().getR(), firstMotion.getStartingKeyframe().getG(),
        firstMotion.getEndingKeyframe().getB()));

    return result.toString();
  }

  /**
   * Generates a string containing the SVG code that defines all of this shape's motions. Uses the
   * map from animateMap to know which strings to append to the result. Uses fill, x, y, width, and
   * height to animate the rect.
   *
   * @param tempo the tempo of the animation.
   * @return a string that has SVG code to create animations for all of this shape's motions.
   */
  @Override
  public String makeSVGMotions(int tempo, int offsetX, int offsetY) {
    StringBuilder result = new StringBuilder();
    boolean visible = false;
    for (IMotion m : this.getMotions()) {
      if (!visible) {
        result.append(animateMap(tempo, offsetX, offsetY).get("show").apply("opacity", m));
        visible = true;
      }
      if (m.getStartingKeyframe().getX() != m.getEndingKeyframe().getX()) {
        result.append(animateMap(tempo, offsetX, offsetY).get("x").apply("x", m));
      }
      if (m.getStartingKeyframe().getY() != m.getEndingKeyframe().getY()) {
        result.append(animateMap(tempo, offsetX, offsetY).get("y").apply("y", m));
      }
      if (m.getStartingKeyframe().getWidth() != m.getEndingKeyframe().getWidth()) {
        result.append(animateMap(tempo, offsetX, offsetY).get("width").apply("width", m));
      }
      if (m.getStartingKeyframe().getHeight() != m.getEndingKeyframe().getHeight()) {
        result.append(animateMap(tempo, offsetX, offsetY).get("height").apply("height", m));
      }
      if (m.getStartingKeyframe().getR() != m.getEndingKeyframe().getR()
          || m.getStartingKeyframe().getG() != m.getEndingKeyframe().getG()
          || m.getStartingKeyframe().getB() != m
          .getEndingKeyframe().getB()) {
        result.append(animateMap(tempo, offsetX, offsetY).get("color").apply("fill", m));
      }
    }
    result.append("</rect>");
    return result.toString();
  }

}
