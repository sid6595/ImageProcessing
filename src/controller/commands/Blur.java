package controller.commands;

import controller.FunctionObjects;
import model.ImageProcessorModel;
import model.Picture;

/**
 * This class blurs the supplied image.
 */
public class Blur implements FunctionObjects {

  double[][] blur = {
          {1 / 16, 1 / 8, 1 / 16},
          {1 / 8, 1 / 4, 1 / 8},
          {1 / 16, 1 / 8, 1 / 16}
  };

  /**
   * Apply takes in the supplied image and applies blur, then returns the
   * new image.
   *
   * @param m       This is the location where all the images are stored.
   * @param oldName This is the old name of the supplied image.
   * @param newName This is the new name of the image.
   */
  @Override
  public void apply(ImageProcessorModel m, String oldName, String newName) {
    Picture oldImage = m.getPicture(oldName);
    m.addPicture(oldImage.colorTransformation(blur), newName);
  }
}
