package cs3500.animator.view;

import cs3500.animator.controller.IFeatures;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import javax.swing.SpinnerNumberModel;

/**
 * Class to represent EditorViews, which are able to generate a visual portrayal of the animation
 * with the abilities to change the animation by adding or removing shapes and keyframes, changing
 * the speed of the animation, playing or pausing the animation, etc.
 */
public class EditorView extends VisualView implements ActionListener {

  JPanel mainPanel;
  JScrollPane mainScrollPane;

  JPanel buttonPanel;
  JButton playButton;
  JButton pauseButton;
  JButton resumeButton;
  JButton restartButton;
  JCheckBox loopingBox;
  JSpinner speedChanger;

  JPanel drawAndKeyframePanel;
  ViewPanel animationPanel;
  JPanel keyframePanel;

  JPanel shapeListPanel;
  JScrollPane shapeSelectionList;
  JList shapeList;
  DefaultListModel shapeListContent;
  String selectedShapeName;

  ButtonGroup shapeRadioButtons;
  JRadioButton rectRadio;
  JRadioButton ellipseRadio;
  JButton addShapeButton;
  JButton removeShapeButton;
  JTextField newShapeNameField;

  JList keyframeList;
  JScrollPane keyframeSelectionList;
  DefaultListModel keyframeListContent;
  int selectedKeyframeTick;

  JSpinner keyframeTickSpinner;
  JButton addKeyframeButton;
  JButton removeKeyframeButton;
  JButton editKeyframeButton;
  JSpinner xSpinner;
  JSpinner ySpinner;
  JSpinner widthSpinner;
  JSpinner heightSpinner;
  Color chosenColor;

  /**
   * Constructs an EditorView with a default layout of all panels, buttons, spinners, selection
   * lists, etc that provide for all the functionality of an EditorView is capable of.
   */
  public EditorView() {

    // Setup the pannel
    super();
    this.setTitle("Animation Editor");

    this.chosenColor = Color.BLACK;

    this.mainPanel = new JPanel();
    this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    this.mainScrollPane = new JScrollPane(mainPanel);
    this.add(this.mainScrollPane);

    //---------------------------------------------------------------------
    // Button panel
    this.buttonPanel = new JPanel();
    this.buttonPanel.setMaximumSize(new Dimension(2000, 500));

    //Button to play the animation
    this.playButton = new JButton();
    this.playButton.setText("Play");
    this.buttonPanel.add(playButton);

    // Button to pause the animation
    this.pauseButton = new JButton();
    this.pauseButton.setText("Pause");
    this.buttonPanel.add(pauseButton);
    this.pauseButton.setEnabled(false);

    // Button to resume the animation
    this.resumeButton = new JButton();
    this.resumeButton.setText("Resume");
    this.buttonPanel.add(resumeButton);
    this.resumeButton.setEnabled(false);

    // Button to restart the animation
    this.restartButton = new JButton();
    this.restartButton.setText("Restart");
    this.buttonPanel.add(restartButton);
    this.restartButton.setEnabled(false);

    // To toggle animation looping
    this.loopingBox = new JCheckBox();
    this.loopingBox.setText("Is looping?");
    this.buttonPanel.add(loopingBox);

    this.mainPanel.add(buttonPanel);

    // ----------------------------------------------------------------

    // Panel containing the animation and the keyframe editing menu.

    this.drawAndKeyframePanel = new JPanel();
    this.drawAndKeyframePanel.setLayout(new BoxLayout(this.drawAndKeyframePanel, BoxLayout.X_AXIS));

    this.animationPanel = new ViewPanel();

    this.drawAndKeyframePanel.add(animationPanel);

    // --------------------------------------------------------------------

    // Keyframe editing panel

    this.keyframePanel = new JPanel();

    this.keyframePanel.setLayout(new BoxLayout(this.keyframePanel, BoxLayout.Y_AXIS));
    keyframePanel.setBackground(Color.lightGray);
    this.keyframePanel.setMaximumSize(new Dimension(300, 5000));

    this.shapeListPanel = new JPanel();

    this.shapeListContent = new DefaultListModel();
    this.shapeList = new JList(shapeListContent);
    shapeList.setLayoutOrientation(JList.VERTICAL);
    shapeList.setPrototypeCellValue("_________________________");
    shapeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    shapeList.setVisibleRowCount(5);

    this.shapeSelectionList = new JScrollPane(shapeList);
    this.shapeSelectionList.setMinimumSize(new Dimension(300, 200));
    this.shapeSelectionList.setMaximumSize(new Dimension(300, 400));
    this.shapeListPanel.add(new JLabel("Shapes"));

    this.shapeListPanel.add(shapeSelectionList);
    this.shapeListPanel.setBackground(Color.cyan);
    this.shapeListPanel.setMaximumSize(new Dimension(400, 300));

    this.rectRadio = new JRadioButton("Rectangle");
    this.rectRadio.setBackground(Color.cyan);
    this.ellipseRadio = new JRadioButton("Ellipse");
    this.ellipseRadio.setBackground(Color.cyan);
    this.shapeRadioButtons = new ButtonGroup();
    this.shapeRadioButtons.add(rectRadio);
    this.shapeRadioButtons.add(ellipseRadio);

    JPanel radioPanel = new JPanel();
    radioPanel.setBackground(Color.cyan);
    radioPanel.add(new JLabel("New shape type:"));
    radioPanel.add(rectRadio);
    radioPanel.add(ellipseRadio);

    this.shapeListPanel.add(radioPanel);

    JPanel addRemoveShapesPanel = new JPanel();
    addRemoveShapesPanel.setBackground(Color.cyan);
    addRemoveShapesPanel.setLayout(new BoxLayout(addRemoveShapesPanel, BoxLayout.Y_AXIS));
    addRemoveShapesPanel.add(new JLabel("Add a new shape:"));
    this.newShapeNameField = new JTextField();
    addRemoveShapesPanel.add(this.newShapeNameField);
    this.addShapeButton = new JButton("Add new shape");
    addRemoveShapesPanel.add(addShapeButton);
    this.removeShapeButton = new JButton("Remove selected shape");
    addRemoveShapesPanel.add(removeShapeButton);

    this.shapeListPanel.add(addRemoveShapesPanel);

    this.keyframePanel.add(shapeListPanel);

    this.keyframeListContent = new DefaultListModel();

    this.keyframeList = new JList(keyframeListContent);
    keyframeList.setLayoutOrientation(JList.VERTICAL);
    keyframeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    keyframeList.setVisibleRowCount(5);

    this.keyframeSelectionList = new JScrollPane(keyframeList);
    this.keyframeSelectionList.setMaximumSize(new Dimension(500, 250));
    this.keyframeSelectionList.setRowHeaderView(new JLabel("Keyframes"));
    this.keyframePanel.add(keyframeSelectionList);

    JPanel keyframeTickPanel = new JPanel();
    keyframeTickPanel.setMaximumSize(new Dimension(200, 175));
    keyframeTickPanel.setBackground(Color.lightGray);

    JLabel changeKeyframeTick = new JLabel("Tick to add keyframe at:");

    this.keyframeTickSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 9999999, 1));
    keyframeTickSpinner.setMaximumSize(new Dimension(50, 50));
    keyframeTickSpinner.setToolTipText("select keyframe location");

    this.addKeyframeButton = new JButton("Add new keyframe");
    this.removeKeyframeButton = new JButton("Delete selected keyframe");

    this.removeKeyframeButton = new JButton("Delete selected keyframe");
    keyframeTickPanel.add(this.removeKeyframeButton);

    keyframeTickPanel.add(changeKeyframeTick);
    keyframeTickPanel.add(keyframeTickSpinner);
    keyframeTickPanel.add(addKeyframeButton);

    this.keyframePanel.add(keyframeTickPanel);

    JPanel editKeyframePanel = new JPanel();
    editKeyframePanel.setBackground(Color.PINK);
    editKeyframePanel.setMaximumSize(new Dimension(150, 100));
    editKeyframePanel.setLayout(new BoxLayout(editKeyframePanel, BoxLayout.Y_AXIS));

    JLabel xLabel = new JLabel("X: ");
    editKeyframePanel.add(xLabel);

    this.xSpinner = new JSpinner(new SpinnerNumberModel(0, -9999999, 9999999, 1));
    xSpinner.setToolTipText("Change x location");
    xSpinner.setMaximumSize(new Dimension(50, 50));
    editKeyframePanel.add(xSpinner);

    JLabel yLabel = new JLabel("Y: ");
    editKeyframePanel.add(yLabel);

    this.ySpinner = new JSpinner(new SpinnerNumberModel(0, -9999999, 9999999, 1));
    ySpinner.setToolTipText("Change y location");
    ySpinner.setMaximumSize(new Dimension(50, 50));

    editKeyframePanel.add(ySpinner);

    JLabel widthLabel = new JLabel("Width: ");
    editKeyframePanel.add(widthLabel);

    this.widthSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 9999999, 1));
    widthSpinner.setToolTipText("Change width");
    widthSpinner.setMaximumSize(new Dimension(50, 50));

    editKeyframePanel.add(widthSpinner);

    JLabel heightLabel = new JLabel("Height: ");
    editKeyframePanel.add(heightLabel);

    this.heightSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 9999999, 1));
    heightSpinner.setToolTipText("Change height");
    heightSpinner.setMaximumSize(new Dimension(50, 50));
    editKeyframePanel.add(heightSpinner);

    JButton colorButton = new JButton("Change color");
    colorButton.setActionCommand("color");
    colorButton.addActionListener(this);

    editKeyframePanel.add(colorButton);

    this.editKeyframeButton = new JButton("Edit selected keyframe");
    this.editKeyframeButton.setEnabled(false);
    editKeyframePanel.add(this.editKeyframeButton);

    editKeyframePanel.setMaximumSize(new Dimension(700, 300));

    this.keyframePanel.add(editKeyframePanel);

    this.drawAndKeyframePanel.add(keyframePanel);

    this.mainPanel.add(drawAndKeyframePanel);

  }

  @Override
  public void addShapes(List<Shape> currentShapes, List<Color> currentColors) {
    this.animationPanel.addShapes(currentShapes, currentColors);
  }

  @Override
  public void setCanvasSize(int x, int y, int width, int height) {
    this.animationPanel.setPreferredSize(new Dimension(width, height));
    this.animationPanel.setLocation(400, 400);

  }

  @Override
  public void addFeatures(IFeatures features) {
    features.createSpeedChanger();
    this.playButton.addActionListener(e -> {
      features.playAnimation();
      this.playButton.setEnabled(false);
      this.pauseButton.setEnabled(true);
      this.restartButton.setEnabled(true);
    });
    this.pauseButton.addActionListener(e -> {
      features.pauseAnimation();
      this.pauseButton.setEnabled(false);
      this.resumeButton.setEnabled(true);
    });
    this.resumeButton.addActionListener(e -> {
      features.resumeAnimation();
      this.resumeButton.setEnabled(false);
      this.pauseButton.setEnabled(true);
    });
    this.restartButton.addActionListener(e -> {
      features.restartAnimation();
      this.playButton.setEnabled(true);
      this.pauseButton.setEnabled(false);
      this.resumeButton.setEnabled(false);
    });
    this.loopingBox.addActionListener(e -> features.toggleLooping());

    this.speedChanger.addChangeListener(e -> features.setSpeed((Integer) speedChanger.getValue()));

    features.addToShapeGUIList();

    this.removeKeyframeButton.addActionListener(e -> {
      features.deleteKeyframe(this.selectedKeyframeTick, this.selectedShapeName);
      this.keyframeListContent.clear();
      features.addToKeyFrameGUIList(this.selectedShapeName);

    });

    this.shapeList.addListSelectionListener(e -> {
      if (!this.shapeList.isSelectionEmpty()) {
        this.selectedShapeName = shapeList.getSelectedValue().toString();
      }
      features.addToKeyFrameGUIList(this.selectedShapeName);
      this.editKeyframeButton.setEnabled(false);
    });

    this.keyframeList.addListSelectionListener(e -> {

      if (!keyframeList.isSelectionEmpty()) {
        String[] selectedContent = this.keyframeList.getSelectedValue().toString().split(" ");
        this.selectedKeyframeTick = Integer.parseInt(selectedContent[1]);
        this.editKeyframeButton.setEnabled(true);
        features.updateKeyframeEditorValues(this.selectedKeyframeTick, this.selectedShapeName);
      }
    });

    this.addKeyframeButton.addActionListener(
        e -> {
          features.addNewKeyframe((Integer) keyframeTickSpinner.getValue(), this.selectedShapeName);
          // Updates the gui list of keyframes every time the button is clicked
          features.addToKeyFrameGUIList(this.selectedShapeName);
        });

    this.editKeyframeButton.addActionListener(e -> {

      features.editKeyframe(selectedKeyframeTick,
          (Integer) xSpinner.getValue(),
          (Integer) ySpinner.getValue(),
          this.chosenColor.getRed(),
          this.chosenColor.getGreen(),
          this.chosenColor.getBlue(),
          (Integer) heightSpinner.getValue(),
          (Integer) widthSpinner.getValue(),
          this.selectedShapeName); // Should the view be passing keyframes
      // Disable button until user re-selects a keyframe
      this.editKeyframeButton.setEnabled(false);

      // Refresh the gui keyframe list after a keyframe has been edited
      features.addToKeyFrameGUIList(selectedShapeName);

    });

    this.addShapeButton.addActionListener(e -> {
      String selectedRadio = "";
      if (ellipseRadio.isSelected()) {
        selectedRadio = ellipseRadio.getText();
      } else if (rectRadio.isSelected()) {
        selectedRadio = rectRadio.getText();
      }

      features.addNewShape(newShapeNameField.getText(), selectedRadio);
      features.addToShapeGUIList();

    });

    this.removeShapeButton.addActionListener(e -> {

      features.removeShape(selectedShapeName);
      features.addToShapeGUIList();
    });
  }

  @Override
  public void initializeSpeedChanger(int speed) {
    this.speedChanger = new JSpinner(new SpinnerNumberModel(speed, 1, 100, 2));
    this.speedChanger.setToolTipText("Change animation speed");
    this.buttonPanel.add(new JLabel("Animation speed:"));
    this.buttonPanel.add(speedChanger);
  }

  @Override
  public void initializeShapeList(List<String> shapes) {
    this.shapeListContent.clear();
    this.shapeListContent.addAll(shapes);

  }

  @Override
  public void initializeKeyframeList(List<String> keyframes) {
    this.keyframeListContent.clear();
    for (String s : keyframes) {
      this.keyframeListContent.addElement(s);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if ("color".equals(e.getActionCommand())) {
      this.chosenColor = JColorChooser.showDialog(EditorView.this, "Choose a color", Color.BLACK);
    }
  }

  @Override
  public void makePopupError(String message) {
    JFrame frame = new JFrame();
    JOptionPane
        .showMessageDialog(frame, message,
            "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void updateKeyframeEditorGUI(int x, int y, int r, int g, int b, int height, int width) {
    this.xSpinner.setValue(x);
    this.ySpinner.setValue(y);
    this.chosenColor = new Color(r, g, b);
    this.heightSpinner.setValue(height);
    this.widthSpinner.setValue(width);
  }
}
