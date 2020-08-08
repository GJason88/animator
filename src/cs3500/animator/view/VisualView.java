package cs3500.animator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * A class to represent Visual Views, which are able to generate a visual animation using the Swing
 * library via JFrames, JPanels, and JScrollPanes to allow for a visual portrayal of
 * IModel Animations in a pop-up window.
 */
public class VisualView extends AbstractView {
  private final ViewPanel panel;

  /**
   * Constructor that constructs a new VisualView with a default window size of 800x600 and window
   * name of "Animation Visual". Consists of a JScrollPane that consists of a ViewPanel to allow
   * for scrollbars and the portrayed visual animation within the ViewPanel.
   */
  public VisualView() {
    this.setSize(800, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Animation Visual");
    this.panel = new ViewPanel();

    JScrollPane scrollPane = new JScrollPane(this.panel);

    this.add(scrollPane);

  }

  @Override
  public void addShapes(List<Shape> currentShapes, List<Color> currentColors) {
    this.panel.addShapes(currentShapes, currentColors);
  }

  @Override
  public void setCanvasSize(int x, int y, int width, int height) {
    this.panel.setPreferredSize(new Dimension(width, height));
    this.panel.setLocation(400, 400);

  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

}
