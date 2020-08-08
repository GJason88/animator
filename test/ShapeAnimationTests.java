import static org.junit.Assert.assertEquals;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeAnimation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ShapeAnimations. Tests to make sure the functionality of ShapeAnimation methods
 * work properly.
 */
public class ShapeAnimationTests {

  private Motion m2;

  private Motion m3;

  // Motions for cs3500.animator.model.Oval B
  private Motion m4;
  private Motion m5;
  private Rectangle rectB;

  private List<IMotion> motionsOvalA;
  private List<IMotion> motionsRectA1;
  private List<IMotion> emptyMotions;

  private Oval ovalA;
  private Oval ovalA1;
  private Oval ovalA3;

  private Rectangle rectA1;
  private Rectangle rectA3;
  private Rectangle rectA3_1;
  private Rectangle rectA3_2;
  private Rectangle rectA3_3;

  private ShapeAnimation testAnimation;

  @Before
  public void init() {

    // Motions for rectangle A
    //Motions for rectA
    Motion m1 = new Motion(new Keyframe(0, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(5, 10, 1, 0, 0, 255, 50, 10));
    Motion m1_o1 = new Motion(new Keyframe(0, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(5, 6, 0, 255, 0, 0, 20, 30));
    Motion m1_o11 = new Motion(new Keyframe(0, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(5, 5, 0, 0, 255, 0, 20, 30));

    Motion m1_o2 = new Motion(new Keyframe(0, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(5, 5, 1, 255, 0, 0, 20, 30));

    Motion m1_o3 = new Motion(new Keyframe(0, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(5, 5, 0, 255, 0, 0, 20, 31));
    Motion m1_o4 = new Motion(new Keyframe(0, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(5, 5, 0, 255, 0, 0, 21, 30));
    Motion m1_o5 = new Motion(new Keyframe(0, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(5, 5, 0, 0, 255, 0, 20, 31));
    Motion m1_o6 = new Motion(new Keyframe(1, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(4, 6, 0, 255, 0, 0, 20, 30));
    Motion m1_o7 = new Motion(new Keyframe(1, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(6, 6, 0, 255, 0, 0, 20, 30));

    this.m3 = new Motion(new Keyframe(5, 6, 1, 0, 255, 0, 23, 33),
        new Keyframe(10, 5, 3, 255, 0, 0, 20, 30));
    Motion m3_o = new Motion(new Keyframe(4, 6, 1, 0, 255, 0, 23, 33),
        new Keyframe(9, 5, 3, 255, 0, 0, 20, 30));
    this.m2 = new Motion(new Keyframe(12, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(20, 5, 2, 255, 0, 0, 20, 30));

    Motion m2_1 = new Motion(new Keyframe(13, 5, 3, 255, 0, 0, 20, 30),
        new Keyframe(14, 5, 2, 255, 0, 0, 20, 30));
    Motion m2_2 = new Motion(new Keyframe(13, 5, 3, 255, 0, 0, 20, 30),
        new Keyframe(22, 5, 2, 255, 0, 0, 20, 30));
    Motion m2_3 = new Motion(new Keyframe(11, 5, 3, 255, 0, 0, 20, 30),
        new Keyframe(15, 5, 2, 255, 0, 0, 20, 30));
    Motion m2_4 = new Motion(new Keyframe(20, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(30, 5, 2, 255, 0, 0, 20, 30));

    // Motions for cs3500.animator.model.Oval B
    this.m4 = new Motion(new Keyframe(5, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(10, 5, 1, 255, 0, 0, 20, 30));

    this.m5 = new Motion(new Keyframe(10, 5, 1, 255, 0, 0, 20, 30),
        new Keyframe(20, 5, 2, 0, 255, 0, 20, 30));

    Motion m6 = new Motion(new Keyframe(20, 5, 2, 0, 255, 0, 20, 30),
        new Keyframe(30, 5, 3, 255, 0, 0, 20, 30));

    Motion m7 = new Motion(new Keyframe(5, 10, 0, 0, 0, 255, 50, 10),
        new Keyframe(15, 5, 0, 255, 0, 0, 20, 30));

    Motion changingColorMotion = new Motion(new Keyframe(0, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(10, 5, 0, 0, 0, 255, 20, 30));

    Motion changingPositionMotion = new Motion(new Keyframe(5, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(15, 5, 10, 255, 0, 0, 20, 30));

    List<IMotion> motionsRectA = new ArrayList(Arrays.asList(
        m1, m1_o1, m1_o11, m1_o2, m1_o3, m1_o4, m1_o5, m1_o6, m1_o7, m2_4));
    List<IMotion> motionsRectB = new ArrayList(Arrays.asList(m7));
    motionsOvalA = new ArrayList(Arrays.asList(m4));
    motionsRectA1 = new ArrayList(Arrays.asList(m4, m5, m6));
    List<IMotion> motionsRectA2 = new ArrayList(Arrays.asList(m6));
    List<IMotion> motionsRectA3 = new ArrayList(Arrays.asList(m2, m2_1));
    List<IMotion> motionsOvalA1 = new ArrayList(Arrays.asList(m4, m5, m6));
    List<IMotion> motionsOvalA2 = new ArrayList(Arrays.asList(m6));
    List<IMotion> motionsChangingColor = new ArrayList(Arrays.asList(changingColorMotion));
    List<IMotion> motionsChangingPosition = new ArrayList(Arrays.asList(changingPositionMotion));
    emptyMotions = new ArrayList<>();

    // Overlaps but changing different fields

    Rectangle changingColorStart = new Rectangle("rect Color", motionsChangingColor);

    Rectangle changingPositionStart = new Rectangle("rect Position", motionsChangingPosition);

    Rectangle rectA = new Rectangle("rect A", motionsRectA);
    this.rectB = new Rectangle("rect B", motionsRectB);
    this.ovalA = new Oval("oval A", motionsOvalA);

    this.rectA1 = new Rectangle("rect A1", motionsRectA1);

    Rectangle rectA2 = new Rectangle("rect A2", motionsRectA2);
    this.rectA3 = new Rectangle("rect A3", motionsRectA3);
    this.rectA3_1 = new Rectangle("rect A3_1", new ArrayList(Arrays.asList(m2, m2_2)));
    this.rectA3_2 = new Rectangle("rect A3_2", new ArrayList(Arrays.asList(m2, m2_3)));
    this.rectA3_3 = new Rectangle("rect A3_3", new ArrayList(Arrays.asList(m2, m2_4)));

    Rectangle rectB1 = new Rectangle("rect B1", emptyMotions);

    ovalA1 = new Oval("oval A1", motionsOvalA1);
    Oval ovalA2 = new Oval("oval A2", motionsOvalA2);
    ovalA3 = new Oval("oval A3", emptyMotions);

    this.testAnimation = new ShapeAnimation(
        new ArrayList<>(Arrays.asList(this.rectA1, this.rectB, this.ovalA, this.ovalA1)),
        0, 5, 600, 500);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullShapes() {
    new ShapeAnimation(null, 0, 0, 500, 500);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCanvasLargerOffset() {
    new ShapeAnimation(new ArrayList<>(), 2, 2, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCanvasNegativeDimensions() {
    new ShapeAnimation(new ArrayList<>(), -10, -10, -1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMotionOverlapAllWithin() {
    new ShapeAnimation(new ArrayList<>(Arrays.asList(this.rectA3)), 0, 0, 500, 500);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMotionOverlapStartingWithin() {
    new ShapeAnimation(new ArrayList<>(Arrays.asList(this.rectA3_1)), 0, 0, 500, 500);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMotionOverlapEndingWithin() {
    new ShapeAnimation(new ArrayList<>(Arrays.asList(this.rectA3_2)), 0, 0, 500, 500);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMotionInconsistentWithLast() {
    new ShapeAnimation(new ArrayList<>(Arrays.asList(this.rectA3_3)), 0, 0, 500, 500);
  }

  @Test
  public void testGetShapes() {
    assertEquals(
        new ArrayList<Shape>(Arrays.asList(this.rectA1, this.rectB, this.ovalA, this.ovalA1)),
        testAnimation.getShapes());
  }

  @Test
  public void testAddShape() {
    assertEquals(
        new ArrayList<Shape>(Arrays.asList(this.rectA1, this.rectB, this.ovalA, this.ovalA1)),
        testAnimation.getShapes());

    this.testAnimation.addShape(this.rectA3_3);

    assertEquals(
        new ArrayList<Shape>(
            Arrays.asList(this.rectA1, this.rectB, this.ovalA, this.ovalA1, this.rectA3_3)),
        testAnimation.getShapes());
  }

  @Test
  public void removeShape() {
    assertEquals(
        new ArrayList<Shape>(Arrays.asList(this.rectA1, this.rectB, this.ovalA, this.ovalA1)),
        testAnimation.getShapes());

    this.testAnimation.removeShape("rect A1");

    assertEquals(
        new ArrayList<Shape>(Arrays.asList(this.rectB, this.ovalA, this.ovalA1)),
        testAnimation.getShapes());
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeNullShape() {
    assertEquals(
        new ArrayList<Shape>(Arrays.asList(this.rectA1, this.rectB, this.ovalA, this.ovalA1)),
        testAnimation.getShapes());

    this.testAnimation.removeShape(null);

  }

  @Test
  public void testGetCanvasXAndY() {
    assertEquals(0, this.testAnimation.getCanvasX());
    assertEquals(5, this.testAnimation.getCanvasY());
  }

  @Test
  public void testGetCanvasWidthAndHeight() {
    assertEquals(600, this.testAnimation.getCanvasWidth());
    assertEquals(500, this.testAnimation.getCanvasHeight());
  }

  @Test
  public void voidTestAddMotion() {
    assertEquals(this.motionsOvalA, this.ovalA.getMotions());

    Motion motionToAdd = new Motion(new Keyframe(10, 5, 1, 255, 0, 0, 20, 30),
        new Keyframe(20, 5, 1, 0, 0, 255, 20, 30));
    this.testAnimation.addMotion(motionToAdd, ovalA);

    assertEquals(new ArrayList<>(Arrays.asList(this.m4, motionToAdd)), this.ovalA.getMotions());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNullMotion() {
    this.testAnimation.addMotion(null, ovalA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNullShape() {
    Motion motionToAdd = new Motion(new Keyframe(10, 5, 1, 255, 0, 0, 20, 30),
        new Keyframe(20, 5, 1, 0, 0, 255, 20, 30));
    this.testAnimation.addMotion(motionToAdd, null);
  }

  @Test
  public void testRemoveMotion() {
    assertEquals(this.motionsOvalA, this.ovalA.getMotions());

    this.testAnimation.removeMotion(m4, ovalA);

    assertEquals(new ArrayList<IMotion>(), this.ovalA.getMotions());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAlreadyContainsAddMotion() {
    this.testAnimation.addMotion(this.m4, this.rectA1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInconsistentAddMotion() {
    this.testAnimation.addMotion(new Motion(new Keyframe(5, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(10, 5, 1, 255, 0, 0, 20, 30)), this.rectA1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInconsistentRemoveMotion() {
    this.testAnimation.removeMotion(this.m5, this.rectA1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionNullMotion() {
    assertEquals(this.motionsOvalA, this.ovalA.getMotions());

    this.testAnimation.removeMotion(null, ovalA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionNullShape() {
    assertEquals(this.motionsOvalA, this.ovalA.getMotions());

    this.testAnimation.removeMotion(m4, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionNonExistingMotion() {
    this.testAnimation.removeMotion(m3, this.ovalA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionNonExistingShape() {
    this.testAnimation.removeMotion(m2, this.ovalA3);
  }

  @Test
  public void testGetMotionsForShape() {
    assertEquals(this.motionsRectA1, this.testAnimation.getMotionsForShape(this.rectA1));
    assertEquals(this.emptyMotions, new ShapeAnimation(
        new ArrayList<>(Arrays.asList(this.ovalA3, this.rectB, this.ovalA, this.ovalA1)),
        0, 5, 600, 500).getMotionsForShape(this.ovalA3));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetMotionsForShapeNull() {
    assertEquals(this.motionsRectA1, this.testAnimation.getMotionsForShape(null));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetMotionsForShapeNonExistent() {
    assertEquals(this.motionsRectA1, this.testAnimation.getMotionsForShape(this.rectA3));

  }
}