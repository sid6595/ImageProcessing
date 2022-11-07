package view;

import java.io.IOException;

import model.ImageProcessorModel;

/**
 * This is the Marble Solitaire Text view.
 */
public class ImageTextView implements TextViewInterface {
  private final ImageProcessorModel imageprocessor;
  private final Appendable destination;

  /**
   * This is the constructor for the view. It takes in a MarbleSolitaire and checks if it's null.
   *
   * @param imageprocessor the MarbleSolitaireModelState
   * @throws IllegalArgumentException if the state of the input parameter is null
   */
  public ImageTextView(ImageProcessorModel imageprocessor)
          throws IllegalArgumentException {
    if (imageprocessor == null) {
      throw new IllegalArgumentException("The board is null. Not good. Very bad.");
    }
    this.imageprocessor = imageprocessor;
    this.destination = System.out;
  }

  /**
   * This constructor for the view is with the view as well as an appendable.
   *
   * @param imageprocessor the MarbleSolitaireModelState
   * @param destination    where the outputs are going
   * @throws IllegalArgumentException when one of the inputs is null
   */
  public ImageTextView(ImageProcessorModel imageprocessor, Appendable destination)
          throws IllegalArgumentException {
    if (imageprocessor == null || destination == null) {
      throw new IllegalArgumentException("One of the inputs in null.");
    }
    this.imageprocessor = imageprocessor;
    this.destination = destination;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      destination.append(message);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
