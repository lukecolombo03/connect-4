package all.strategy;

import java.util.ArrayList;

import all.Piece;
import all.model.IModel;

public class TryStrategies implements IStrategy {
  private ArrayList<IStrategy> listOfStrategies;

  public TryStrategies(IStrategy... strategies) {
    for (IStrategy s : strategies) {
      this.listOfStrategies.add(s);
    }
    if (this.listOfStrategies.size() < 1) {
      throw new IllegalArgumentException("Must have at least one strategy!");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    else return (this.getClass() == o.getClass());
  }

  @Override
  public Integer choosePosn(IModel model, Piece player) {
    Integer chosenCol = null;
    for (IStrategy s : listOfStrategies) {
      //return the first strategy's choice, if that's null, then go to the next
      chosenCol = s.choosePosn(model, player);
      if (chosenCol != null) return chosenCol;
    }
    //what to do if they're all null?
    return chosenCol;
  }
}
