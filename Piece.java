package all;

/**
 * All possible pieces. Connect 4 has two types: red and yellow
 */
public enum Piece {
  RED, YELLOW, EMPTY;

  @Override
  public String toString() {
    if (this == RED) {
      return "Red";
    } else if (this == YELLOW) {
      return "Yellow";
    } else {
      return "Empty";
    }
  }

}
