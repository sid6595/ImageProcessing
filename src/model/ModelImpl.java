package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * This class is where the loaded images are store and where the new images will be stored.
 */
public class ModelImpl implements ImageProcessorModel {

  private final Map<String, Picture> catalog;

  // TODO what to put in constructor?

  /**
   * This constructor takes no arguments and catalogues a new hashmap representing an image.
   */
  public ModelImpl() {
    this.catalog = new HashMap<String, Picture>();
  }

  /**
   * This will get the picture with the provided name from the catalog.
   *
   * @param name This is the name of the picture being grabbed.
   * @return This returns a picture.
   */
  public Picture getPicture(String name) {
    return catalog.get(name);
  }

  /**
   * This method will add a picture to the catalog.
   *
   * @param p This argument is the picture being added.
   * @param s This argument is the name of the picture.
   */
  public void addPicture(Picture p, String s) {
    catalog.put(s, p);
  }

  /**
   * This method will grab an image from the folder and add it to the folder.
   *
   * @param path      This argument is the source path of the image.
   * @param imageName This argument is the name of the image.
   */
  public void loadImage(String path, String imageName) {
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      System.out.println("File " + path + " not found");
    }

    int height = img.getHeight();
    int width = img.getWidth();
    int maxValue = 255;


    Pixel[][] allPixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int color = img.getRGB(j, i);
        int blue = color & 0xff;
        int green = (color & 0xff00) >> 8;
        int red = (color & 0xff0000) >> 16;
        allPixels[i][j] = new Pixel(red, green, blue);
      }
    }

    if (imageName.length() == 0) {
      throw new IllegalArgumentException("No image name given");
    }
    catalog.put(imageName, new Picture(allPixels, width, height, maxValue));
  }

}

