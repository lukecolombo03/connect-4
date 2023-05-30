package all.strategy;

import java.util.Random;

import all.Piece;
import all.model.IModel;

public class RandomBot implements IStrategy {

  public RandomBot() {
    //no fields
  }

  @Override
  public Integer choosePosn(IModel model, Piece player) {
    return new Random().nextInt(7) + 1;
  }
}