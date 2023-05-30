package all.view;

import java.awt.*;

import javax.swing.*;

import all.Status;
import all.controller.Features;
import all.model.IModelState;

/**
 * A GUI using the Swing library.
 */
public class GUIViewImpl extends JFrame implements GUIView {
  private final IModelState model;
  private BoardPanel boardPanel;
  private JLabel turnIndicator; //tells the user whose turn it is
  //have the winner message be a popup
  private Dimension frameDimension;
  private static final int cellDimension = 50;
  private JLabel littleMessage;

  public GUIViewImpl(IModelState model) {
    super();
    this.model = model;
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //set up dimensions, location, etc

    this.frameDimension = new Dimension((this.model.getWidth() + 3) * cellDimension,
            (this.model.getHeight() + 4) * cellDimension);
    this.setPreferredSize(frameDimension);
    Rectangle test = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    int originX = ((test.x + test.width) - this.frameDimension.width) / 2;
    int originY = ((test.y + test.height) - this.frameDimension.height) / 2;
    this.setLocation(originX, originY);


    this.setSize(frameDimension);
    this.setLayout(new BorderLayout());

    this.setResizable(true); //maybe change?


    //bottom panel
    JPanel bottom = new JPanel();
    bottom.setLayout(new GridLayout(1, 0, 0, 0)); //figure out the arguments in grid layout
    bottom.setVisible(true);
    turnIndicator = new JLabel();
    bottom.add(turnIndicator);
    littleMessage = new JLabel();
    bottom.add(littleMessage);
    this.add(bottom, BorderLayout.SOUTH);


    //center panel
    boardPanel = new BoardPanel(model, cellDimension);
    this.add(boardPanel, BorderLayout.CENTER);

    this.pack();
    this.setVisible(true);
    this.refresh();
  }




  @Override
  public void refresh() {
    this.repaint();
    if (model.getGameStatus() == Status.WON) {
      this.renderMessage("Game over!\nWinner: " + model.getWinner(), true);
    }
  }

  @Override
  public void renderMessage(String message, boolean bigOrLittle) {
    if (bigOrLittle) {
      JOptionPane.showMessageDialog(this, message);
    }
    else {
      littleMessage.setText(message);
    }
  }

  @Override
  public void setUpFeatures(Features features) {
    this.boardPanel.addFeatures(features);
  }

  @Override
  public void changeTurnIndicator(String message) {
    this.turnIndicator.setText(message);
  }


}
