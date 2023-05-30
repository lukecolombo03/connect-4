package all.model;

import all.Piece;
import all.model.ICommand;
import all.model.IModel;

/**
 * Function object to perform a move on the board by placing one piece in a certain column.
 */
public class Place implements ICommand {
  private final int col;
  private final String piece;

  public Place(String piece, int col) {
    this.piece = piece;
    this.col = col;
  }

  @Override
  public void apply(IModel m) {
    m.place(piece, col);
  }

}
