package controller.commands;

import controller.FunctionObjects;
import model.ImageProcessorModel;
import model.Picture;

public class Sharpen implements FunctionObjects {

  double[][] sharpen = {
          {-1 / 8, -1 / 8, -1 / 8, -1 / 8, -1 / 8},
          {-1 / 8, 1 / 4, 1 / 4, 1 / 4, -1 / 8},
          {-1 / 8, 1 / 4, 1, 1 / 4, -1 / 8},
          {-1 / 8, 1 / 4, 1 / 4, 1 / 4, -1 / 8},
          {-1 / 8, -1 / 8, -1 / 8, -1 / 8, -1 / 8}
  };

  @Override
  public void apply(ImageProcessorModel m, String oldName, String newName) {
    Picture oldImage = m.getPicture(oldName);
    m.addPicture(oldImage.colorTransformation(sharpen), newName);
  }
}