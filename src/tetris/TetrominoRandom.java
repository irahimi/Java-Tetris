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

import java.util.Stack;
import java.util.Collections;

public class TetrominoRandom {
  private Stack<Tetromino.Type> stack;

  public TetrominoRandom() {
    stack = new Stack<Tetromino.Type>();
    refreshStack();
  }

  private void refreshStack() {
    Stack<Tetromino.Type> tmp = new Stack<Tetromino.Type>();
    for (int i = 1; i <= Tetromino.TETROMINOES; i++)
      tmp.push(Tetromino.Type.values()[i]);
    Collections.shuffle(tmp);
    for (Tetromino.Type tetromino : tmp) {
      stack.add(0, tetromino);
    }
  }

  public Tetromino.Type get() {
    if (stack.size() == PreviewPanel.PREVIEW + 1) {
      refreshStack();
    }
    return stack.pop();
  }

  public int size() {
    return stack.size();
  }

  public Tetromino.Type elementAt(int i) {
    return stack.elementAt(i);
  }

  public Stack<Tetromino.Type> getStack() {
    return stack;
  }
}

