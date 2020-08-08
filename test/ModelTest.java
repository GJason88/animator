
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.Motion;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to represent tests to ensure the functionality of the cs3500.animator.model.IModel simple
 * animator.
 */
public class ModelTest {


  //Motions for rectA
  private Motion m1;

  private Motion m2;

  private Motion m3;

  private Motion m5;
  private Rectangle rectB;
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

  private List<Motion> motionsRectA;
  private List<Motion> motionsRectB;
  private List<Motion> motionsOvalA;

  private Rectangle rectA;

  private Motion changingColorMotion;
  private Motion changingPositionMotion;


  @Before
  public void init() {

    // Motions for rectangle A
    this.m1 = new Motion(0, 5, 0, 255, 0, 0, 20, 30,
        5, 10, 1, 0, 0, 255, 50, 10);
    this.m1_o1 = new Motion(0, 5, 0, 255, 0, 0, 20, 30,
        5, 6, 0, 255, 0, 0, 20, 30);
    this.m1_o11 = new Motion(0, 5, 0, 255, 0, 0, 20, 30,
        5, 5, 0, 0, 255, 0, 20, 30);

    this.m1_o2 = new Motion(0, 5, 0, 255, 0, 0, 20, 30,
        5, 5, 1, 255, 0, 0, 20, 30);

    this.m1_o3 = new Motion(0, 5, 0, 255, 0, 0, 20, 30,
        5, 5, 0, 255, 0, 0, 20, 31);
    this.m1_o4 = new Motion(0, 5, 0, 255, 0, 0, 20, 30,
        5, 5, 0, 255, 0, 0, 21, 30);
    this.m1_o5 = new Motion(0, 5, 0, 255, 0, 0, 20, 30,
        5, 5, 0, 0, 255, 0, 20, 31);
    this.m1_o6 = new Motion(1, 5, 0, 255, 0, 0, 20, 30,
        4, 6, 0, 255, 0, 0, 20, 30);
    this.m1_o7 = new Motion(1, 5, 0, 255, 0, 0, 20, 30,
        6, 6, 0, 255, 0, 0, 20, 30);

    this.m3 = new Motion(5, 6, 1, 0, 255, 0, 23, 33,
        10, 5, 3, 255, 0, 0, 20, 30);
    this.m3_o = new Motion(4, 6, 1, 0, 255, 0, 23, 33,
        9, 5, 3, 255, 0, 0, 20, 30);
    this.m2 = new Motion(12, 5, 0, 255, 0, 0, 20, 30,
        20, 5, 2, 255, 0, 0, 20, 30);

    Motion m2_1 = new Motion(13, 5, 3, 255, 0, 0, 20, 30,
        14, 5, 2, 255, 0, 0, 20, 30);
    Motion m2_2 = new Motion(13, 5, 3, 255, 0, 0, 20, 30,
        22, 5, 2, 255, 0, 0, 20, 30);
    Motion m2_3 = new Motion(11, 5, 3, 255, 0, 0, 20, 30,
        15, 5, 2, 255, 0, 0, 20, 30);
    Motion m2_4 = new Motion(20, 5, 0, 255, 0, 0, 20, 30,
        30, 5, 2, 255, 0, 0, 20, 30);

    // Motions for cs3500.animator.model.Oval B
    // Motions for cs3500.animator.model.Oval B
    Motion m4 = new Motion(5, 5, 0, 255, 0, 0, 20, 30,
        10, 5, 1, 255, 0, 0, 20, 30);

    this.m5 = new Motion(10, 5, 1, 255, 0, 0, 20, 30,
        20, 5, 2, 0, 255, 0, 20, 30);

    Motion m6 = new Motion(20, 5, 2, 0, 255, 0, 20, 30,
        30, 5, 3, 255, 0, 0, 20, 30);

    this.m7 = new Motion(5, 10, 0, 0, 0, 255, 50, 10,
        15, 5, 0, 255, 0, 0, 20, 30);

    this.changingColorMotion = new Motion(0, 5, 0, 255, 0, 0, 20, 30,
        10, 5, 0, 0, 0, 255, 20, 30);

    this.changingPositionMotion = new Motion(5, 5, 0, 255, 0, 0, 20, 30,
        15, 5, 10, 255, 0, 0, 20, 30);

    motionsRectA = new ArrayList(Arrays.asList(
        m1, m1_o1, m1_o11, m1_o2, m1_o3, m1_o4, m1_o5, m1_o6, m1_o7, m2_4));
    motionsRectB = new ArrayList(Arrays.asList(m7));
    motionsOvalA = new ArrayList(Arrays.asList(m4));
    List<Motion> motionsRectA1 = new ArrayList(Arrays.asList(m4, m5, m6));
    List<Motion> motionsRectA2 = new ArrayList(Arrays.asList(m6));
    List<Motion> motionsRectA3 = new ArrayList(Arrays.asList(m2, m2_1));
    List<Motion> motionsOvalA1 = new ArrayList(Arrays.asList(m4, m5, m6));
    List<Motion> motionsOvalA2 = new ArrayList(Arrays.asList(m6));
    List<Motion> motionsChangingColor = new ArrayList(Arrays.asList(changingColorMotion));
    List<Motion> motionsChangingPosition = new ArrayList(Arrays.asList(changingPositionMotion));
    List<Motion> emptyMotions = new ArrayList<>();

    // Overlaps but changing different fields

    Rectangle changingColorStart = new Rectangle("rect Color", motionsChangingColor);

    Rectangle changingPositionStart = new Rectangle("rect Position", motionsChangingPosition);

    this.rectA = new Rectangle("rect A", motionsRectA);
    this.rectB = new Rectangle("rect B", motionsRectB);
    Oval ovalA = new Oval("oval A", motionsOvalA);

    Rectangle rectA1 = new Rectangle("rect A1", motionsRectA1);

    Rectangle rectA2 = new Rectangle("rect A2", motionsRectA2);
    Rectangle rectA3 = new Rectangle("rect A3", motionsRectA3);
    Rectangle rectA3_1 = new Rectangle("rect A3_1", new ArrayList(Arrays.asList(m2, m2_2)));
    Rectangle rectA3_2 = new Rectangle("rect A3_2", new ArrayList(Arrays.asList(m2, m2_3)));
    Rectangle rectA3_3 = new Rectangle("rect A3_2", new ArrayList(Arrays.asList(m2, m2_4)));

    Rectangle rectB1 = new Rectangle("rect B1", emptyMotions);

    Oval ovalA1 = new Oval("oval A1", motionsOvalA1);
    Oval ovalA2 = new Oval("oval A2", motionsOvalA2);
    Oval ovalA3 = new Oval("oval A3", emptyMotions);


  }

  // Tests for motion

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeStart() {
    new Motion(-1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
  }

  //
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeEnd() {
    new Motion(-10, 2, 3, 4, 5, 6, 7, 8, -9, 10, 11, 12, 13, 14, 15, 16);
  }

  //
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMotionEndTickLessThanStart() {
    new Motion(10, 2, 3, 4, 5, 6, 7, 8, 5, 10, 11, 12, 13, 14, 15, 16);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadColorV1() {
    new Motion(0, 2, 3, 256, 5, 6, 7, 8,
        5, 10, 11, 12, 13, 14, 15, 16);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadColorV2() {
    new Motion(0, 2, 3, 0, -200, 6, 7, 8,
        5, 10, 11, 12, 13, 14, 15, 16);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidth() {
    new Motion(0, 2, 3, 0, 5, 6, 7, 8,
        5, 10, 11, 12, 13, 14, 15, -20);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeHeight() {
    new Motion(0, 2, 3, 0, 5, 6, -90, 8,
        5, 10, 11, 12, 13, 14, 15, 16);

  }


  // Tests for text description
  @Test
  public void testGenerateDescription() {
    assertEquals("0 5 0 30 20 255 0 0 5 10 1 10 50 0 0 255",
        this.m1.generateDescription());
    assertEquals("5 10 0 10 50 0 0 255 15 5 0 30 20 255 0 0",
        this.m7.generateDescription());
    assertEquals("10 5 1 30 20 255 0 0 20 5 2 30 20 0 255 0",
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

  @Test(expected = IllegalArgumentException.class)
  public void testNullOverlap() {
    this.m2.overlaps(null);
  }


  @Test
  public void testGetStartingTick() {
    assertEquals(0, this.m1.getStartingTick());

  }

  @Test
  public void testGetEndingTick() {
    assertEquals(5, this.m1.getEndingTick());
  }

  @Test
  public void testGetStartingX() {
    assertEquals(5, this.m1.getStartX());
  }

  @Test
  public void testGetStartingY() {
    assertEquals(0, this.m1.getStartY());
  }

  @Test
  public void testGetStartingR() {
    assertEquals(255, this.m1.getStartR());
  }

  @Test
  public void testGetStartingG() {
    assertEquals(0, this.m1.getStartG());
  }

  @Test
  public void testGetStartingB() {
    assertEquals(0, this.m1.getStartB());
  }

  @Test
  public void testGetStartingWidth() {
    assertEquals(30, this.m1.getStartWidth());
  }

  @Test
  public void testGetStartingHeight() {
    assertEquals(20, this.m1.getStartHeight());
  }


  @Test
  public void testGetEndingX() {
    assertEquals(10, this.m1.getEndX());
  }

  @Test
  public void testGetEndingY() {
    assertEquals(1, this.m1.getEndY());
  }

  @Test
  public void testGetEndingR() {
    assertEquals(0, this.m1.getEndR());
  }

  @Test
  public void testGetEndingG() {
    assertEquals(0, this.m1.getEndG());
  }

  @Test
  public void testGetEndingB() {
    assertEquals(255, this.m1.getEndB());
  }

  @Test
  public void testGetEndingWidth() {
    assertEquals(10, this.m1.getEndWidth());
  }

  @Test
  public void testGetEndingHeight() {
    assertEquals(50, this.m1.getEndHeight());
  }


  @Test
  public void testIsConsistent() {
    Motion motion1 = new Motion(0, 5, 0, 255, 0, 0, 20, 30,

        5, 10, 1, 0, 0, 255, 50, 10);

    Motion motion2 = new Motion(5, 10, 1, 0, 0, 255, 50, 10,
        10, 20, 1, 0, 0, 255, 50, 10);

    Motion motion3 = new Motion(10, 25, 1, 0, 0, 255, 50, 10,
        20, 20, 1, 0, 0, 255, 50, 10);

    assertTrue(motion1.isConsistent(motion2));
    assertFalse(motion2.isConsistent(motion3));
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIsConsistentNull() {
    this.m1.isConsistent(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    Shape s = new Rectangle(null, this.motionsOvalA);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullMotions() {
    Shape s = new Rectangle("hello", null);
  }

  @Test
  public void testGetName() {
    assertEquals("rect A", this.rectA.getName());
  }

  @Test
  public void testGetMotions() {
    assertEquals(this.motionsRectA, this.rectA.getMotions());
  }

  @Test
  public void testAddMotion() {
    assertEquals(this.motionsRectB, this.rectB.getMotions());
    assertEquals(1, this.rectB.getMotions().size());

    Motion addedMotion = new Motion(15, 5, 0, 255, 0, 0, 20, 30,
        20, 5, 0, 0, 255, 0, 20, 30);
    rectB.addMotion(addedMotion);

    assertEquals(new ArrayList<Motion>(Arrays.asList(this.m7, addedMotion)),
        this.rectB.getMotions());
    assertEquals(2, this.rectB.getMotions().size());
  }

  @Test
  public void testRemoveMotion() {
    assertEquals(this.motionsRectB, this.rectB.getMotions());
    assertEquals(1, this.rectB.getMotions().size());

    rectB.removeMotion(this.m7);

    assertEquals(new ArrayList<Motion>(Arrays.asList()), this.rectB.getMotions());
    assertEquals(0, this.rectB.getMotions().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNonExistentMotion() {

    rectB.removeMotion(this.m1);

  }


}
