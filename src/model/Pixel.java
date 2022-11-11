package model;

import java.awt.*;

import enumpackage.Brightness;
import enumpackage.Component;

//TODO make pixel interface

/**
 * This class represents a single pixel in an image.
 */
public class Pixel {
  private int r;
  private int g;
  private int b;

  /**
   * This constructor takes in rgb values and checks to see if they are in bounds.
   *
   * @param red   The value of red color.
   * @param green The value of green color.
   * @param blue  The value of blue color.
   */
  public Pixel(int red, int green, int blue) {
    this.r = red;
    this.g = green;
    this.b = blue;

    if (red < 0) {
      this.r = 0;
    }
    if (red > 255) {
      this.r = 255;
    }
    if (green < 0) {
      this.g = 0;
    }
    if (green > 255) {
      this.g = 255;
    }
    if (blue < 0) {
      this.b = 0;
    }
    if (blue > 255) {
      this.b = 255;
    }

  }

  /**
   * This turns the pixel into an rgb value that can be used by bufferedImage.
   * @return
   */
  public int toRGB() {
//    System.out.println("Red: " + this.r + "; green: " + this.g + "; blue: " + this.b);
    Color color = new Color(this.r, this.g, this.b);
    return color.getRGB();
  }

  /**
   * This method makes a copy of a pixel.
   *
   * @return This method gives back a pixel.
   */
  public Pixel makeCopy() {
    return new Pixel(this.r, this.g, this.b);
  }

  /**
   * This represents the r value of the pixel.
   *
   * @return This method will give access to the r value of the pixel.
   */
  public int getR() {
    return this.r;
  }

  /**
   * This represents the g value of the pixel.
   *
   * @return This method will give access to the g value of the pixel.
   */
  public int getG() {
    return this.g;
  }

  /**
   * This represents the b value of the pixel.
   *
   * @return This method will give access to the b value of the pixel.
   */
  public int getB() {
    return this.b;
  }

  /**
   * This represents the max value of rgb.
   *
   * @return This returns an int.
   */
  public int value() {
    return Math.max((Math.max(r, g)), b);
  }

  /**
   * This method represents the intensity of the pixel.
   *
   * @return This returns an int.
   */
  public int intensity() {
    // need an integer so rounding should provide a reasonable output
    return Math.round((r + g + b) / 3);
  }

  /**
   * This returns the weighted sum of Luma.
   *
   * @return This returns an int.
   */
  public int luma() {
    // TODO ask if casting is allowed
    // luma double gets rounded to a long and then to an integer
    return Math.toIntExact(Math.round(0.2126 * r + 0.7152 * g + 0.0722 * b));
  }

  /**
   * This method turns an image to greyscaled image.
   *
   * @param c This argument is the greyscale component.
   */
  public void greyscaleColor(Component c) {
    switch (c) {
      case Red: {
        this.b = this.r;
        this.g = this.r;
        break;
      }
      case Green: {
        this.r = this.g;
        this.b = this.g;
        break;
      }
      case Blue: {
        this.r = this.b;
        this.g = this.b;
        break;
      }
      default: {
        throw new IllegalArgumentException("Invalid rgb component given.");
      }
    }
  }

  /**
   * This method changes the brighness of the greyscaled pixel.
   *
   * @param b This is the brightness of the pixel.
   */
  public void greyscaleBrightness(Brightness b) {
    int futureFields = 0;

    // TODO why isn't future fields assigned (difference between : and -> syntax)
    switch (b) {
      case Value:
        futureFields = this.value();
        break;
      case Intensity:
        futureFields = this.intensity();
        break;
      case Luma:
        futureFields = this.luma();
        break;
      default:
        throw new IllegalArgumentException("Invalid brightness component given.");
    }

    this.r = futureFields;
    this.g = futureFields;
    this.b = futureFields;
  }

  /**
   * This method brighens a pixel.
   *
   * @param inc      This is the amount to increment by.
   * @param maxValue This is the max value allowed.
   */
  public void brighten(int inc, int maxValue) {
    this.r = brightenHelper(this.r, inc, maxValue);
    this.g = brightenHelper(this.g, inc, maxValue);
    this.b = brightenHelper(this.b, inc, maxValue);
  }

  /**
   * This method helps with the brighten method.
   *
   * @param rgbVal    This is the rgb value of the pixel.
   * @param increment This is the amount to increment.
   * @param maxValue  This is the max value allowed.
   * @return This returns an int.
   */
  public int brightenHelper(int rgbVal, int increment, int maxValue) {
    if (rgbVal + increment > maxValue) {
      return maxValue;
    }
    if (rgbVal + increment < 0) {
      return 0;
    } else {
      return rgbVal + increment;
    }
  }

  /**
   * This method checks to see if an object is equal to another object.
   *
   * @param o This is the other object being compared.
   * @return This returns a boolean.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    return this.hashCode() == o.hashCode();
  }

  /**
   * This calculates the hash code of the image.
   *
   * @return This returns an int.
   */
  @Override
  public int hashCode() {
    return Integer.toString(this.r).hashCode() + Integer.toString(this.g).hashCode()
            + Integer.toString(this.b).hashCode();
  }

}
