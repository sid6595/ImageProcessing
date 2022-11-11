package controller.commands;

import controller.FunctionObjects;
import model.ImageProcessorModel;
import model.Picture;

/**
 * This applies a sepia to the supplied image.
 */
public class SepiaTone implements FunctionObjects {

  double[][] sepia = {
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}
  };

  /**
   * Apply takes in the supplied image and applies sepia, then returns the
   * new image.
   *
   * @param m       This is the location where all the images are stored.
   * @param oldName This is the old name of the supplied image.
   * @param newName This is the new name of the image.
   */
  @Override
  public void apply(ImageProcessorModel m, String oldName, String newName) {
    Picture oldImage = m.getPicture(oldName);
    m.addPicture(oldImage.colorTransformation(sepia), newName);
  }
}