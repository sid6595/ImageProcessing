package controller.commands;

import controller.FunctionObjects;
import model.ImageProcessorModel;
import model.Picture;

/**
 * This holds the kernel to sharpen an image more into focus.
 */
public class Sharpen implements FunctionObjects {

  double[][] sharpen = {
          {-1 / 8, -1 / 8, -1 / 8, -1 / 8, -1 / 8},
          {-1 / 8, 1 / 4, 1 / 4, 1 / 4, -1 / 8},
          {-1 / 8, 1 / 4, 1, 1 / 4, -1 / 8},
          {-1 / 8, 1 / 4, 1 / 4, 1 / 4, -1 / 8},
          {-1 / 8, -1 / 8, -1 / 8, -1 / 8, -1 / 8}
  };

  /**
   * Apply takes in the supplied image and applies sharpen, then returns the
   * new image.
   */
  @Override
  public void apply(ImageProcessorModel m, String oldName, String newName) {
    Picture oldImage = m.getPicture(oldName);
    m.addPicture(oldImage.colorTransformation(sharpen), newName);
  }
}