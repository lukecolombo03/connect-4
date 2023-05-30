package all.view;

import java.io.IOException;

/**
 * Represents a everything.view or display for this game. It gets information from the everything.controller and
 * translates that into a user-friendly visual.
 */
public interface IView {

  /**
   * Render the board, transmitting it to the output.
   * @throws IOException if transmission of the board fails.
   */
  void renderBoard() throws IOException;

  /**
   * Transmit a message to the output.
   * @param message a string message.
   * @throws IOException if transmission of the message fails.
   */
  void renderMessage(String message) throws IOException;
}
