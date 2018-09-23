package de.w3l.anw.wetterstation.gui;

/* Programmname: Wetterstation
 * GUI-Klasse: Messkomponente
 * Aufgabe: Stellt das Look and Feel fuer alle Unterklassen bereit
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Messkomponente extends JComponent {
  private static final long serialVersionUID =
      -3056232730200130398L;

  // Attribute
  private Image schraubenbild =
      getToolkit().getImage("Schraube_16.gif");

  public synchronized void paintComponent(Graphics g) {
    super.paintComponent(g);
    Rectangle r = getBounds();
    // Hintergrund mit 3D Effekt
    g.setColor(getBackground());
    g.fillRect(1, 1, r.width - 2, r.height - 2);
    g.draw3DRect(0, 0, r.width - 1, r.height - 1, true);
    g.setColor(Color.black);
    g.drawRect(2, 2, r.width - 5, r.height - 5);

    // die Schrauben
    g.drawImage(schraubenbild, 4, 4, this);
    g.drawImage(schraubenbild, r.width - 20, r.height - 20,
        this);
    g.drawImage(schraubenbild, 4, r.height - 20, this);
    g.drawImage(schraubenbild, r.width - 20, 4, this);
  }
}
