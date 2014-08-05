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

import java.util.HashMap;

public class TetrominoBlock {

  private static TetrominoBlock instance;

  public ImageIcon block;
  private HashMap<Color, ImageIcon> cache;

  private TetrominoBlock() {
    block = new ImageIcon(this.getClass().getClassLoader().getResource("images/block.png"));
    cache = new HashMap<Color, ImageIcon>();
  }

  public static TetrominoBlock instance() {
    if (instance == null) return (instance = new TetrominoBlock());
    else return instance;
  }

  public ImageIcon getIconForColor(Color color) {
    if (cache.containsKey(color)) return cache.get(color);
    else {
      BufferedImage bimage = toBufferedImage(block.getImage());
      colorize(bimage, color);
      ImageIcon out = new ImageIcon(bimage);
      cache.put(color, out);
      return out;
    }
  }

  public static BufferedImage toBufferedImage(Image image) {
    if (image instanceof BufferedImage)
      return (BufferedImage)image;
    BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = bimage.createGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    return bimage;
  }

  public static void colorize(BufferedImage texture, Color color) {
    for (int y = 0; y < texture.getHeight(); y++) {
      for (int x = 0; x < texture.getWidth(); x++) {
        int pixel = texture.getRGB(x, y);

        int red   = ((pixel & 0xFF0000) >> 16);
        int green = ((pixel & 0x00FF00) >> 8);
        int blue  = ((pixel & 0x0000FF));

        red = (int)(red * (color.getRed() / 255.0));
        green = (int)(green * (color.getGreen() / 255.0));
        blue = (int)(blue * (color.getBlue() / 255.0));

        texture.setRGB(x, y, new Color(red, green, blue).getRGB());
      }
    }
  }
}
