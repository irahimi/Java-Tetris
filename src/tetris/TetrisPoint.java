/*
 * Ian Rahimi
 * Mr. Segall
 * AP Computer Science
 * 5/23/14
 */

package tetris;

public class TetrisPoint {
  private int x, y;
  public TetrisPoint() {
    x = 0;
    y = 0;
  }
  public TetrisPoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() { return x; }
  public int getY() { return y; }

  public TetrisPoint add(TetrisPoint point) {
    return new TetrisPoint(this.x + point.getX(),
        this.y + point.getY());
  }

  public TetrisPoint subtract(TetrisPoint point) {
    return new TetrisPoint(this.x - point.getX(),
        this.y - point.getY());
  }

  public boolean equals(TetrisPoint point) {
    return x == point.getX() && y == point.getY();
  }

  public TetrisPoint multiply(int scalar) {
    return new TetrisPoint(this.x * scalar, this.y * scalar);
  }
}
