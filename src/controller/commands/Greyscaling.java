package controller.commands;

import controller.FunctionObjects;
import model.ImageProcessorModel;
import model.Picture;

/**
 * This class provides a greyscale to the provided image.
 */
public class Greyscaling implements FunctionObjects {

  double[][] greyScale = {
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722}
  };

  /**
   * Apply takes in the supplied image and applies greyscale, then returns the
   * new image.
   *
   * @param m       This is the location where all the images are stored.
   * @param oldName This is the old name of the supplied image.
   * @param newName This is the new name of the image.
   */
  @Override
  public void apply(ImageProcessorModel m, String oldName, String newName) {
    Picture oldImage = m.getPicture(oldName);
    m.addPicture(oldImage.colorTransformation(greyScale), newName);
  }
}