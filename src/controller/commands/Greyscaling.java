package controller.commands;

import controller.FunctionObjects;
import model.ImageProcessorModel;
import model.Picture;

public class Greyscaling implements FunctionObjects {

  double[][] greyScale = {
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722}
  };

  @Override
  public void apply(ImageProcessorModel m, String oldName, String newName) {
    Picture oldImage = m.getPicture(oldName);
    m.addPicture(oldImage.colorTransformation(greyScale), newName);
  }
}