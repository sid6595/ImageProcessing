package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
  @Override
  public void loadImage(String path, String imageName) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream("res/" + path));
    } catch (FileNotFoundException e) {
      System.out.println("File " + path + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    Pixel[][] allPixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        allPixels[i][j] = new Pixel(r, g, b);
      }
    }
    if (imageName.length() == 0) {
      throw new IllegalArgumentException("No image name given");
    }
    catalog.put(imageName, new Picture(allPixels, width, height, maxValue));

  }
}

