import org.junit.Test;

import java.io.InputStreamReader;
import java.io.StringReader;

import controller.ImageController;
import controller.SimpleController;
import model.ImageProcessorModel;
import model.ModelImpl;
import view.ImageTextView;
import view.TextViewInterface;

import static org.junit.Assert.assertEquals;

public class SimpleControllerTest {
  ImageProcessorModel model = new ModelImpl();
  Appendable out = new StringBuilder();
  Readable rd = new StringReader("q");
  TextViewInterface view = new ImageTextView(model, out);
  SimpleController controller = new SimpleController(model, view, rd);

  /**
   * This method lets me run my game and play it.
   *
   * @param args the java syntax
   */
  public static void main(String[] args) {
    ImageProcessorModel modelExample = new ModelImpl();
    TextViewInterface viewExample = new ImageTextView(modelExample, System.out);
    Readable rdExample = new InputStreamReader(System.in);

    SimpleController trial = new SimpleController(modelExample, viewExample,
            rdExample);
    trial.execute();
  }

  //Sets objects to initial values.
  private void init() {
    ImageProcessorModel model = new ModelImpl();
    Appendable out = new StringBuilder();

    TextViewInterface view = new ImageTextView(model, out);
  }

  @Test
  public void testTwoInvalidValue() {
    init();
    Readable rd = new StringReader("load butterfly.jpg butterfly");
    SimpleController controller = new SimpleController(model, view, rd);
    controller.execute();
    assertEquals("",model.getPicture("butterfly").toString());
  }
}
