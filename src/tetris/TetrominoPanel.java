/*
 * Ian Rahimi
 * Mr. Segall
 * AP Computer Science
 * 06/08/14
 */

package tetris;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class TetrominoPanel extends JPanel {
  private static final long serialVersionUID = 0L;

  public static final int BLOCK_SIZE = TetrisPanel.BLOCK_SIZE / 2;

  public static final int WIDTH  = 4 * BLOCK_SIZE;
  public static final int HEIGHT  = 4 * BLOCK_SIZE;

  private Tetris tetris;
  private Tetromino.Type type;

  public TetrominoPanel(Tetris initTetris, Tetromino.Type initType) {
    this.tetris = initTetris;
    this.type = initType;
  }

  public Tetromino.Type getType() {
    return this.type;
  }

  public void setType(Tetromino.Type type) {
    this.type = type;
  }

  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.black);
    g.fillRect(0, 0, WIDTH, HEIGHT);
    if (this.type == Tetromino.Type.X) return;
    for (TetrisPoint point : new Tetromino(type).getBlocks()) {
      Color color = TetrisBoard.colors[type.getValue()];
      g.setColor(color);
      g.fillRect(point.getX() * BLOCK_SIZE, point.getY() * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
    }
  }
}
