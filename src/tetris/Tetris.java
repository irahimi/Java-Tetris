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

public class Tetris {

  public static final boolean use_block_texture = true;

  private Timer timer;

  private TetrominoRandom rng;

  private TetrisBoard board;
  private Tetromino activeTetromino;

  private boolean running;
  private boolean game_over;
  private boolean firstFrame;
  private long lastTime;
  private int frameAverageCounter;
  private double framerate;

  private int lines;
  private int level;
  private int score;
  private boolean locked;

  public int dropCounter;
  public int lastClearedCounter;

  private Tetromino.Type hold;

  private ScorePanel score_panel;
  private TetrominoPanel hold_panel;
  private PreviewPanel preview_panel;

  public Tetris() {
    rng = new TetrominoRandom();
    board = new TetrisBoard();
    running = true;
    game_over = false;
    this.timer = null;
    firstFrame = true;
    lines = 0;
    locked = false;
    dropCounter = 1;
    lastClearedCounter = 1;
    level = 1;
    hold = null;
    score = 0;
  }

  public static void main(String[] args) {
    final Tetris game = new Tetris();
    final TetrisFrame frame = game.createMainWindow();

    final ActionListener ticker = new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        game.tick(frame);
      }
    };

    final Timer timer = new Timer(16, ticker);
    game.setTimer(timer);
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        timer.start();
      }
    });
  }

  private void setTimer(Timer timer) {
    this.timer = timer;
  }

  private TetrisFrame createMainWindow() {
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

    JPanel sidePanel = new JPanel();
    sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));

    mainPanel.add(new TetrisPanel(this));

    sidePanel.add(this.score_panel = new ScorePanel(this));
    sidePanel.add(this.hold_panel = new TetrominoPanel(this, Tetromino.Type.X));
    sidePanel.add(this.preview_panel = new PreviewPanel(this));

    mainPanel.add(sidePanel);

    TetrisFrame frame = new TetrisFrame(this, "Tetris");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(mainPanel);
    frame.pack();
    frame.setVisible(true);
    return frame;
  }

  public TetrisBoard getBoard() {
    return board;
  }

  public int getLines() {
    return lines;
  }

  public Tetromino getActiveTetromino() {
    return activeTetromino;
  }

  public double getFramerate() {
    return framerate;
  }

  public boolean isGameOver() {
    return this.game_over;
  }

  public boolean isRunning() {
    return this.running;
  }

  public void toggleRunning() {
    this.running = !this.running;
  }

  public boolean activateNextTetromino() {
    activeTetromino = new Tetromino(this.rng.get());
    return activeTetromino.inFreeSpace(this.board);
  }

  public TetrominoRandom getRNG() {
    return rng;
  }

  public boolean holdingPiece() {
    return this.hold != null;
  }

  public Tetromino.Type getHoldPiece() {
    return this.hold;
  }

  public void holdPieceAction() {
    //this.activeTetromino.removeFrom(this.board);
    Tetromino.Type tmp  = this.activeTetromino.getType();
    this.activeTetromino = this.hold == null ? new Tetromino(this.rng.get()): new Tetromino(this.hold);
    this.hold = tmp;
    //this.activeTetromino.addTo(this.board);
  }

  public void tick(JFrame frame) {
    if (game_over) {
      board.resetBoard();
      this.timer.stop();
      activeTetromino = null;
      frame.repaint();
      return;
    }

    if (this.running) {
      if (firstFrame) {
        lastTime = System.nanoTime();
        frameAverageCounter = 1;
        firstFrame = false;
        this.activateNextTetromino();
        frame.repaint();
      }

      this.hold_panel.setType(this.holdingPiece() ? this.getHoldPiece(): Tetromino.Type.X);

      if (this.activeTetromino.isLocked()) {
        this.activeTetromino.addTo(board);
        this.lastClearedCounter++;
        if (this.lastClearedCounter % 50 == 0) {
          int cleared = this.board.clearLines();
          this.lines += cleared;
          this.score += Math.pow(2, cleared) * 1000;
          this.level = this.lines / 10 + 1;

          if (!this.activateNextTetromino()) this.game_over = true;
        }
      }

      this.dropCounter++;
      if ((this.dropCounter % Math.max(50 - this.level, 5)) == 0) {
        dropCounter = 1;
        activeTetromino.drop(board);
      }

      frameAverageCounter = (frameAverageCounter + 1) % 15;
      if (frameAverageCounter == 0) {
        framerate = -15e9 / (lastTime - (lastTime = System.nanoTime()));
      }
      frame.repaint();
    }
  }
}

