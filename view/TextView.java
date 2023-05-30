package all.view;

import java.io.IOException;

import all.Piece;
import all.model.IModel;

/**
 * A text view. Displays the board as a String in the console.
 */
public class TextView implements IView {
  private final Appendable output;
  private final IModel model;

  public TextView(IModel model) {
    this.model = model;
    this.output = System.out;
  }

  public TextView(IModel model, Appendable output) {
    this.model = model;
    this.output = output;
  }

  @Override
  public void renderBoard() throws IOException {
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {
        if (col == model.getWidth() - 1) {
          this.renderHelper(this.model.getPieceAt(row, col), "X", "O", "_");
        } else {
          this.renderHelper(this.model.getPieceAt(row, col), "X ", "O ", "_ ");
        }
      }
      output.append("\n");
    }
  }

  private void renderHelper(Piece p, String red, String yel, String empty) throws IOException {
    switch (p) {
      case RED:
        output.append(red);
        break;
      case YELLOW:
        output.append(yel);
        break;
      case EMPTY:
        output.append(empty);
        break;
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    output.append(message);
  }
}
