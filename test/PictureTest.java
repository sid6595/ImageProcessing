import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;

import enumpackage.Brightness;
import enumpackage.Component;
import enumpackage.Orientation;
import model.Picture;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This is the tester for the picture methods.
 */
public class PictureTest {
  Pixel topLeft;
  Pixel topRight;
  Pixel middleLeft;
  Pixel middleRight;
  Pixel bottomLeft;
  Pixel bottomRight;

  Picture p1;

  @Before
  public void init() {
    // Pixel[height][width]
    topLeft = new Pixel(255, 0, 0);
    topRight = new Pixel(0, 255, 0);
    middleLeft = new Pixel(0, 9, 0);
    middleRight = new Pixel(0, 0, 9);
    bottomLeft = new Pixel(0, 0, 255);
    bottomRight = new Pixel(0, 0, 0);

    Pixel[][] pixelArray = new Pixel[3][2];
    pixelArray[0][0] = topLeft;
    pixelArray[0][1] = topRight;
    pixelArray[1][0] = middleLeft;
    pixelArray[1][1] = middleRight;
    pixelArray[2][0] = bottomLeft;
    pixelArray[2][1] = bottomRight;

    p1 = new Picture(pixelArray, 2, 3, 255, "ppm",
            BufferedImage.TYPE_INT_ARGB);

  }

  @Test
  public void testBuff() {
    init();
    String out = p1.toBufferedImage().toString();
    assertEquals("BufferedImage@28864e92: type = 2 DirectColorModel: rmask=ff0000 " +
            "gmask=ff00 bmask=ff amask=ff000000 IntegerInterleavedRaster: width = 2 height = 3 " +
            "#Bands = 4 xOff = 0 yOff = 0 dataOffset[0] 0", out);

  }

  @Test
  public void testVisIntensity() {
    init();
    p1.visIntensity(Brightness.Intensity);

    Pixel expectedTopLeft = new Pixel(85, 85, 85);
    Pixel expectedTopRight = new Pixel(85, 85, 85);
    Pixel expectedMiddleLeft = new Pixel(3, 3, 3);
    Pixel expectedMiddleRight = new Pixel(3, 3, 3);
    Pixel expectedBottomLeft = new Pixel(85, 85, 85);
    Pixel expectedBottomRight = new Pixel(0, 0, 0);

    Pixel[][] expectedPixelArray = new Pixel[3][2];
    expectedPixelArray[0][0] = expectedTopLeft;
    expectedPixelArray[0][1] = expectedTopRight;
    expectedPixelArray[1][0] = expectedMiddleLeft;
    expectedPixelArray[1][1] = expectedMiddleRight;
    expectedPixelArray[2][0] = expectedBottomLeft;
    expectedPixelArray[2][1] = expectedBottomRight;

    Picture expectedPic = new Picture(expectedPixelArray, 2, 3, 255,
            "ppm", BufferedImage.TYPE_INT_ARGB);

    assertEquals(expectedPic, p1.visIntensity(Brightness.Intensity));
    assertNotEquals(expectedPic, p1);

  }

  @Test
  public void testVisRGB() {
    init();
    p1.visRGB(Component.Red);

    Pixel expectedTopLeft = new Pixel(255, 255, 255);
    Pixel expectedTopRight = new Pixel(0, 0, 0);
    Pixel expectedMiddleLeft = new Pixel(0, 0, 0);
    Pixel expectedMiddleRight = new Pixel(0, 0, 0);
    Pixel expectedBottomLeft = new Pixel(0, 0, 0);
    Pixel expectedBottomRight = new Pixel(0, 0, 0);

    Pixel[][] expectedPixelArray = new Pixel[3][2];
    expectedPixelArray[0][0] = expectedTopLeft;
    expectedPixelArray[0][1] = expectedTopRight;
    expectedPixelArray[1][0] = expectedMiddleLeft;
    expectedPixelArray[1][1] = expectedMiddleRight;
    expectedPixelArray[2][0] = expectedBottomLeft;
    expectedPixelArray[2][1] = expectedBottomRight;

    Picture expectedPic = new Picture(expectedPixelArray, 2, 3, 255,
            "ppm", BufferedImage.TYPE_INT_ARGB);

    assertEquals(expectedPic, p1.visRGB(Component.Red));
    assertNotEquals(expectedPic, p1);

  }

  @Test
  public void testToPPM() {
    init();
    String actual = p1.toPPM();

    String expected = "P3\n" +
            "2 3\n" +
            "255\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "9\n" +
            "0\n" +
            "0\n" +
            "0\n" +
            "9\n" +
            "0\n" +
            "0\n" +
            "255\n" +
            "0\n" +
            "0\n" +
            "0\n";

    assertEquals(expected, actual);
  }


  @Test
  public void testFlipVertical() {
    init();
    p1.flip(Orientation.Vertical);

    Pixel expectedTopLeft = new Pixel(0, 0, 255);
    Pixel expectedTopRight = new Pixel(0, 0, 0);
    Pixel expectedMiddleLeft = new Pixel(0, 9, 0);
    Pixel expectedMiddleRight = new Pixel(0, 0, 9);
    Pixel expectedBottomLeft = new Pixel(255, 0, 0);
    Pixel expectedBottomRight = new Pixel(0, 255, 0);

    Pixel[][] expectedPixelArray = new Pixel[3][2];
    expectedPixelArray[0][0] = expectedTopLeft;
    expectedPixelArray[0][1] = expectedTopRight;
    expectedPixelArray[1][0] = expectedMiddleLeft;
    expectedPixelArray[1][1] = expectedMiddleRight;
    expectedPixelArray[2][0] = expectedBottomLeft;
    expectedPixelArray[2][1] = expectedBottomRight;

    Picture expectedPic = new Picture(expectedPixelArray, 2, 3, 255,
            "ppm", BufferedImage.TYPE_INT_ARGB);

    assertEquals(expectedPic, p1.flip(Orientation.Vertical));
    assertNotEquals(expectedPic.toPPM(), p1.toPPM());
  }

  @Test
  public void testFlipHorizontal() {
    init();
    p1.flip(Orientation.Horizontal);

    Pixel expectedTopLeft = new Pixel(0, 255, 0);
    Pixel expectedTopRight = new Pixel(255, 0, 0);
    Pixel expectedMiddleLeft = new Pixel(0, 0, 9);
    Pixel expectedMiddleRight = new Pixel(0, 9, 0);
    Pixel expectedBottomLeft = new Pixel(0, 0, 0);
    Pixel expectedBottomRight = new Pixel(0, 0, 255);

    Pixel[][] expectedPixelArray = new Pixel[3][2];
    expectedPixelArray[0][0] = expectedTopLeft;
    expectedPixelArray[0][1] = expectedTopRight;
    expectedPixelArray[1][0] = expectedMiddleLeft;
    expectedPixelArray[1][1] = expectedMiddleRight;
    expectedPixelArray[2][0] = expectedBottomLeft;
    expectedPixelArray[2][1] = expectedBottomRight;

    Picture expectedPic = new Picture(expectedPixelArray, 2, 3, 255,
            "ppm", BufferedImage.TYPE_INT_ARGB);

    assertEquals(expectedPic, p1.flip(Orientation.Horizontal));
    assertNotEquals(expectedPic.toPPM(), p1.toPPM());
  }

  @Test
  public void testBrighten() {
    init();
    p1.brighten(50);

    Pixel expectedTopLeft = new Pixel(255, 50, 50);
    Pixel expectedTopRight = new Pixel(50, 255, 50);
    Pixel expectedMiddleLeft = new Pixel(50, 59, 50);
    Pixel expectedMiddleRight = new Pixel(50, 50, 59);
    Pixel expectedBottomLeft = new Pixel(50, 50, 255);
    Pixel expectedBottomRight = new Pixel(50, 50, 50);

    Pixel[][] expectedPixelArray = new Pixel[3][2];
    expectedPixelArray[0][0] = expectedTopLeft;
    expectedPixelArray[0][1] = expectedTopRight;
    expectedPixelArray[1][0] = expectedMiddleLeft;
    expectedPixelArray[1][1] = expectedMiddleRight;
    expectedPixelArray[2][0] = expectedBottomLeft;
    expectedPixelArray[2][1] = expectedBottomRight;

    Picture expectedPic = new Picture(expectedPixelArray, 2, 3, 255,
            "ppm", BufferedImage.TYPE_INT_ARGB);

    assertEquals(expectedPic, p1.brighten(50));
    assertNotEquals(expectedPic, p1);
  }

}
