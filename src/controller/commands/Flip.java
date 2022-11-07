package controller.commands;

import enumpackage.Orientation;
import controller.FunctionObjects;
import model.ImageProcessorModel;
import model.Picture;

/**
 * This class will flip the supplied image either horizontally or vertically.
 */
public class Flip implements FunctionObjects {

  Orientation o;

  public Flip(Orientation o) {
    this.o = o;
  }

  /**
   * Apply takes in the supplied image and applies Flip to it, then returns the
   * new image.
   */
  @Override
  public void apply(ImageProcessorModel m, String oldName, String newName) {
    Picture oldImage = m.getPicture(oldName);
    m.addPicture(oldImage.flip(this.o), newName);
  }
}