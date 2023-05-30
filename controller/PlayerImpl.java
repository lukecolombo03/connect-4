package all.controller;

import java.util.Objects;

import all.Piece;
import all.model.IModel;
import all.strategy.IStrategy;
import all.strategy.UserInput;

/**
 * A player can be a human or AI. It needs a piece (color), and a strategy. For humans,
 * the 'strategy' is just asking for input from System.in
 *
 * This class delegates most of its functionality to the Strategy interface
 */
public class PlayerImpl implements Player {
  private final Piece piece;
  private IStrategy strat;

  public PlayerImpl(Piece piece, IStrategy strat) {
    if (piece == null) {
      throw new IllegalArgumentException("Piece cannot be null");
    }
    if (strat == null) {
      throw new IllegalArgumentException("Strategy cannot be null");
    }
    this.piece = piece;
    this.strat = strat;
  }


  @Override
  public int play(IModel model) {
    return this.strat.choosePosn(model, this.piece);
  }

  @Override
  public Piece getPiece() {
    return this.piece;
  }

  @Override
  public boolean isBot() {
    //This player is a bot if their strategy does NOT equal a new UserInput()
    //for all the IStrategy classes I made the equals method just check for same class, since every
    // instance of a certain class should be the same (UserInput is the only one that has fields)
    return !this.strat.equals(new UserInput());
  }
}
