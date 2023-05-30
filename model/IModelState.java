package all.model;

import all.Piece;
import all.Status;

/**
 * Represents operations to monitor the state of the board, without mutating it.
 */
public interface IModelState {
  /**
   * Get the height of the board.
   * @return board height.
   */
  int getHeight();

  /**
   * Get the width of the board.
   * @return board width.
   */
  int getWidth();

  /**
   * Get the winning score - how many pieces do you have to connect in order to win the game.
   * @return winning score.
   */
  int getWinningScore();

  /**
   * Get the status of a certain coordinate on the board. It's either empty, a red piece, or a
   * yellow piece (for now, the game is only two players).
   * @param row row coordinate of the board.
   * @param col column coordinate of the board.
   * @return a Piece enum representing the status of that coordinate.
   */
  Piece getPieceAt(int row, int col);

  /**
   * Get which player won the game. It returns yellow or red if either of those won the game, and
   * empty if the game ended in a tie.
   * @return who won the game.
   */
  Piece getWinner();

  /**
   * Get the status of the game - is it still in progress, did someone win, or did it end in a tie.
   * @return a status enum.
   */
  Status getGameStatus();

}
