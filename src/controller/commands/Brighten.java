package controller.commands;

import controller.FunctionObjects;
import model.ImageProcessorModel;
//import model.ModelImpl;
import model.Picture;

/**
 * This class brightens the supplied image.
 */
public class Brighten implements FunctionObjects {

  int inc;

  public Brighten(int inc) {
    this.inc = inc;
  }

  /**
   * Apply takes in the supplied image and applies brighten, then returns the
   * new image.
   */
  @Override
  public void apply(ImageProcessorModel m, String oldName, String newName) {
    Picture oldImage = m.getPicture(oldName);
    m.addPicture(oldImage.brighten(this.inc), newName);
  }
}
