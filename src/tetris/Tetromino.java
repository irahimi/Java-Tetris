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

public class Tetromino {
  public static enum Type {
    X(0), I(1), O(2), T(3), S(4), Z(5), J(6), L(7);

    private final int value;
    private Type(int value) {
      this.value = value;
    }
    public int getValue() {
      return this.value;
    }
  }

  public static enum Rotation {
    East(0), North(1), West(2), South(3);

    private final int value;
    private Rotation(int value) {
      this.value = value;
    }
    public int getValue() {
      return this.value;
    }
  }

  public static enum RotationPair {
    EastToNorth(0), NorthToEast(1), NorthToWest(2), WestToNorth(3), WestToSouth(4), SouthToWest(5), SouthToEast(6), EastToSouth(7), Invalid(8);

    private final int value;

    private RotationPair(int value) {
      this.value = value;
    }

    public int getValue() {
      return this.value;
    }

    public static RotationPair get(Rotation from, Rotation to) {
      switch (from) {
        case East:
          switch(to) {
            case North:
              return RotationPair.EastToNorth;
            case South:
              return RotationPair.EastToSouth;
          }
        case North:
          switch(to) {
            case East:
              return RotationPair.NorthToEast;
            case West:
              return RotationPair.NorthToWest;
          }
        case West:
          switch(to) {
            case North:
              return RotationPair.WestToNorth;
            case South:
              return RotationPair.WestToSouth;
          }
          break;
        case South:
          switch(to) {
            case East:
              return RotationPair.SouthToEast;
            case West:
              return RotationPair.SouthToWest;
          }
      }
      return RotationPair.Invalid;
    }
  }

  public final static TetrisPoint[][][] TETROMINO = {
    new TetrisPoint[][] { // I Tetromino
      new TetrisPoint[] { new TetrisPoint(0, 1), new TetrisPoint(1, 1), new TetrisPoint(2, 1), new TetrisPoint(3, 1)},
      new TetrisPoint[] { new TetrisPoint(2, 0), new TetrisPoint(2, 1), new TetrisPoint(2, 2), new TetrisPoint(2, 3)},
      new TetrisPoint[] { new TetrisPoint(0, 2), new TetrisPoint(1, 2), new TetrisPoint(2, 2), new TetrisPoint(3, 2)},
      new TetrisPoint[] { new TetrisPoint(1, 0), new TetrisPoint(1, 1), new TetrisPoint(1, 2), new TetrisPoint(1, 3)}
    },
    new TetrisPoint[][] { // O Tetromino
      new TetrisPoint[] { new TetrisPoint(1, 0), new TetrisPoint(2, 0), new TetrisPoint(1, 1), new TetrisPoint(2, 1)},
      new TetrisPoint[] { new TetrisPoint(1, 0), new TetrisPoint(2, 0), new TetrisPoint(1, 1), new TetrisPoint(2, 1)},
      new TetrisPoint[] { new TetrisPoint(1, 0), new TetrisPoint(2, 0), new TetrisPoint(1, 1), new TetrisPoint(2, 1)},
      new TetrisPoint[] { new TetrisPoint(1, 0), new TetrisPoint(2, 0), new TetrisPoint(1, 1), new TetrisPoint(2, 1)}
    },
    new TetrisPoint[][]{ // T Tetromino
      new TetrisPoint[] { new TetrisPoint(0, 1), new TetrisPoint(1, 1), new TetrisPoint(1, 0), new TetrisPoint(2, 1)},
      new TetrisPoint[] { new TetrisPoint(1, 0), new TetrisPoint(1, 1), new TetrisPoint(2, 1), new TetrisPoint(1, 2)},
      new TetrisPoint[] { new TetrisPoint(0, 1), new TetrisPoint(1, 1), new TetrisPoint(1, 2), new TetrisPoint(2, 1)},
      new TetrisPoint[] { new TetrisPoint(1, 0), new TetrisPoint(1, 1), new TetrisPoint(0, 1), new TetrisPoint(1, 2)}
    },
    new TetrisPoint[][] { // S Tetromino
      new TetrisPoint[] { new TetrisPoint(0, 1), new TetrisPoint(1, 1), new TetrisPoint(1, 0), new TetrisPoint(2, 0)},
      new TetrisPoint[] { new TetrisPoint(1, 0), new TetrisPoint(1, 1), new TetrisPoint(2, 1), new TetrisPoint(2, 2)},
      new TetrisPoint[] { new TetrisPoint(0, 2), new TetrisPoint(1, 2), new TetrisPoint(1, 1), new TetrisPoint(2, 1)},
      new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint(0, 1), new TetrisPoint(1, 1), new TetrisPoint(1, 2)}
    },
    new TetrisPoint[][] { // Z Tetromino
      new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint(1, 0), new TetrisPoint(1, 1), new TetrisPoint(2, 1)},
      new TetrisPoint[] { new TetrisPoint(1, 2), new TetrisPoint(1, 1), new TetrisPoint(2, 1), new TetrisPoint(2, 0)},
      new TetrisPoint[] { new TetrisPoint(0, 1), new TetrisPoint(1, 1), new TetrisPoint(1, 2), new TetrisPoint(2, 2)},
      new TetrisPoint[] { new TetrisPoint(0, 2), new TetrisPoint(0, 1), new TetrisPoint(1, 1), new TetrisPoint(1, 0)}
    },
    new TetrisPoint[][] { // J Tetromino
      new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint(0, 1), new TetrisPoint(1, 1), new TetrisPoint(2, 1)},
      new TetrisPoint[] { new TetrisPoint(1, 2), new TetrisPoint(1, 1), new TetrisPoint(1, 0), new TetrisPoint(2, 0)},
      new TetrisPoint[] { new TetrisPoint(0, 1), new TetrisPoint(1, 1), new TetrisPoint(2, 1), new TetrisPoint(2, 2)},
      new TetrisPoint[] { new TetrisPoint(0, 2), new TetrisPoint(1, 2), new TetrisPoint(1, 1), new TetrisPoint(1, 0)}
    },
    new TetrisPoint[][] { // L Tetromino
      new TetrisPoint[] { new TetrisPoint(0, 1), new TetrisPoint(1, 1), new TetrisPoint(2, 1), new TetrisPoint(2, 0)},
      new TetrisPoint[] { new TetrisPoint(1, 0), new TetrisPoint(1, 1), new TetrisPoint(1, 2), new TetrisPoint(2, 2)},
      new TetrisPoint[] { new TetrisPoint(0, 2), new TetrisPoint(0, 1), new TetrisPoint(1, 1), new TetrisPoint(2, 1)},
      new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint(1, 0), new TetrisPoint(1, 1), new TetrisPoint(1, 2)}
    }
  };

  public final static TetrisPoint[][] WALL_KICKS = {
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint(-1, 0), new TetrisPoint(-1,  1), new TetrisPoint(0, -2), new TetrisPoint(-1, -2)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint( 1, 0), new TetrisPoint( 1, -1), new TetrisPoint(0,  2), new TetrisPoint( 1,  2)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint( 1, 0), new TetrisPoint( 1, -1), new TetrisPoint(0,  2), new TetrisPoint( 1,  2)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint(-1, 0), new TetrisPoint(-1,  1), new TetrisPoint(0, -2), new TetrisPoint(-1, -2)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint( 1, 0), new TetrisPoint( 1,  1), new TetrisPoint(0, -2), new TetrisPoint( 1, -2)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint(-1, 0), new TetrisPoint(-1, -1), new TetrisPoint(0,  2), new TetrisPoint(-1,  2)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint(-1, 0), new TetrisPoint(-1, -1), new TetrisPoint(0,  2), new TetrisPoint(-1,  2)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint( 1, 0), new TetrisPoint( 1,  1), new TetrisPoint(0, -2), new TetrisPoint( 1, -2)},
  };

  public final static TetrisPoint[][] WALL_KICKS_I = {
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint(-2, 0), new TetrisPoint( 1, 0), new TetrisPoint(-2, -1), new TetrisPoint( 1,  2)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint( 2, 0), new TetrisPoint(-1, 0), new TetrisPoint( 2,  1), new TetrisPoint(-1, -2)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint(-1, 0), new TetrisPoint( 2, 0), new TetrisPoint(-1,  2), new TetrisPoint( 2, -1)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint( 1, 0), new TetrisPoint(-2, 0), new TetrisPoint( 1, -2), new TetrisPoint(-2,  1)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint( 2, 0), new TetrisPoint(-1, 0), new TetrisPoint( 2,  1), new TetrisPoint(-1, -2)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint(-2, 0), new TetrisPoint( 1, 0), new TetrisPoint(-2, -1), new TetrisPoint( 1,  2)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint( 1, 0), new TetrisPoint(-2, 0), new TetrisPoint( 1, -2), new TetrisPoint(-2,  1)},
    new TetrisPoint[] { new TetrisPoint(0, 0), new TetrisPoint(-1, 0), new TetrisPoint( 2, 0), new TetrisPoint(-1,  2), new TetrisPoint( 2, -1)},
  };


  public final static int MINOS = 4;
  public final static int ROTATIONS = 4;
  public final static int TETROMINOES = 7;

  private final static int LOCKING_THRESHOLD = 3;

  private Type type;
  private Rotation rotation;
  private TetrisPoint point;
  private TetrisPoint[] blocks;
  private int lock = 0;

  public Tetromino(Type type, Rotation rotation, TetrisPoint point) {
    this.type = type;
    this.rotation = rotation;
    this.point = point;
    this.blocks = TETROMINO[this.type.getValue() - 1][this.rotation.getValue()];
    this.lock = 0;
  }

  public Tetromino(Type type) {
    this.type = type;
    this.rotation = Rotation.values()[0];
    this.point = new TetrisPoint(4, 0);
    this.blocks = TETROMINO[this.type.getValue() - 1][this.rotation.getValue()];
    this.lock = 0;
  }

  public Tetromino(Type type, TetrisPoint point) {
    this.type = type;
    this.rotation = Rotation.values()[0];
    this.point = point;
    this.blocks = TETROMINO[this.type.getValue() - 1][this.rotation.getValue()];
    this.lock = 0;
  }

  public Tetromino(Type type, Rotation rotation) {
    this.type = type;
    this.rotation = rotation;
    this.point = new TetrisPoint(4, 0);
    this.blocks = TETROMINO[this.type.getValue() - 1][this.rotation.getValue()];
    this.lock = 0;
  }

  public TetrisPoint[] getBlocks() {
    return this.blocks;
  }

  public Type getType() {
    return this.type;
  }

  public Rotation getRotation() {
    return this.rotation;
  }

  public TetrisPoint getPoint() {
    return this.point;
  }

  public void setPoint(TetrisPoint point) {
    this.point = point;
  }

  public boolean isLocked() {
    return this.lock >= LOCKING_THRESHOLD;
  }

  public boolean isLocking() {
    return this.lock > 0;
  }

  public int getLock() {
    return this.lock;
  }

  public boolean inBounds() {
    for (TetrisPoint mino : this.blocks) {
      if (!TetrisBoard.inBounds(mino.add(point))) return false;
    }
    return true;
  }

  public boolean inTetromino(TetrisPoint point) {
    for (TetrisPoint mino : this.blocks) {
      if (mino.add(this.point).equals(point)) return true;
    }
    return false;
  }

  public boolean intersects(Tetromino tetromino) {
    for (TetrisPoint a : this.blocks) {
      for (TetrisPoint b : tetromino.getBlocks()) {
        if (a.add(this.point).equals(b.add(tetromino.getPoint()))) return true;
      }
    }
    return false;
  }

  public boolean inFreeSpace(TetrisBoard board) {
    for (TetrisPoint mino : this.blocks) {
      if (board.get(mino.add(point)) != Tetromino.Type.X) return false;
    }
    return true;
  }

  public boolean validPlacement(TetrisBoard board) {
    return this.inBounds() && this.inFreeSpace(board);
  }

  public boolean blockedBelow(TetrisBoard board) {
    for (TetrisPoint a : this.blocks) {
      boolean bottom_block = true;
      for (TetrisPoint b : this.blocks) {

        if (b.equals(new TetrisPoint(a.getX(), a.getY() + 1))) bottom_block = false;
      }
      if (bottom_block) {
        TetrisPoint tmp = this.point.add(a.add(new TetrisPoint(0, 1)));
        if (!TetrisBoard.inBounds(tmp)) return true;
        if (board.get(tmp) != Tetromino.Type.X) return true;
      }
    }
    return false;
  }

  public boolean addTo(TetrisBoard board) {
    if (!this.validPlacement(board)) return false;
    for (TetrisPoint mino : this.blocks) board.set(mino.add(point), type);
    return true;
  }

  public boolean removeFrom(TetrisBoard board) {
    for (TetrisPoint mino : this.blocks) board.set(mino.add(point), Type.X);
    return true;
  }


  public boolean move(TetrisBoard board, TetrisPoint point) {
    //this.removeFrom(board);
    TetrisPoint tmp = this.point;
    this.point = point;
    if (!this.validPlacement(board)) {
      this.point = tmp;
      //this.addTo(board);
      return false;
    }
    this.lock = 0;
    //this.addTo(board);
    return true;
  }

  public boolean shift(TetrisBoard board, TetrisPoint displacement) {
    return this.move(board, this.point.add(displacement));
  }

  public void drop(TetrisBoard board) {
    this.lock = !this.shift(board, new TetrisPoint(0, 1)) ? this.lock + 1: 0;
  }

  public void hardDrop(TetrisBoard board) {
    for (; !this.isLocked(); this.drop(board));
  }

  public boolean setRotation(TetrisBoard board, Rotation rotation) {
    return this.setRotation(board, rotation, true);
  }

  public boolean setRotation(TetrisBoard board, Rotation rotation, boolean wall_kick) {
    Rotation tmp = this.rotation;
    TetrisPoint[] tmp2 = this.blocks;
    this.rotation = rotation;
    this.blocks = TETROMINO[this.type.getValue() - 1][this.rotation.getValue()];
    if (!this.validPlacement(board)) {
      /* Restore the state */
      this.rotation = tmp;
      this.blocks = tmp2;

      /* Wall Kick */
      if (wall_kick) {
        RotationPair edge = RotationPair.get(this.rotation, rotation);
        for (int i = 0; i < 5; i++) {
          this.shift(board, this.type == Type.I ? WALL_KICKS_I[edge.getValue()][i]: WALL_KICKS[edge.getValue()][i]);
          if (this.setRotation(board, rotation, false)) return true;
          this.shift(board, (this.type == Type.I ? WALL_KICKS_I[edge.getValue()][i]: WALL_KICKS[edge.getValue()][i]).multiply(-1));

        }
      }

      return false;
    }

    this.lock = 0;
    //this.addTo(board);
    return true;
  }

  public boolean rotateCounterClockwise(TetrisBoard board) {
    return this.setRotation(board, Rotation.values()[(this.rotation.getValue() + 1) % ROTATIONS]);
  }

  public boolean rotateClockwise(TetrisBoard board) {
    // -1 == (n - 1) mod n
    return this.setRotation(board, Rotation.values()[((this.rotation.getValue() + (ROTATIONS - 1)) % ROTATIONS)]);
  }

  public String toString() {
    String out = "tetris.Tetromino[";
    out += "Type: " + this.type + ", ";
    out += "Rotation: " + this.rotation + ", ";
    for (TetrisPoint mino : this.blocks) {
      //TetrisPoint tmp = mino.add(point);
      out += "(" + mino.getX() + ", " + mino.getY() + "), ";
    }
    return out.substring(0, out.length() - 2) + "]";
  }
}

