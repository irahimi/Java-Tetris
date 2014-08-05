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

public class TetrisPanel extends JPanel {
  private static final long serialVersionUID = 0L;

  public final static int BLOCK_SIZE = 32;

  private Tetris tetris;

  private boolean font_fallback = false;

  public TetrisPanel(Tetris initTetris) {
    tetris = initTetris;
  }

  public Dimension getPreferredSize() {
    return new Dimension(TetrisBoard.WIDTH * BLOCK_SIZE, (TetrisBoard.HEIGHT - 2) * BLOCK_SIZE);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    /* Game Over Screen */
    if (tetris.isGameOver()) {
      for (int y = 2; y < TetrisBoard.HEIGHT; y++) {
        for (int x = 0; x < TetrisBoard.WIDTH; x++) {
          Tetromino.Type type = tetris.getBoard().get(x, y);
          Color color = TetrisBoard.colors[type.getValue()];
          TetrominoBlock.instance()
            .getIconForColor(color)
            .paintIcon(this, g, BLOCK_SIZE * x, BLOCK_SIZE * (y - 2));
        }
      }
      g.setColor(Color.white);
      g.setFont(new Font("Arial", Font.BOLD, 60));
      g.drawString("Game", TetrisBoard.WIDTH * BLOCK_SIZE / 2 - 50, TetrisBoard.HEIGHT * BLOCK_SIZE / 2 - 100);
      g.drawString("Over", TetrisBoard.WIDTH * BLOCK_SIZE / 2 - 45, TetrisBoard.HEIGHT * BLOCK_SIZE / 2 - 50);
    } else {
      g.setColor(Color.black);
      g.fillRect(0, 0, TetrisBoard.WIDTH * BLOCK_SIZE, (TetrisBoard.HEIGHT - 2) * BLOCK_SIZE);

      /* Piece Ghosting */
      if (tetris.getActiveTetromino() != null && !tetris.getActiveTetromino().isLocked() && !tetris.getActiveTetromino().blockedBelow(tetris.getBoard())) {
        Tetromino ghost = new Tetromino(tetris.getActiveTetromino().getType(), tetris.getActiveTetromino().getRotation(), tetris.getActiveTetromino().getPoint());
        while (ghost.inBounds() && ghost.inFreeSpace(tetris.getBoard())) {//(ghost.intersects(tetris.getActiveTetromino()) ? true: ghost.inFreeSpace(tetris.getBoard()))) {
          ghost.setPoint(ghost.getPoint().add(new TetrisPoint(0, 1)));
        }

        ghost.setPoint(ghost.getPoint().add(new TetrisPoint(0, -1)));

        for (TetrisPoint mino : ghost.getBlocks()) {
          TetrominoBlock.instance()
            .getIconForColor(Color.gray.brighter())
            .paintIcon(this, g, BLOCK_SIZE * mino.add(ghost.getPoint()).getX(), BLOCK_SIZE * (mino.add(ghost.getPoint()).getY() - 2));
        }
      }

      /* Regular Piece Drawing */
      for (int y = 2; y < TetrisBoard.HEIGHT; y++) {
        for (int x = 0; x < TetrisBoard.WIDTH; x++) {
          Tetromino.Type type = tetris.getBoard().get(x, y);
          Color color = tetris.getBoard().colors[type.getValue()];
          if (type != Tetromino.Type.X) {
            TetrominoBlock.instance().getIconForColor(color).paintIcon(this, g, BLOCK_SIZE * x, BLOCK_SIZE * (y - 2));
          }
        }
      }

      Tetromino active_tetromino = tetris.getActiveTetromino();
      if (active_tetromino == null) { return; } // Just getting up and running now
      for (TetrisPoint mino : active_tetromino.getBlocks()) {
        int x = mino.getX() + active_tetromino.getPoint().getX();
        int y = mino.getY() + active_tetromino.getPoint().getY();
        Color color = tetris.getBoard().colors[active_tetromino.getType().getValue()];
        TetrominoBlock.instance().getIconForColor(color).paintIcon(this, g, BLOCK_SIZE * x, BLOCK_SIZE * (y - 2));
      }

      g.setFont(new Font("Courier", Font.BOLD, 10));
      g.setColor(Color.red);
      g.drawString(String.format("%2.2f", tetris.getFramerate()), 10, 10);
    }
  }
}
