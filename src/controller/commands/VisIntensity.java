package controller.commands;

import enumpackage.Brightness;
import controller.FunctionObjects;
import model.ImageProcessorModel;
import model.Picture;

/**
 * This class will create an image that visualizes the value of the rgb
 * of the image.
 */
public class VisIntensity implements FunctionObjects {

  Brightness b;

  public VisIntensity(Brightness b) {
    this.b = b;
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
    m.addPicture(oldImage.visIntensity(this.b), newName);
  }
}
