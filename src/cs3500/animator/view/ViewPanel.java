package cs3500.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import java.awt.Shape;

/**
 * A class to represent a panel/JPanel that will be part of the JFrame that is a VisualView for
 * drawing the current shapes and with their respective colors for viewing animations visually.
 */
public class ViewPanel extends JPanel {
  private List<Shape> shapes;
  private List<Color> shapeColors;

  /**
   * Constructor that constructs a ViewPanel with the list of current shapes and colors
   * for those shapes being empty, as well as calling the super constructor for JPanel.
   */
  public ViewPanel() {
    super();
    this.shapes = new ArrayList<>();
    this.shapeColors = new ArrayList<>();
  }

  /**
   * Adds the current list of Shape and list of Color for those Shape for the current Frame to this
   * ViewPanel to be drawn/painted.
   * @param currentShapes the current shapes to be drawn
   * @param currentColors the respective colors of the current shapes to be drawn
   */
  public void addShapes(List<Shape> currentShapes, List<Color> currentColors) {
    this.shapes = currentShapes;
    this.shapeColors = currentColors;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    for (int i = 0; i < this.shapes.size(); i++) {
      g2d.setPaint(this.shapeColors.get(i));
      g2d.fill(this.shapes.get(i));
    }

  }

}