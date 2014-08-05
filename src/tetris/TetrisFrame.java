/*
 * Ian Rahimi
 * Mr. Segall
 * AP Computer Science
 * 5/23/14
 */

package tetris;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TetrisFrame extends JFrame implements KeyListener {
  private static final long serialVersionUID = 0L;

  private Tetris tetris;

  public TetrisFrame(Tetris initTetris, String title) {
    super(title);
    setResizable(false);
    this.addKeyListener(this);
    tetris = initTetris;
  }

  public void keyPressed(KeyEvent event) {
    if (tetris.isGameOver()) return;
    int keyCode = event.getKeyCode();
    switch (keyCode) {
      case KeyEvent.VK_LEFT:  if (tetris.isRunning() && !tetris.getActiveTetromino().isLocked())  tetris.getActiveTetromino().shift(tetris.getBoard(), new TetrisPoint(-1, 0)); break;
      case KeyEvent.VK_RIGHT: if (tetris.isRunning() && !tetris.getActiveTetromino().isLocked())  tetris.getActiveTetromino().shift(tetris.getBoard(), new TetrisPoint(1, 0)); break;
      case KeyEvent.VK_UP:    if (tetris.isRunning() && !tetris.getActiveTetromino().isLocked())  tetris.getActiveTetromino().hardDrop(tetris.getBoard()); break;
      case KeyEvent.VK_DOWN:  if (tetris.isRunning() && !tetris.getActiveTetromino().isLocked())  tetris.getActiveTetromino().shift(tetris.getBoard(), new TetrisPoint(0, 1)); break;
      case KeyEvent.VK_Z:     if (tetris.isRunning() && !tetris.getActiveTetromino().isLocked())  tetris.getActiveTetromino().rotateCounterClockwise(tetris.getBoard()); break;
      case KeyEvent.VK_X:     if (tetris.isRunning() && !tetris.getActiveTetromino().isLocked())  tetris.getActiveTetromino().rotateClockwise(tetris.getBoard()); break;
      case KeyEvent.VK_C:     if (tetris.isRunning() && !tetris.getActiveTetromino().isLocking()) tetris.holdPieceAction(); break;
      case KeyEvent.VK_P: tetris.toggleRunning(); break;
    }
    repaint();
  }

  public void keyTyped(KeyEvent event) {
  }

  public void keyReleased(KeyEvent event) {
  }
}
