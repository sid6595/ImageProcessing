import org.junit.Before;
import org.junit.Test;

import enumpackage.Brightness;
import enumpackage.Component;
import enumpackage.Orientation;
import model.ModelImpl;
import model.Picture;
import model.Pixel;
import controller.commands.Brighten;
import controller.commands.Flip;
import controller.commands.VisRGB;
import controller.commands.VisIntensity;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class that is used to test the program.
 */
public class ModelImplTest {
  ModelImpl m1;
  Picture p1;

  @Before
  public void init() {
    Pixel topLeft = new Pixel(255, 0, 0);
    Pixel topRight = new Pixel(0,255,0);
    Pixel bottomLeft = new Pixel(0,0,255);
    Pixel bottomRight = new Pixel(0,0,0);

    Pixel[][] pixelArray = new Pixel[2][2];
    pixelArray[0][0] = topLeft;
    pixelArray[1][0] = topRight;
    pixelArray[0][1] = bottomLeft;
    pixelArray[1][1] = bottomRight;

    p1 = new Picture(pixelArray, 2,2,255);
    m1 = new ModelImpl();
    m1.addPicture(p1, "testPic");
  }

//  @Test
//  public void testLoadjpg() {
//    ModelImpl m1 = new ModelImpl();
//    m1.loadDifferentImage("jpgsample.jpg", "Sample");
//
//
//  }

  @Test
  public void testBrightenFunc() {
    init();
    Brighten b = new Brighten(10);
    b.apply(m1, "testPic", "testBrighter");
    p1.brighten(10);
    assertEquals(p1.brighten(10), m1.getPicture("testBrighter"));
  }

  @Test
  public void testFlipVertFunc() {
    init();
    Flip f = new Flip(Orientation.Vertical);
    f.apply(m1, "testPic", "testFlipVertical");
    p1.flip(Orientation.Vertical);
    assertEquals(p1, m1.getPicture("testFlipVertical"));
  }

  @Test
  public void testFlipHorFunc() {
    init();
    Flip f = new Flip(Orientation.Horizontal);
    f.apply(m1, "testPic", "testFlipHorizontal");
    p1.flip(Orientation.Horizontal);
    assertEquals(p1, m1.getPicture("testFlipHorizontal"));
  }

  @Test
  public void visIntensity() {
    init();
    VisIntensity v = new VisIntensity(Brightness.Luma);
    v.apply(m1, "testPic", "testBrightnessLuma");
    p1.visIntensity(Brightness.Luma);
    assertEquals(p1.visIntensity(Brightness.Luma), m1.getPicture("testBrightnessLuma"));
  }

  @Test
  public void visRGB() {
    init();
    VisRGB v = new VisRGB(Component.Green);
    v.apply(m1, "testPic", "testComponentGreen");
    p1.visRGB(Component.Green);
    assertEquals(p1, m1.getPicture("testComponentGreen"));
  }

  @Test
  public void testLoadPPM3x3() {
    m1 = new ModelImpl();
    m1.loadImage("3x3checker.ppm", "3x3");
    String expected = "P3\n" +
            "3 3\n" +
            "255\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "255\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "255\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "255\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "255\n" +
            "255\n" +
            "0\n" +
            "0";
    String actual = m1.getPicture("3x3").toPPM();
    assertEquals(expected, actual);
  }

}
