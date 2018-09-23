package de.w3l.anw.othello.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import de.w3l.anw.othello.engine.Othello;

public class OthelloPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  private Othello othelloEngine = null;

  private BufferedImage weisserStein = null;

  private BufferedImage schwarzerStein = null;

  /**
   * @param computer: Farbe des Computers: 1 weiss, -1 schwarz
   */
  public OthelloPanel(int computer) {
    super();
    this.setPreferredSize(new Dimension(640, 640));
    setBorder(BorderFactory.createLineBorder(Color.black));
    othelloEngine = new Othello(computer);
    try {
      weisserStein =
          ImageIO.read(new File("steinWeiss.gif"));
      schwarzerStein =
          ImageIO.read(new File("steinSchwarz.gif"));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public Othello getOthelloEngine() {
    return othelloEngine;
  }

  public void setOthelloEngine(Othello othelloEngine) {
    this.othelloEngine = othelloEngine;
  }

  public Point getZeileUndSpalte(MouseEvent mausevent) {
    Point mausklick = mausevent.getPoint();
    return new Point(mausklick.x / 80, mausklick.y / 80);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (int x = 80; x < 640; x = x + 80)
      g.drawLine(x, 0, x, 639);

    for (int y = 80; y < 640; y = y + 80)
      g.drawLine(0, y, 639, y);

    // Die Spielsteine ausgeben:
    for (int zeile = 0; zeile < 8; zeile++) {
      for (int spalte = 0; spalte < 8; spalte++) {
        Integer wert =
            (Integer) othelloEngine.getValueAt(zeile,
                spalte);
        if (wert != null) {
          if (wert.intValue() == Othello.SCHWARZ)
            g.drawImage(schwarzerStein, zeile * 80 + 20,
                spalte * 80 + 20, this);
          else
            g.drawImage(weisserStein, zeile * 80 + 20,
                spalte * 80 + 20, this);
        }
      }
    }
  }
}
