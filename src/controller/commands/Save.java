package controller.commands;

import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Objects;

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

    String content = m.getPicture(imageName).toPPM();

    try {
      fw = new FileWriter("res/" + saveLocation);
      fw.write(content);
      fw.close();
    } catch (Exception e) {
      throw new IllegalArgumentException("Something occurred when making fileWriter.");
    }


  }

}
