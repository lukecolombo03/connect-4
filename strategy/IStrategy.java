package all.strategy;

import all.Piece;
import all.controller.Posn;
import all.model.IModel;

/**
 * A Strategy interface for choosing where to play next for the given player
 */
public interface IStrategy {

  /**
   * Returns an int representing which column this player will place its piece at. Technically it
   * returns an Integer but that's only because we need it to be null in some cases.
   * @param model
   * @param player
   * @return
   */
  Integer choosePosn(IModel model, Piece player);
}
