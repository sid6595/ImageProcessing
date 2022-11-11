package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.FunctionObjects;
import model.ImageProcessorModel;
//import model.ModelImpl;

/**
 * This class will save the image with the name given to it from the
 * path including the name of the file.
 */
public class Save implements FunctionObjects {


  /**
   * Apply will take in the location and the name of the file and store the file in the supplied
   * location and with the supplied name.
   *
   * @param m            This is the location where all the images are stored.
   * @param saveLocation This is the location that the new image is stored.
   * @param imageName    This is the name of the image being saved.
   */
  public void apply(ImageProcessorModel m, String saveLocation, String imageName) {

    try {
      // retrieve image
      BufferedImage bi = m.getPicture(imageName).toBufferedImage();
      String format = m.getPicture(imageName).getFormat();
      File outputFile = new File("res/" + saveLocation);
      ImageIO.write(bi, format, outputFile);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed save");
    }


  }

}
