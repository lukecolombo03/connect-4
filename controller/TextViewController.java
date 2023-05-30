package all.controller;

import java.io.IOException;
import java.util.ArrayList;

import all.Piece;
import all.Status;
import all.model.IModel;
import all.view.IView;

/**
 * Implementation of the controller for a text view.
 */
public class TextViewController {
  private final IView view;
  private final IModel model;
  private final Readable input;
  private final ArrayList<Player> players;
  private int playerIndex;

  public TextViewController(IView view, IModel model, Readable input) {
    if (view == null) {
      throw new IllegalArgumentException("Null view");
    }
    if (model == null) {
      throw new IllegalArgumentException("Null model");
    }
    this.view = view;
    this.model = model;
    this.input = input;
    this.players = new ArrayList<>();
  }

  /**
   * Initializes the players (could be humans or AI strategies).
   * @param players some number of Player objects.
   */
  public void addPlayers(Player... players) {
    for (Player p : players) {
      this.players.add(p);
    }
  }

  public void playGame() {
    this.playerIndex = 0;
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("IO error");
    }
    while (model.getGameStatus() == Status.PLAYING) {
      System.out.println("playerIndex = " + playerIndex);
      //get info on current player and where they want to play, then pass info to the model
      Piece currentPlayer = this.players.get(playerIndex).getPiece();
      int pos = this.players.get(playerIndex).play(model);
      System.out.println("placed at: " + pos);
      try {
        this.model.place(currentPlayer.toString(), pos);
        this.playerIndex = (playerIndex + 1) % this.players.size();
      } catch (Exception e) {
        System.out.println("There was an error");
      }
      try {
        this.view.renderBoard();
      } catch (IOException e) {
        throw new IllegalStateException("IO error");
      }
      System.out.println("\n");
      if (model.getGameStatus() != Status.PLAYING) {
        break;
      }
    }
    this.renderMessageHelper("Game over!\n");
    switch(this.model.getWinner()) {
      case YELLOW:
        this.renderMessageHelper("Player Yellow is the winner!\n");
        break;
      case RED:
        this.renderMessageHelper("Player Red is the winner!\n");
        break;
      case EMPTY: // this means it's a tie (idk how this could happen but I added it as a possibility)
        this.renderMessageHelper("It's a tie!\n");
        break;
    }
  }

  private void renderMessageHelper(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Bruh 💀");
    }
  }


  //the help message that's gonna display when you type help
  private void helpMessage() {
    this.renderMessageHelper("How to play:\n" + "Type place, then which color you want to play as, " +
            "then which column you want to place your piece at. \n(The leftmost column is column 1.) " +
            "Right now, the available colors are red and yellow.\n");
  }



}


