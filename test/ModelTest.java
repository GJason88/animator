
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to represent tests to ensure the functionality of the Excellence simple animator.
 */
public class ModelTest {

  private Rectangle rectA;
  private Oval ovalA;

  private Rectangle rectA1;
  private Rectangle rectA2;
  private Rectangle rectA3;

  //Motions for rectA
  private Motion m1;

  private Motion m2;
  private Motion m2_1;
  private Motion m2_2;
  private Motion m2_3;
  private Motion m2_4;

  private Motion m3;

  // Motions for Oval B
  private Motion m4;
  private Motion m5;
  private Motion m6;
  private Rectangle rectB;
  private Rectangle rectB1;
  private Motion m7;
  private Motion m1_o1;
  private Motion m1_o2;
  private Motion m1_o3;
  private Motion m1_o4;
  private Motion m1_o5;
  private Motion m1_o6;
  private Motion m1_o7;
  private Motion m3_o;
  private Motion m1_o11;


  Rectangle changingColorStart;
  Rectangle changingColorEnd;

  Rectangle changingPositionStart;
  Rectangle changingPositionEnd;

  Motion changingColorMotion;
  Motion changingPositionMotion;


  @Before
  public void init() {
    this.rectA = new Rectangle(5, 0, Color.red, 20, 30, "rect A");
    this.rectB = new Rectangle(10, 0, Color.blue, 50, 10, "rect B");
    this.ovalA = new Oval(5, 0, Color.red, 20, 30, "oval A");

    this.rectA1 = new Rectangle(6, 1, Color.green, 23, 33, "rect A");

    this.rectA2 = new Rectangle(5, 2, Color.red, 20, 30, "rect A");
    this.rectA3 = new Rectangle(5, 3, Color.red, 20, 30, "rect A");

    this.rectB1 = new Rectangle(5, 0, Color.blue, 50, 10, "rect B");

    Oval ovalA1 = new Oval(5, 1, Color.red, 20, 30, "oval A");
    Oval ovalA2 = new Oval(5, 2, Color.GREEN, 20, 30, "oval A");
    Oval ovalA3 = new Oval(5, 3, Color.red, 20, 30, "oval A");

    // Motions for rectangle A
    this.m1 = new Motion(rectA, rectA1, 0, 5);
    this.m1_o1 = new Motion(rectA, new Rectangle(6, 0, Color.red, 20, 30, "rect A"), 0, 5);
    this.m1_o11 = new Motion(rectA, new Rectangle(5, 0, Color.green, 20, 30, "rect A"), 0, 5);

    this.m1_o2 = new Motion(rectA, new Rectangle(5, 1, Color.red, 20, 30, "rect A"), 0, 5);
    this.m1_o3 = new Motion(rectA, new Rectangle(5, 0, Color.red, 20, 31, "rect A"), 0, 5);
    this.m1_o4 = new Motion(rectA, new Rectangle(5, 0, Color.red, 21, 30, "rect A"), 0, 5);
    this.m1_o5 = new Motion(rectA, new Rectangle(5, 0, Color.green, 20, 31, "rect A"), 0, 5);
    this.m1_o6 = new Motion(rectA, new Rectangle(6, 0, Color.red, 20, 30, "rect A"), 1, 4);
    this.m1_o7 = new Motion(rectA, new Rectangle(6, 0, Color.red, 20, 30, "rect A"), 1, 6);

    this.m3 = new Motion(rectA1, rectA3, 5, 10);
    this.m3_o = new Motion(rectA1, rectA3, 4, 9);
    this.m2 = new Motion(rectA3, rectA2, 12, 20);

    this.m2_1 = new Motion(rectA3, rectA2, 13, 14);
    this.m2_2 = new Motion(rectA3, rectA2, 13, 22);
    this.m2_3 = new Motion(rectA3, rectA2, 11, 15);
    this.m2_4 = new Motion(rectA, rectA2, 12, 20);

    // Motions for Oval B
    this.m4 = new Motion(ovalA, ovalA1, 5, 10);

    this.m5 = new Motion(ovalA1, ovalA2, 10, 20);

    this.m6 = new Motion(ovalA2, ovalA3, 20, 30);

    this.m7 = new Motion(rectB, rectB1, 5, 15);

    // Overlaps but changing different fields

    this.changingColorStart = new Rectangle(5, 0, Color.red, 20, 30, "rect A");
    this.changingColorEnd = new Rectangle(5, 0, Color.blue, 20, 30, "rect A");

    this.changingPositionStart = new Rectangle(5, 0, Color.red, 20, 30, "rect A");
    this.changingPositionEnd = new Rectangle(5, 10, Color.red, 20, 30, "rect A");

    this.changingColorMotion = new Motion(changingColorStart, changingColorEnd, 0, 10);
    this.changingPositionMotion = new Motion(changingPositionStart, changingPositionEnd, 5, 15);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyMotions() {
    new ShapeAnimation(new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMotionOverlapAllWithin() {
    new ShapeAnimation(new ArrayList<>(Arrays.asList(this.m2, this.m2_1)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMotionOverlapStartingWithin() {
    new ShapeAnimation(new ArrayList<>(Arrays.asList(this.m2, this.m2_2)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMotionOverlapEndingWithin() {
    new ShapeAnimation(new ArrayList<>(Arrays.asList(this.m2, this.m2_3)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMotionInconsistentWithLast() {
    new ShapeAnimation(new ArrayList<>(Arrays.asList(this.m2, this.m2_4)));
  }

  @Test
  public void testGetTextDescription() {
    // Sorted
    ShapeAnimation s1 =
        new ShapeAnimation(
            new ArrayList<>(Arrays.asList(this.m1, this.m3, this.m2, this.m4, this.m5, this.m6)));
    // Unsorted
    ShapeAnimation s2 = new ShapeAnimation(
        new ArrayList<>(Arrays.asList(this.m2, this.m6, this.m5, this.m1, this.m3, this.m4)));

    // With overlap of different shapes (same fields changing during same ticks)
    ShapeAnimation s3 = new ShapeAnimation(
        new ArrayList<>(Arrays.asList(this.m3, this.m4, this.m7)));

    // Same shape changing multiple fields during same tick frame
    ShapeAnimation s4 = new ShapeAnimation(
        new ArrayList<>(Arrays.asList(this.m1)));

    assertEquals("shape rect A Rectangle\n"
        + "motion rect A 0 5 0 30 20 255 0 0   5 6 1 33 23 0 255 0\n"
        + "motion rect A 5 6 1 33 23 0 255 0   10 5 3 30 20 255 0 0\n"
        + "motion rect A 12 5 3 30 20 255 0 0   20 5 2 30 20 255 0 0\n"
        + "\n"
        + "shape oval A Oval\n"
        + "motion oval A 5 5 0 30 20 255 0 0   10 5 1 30 20 255 0 0\n"
        + "motion oval A 10 5 1 30 20 255 0 0   20 5 2 30 20 0 255 0\n"
        + "motion oval A 20 5 2 30 20 0 255 0   30 5 3 30 20 255 0 0", s1.getTextDescription());

    assertEquals("shape rect A Rectangle\n"
        + "motion rect A 0 5 0 30 20 255 0 0   5 6 1 33 23 0 255 0\n"
        + "motion rect A 5 6 1 33 23 0 255 0   10 5 3 30 20 255 0 0\n"
        + "motion rect A 12 5 3 30 20 255 0 0   20 5 2 30 20 255 0 0\n"
        + "\n"
        + "shape oval A Oval\n"
        + "motion oval A 5 5 0 30 20 255 0 0   10 5 1 30 20 255 0 0\n"
        + "motion oval A 10 5 1 30 20 255 0 0   20 5 2 30 20 0 255 0\n"
        + "motion oval A 20 5 2 30 20 0 255 0   30 5 3 30 20 255 0 0", s2.getTextDescription());

    assertEquals("shape rect A Rectangle\n"
        + "motion rect A 5 6 1 33 23 0 255 0   10 5 3 30 20 255 0 0\n"
        + "\n"
        + "shape oval A Oval\n"
        + "motion oval A 5 5 0 30 20 255 0 0   10 5 1 30 20 255 0 0\n"
        + "\n"
        + "shape rect B Rectangle\n"
        + "motion rect B 5 10 0 10 50 0 0 255   15 5 0 10 50 0 0 255", s3.getTextDescription());

    assertEquals("shape rect A Rectangle\n"
        + "motion rect A 0 5 0 30 20 255 0 0   5 6 1 33 23 0 255 0", s4.getTextDescription());

  }

  // Tests for motion

  @Test(expected = NullPointerException.class)
  public void testInvalidMotionNullStart() {
    new Motion(null, rectB1, 5, 15);
  }

  @Test(expected = NullPointerException.class)
  public void testInvalidMotionNullEnd() {
    new Motion(rectA, null, 5, 15);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMotionEndTickLessThanStart() {
    new Motion(rectA, rectB1, 15, 5);
  }

  @Test
  public void testGenerateDescription() {
    assertEquals("motion rect A 0 5 0 30 20 255 0 0   5 6 1 33 23 0 255 0",
        this.m1.generateDescription());
    assertEquals("motion rect B 5 10 0 10 50 0 0 255   15 5 0 10 50 0 0 255",
        this.m7.generateDescription());
    assertEquals("motion oval A 10 5 1 30 20 255 0 0   20 5 2 30 20 0 255 0",
        this.m5.generateDescription());

  }

  @Test
  public void testOverlaps() {
    // overlaps itself
    assertTrue(this.m1.overlaps(this.m1));
    // overlaps x pos
    assertTrue(this.m1.overlaps(this.m1_o1));
    // overlaps y pos
    assertTrue(this.m1.overlaps(this.m1_o2));
    // overlaps width
    assertTrue(this.m1.overlaps(this.m1_o3));
    // overlaps height
    assertTrue(this.m1.overlaps(this.m1_o4));
    // overlaps color
    assertTrue(this.m1.overlaps(this.m1_o5));
    // overlaps start and end within
    assertTrue(this.m3.overlaps(this.m3_o));
    assertTrue(this.m3_o.overlaps(this.m3));
    // overlaps only start within
    assertTrue(this.m1.overlaps(this.m1_o6));
    // overlaps only end within
    assertTrue(this.m1.overlaps(this.m1_o7));
    // nothing overlaps
    assertFalse(this.m1.overlaps(m2));
    // time overlaps but not same field being changed
    assertFalse(this.m1_o1.overlaps(m1_o11));

    // Two motions occur within overlapping time frames, but change different fields
    // on the same shape
    assertFalse(this.changingColorMotion.overlaps(changingPositionMotion));
  }

  @Test
  public void testGetStartingShape() {
    assertEquals(this.rectA, this.m1.getStartingShape());
  }

  @Test
  public void testGetEndingState() {
    assertEquals(this.rectA1, this.m1.getEndingState());
  }

  @Test
  public void testGetStartingTick() {
    assertEquals(0, this.m1.getStartingTick());

  }

  @Test
  public void testGetEndingTick() {
    assertEquals(5, this.m1.getEndingTick());
  }

  // Tests for shape

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeNegativeWidth() {
    new Rectangle(1, 1, Color.red, 1, -1, "A");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeNegativeHeight() {
    new Rectangle(1, 1, Color.red, -1, 1, "A");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeNegativeBoth() {
    new Rectangle(1, 1, Color.red, -1, -1, "A");
  }

  @Test(expected = NullPointerException.class)
  public void testInvalidShapeNullName() {
    new Rectangle(1, 1, Color.red, 1, 1, null);
  }

  @Test(expected = NullPointerException.class)
  public void testInvalidShapeNullColor() {
    new Rectangle(1, 1, null, 1, 1, "A");
  }

  @Test
  public void testMove() {
    assertEquals(5, this.rectA.getX());
    assertEquals(0, this.rectA.getY());
    this.rectA.move(60, 70);
    assertEquals(60, this.rectA.getX());
    assertEquals(70, this.rectA.getY());

    assertEquals(5, this.ovalA.getX());
    assertEquals(0, this.ovalA.getY());
    this.ovalA.move(-5, -8);
    assertEquals(-5, this.ovalA.getX());
    assertEquals(-8, this.ovalA.getY());
  }

  @Test
  public void testResize() {
    assertEquals(30, this.rectA.getWidth());
    assertEquals(20, this.rectA.getHeight());
    this.rectA.resize(60, 40);
    assertEquals(60, this.rectA.getWidth());
    assertEquals(40, this.rectA.getHeight());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeResize() {
    this.rectA.resize(-2, -5);
  }

  @Test
  public void testChangeColor() {
    assertEquals(Color.red, rectA.getColor());
    this.rectA.changeColor(0, 0, 255);
    assertEquals(Color.blue, rectA.getColor());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadColorChange() {
    this.ovalA.changeColor(-4, 50, 22);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadColorChangeV2() {
    this.ovalA.changeColor(90, 900, 22);
  }

  @Test
  public void testGetName() {
    assertEquals("rect A", this.rectA.getName());
    assertEquals("oval A", this.ovalA.getName());
  }

  @Test
  public void testGetX() {
    assertEquals(5, this.rectA.getX());
    assertEquals(10, this.rectB.getX());
  }

  @Test
  public void testGetY() {
    assertEquals(2, this.rectA2.getY());
    assertEquals(3, this.rectA3.getY());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.red, this.rectA.getColor());
    assertEquals(Color.blue, this.rectB.getColor());
  }

  @Test
  public void testGetWidth() {
    assertEquals(30, this.rectA2.getWidth());
    assertEquals(10, this.rectB1.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(20, this.rectA2.getHeight());
    assertEquals(50, this.rectB1.getHeight());
  }

  @Test
  public void isSameStateTest() {
    assertTrue(new Rectangle(1, 1, Color.red, 1, 1, "A")
        .isSameState(new Rectangle(1, 1, Color.red, 1, 1, "B")));
    assertFalse(new Rectangle(1, 1, Color.red, 1, 1, "A")
        .isSameState(new Oval(1, 1, Color.red, 1, 1, "A")));
    assertFalse(new Rectangle(1, 1, Color.red, 1, 1, "A")
        .isSameState(new Oval(1, 1, Color.red, 1, 1, "B")));
    assertTrue(this.rectA.isSameState(this.rectA));
    assertFalse(this.rectA.isSameState(this.rectB));

  }

  @Test(expected = NullPointerException.class)
  public void isSameStateTestNull() {
    this.rectB1.isSameState(null);
  }


  // Changing color motion occurs from time 0-10. Changing position motion occurs from time 5-15.
  // Both motions are occuring on the same shape. However, they are ending in the same state, which
  // should throw an exception.
  @Test(expected = IllegalArgumentException.class)
  public void testNotEndingInSameState() {

    ShapeAnimation anim = new ShapeAnimation(
        new ArrayList<Motion>(Arrays.asList(changingColorMotion, changingPositionMotion)));

  }

  // Two motions start and end at the same time changing different values on the same shape.
  // However, they do not end in the same state, which should throw an exception.
  @Test(expected = IllegalArgumentException.class)
  public void testSameStartingStateSameStartingTime() {

    this.changingPositionMotion = new Motion(changingPositionStart, changingPositionEnd,
        0, 10);

    ShapeAnimation anim = new ShapeAnimation(
        new ArrayList<Motion>(Arrays.asList(changingColorMotion, changingPositionMotion)));
  }

}
