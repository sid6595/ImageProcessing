package controller.commands;

import controller.FunctionObjects;
import model.ImageProcessorModel;
import model.Picture;

/**
 * This holds the kernel to turn an image into an older looking image.
 */
public class SepiaTone implements FunctionObjects {

  double[][] sepia = {
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}
  };

  @Override
  public void apply(ImageProcessorModel m, String oldName, String newName) {
    Picture oldImage = m.getPicture(oldName);
    m.addPicture(oldImage.colorTransformation(sepia), newName);
  }
}