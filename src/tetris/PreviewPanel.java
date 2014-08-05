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
import java.util.Stack;

public class PreviewPanel extends JPanel {
  private static final long serialVersionUID = 0L;

  public static final int PREVIEW = 3;

  public static final int WIDTH  = 4 * TetrominoPanel.BLOCK_SIZE;
  public static final int HEIGHT = PREVIEW * 4 * TetrominoPanel.BLOCK_SIZE;

  private TetrominoPanel[] panels;


  private Tetris tetris;

  public PreviewPanel(Tetris initTetris) {
    tetris = initTetris;
    panels = new TetrominoPanel[PREVIEW];
    for (int i = 0; i < PREVIEW; i++) {
      panels[i] = new TetrominoPanel(initTetris, Tetromino.Type.X);
      this.add(panels[i]);
    }
  }

  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    TetrominoRandom rng = tetris.getRNG();
    for (int i = 0; i < PREVIEW; i++) {
      panels[i].setType(rng.elementAt(rng.size() - (i + 1)));
    }
  }
}
