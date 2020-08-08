import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.IController;
import cs3500.animator.controller.TextController;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Shape;
import cs3500.animator.model.ShapeAnimation;
import cs3500.animator.view.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

/**
 * Class for testing keyframes. Ensures the functionality of the keyframe methods.
 */
public class KeyframeTest {

  @Test
  public void testGetKeyframe() throws IOException {
    Motion m1 = new Motion(new Keyframe(0, 5, 0, 255, 0, 0, 20, 30),
        new Keyframe(20, 10, 1, 0, 0, 255, 50, 10));

    Shape rectA = new Rectangle("test shape", new ArrayList<IMotion>(Arrays.asList(m1)));

    ShapeAnimation testAnimation = new ShapeAnimation(new ArrayList<>(Arrays.asList(rectA)), 0, 0,
        200, 200);

    TextView tv = new TextView();
    IController ctrl = new TextController(tv, testAnimation);

    assertEquals(2, rectA.getKeyframes().size());

    rectA.addKeyframe(10);
    rectA.addKeyframe(15);

    rectA.deleteKeyframe(10);

    rectA.editKeyframe(15, new Keyframe(15, 9, 1, 255, 0, 0, 100, 200));

    assertEquals(3, rectA.getKeyframes().size());
  }
}
