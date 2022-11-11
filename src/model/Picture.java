package model;

import java.awt.image.BufferedImage;
import java.util.Objects;

import enumpackage.Brightness;
import enumpackage.Component;
import enumpackage.Orientation;

// TODO ask if image needs to be tested

/**
 * This class represents an image and contains all the methods to modify that image.
 */
public class Picture {
  private final Pixel[][] picture;
  private final int width;
  private final int height;
  //TODO What is max used for?
  private final int maxValue;
  private final String format;
  private final int type;

  /**
   * This constructor takes in the picture as well as its width, height and max value.
   *
   * @param p        This argument represents the array of pixels that make up the picture.
   * @param width    This argument is the width of the image.
   * @param height   This argument is the height of the image.
   * @param maxValue This argument is the maximum rgb value that the image can have.
   */
  public Picture(Pixel[][] p, int width, int height, int maxValue, String format, int type) {
    if (width < 0 || height < 0 || maxValue < 0) {
      throw new IllegalArgumentException("Value less than zero given.");
    }
    if (format == null) {
      throw new IllegalArgumentException("No format given");
    }
    // i -> width   ||    j -> height
    this.picture = Objects.requireNonNull(p);
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.format = format;
    this.type = type;
  }

  /**
   * This method makes a copy of the supplied picture.
   *
   * @return This method returns an image.
   */
  public Pixel[][] makeCopy() {
    Pixel[][] copy = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        copy[i][j] = this.picture[i][j].makeCopy();
      }
    }
    return copy;
  }

  public String getFormat() {
    return this.format;
  }

  /**
   * This method visualizes the individual rgp components.
   *
   * @param c This argument represents which rgb value you are choosing.
   * @return This method returns an image.
   */
  public Picture visRGB(Component c) {
    Picture applied = new Picture(this.makeCopy(), this.width, this.height, this.maxValue, this.format, this.type);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        applied.picture[i][j].greyscaleColor(c);
      }
    }
    return applied;
  }

  /**
   * This class will create a new image based on the visuals of each individual
   * intensity value.
   *
   * @param b This argument is the brightness of the image.
   * @return This method returns an image.
   */
  public Picture visIntensity(Brightness b) {
    Picture applied = new Picture(this.makeCopy(), this.width, this.height, this.maxValue, this.format, this.type);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        applied.picture[i][j].greyscaleBrightness(b);
      }
    }
    return applied;
  }

  /**
   * This method converts the image to a string with all the data of a PPM file.
   *
   * @return This method returns a string.
   */
  public String toPPM() {

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    builder.append("P3" + System.lineSeparator());
    builder.append(this.width + " " + this.height + System.lineSeparator());
    builder.append(this.maxValue + System.lineSeparator());

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        try {
          builder.append((this.picture[i][j].getR()) + System.lineSeparator());
          builder.append((this.picture[i][j].getG()) + System.lineSeparator());
          builder.append((this.picture[i][j].getB()) + System.lineSeparator());
        } catch (ArrayIndexOutOfBoundsException a) {
          throw new IllegalArgumentException("i:" + i + "j:" + j);
        }
      }
    }

    return builder.toString();
  }

  public BufferedImage toBufferedImage()  {
    BufferedImage formal = new BufferedImage(this.width, this.height, this.type);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        formal.setRGB(j, i, picture[i][j].toRGB());
      }
    }
    return formal;
  }

  /**
   * This method flips the supplied picture whichever way is orientation dictates.
   *
   * @param o This argument is the direction the image will be flipped.
   * @return This method returns a picture after it has been flipped.
   */
  public Picture flip(Orientation o) {
    Picture applied = new Picture(this.makeCopy(), this.width, this.height, this.maxValue, this.format, this.type);

    Pixel[][] result = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (o.equals(Orientation.Vertical)) {
          result[i][j] = applied.picture[height - i - 1][j];
        } else if (o.equals(Orientation.Horizontal)) {
          result[i][j] = applied.picture[i][width - j - 1];
        } else {
          throw new IllegalArgumentException("Something wrong with orientation.");
        }
      }
    }
    return new Picture(result, this.width, this.height, this.maxValue, this.format, this.type);
  }

  /**
   * This method makes an image brighter by whatever the supplied increment is.
   *
   * @param increment This argument is how much the image will be brightened by.
   * @return This method returns an image that has been brightened.
   */
  public Picture brighten(int increment) {
    Picture applied = new Picture(this.makeCopy(), this.width, this.height, this.maxValue, this.format, this.type);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        applied.picture[i][j].brighten(increment, maxValue);
      }
    }
    return applied;
  }

  /**
   * This method checks to see if an object is equal to another object.
   *
   * @param o This argument is the other object being compared.
   * @return This method returns a boolean.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    return this.hashCode() == o.hashCode();
  }

  /**
   * This method calculates the hash code of the image.
   *
   * @return This method returns an int.
   */
  @Override
  public int hashCode() {
    int result = 0;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        result += this.picture[i][j].hashCode();
      }
    }

    int rest = Integer.toString(this.height).hashCode() + Integer.toString(this.width).hashCode()
            + Integer.toString(this.maxValue).hashCode();
    return rest + result;
  }

  /**
   * This will change the image it is applied to by applying the kernel to the rgb values
   * of the pixels.
   * @param kernel  This is the matrix that will change the image.
   * @return This will return the blurred image.
   */
  public Picture colorTransformation(double[][] kernel) {

    int newR;
    int newG;
    int newB;
/*
    double[][] blur = {
            {1 / 16, 1 / 8, 1 / 16},
            {1 / 8, 1 / 4, 1 / 8},
            {1 / 16, 1 / 8, 1 / 16}
    };
 */

    Picture applied = new Picture(this.makeCopy(), this.width, this.height, this.maxValue, this.format, this.type);
    for (int i = 0; i < applied.height; i++) {
      for (int j = 0; j < applied.width; j++) {
        //this grabs the individual picture pixel
        newR = (int) (applied.picture[i][j].getR() * kernel[0][0] + applied.picture[i][j].getG()
                * kernel[0][1] + applied.picture[i][j].getB() * kernel[0][2]);
        newG = (int) (applied.picture[i][j].getR() * kernel[1][0] + applied.picture[i][j].getG()
                * kernel[1][1] + applied.picture[i][j].getB() * kernel[2][2]);
        newB = (int) (applied.picture[i][j].getR() * kernel[2][0] + applied.picture[i][j].getG()
                * kernel[2][1] + applied.picture[i][j].getB() * kernel[2][2]);

        applied.picture[i][j] = new Pixel(newR, newG, newB);
      }
    }

    return new Picture(applied.picture, this.width, this.height, this.maxValue, this.format, this.type);
  }

  /**
   * This will filter through the image and change the rgb value on the pixel.
   * @param kernel  This is the matrix that will change the image.
   * @return This will return the new picture.
   */
  public Picture filter(double[][] kernel) {
    int newR;
    int newG;
    int newB;

    Picture applied = new Picture(this.makeCopy(), this.width, this.height, this.maxValue, this.format, this.type);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int a = 0; a < kernel.length; a++) {
          for (int b = 0; b < kernel[0].length; b++) {
            if (i >= applied.height / 2 && i >= applied.width
                    && j >= applied.height && j >= applied.width) {
              newR = (int) (applied.picture[i][j].getR() * kernel[a][b] + applied.picture[i][j].getG()
                      * kernel[a][b + 1] + applied.picture[i][j].getB() * kernel[a][b + 2]);
              newG = (int) (applied.picture[i][j].getR() * kernel[a + 1][b] + applied.picture[i][j].getG()
                      * kernel[a + 1][b + 1] + applied.picture[i][j].getB() * kernel[a + 1][b + 2]);
              newB = (int) (applied.picture[i][j].getR() * kernel[a + 2][b] + applied.picture[i][j].getG()
                      * kernel[a + 2][b + 1] + applied.picture[i][j].getB() * kernel[a + 2][b + 2]);

              applied.picture[i][j] = new Pixel(newR, newG, newB);
            }
          }
        }
      }
    }
    return applied;

  }


}
