package controller.commands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.IOException;
//import java.util.Objects;

import javax.imageio.ImageIO;

import controller.FunctionObjects;
import model.ImageProcessorModel;
//import model.ModelImpl;

/**
 * This class will save the image with the name given to it from the
 * path including the name of the file.
 */
public class Save implements FunctionObjects {


  // public Save() {  }


  /**
   * Apply will take in the location and the name of the file and store the file in the supplied
   * location and with the supplied name.
   *
   * @param m            This is the location where all the images are stored.
   * @param saveLocation This is the location that the new image is stored.
   * @param imageName    This is the name of the image being saved.
   */
  public void apply(ImageProcessorModel m, String saveLocation, String imageName) {
    FileWriter fw;
//
//    String content = m.getPicture(imageName).toPPM();
//
//    try {
//      fw = new FileWriter("res/" + saveLocation);
//      fw.write(content);
//      fw.close();
//    } catch (Exception e) {
//      throw new IllegalArgumentException("Something occurred when making fileWriter.");
//    }

    try {
      // retrieve image
      BufferedImage bi = m.getPicture(imageName).toBufferedImage();
      String format = m.getPicture("res/" + imageName).getFormat();
      File outputFile = new File(saveLocation);
      ImageIO.write(bi, format, outputFile);
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed save");
    }


  }

}
