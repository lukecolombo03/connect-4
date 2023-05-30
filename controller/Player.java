package all.controller;

import all.Piece;
import all.model.IModel;

public interface Player {
  //the return type here has to match the return type of Strategy.choosePosn()

  /**
   * Which column does this player want to place their piece at?
   * @param model the current model
   * @return an int representing which column to play at
   */
  int play(IModel model);

  /**
   *
   * @return which piece (color) this player is playing as.
   */
  Piece getPiece();

  boolean isBot();
}
