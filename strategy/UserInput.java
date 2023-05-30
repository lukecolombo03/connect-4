package all.strategy;

import java.util.Objects;
import java.util.Scanner;

import all.Piece;
import all.model.ConnectNModel;
import all.model.IModel;

/**
 * Technically a 'strategy', this just asks the user for input
 */
public class UserInput implements IStrategy {
  private final Scanner input;


  /**
   * Use this constructor
   */
  public UserInput() {
    this.input = new Scanner(System.in);
  }

  @Override
  public Integer choosePosn(IModel model, Piece player) {
    System.out.println(player.toString() + "'s turn.");
    System.out.println("Type which column to place your piece:");
    if (input.hasNextInt()) {
      return input.nextInt();
    } else {
      throw new IllegalStateException("Please type a number");
    }
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    return (this.getClass() == o.getClass());
  }

  @Override
  public int hashCode() {
    return Objects.hash(input);
  }
}
