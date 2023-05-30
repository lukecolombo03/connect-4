package all.model;

import java.util.HashMap;

import all.FullColumnException;
import all.Piece;
import all.Status;

/**
 * Model implementation for a 2-player ConnectN. Board dimensions and goal (how many pieces to
 * connect in order to win) are arbitrary.
 */
public class ConnectNModel implements IModel {
  private final int height;
  private final int width;
  private final int goal;
  public Piece[][] board;
  private Piece winner;
  private Status gameState;
  //make a who's turn variable for the View

  /**
   * A normal game of connect 4
   */
  public ConnectNModel() {
    this(6, 7, 4);
  }

  /**
   * Connect 4, but with a customizable size.
   *
   * @param height
   * @param width
   */
  public ConnectNModel(int height, int width) {
    this(height, width, 4);
  }

  /**
   * 6x7 board, but with a customizable board
   *
   * @param goal
   */
  public ConnectNModel(int goal) {
    this(6, 7, goal);
  }

  public ConnectNModel(int height, int width, int goal) {
    if (goal > height && goal > width)
      throw new IllegalArgumentException("With current height, width, and goal, " +
              "this game is unwinnable.");
    if (height < 1 || width < 1 || goal < 1) {
      throw new IllegalArgumentException("Width, height, and goal cannot be zero or negative");
    }
    this.height = height;
    this.width = width;
    this.goal = goal;
    this.winner = null;
    this.gameState = Status.PLAYING;

    this.board = new Piece[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        this.board[row][col] = Piece.EMPTY;
      }
    }
  }


  /**
   * Actually places a piece on the board by determining which player's piece it is (red or yellow),
   * then putting it in the correct spot. Gives the user feedback if they didn't do the syntax right.
   *
   * @param piece which piece to place.
   * @param row   row coordinate.
   * @param col   column coordinate.
   */
  private void placeHelper(String piece, int row, int col) {
    if (piece.equalsIgnoreCase("red")) {
      this.board[row][col] = Piece.RED;
    } else if (piece.equalsIgnoreCase("yellow")) {
      this.board[row][col] = Piece.YELLOW;
    } else {
      throw new IllegalArgumentException("That piece is not recognized");
    }
  }


  /**
   * Reverse for loop, starts at the bottom of a certain column and counts up. Once it gets to an
   * empty slot, it places the piece there. This simulates actual connect four where you can't just
   * place a piece at any row, it has to be on top of another piece (or on the very bottom).
   * <p>
   * BTW column indexes at 1 to make it user-friendly!
   *
   * @param piece which piece to place, inputted as a String to make it easier with the command pattern.
   * @param col   which column to place a piece on.
   */
  @Override
  public void place(String piece, int col) {
    if (col < 1 || col > this.getWidth()) {
      throw new IllegalArgumentException("Coordinate out of bounds. " +
              "Must be between 1 and " + this.width);
    }
    else if (this.getPieceAt(0, col - 1) != Piece.EMPTY) {
      throw new FullColumnException("That column is full");
    }
    else {
      for (int i = this.height - 1; i > -1; i--) { //loops from the bottom up
        if (this.getPieceAt(i, col - 1) == Piece.EMPTY) {
          this.placeHelper(piece, i, col - 1); //actually place the piece
          break;
        }
      }
    }
    this.updateWinner();
  }


  @Override
  public Piece getWinner() {
    if (this.gameState == Status.PLAYING) {
      throw new IllegalStateException("Game still playing!");
    }
    return this.winner;
  }

  /**
   * I just stole this from the tictactoe lecture code but future me should remember how it works
   *
   * @param row row coordinate
   * @param col column coordinate
   * @param dr  delta in rows
   * @param dc  delta in columns.
   */
  private void checkWinnerHelper(int row, int col, int dr, int dc) {
    Piece current = null;
    int count = 0;
    while (row >= 0 && row < this.height && col >= 0 && col < this.width) {
      if (this.board[row][col] == current) { //if this piece is the same color (player) as the last,
        count++;                             //then increase the count
      } else {                               //otherwise, start counting the other color
        count = 1;
        current = this.board[row][col];
      }
      //if we've reached the goal, this means someone won!
      if (current != null && current != Piece.EMPTY && count == this.goal) {
        this.gameState = Status.WON;
        this.winner = current;
        return;
      }
      row += dr;  //increase the row by the desired amount (delta row)
      col += dc;  //increase the column by the desired amount (delta col)
    }

  }

  private void updateWinner() {
    // check for win in rows
    for (int r = 0; r < this.height; r++) {
      checkWinnerHelper(r, 0, 0, 1);
    }
    // check for win in cols
    for (int c = 0; c < this.width; c++) {
      checkWinnerHelper(0, c, 1, 0);
    }
    // check for win on diagonals
    for (int r = 0; r < this.height; r++) {
      checkWinnerHelper(r, 0, -1, 1); // to upper-right, from left column
      checkWinnerHelper(r, 0, 1, 1); // to lower-right, from left column
    }
    for (int c = 0; c < this.width; c++) {
      checkWinnerHelper(this.height - 1, c, -1, 1); // to upper-right, from bottom row
      checkWinnerHelper(0, c, 1, 1); // to lower-right, from top row
    }

    //check if the game is a tie-if there's no possible way either player can get to 4
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getWinningScore() {
    return this.goal;
  }

  @Override
  public Piece getPieceAt(int row, int col) {
    if (row < 0 || row >= this.height)
      throw new IllegalArgumentException("Invalid row: " + row);
    if (col < 0 || col >= this.width)
      throw new IllegalArgumentException("Invalid col: " + col);
    return this.board[row][col];
  }


  @Override
  public Status getGameStatus() {
    try {
      this.getWinner();
    } catch (IllegalStateException e) {
      return Status.PLAYING;
    }
    switch (this.getWinner()) {
      case EMPTY:
        return Status.TIED;
      case RED:
      case YELLOW:
        return Status.WON;
      default:
        return Status.PLAYING;
    }
  }

}
