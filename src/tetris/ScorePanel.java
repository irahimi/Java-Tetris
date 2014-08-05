/*
 * Ian Rahimi
 * Mr. Segall
 * AP Computer Science
 * 5/23/14
 */

package tetris;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class ScorePanel extends JPanel {
  private static final long serialVersionUID = 0L;

  public static final int WIDTH  = 100;
  public static final int HEIGHT = 250;

  private Tetris tetris;

  private TetrominoPanel hold_panel;

  public ScorePanel(Tetris initTetris) {
    tetris = initTetris;
  }

  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawString(String.format("Lines: %d", tetris.getLines()), this.getSize().width / 2 - 32, 40);
  }
}
