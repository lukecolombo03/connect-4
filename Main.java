package all;

import java.io.IOException;

import all.controller.Player;
import all.controller.PlayerImpl;
import all.controller.SwingViewController;
import all.model.ConnectNModel;
import all.model.IModel;
import all.strategy.FirstOpenSquareStrat;
import all.strategy.RandomBot;
import all.strategy.UserInput;
import all.view.GUIView;
import all.view.GUIViewImpl;

public final class Main {
  public static void main(String... args) throws IOException {
    IModel model = new ConnectNModel();
    GUIView view = new GUIViewImpl(model);
    SwingViewController controller = new SwingViewController(view, model);
    view.setUpFeatures(controller);
//    TextViewController controller = new TextViewController(view, model,
//            new InputStreamReader(System.in));

    Player humanRed = new PlayerImpl(Piece.RED, new UserInput());
    Player humanYel = new PlayerImpl(Piece.YELLOW, new UserInput());
    Player botYel = new PlayerImpl(Piece.YELLOW, new FirstOpenSquareStrat());
    Player botRed = new PlayerImpl(Piece.RED, new FirstOpenSquareStrat());
    Player randomBotYel = new PlayerImpl(Piece.RED, new RandomBot());
    controller.addPlayers(humanRed, botYel);


  }

}
