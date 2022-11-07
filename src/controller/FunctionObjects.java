package controller;

//import java.util.Map;

import model.ImageProcessorModel;
//import model.ModelImpl;

/**
 * This interface represents commands that will be given to images.
 */
public interface FunctionObjects {

  /**
   * This represents the command being applied onto an image.
   *
   * @param m       This is the location where all the images are stored.
   * @param oldName This is the old name of the supplied image.
   * @param newName This is the new name of the image.
   */
  void apply(ImageProcessorModel m, String oldName, String newName);
}
