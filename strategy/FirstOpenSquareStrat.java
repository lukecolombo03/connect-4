package all.strategy;

import all.Piece;
import all.model.IModel;

public class FirstOpenSquareStrat implements IStrategy {

  public FirstOpenSquareStrat() {
    //no fields
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    else return (this.getClass() == o.getClass());
  }

  @Override
  public Integer choosePosn(IModel model, Piece player) {
    for (int i = model.getHeight() - 1; i > 0; i--) {
      for (int j = 0; j < model.getWidth(); j++) {
        if (model.getPieceAt(i, j) != Piece.EMPTY) {
          if (model.getPieceAt(0, j) != Piece.EMPTY) return j + 2;
          return j + 1;
        }
      }
    }
    return null;
  }
}
