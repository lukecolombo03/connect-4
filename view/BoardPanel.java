package all.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import all.controller.Features;
import all.model.IModelState;

public class BoardPanel extends JPanel {
  public final int cellDimension;
  private final IModelState model;
  public int originX, originY;
  private Dimension dimension;
  private Features feature;
  private static final int holeSpace = 5; //the space between the holes in the board
  private final int gridSquare;


  public BoardPanel(IModelState model, int cellDimension) {
    super();
    this.cellDimension = cellDimension;
    this.model = model;
    //450x400 for a normal board
    this.dimension = new Dimension((this.model.getWidth() + 2) * cellDimension,
            (this.model.getHeight() + 2) * cellDimension);
    this.setPreferredSize(this.dimension);
    this.originX = (int) this.getPreferredSize().getWidth() / 2 -
            this.model.getWidth() * cellDimension / 2;
    this.originY = (int) this.getPreferredSize().getHeight() / 2 -
            this.model.getHeight() * cellDimension / 2;
    //if you were to divide the whole board panel into squares, this would be the size of one
    this.gridSquare = cellDimension + holeSpace;

  }

  @Override
  public void paintComponent(Graphics g) {
    int boardMargin = 25; //the margin between the holes and the edge of the board

    //this makes the blue background
    g.setColor(Color.BLUE);
    g.fillRect(originX - boardMargin / 2, originY - boardMargin / 2,
            (this.model.getWidth() * (cellDimension + holeSpace) - holeSpace) + boardMargin,
            (this.model.getHeight() * (cellDimension + holeSpace) - holeSpace) + boardMargin);

    //this fills in the holes where the pieces go
    g.setColor(Color.WHITE);
    int h = (int) this.dimension.getHeight() - cellDimension;
    int w = (int) this.dimension.getWidth() - cellDimension;
    for (int down = originY; down < h; down += cellDimension + holeSpace) {
      for (int across = originX; across < w; across += cellDimension + holeSpace) {
        switch (this.model.getPieceAt(convertViewToModel(down, true),
                convertViewToModel(across, false))) {
          //use lambda here
          case EMPTY:
            g.setColor(Color.WHITE);
            g.fillOval(across, down, cellDimension, cellDimension);
            break;
          case YELLOW:
            g.setColor(Color.YELLOW);
            g.fillOval(across, down, cellDimension, cellDimension);
            break;
          case RED:
            g.setColor(Color.RED);
            g.fillOval(across, down, cellDimension, cellDimension);
            break;
        }
      }
    }
  }


  /**
   * This takes in a coordinate on the model's board (usually a 6x7), and outputs the corresponding
   * coordinate on this BoardPanel.
   * @param coord
   * @param isX true if it's an X coordinate, false if it's a Y coordinate
   * @return
   */
  public int convertModelToView(int coord, boolean isX) {
    if (isX) {
      return coord * gridSquare + originX;
    } else {
      return coord * gridSquare + originY;
    }
  }

  /**
   * This takes in a coordinate on this BoardPanel, and outputs the corresponding coordinate on
   * the model's board
   * @param coord
   * @param isX true if it's an X coordinate, false if it's a Y coordinate
   * @return
   */
  public int convertViewToModel(int coord, boolean isX) {
    if (isX) {
      return (coord - originX) / gridSquare;
    } else {
      return (coord - originY) / gridSquare;
    }
  }


  public void addFeatures(Features feature) {
    this.feature = feature;
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int col = convertViewToModel(e.getX(), true);
        feature.place(col + 1);
      }
    });
  }

}
