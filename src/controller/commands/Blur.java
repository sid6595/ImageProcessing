package controller.commands;

import controller.FunctionObjects;
import model.ImageProcessorModel;
import model.Picture;
/**
 * This holds the kernel to blur an image.
 */
public class Blur implements FunctionObjects {

  double[][] blur = {
          {1 / 16, 1 / 8, 1 / 16},
          {1 / 8, 1 / 4, 1 / 8},
          {1 / 16, 1 / 8, 1 / 16}
  };

  @Override
  public void apply(ImageProcessorModel m, String oldName, String newName) {
    Picture oldImage = m.getPicture(oldName);
    m.addPicture(oldImage.colorTransformation(blur), newName);
  }
}
