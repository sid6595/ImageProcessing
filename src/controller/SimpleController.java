package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import controller.commands.Blur;
import controller.commands.Greyscaling;
import controller.commands.SepiaTone;
import enumpackage.Brightness;
import enumpackage.Component;
import enumpackage.Orientation;
import controller.commands.Brighten;
import controller.commands.Flip;
import controller.commands.Load;
import controller.commands.Save;
import controller.commands.VisRGB;
import controller.commands.VisIntensity;
import model.ImageProcessorModel;
import model.ModelImpl;
import view.ImageTextView;
import view.TextViewInterface;

/**
 * This class is the controller where all the commands are stored and where everything
 * can be run.
 */
public class SimpleController {
  ImageProcessorModel model;
  TextViewInterface view;
  private final Readable rd;

  /**
   * This creates the constructor with 3 parameters
   * @param m the model used in the class
   * @param v the view that the user sees
   * @param r where the user inputs are handled
   */
  // TODO make tests for controller
  // bmp, jpg and png
  public SimpleController(ImageProcessorModel m, TextViewInterface v, Readable r) {
    if (m == null || r == null || v == null) {
      throw new IllegalArgumentException("An argument in the controller is null.");
    }
    this.model = m;
    this.view = v;
    this.rd = r;
  }

  /**
   * This quits the processor on the spot.
   *
   * @throws IOException when the data transmission fails
   */
  private void quit() throws IOException {
    view.renderMessage("Successfully quit image processor.");
  }

  /**
   * This constructor takes in a string from the user.
   *
   */
  public void execute() throws IllegalArgumentException {
    Scanner scan = new Scanner(this.rd);
    Stack<FunctionObjects> commands = new Stack<>();

    Map<String, Function<Scanner, FunctionObjects>> knownCommands = new HashMap<>();
    knownCommands.put("brighten", (Scanner s) -> new Brighten(s.nextInt()));
    knownCommands.put("vertical-flip", (Scanner s) -> new Flip(Orientation.Vertical));
    knownCommands.put("horizontal-flip", (Scanner s) -> new Flip(Orientation.Horizontal));
    knownCommands.put("value-component", (Scanner s) -> new VisIntensity(Brightness.Value));
    knownCommands.put("intensity-component", (Scanner s) -> new VisIntensity(Brightness.Intensity));
    knownCommands.put("luma-component", (Scanner s) -> new VisIntensity(Brightness.Luma));
    knownCommands.put("red-component", (Scanner s) -> new VisRGB(Component.Red));
    knownCommands.put("green-component", (Scanner s) -> new VisRGB(Component.Green));
    knownCommands.put("blue-component", (Scanner s) -> new VisRGB(Component.Blue));
    knownCommands.put("blur", (Scanner s) -> new Blur());
    knownCommands.put("greyscale", (Scanner s) -> new Greyscaling());
    knownCommands.put("sepia", (Scanner s) -> new SepiaTone());
    knownCommands.put("save", (Scanner s) -> new Save());
    knownCommands.put("load", (Scanner s) -> new Load());

    while (scan.hasNext()) {
      FunctionObjects c;
      String in = scan.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        try {
          quit();
          break;
        } catch (IOException e) {
          throw new IllegalStateException("Quit failed");
        }
      }
      Function<Scanner, FunctionObjects> cmd = knownCommands.getOrDefault(in, null);
      if (cmd == null) {
        throw new IllegalArgumentException("Valid command not given");
      } else {
        c = cmd.apply(scan);
        String input = scan.next();
        String saveName = scan.next();
        commands.add(c);
        c.apply(this.model, input, saveName);
      }
    }
  }
}
