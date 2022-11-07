import org.junit.Before;
import org.junit.Test;

import enumpackage.Brightness;
import enumpackage.Component;
import model.ImageProcessorModel;
import model.ModelImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * This is the tester for the pixels methods.
 */
public class PixelTest {

  ModelImpl model;
  ImageProcessorModel model2;
  Pixel p;

  @Before
  public void init() {
    this.p = new Pixel(10,20,30);
  }

  @Test
  public void testValue() {
    init();
    assertEquals(30, p.value());
  }

  @Test
  public void testIntensity() {
    init();
    assertEquals(20, p.intensity());
  }

  @Test
  public void testLuma() {
    init();
    assertEquals(19, p.luma());
  }

  @Test
  public void testVisColor() {
    init();
    p.greyscaleColor(Component.Red);
    Pixel expected = new Pixel(10,10,10);
    assertEquals(expected, this.p);
  }

  @Test
  public void testVisBrightness() {
    init();
    p.greyscaleBrightness(Brightness.Value);
    Pixel expected = new Pixel(30,30,30);
    assertEquals(expected, this.p);

  }

  @Test
  public void testBrighten() {
    init();
    p.brighten(20,255);
    Pixel expected = new Pixel(30,40,50);
    assertEquals(expected, this.p);
  }




}
