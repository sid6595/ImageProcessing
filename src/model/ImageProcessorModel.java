package model;

/**
 * This interface represents the location where images will be stored.
 */
public interface ImageProcessorModel {
  /**
   * This method will grab an image from the folder and add it to the folder.
   *
   * @param p This argument is the source path of the image.
   * @param n This argument is the name of the image.
   */
  void loadImage(String p, String n);

  /**
   * This will get the picture with the provided name from the catalog.
   *
   * @param name This is the name of the picture being grabbed.
   * @return This returns a picture.
   */
  Picture getPicture(String name);


  /**
   * This method will add a picture to the catalog.
   *
   * @param p This argument is the picture being added.
   * @param s This argument is the name of the picture.
   */
  void addPicture(Picture p, String s);

}
