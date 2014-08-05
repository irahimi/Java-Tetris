/*
 * Ian Rahimi
 * Mr. Segall
 * AP Computer Science
 * 5/23/14
 */

package tetris;

import java.awt.*;

public class TetrisBoard {
  public static Color[] colors = { Color.gray, Color.cyan, Color.yellow, Color.magenta, Color.green, Color.red, Color.blue, Color.orange };
  public static final int HEIGHT = 22;
  public static final int WIDTH = 10;

  private Tetromino.Type[][] board;

  public TetrisBoard() {
    board = new Tetromino.Type[HEIGHT][WIDTH];
    this.resetBoard();
  }

  public Tetromino.Type get(TetrisPoint point) {
    return board[point.getY()][point.getX()];
  }

  public Tetromino.Type get(int x, int y) {
    return board[y][x];
  }

  public void set(TetrisPoint point, Tetromino.Type type) {
    board[point.getY()][point.getX()] = type;
  }

  public void set(int x, int y, Tetromino.Type type) {
    board[y][x] = type;
  }

  public static boolean inBounds(TetrisPoint point) {
    return (0 <= point.getX() && point.getX() < WIDTH) &&
           (0 <= point.getY() && point.getY() < HEIGHT);
  }

  public int clearLines() {
    int lines_cleared = 0;

    for (int y = 1; y < HEIGHT; y++) {
      boolean clear_row = true;
      for (int x = 0; x < WIDTH; x++) {
        if (this.get(x, y) == Tetromino.Type.X) {
          clear_row = false;
          break;
        }
      }
      if (clear_row) {
        lines_cleared++;
        for (int x = 0; x < WIDTH; x++) {
          this.set(x, 0, Tetromino.Type.X);
          this.set(x, y, Tetromino.Type.X);
          for (int i = y; i > 0; i--)
            this.set(x, i, this.get(x, i - 1));
        }
      }
    }
    return lines_cleared;
  }

  public void resetBoard() {
    for (int y = 0; y < HEIGHT; y++)
      for (int x = 0; x < WIDTH; x++)
        board[y][x] = Tetromino.Type.X;
  }
}

