package controller.commands;

//import java.io.FileWriter;
//import java.util.Objects;

import controller.FunctionObjects;
import model.ImageProcessorModel;
//import model.ModelImpl;
//import model.Picture;

/**
 * This class will load an image from a specified path
 * and reference it in the program by the name given to it.
 */
public class Load implements FunctionObjects {

  // public Load() {  }


  /**
   * Apply will take in the processor model, save location and the name of the image, then
   * grab and load the image based on the name.
   *
   * @param m            This is the location where all the images are stored.
   * @param saveLocation This is the location that the image is stored.
   * @param imageName    This is the name of the image being loaded.
   */
  public void apply(ImageProcessorModel m, String saveLocation, String imageName) {
    m.loadImage(saveLocation, imageName);
  }

}
