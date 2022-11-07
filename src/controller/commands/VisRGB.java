package controller.commands;

import enumpackage.Component;
import controller.FunctionObjects;
import model.ImageProcessorModel;
import model.Picture;

/**
 * This class will create a new image based on the visuals of each individual
 * RGB value.
 */
public class VisRGB implements FunctionObjects {

  Component rgb;

  public VisRGB(Component rgb) {
    this.rgb = rgb;
  }

  /**
   * Apply will take in the processor model, old name of the image and the new name of an image,
   * then create the new image based off the visual intensity.
   *
   * @param m       This is the location where all the images are stored.
   * @param oldName This is the old name of the supplied image.
   * @param newName This is the new name of the image.
   */
  @Override
  public void apply(ImageProcessorModel m, String oldName, String newName) {
    Picture oldImage = m.getPicture(oldName);
    m.addPicture(oldImage.visRGB(this.rgb), newName);
  }
}
