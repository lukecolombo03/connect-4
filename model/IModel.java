package all.model;

import all.Piece;

/**
 * Offers all the functionality of a connect N everything.model.
 */
public interface IModel extends IModelState {

  /**
   * Place a piece on the board. Pieces stack just like in real Connect Four, so if there is another
   * piece under this one, it will be placed on top of that one.
   * @param col which column to place a piece on.
   * @param piece which piece to place, inputted as a String to make it easier with the command pattern.
   */
  void place(String piece, int col);

}
