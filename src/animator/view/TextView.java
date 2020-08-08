package cs3500.animator.view;

import java.io.IOException;

/**
 * Class to represent Textual Views which are views that are able to generate the text description
 * of an IModel Animation to be outputted/printed into the console for viewing the animation
 * textually.
 */
public class TextView extends AbstractView {

  @Override
  public void printTextView(String textDescription, Appendable a) throws IOException {
    try {
      a.append(textDescription);
    } catch (IOException e) {
      throw new IOException("Unable to output text view to appendable");
    }
  }
}



