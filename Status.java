package all;

/**
 * All possible game statuses.
 */
public enum Status {
  PLAYING, WON, TIED;

  @Override
  public String toString() {
    if (this == PLAYING) {
      return "playing";
    } else if (this == WON) {
      return "won";
    } else {
      return "tied";
    }
  }
}
