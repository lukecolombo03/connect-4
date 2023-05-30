package all.controller;

import java.util.ArrayList;

import all.Utils;
import all.model.IModel;
import all.view.GUIView;

public class SwingViewController implements Features {
  private final GUIView view;
  private final IModel model;
  private final ArrayList<Player> players;
  private int playerIndex;

  public SwingViewController(GUIView view, IModel model) {
    if (view == null) {
      throw new IllegalArgumentException("View is null");
    }
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    this.view = view;
    this.model = model;
    this.players = new ArrayList<>();
    this.playerIndex = 0;
  }

  /**
   * Initializes the players (could be humans or AI strategies).
   *
   * @param players some number of Player objects.
   */
  public void addPlayers(Player... players) {
    for (Player p : players) {
      this.players.add(p);
    }
    this.view.changeTurnIndicator(this.players.get(playerIndex).getPiece().toString() + "'s turn");
  }


  @Override
  public void place(int col) {
    Player currentPlayer = this.players.get(playerIndex);
    System.out.printf("is current player %s a bot?: %s %n",
            currentPlayer.getPiece(), currentPlayer.isBot());
    Integer whereToPlay = null;
    try {
      if (currentPlayer.isBot()) {
        whereToPlay = currentPlayer.play(model);
      } else { //if it's not a bot, just place at whichever column was clicked
        whereToPlay = col;
      }
      this.model.place(currentPlayer.getPiece().toString(), whereToPlay);
      this.playerIndex = (playerIndex + 1) % players.size();
      this.view.changeTurnIndicator(currentPlayer.getPiece().toString() + "'s turn");
      this.view.refresh();
    } catch (Exception e) {
      if (e.toString().contains("Coordinate out of bounds")) {
        this.view.renderMessage(new Utils().formatException(e), false);
      } else {
        String msg = new Utils().formatException(e);
        this.view.renderMessage(msg, true);
      }
    }
  }


}
