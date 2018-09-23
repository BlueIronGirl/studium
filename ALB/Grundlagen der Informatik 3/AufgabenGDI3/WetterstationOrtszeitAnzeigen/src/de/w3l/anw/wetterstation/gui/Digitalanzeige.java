package de.w3l.anw.wetterstation.gui;

/* Programmname: Wetterstation
 * GUI-Klasse: Digitalanzeige
 * Aufgabe: Zeigt Daten wie eine LED-Anzeige an
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Digitalanzeige extends Messkomponente {
  private static final long serialVersionUID =
      5292665144873603279L;

  // Konstanten fuer das Layout
  public static final int HGroesse = 150;

  public static final int VGroesse = 75;

  private static final int BreiteDigitalAnz = 100;

  private static final int HoeheDigitalAnz = 40;

  // Attribute
  private float wert;

  private String einheit = "";

  private String label = "";

  // Konstruktoren
  public Digitalanzeige() {
    setSize(HGroesse, VGroesse);
  }

  public Digitalanzeige(String Einheit, String Label) {
    this();
    this.einheit = Einheit;
    this.label = Label;
  }

  // get- und set-Operationen
  public void setWert(float wert) {
    this.wert = wert;
    repaint();
  }

  public void setEinheit(String einheit) {
    this.einheit = einheit;
    repaint();
  }

  public void setLabel(String label) {
    this.label = label;
    repaint();
  }

  public String getEinheit() {
    return einheit;
  }

  public String getLabel() {
    return label;
  }

  public synchronized void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Die Schrift setzen
    g.setFont(new Font("SansSerif", Font.BOLD, 12));

    // Die Anzeige zeichnen
    int XStart =
        (int) ((HGroesse - BreiteDigitalAnz) / 2.0);
    int YStart = (int) ((VGroesse - HoeheDigitalAnz) / 2.0);

    // Koordinatensystem verschieben
    g.translate(XStart, YStart);

    g.setColor(Color.white);
    g.fillRect(0, 0, BreiteDigitalAnz, HoeheDigitalAnz);

    g.setColor(Color.black);
    g.drawRect(-1, -1, BreiteDigitalAnz + 2,
        HoeheDigitalAnz + 2);
    String Ausgabe = wert + " ";

    FontMetrics fm = g.getFontMetrics(g.getFont());
    int BreiteEinheit = fm.stringWidth(einheit);

    int d = (int) ((HoeheDigitalAnz - 14) / 2.0);
    g.drawString(Ausgabe, 2, 14 + d);
    g.drawString(einheit, BreiteDigitalAnz - BreiteEinheit
        - 2, 14 + d);

    // Das label zeichnen
    g.setFont(new Font("SansSerif", Font.BOLD, 8));
    FontMetrics fm1 = g.getFontMetrics(g.getFont());
    int BreiteLabel = fm1.stringWidth(label);
    int h = (int) ((BreiteDigitalAnz - BreiteLabel) / 2.0);
    g.drawString(label, h, HoeheDigitalAnz);
  }
}