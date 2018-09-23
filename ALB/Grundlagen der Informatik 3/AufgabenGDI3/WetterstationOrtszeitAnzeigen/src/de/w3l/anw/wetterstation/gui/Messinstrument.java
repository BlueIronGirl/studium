package de.w3l.anw.wetterstation.gui;

/* Programmname: Wetterstation
 * GUI-Klasse: Messinstrument
 * Aufgabe: Zeigt Messdaten ueber einen Balken und eine Digitalanzeige an
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.DecimalFormat;

public class Messinstrument extends Messkomponente {
  private static final long serialVersionUID =
      1852504994246329197L;

  // Konstanten fuer das Layout
  public static final int HGroesse = 150;

  public static final int VGroesse = 300;

  private static final int Breite = 30;

  private static final int Hoehe = 200;

  private static final int BreiteDigitalAnz = 100;

  private static final int HoeheDigitalAnz = 30;

  private static final int XStart = 60;

  private static final int YStart = 30;

  private static final int Strichlaenge = 8;

  // Attribute
  private float wert;

  private float maxWert;

  private float minWert;

  private float skalierung;

  private String einheit = "";

  private String label = "";

  @SuppressWarnings("unused")
  private int haeufigkeit = 5;

  private Color balkenfarbe = Color.red;

  private DecimalFormat meinFormat =
      new DecimalFormat("0.0#");

  // Konstruktoren
  public Messinstrument() {
    setSize(HGroesse, VGroesse);
  }

  public Messinstrument(float MaxWert, float MinWert,
                        float Skalierung, String Einheit, String Label) {
    this();
    this.maxWert = MaxWert;
    this.minWert = MinWert;
    this.skalierung = Skalierung;
    this.einheit = Einheit;
    this.label = Label;
  }

  // get- und set-Operationen
  public void setWert(float Wert) throws Exception {
    if ((minWert <= Wert) && (Wert <= maxWert)) {
      this.wert = Wert;
      repaint();
    } else
      throw new Exception(
          "Messinstrument: wert ist nicht im zul. Breich!");
  }

  public void setEinheit(String einheit) {
    this.einheit = einheit;
    repaint();
  }

  public void setMaxWert(float maxWert) {
    this.maxWert = maxWert;
    repaint();
  }

  public void setSkalierung(float skalierung) {
    this.skalierung = skalierung;
    repaint();
  }

  public void setMinWert(float minWert) {
    this.minWert = minWert;
    repaint();
  }

  public void setLabel(String label) {
    this.label = label;
    repaint();
  }

  public void setHaeufigkeit(int h) {
    haeufigkeit = h;
  }

  public void setBalkenfarbe(Color c) {
    balkenfarbe = c;
  }

  public float getWert() {
    return wert;
  }

  public String getEinheit() {
    return einheit;
  }

  public float getMaxWert() {
    return maxWert;
  }

  public float getMinWert() {
    return minWert;
  }

  public String getLabel() {
    return label;
  }

  public Color getBalkenfarbe() {
    return balkenfarbe;
  }

  public synchronized void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Die Schrift setzen
    g.setFont(new Font("SansSerif", Font.BOLD, 12));

    // Instrument mit einheit beschriften
    g.setColor(Color.black);
    FontMetrics fm = g.getFontMetrics(g.getFont());
    int BreiteEinheit = fm.stringWidth(einheit);
    int x = (int) ((HGroesse - BreiteEinheit) / 2.0);
    g.drawString(einheit, x, YStart - 10);

    // Koordinatensystem verschieben
    g.translate(XStart, YStart);

    // Den Rahmen zeichnen
    g.drawRect(-3, -3, Breite + 6, Hoehe + 6);

    // Den Rahmen fuellen
    g.setColor(Color.white);
    g.fillRect(-2, -2, Breite + 4, Hoehe + 4);

    int Divisor = (int) (maxWert - minWert);
    if (Divisor > 0 && skalierung != 0) {

      float scale = (float) Hoehe / (float) Divisor;

      // Die Saeule zeichnen
      float hw = 0;

      hw = wert - minWert;

      g.setColor(balkenfarbe);
      g.fillRect(0, Hoehe - (int) (scale * hw), Breite,
          (int) (scale * hw));

      // Die Skala zeichnen
      g.setColor(Color.black);
      int AnzahlDurchlaeufe = (int) (Divisor / skalierung);
      int Vorzeichenvorschub = 0;

      for (int i = 0; i <= AnzahlDurchlaeufe; i++) {
        if (i % 5 == 0) {
          if ((int) (i * skalierung + minWert) >= 0)
            Vorzeichenvorschub = 8;

          g.drawString((int) (i * skalierung + minWert)
              + "", Breite + 8 + Strichlaenge + 2
              + Vorzeichenvorschub, Hoehe + 3
              - (int) (i * scale * skalierung));
          g.drawLine(Breite + 8, Hoehe
              - (int) (i * scale * skalierung), Breite + 8
              + Strichlaenge, Hoehe
              - (int) (i * scale * skalierung));
        } else {
          g.drawLine(Breite + 8, Hoehe
              - (int) (i * scale * skalierung), Breite + 8
              + Strichlaenge - 4, Hoehe
              - (int) (i * scale * skalierung));
        }
      }
    }

    // Zum Schluss wird die Digitalanzeige gezeichnet
    g.setColor(Color.white);
    int w =
        (int) ((HGroesse - BreiteDigitalAnz) / 2.0)
            - XStart;
    g.fillRect(w, Hoehe + 20, BreiteDigitalAnz,
        HoeheDigitalAnz);

    g.setColor(Color.black);
    g.drawRect(w - 1, Hoehe + 20 - 1, BreiteDigitalAnz + 2,
        HoeheDigitalAnz + 2);

    String Ausgabe = meinFormat.format(wert) + "";

    int d = (int) ((HoeheDigitalAnz - 14) / 2.0);
    g.drawString(Ausgabe, w + 2, Hoehe + 20 + 14 + d);
    g.drawString(einheit, w + BreiteDigitalAnz
        - BreiteEinheit - 2, Hoehe + 20 + 14 + d);

    // Das label zeichnen
    g.setFont(new Font("SansSerif", Font.BOLD, 8));
    FontMetrics fm1 = g.getFontMetrics(g.getFont());
    int BreiteLabel = fm1.stringWidth(label);
    int h =
        (int) ((BreiteDigitalAnz - BreiteLabel) / 2.0) - 15;
    g.drawString(label, h, Hoehe + HoeheDigitalAnz + 20);
  }
}
