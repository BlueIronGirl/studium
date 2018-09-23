package de.w3l.anw.wetterstation.gui;

/* Programmname: Wetterstation
 * GUI-Klasse: Windmesser
 * Aufgabe: Zeigt die Windrichtung an
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Windmesser extends Messkomponente
{
  private static final long serialVersionUID =
	  -3249706548921052314L;

  // Konstanten fuer das Layout
  public static final int HGroesse = 150;

  public static final int VGroesse = 150;

  private static final int Kreisdurchmesser = 130;

  private static final int SkalaInnen = 56;

  private static final int SkalaAussen = 60;

  private static final int Skalierung = 5;

  private static final int Zeigerlaenge = 50;

  // Attribute
  private float windrichtung;

  public Windmesser()
  {
	setSize(HGroesse, VGroesse);
  }

  // get- und set-Operationen
  public void setWindrichtung(float windrichtung)
  {
	this.windrichtung = windrichtung;
	repaint();
  }

  public float getWindrichtung()
  {
	return windrichtung;
  }

  public synchronized void paintComponent(Graphics g)
  {
	super.paintComponent(g);

	// Hintergrundkreis
	int w = (int) ((HGroesse - Kreisdurchmesser) / 2.0);
	g.setColor(Color.white);
	g.fillOval(w, w, Kreisdurchmesser, Kreisdurchmesser);

	g.setColor(Color.black);
	g.drawOval(w - 1, w - 1, Kreisdurchmesser + 2,
		Kreisdurchmesser + 2);
	g.drawOval(w + 4, w + 4, Kreisdurchmesser - 8,
		Kreisdurchmesser - 8);

	// Koordinatensystem verschieben
	g.translate(HGroesse / 2, VGroesse / 2);

	// Skala zeichnen
	g.setFont(new Font("SansSerif", Font.BOLD, 8));
	double Winkel = -Math.PI / 2.0;
	double S = (Skalierung / 360.0d) * 2 * Math.PI;
	int i = 0;
	int x1 = 0, x2 = 0, x3 = 0;
	int y1 = 0, y2 = 0, y3 = 0;
	while (Winkel < 2 * Math.PI - Math.PI / 2.0 - S)
	{
	  x1 = (int) (SkalaInnen * Math.cos(Winkel));
	  y1 = (int) (SkalaInnen * Math.sin(Winkel));
	  x2 = (int) (SkalaAussen * Math.cos(Winkel));
	  y2 = (int) (SkalaAussen * Math.sin(Winkel));
	  x3 = (int) ((SkalaInnen - 10) * Math.cos(Winkel));
	  y3 = (int) ((SkalaInnen - 10) * Math.sin(Winkel));
	  g.drawLine(x1, y1, x2, y2);

	  if (i % 9 == 0)
	  {
		g.drawString(i * Skalierung + "", x3 - 3, y3 + 4);
	  }

	  Winkel = Winkel + S;
	  i++;
	}

	// Die Himmelsrichtungen
	g.setFont(new Font("SansSerif", Font.BOLD, 12));
	g.drawRect(-9, -Kreisdurchmesser / 2 - 5, 18, 18);
	g.drawRect(-Kreisdurchmesser / 2 - 5, -9, 18, 18);
	g.drawRect(-9, Kreisdurchmesser / 2 - 9 - 5, 18, 18);
	g.drawRect(Kreisdurchmesser / 2 - 9 - 5, -9, 18, 18);

	g.setColor(Color.white);
	g.fillRect(-8, -Kreisdurchmesser / 2 - 5 + 1, 17, 17);
	g.fillRect(-Kreisdurchmesser / 2 - 5 + 1, -8, 17, 17);
	g
		.fillRect(-8, Kreisdurchmesser / 2 - 9 - 5 + 1, 17,
			17);
	g
		.fillRect(Kreisdurchmesser / 2 - 9 - 5 + 1, -8, 17,
			17);

	g.setColor(Color.black);
	g.drawString("N", -3, -Kreisdurchmesser / 2 + 9);
	g.drawString("W", -Kreisdurchmesser / 2 - 1, +5);
	g.drawString("S", -3, Kreisdurchmesser / 2);
	g.drawString("O", Kreisdurchmesser / 2 - 9, +5);

	// Den Zeiger zeichnen
	g.setColor(Color.red);
	Winkel =
		-Math.PI / 2.0 + windrichtung / 360.0 * 2 * Math.PI;
	double WinkelPfeil = 8 / 360.0 * 2 * Math.PI;
	int DiffPfeil = 10;
	x1 = (int) (Zeigerlaenge * Math.cos(Winkel));
	y1 = (int) (Zeigerlaenge * Math.sin(Winkel));
	x2 =
		(int) ((Zeigerlaenge - 5) * Math.cos(Winkel
			+ Math.PI));
	y2 =
		(int) ((Zeigerlaenge - 5) * Math.sin(Winkel
			+ Math.PI));

	int xPunkte[] = new int[3];
	int yPunkte[] = new int[3];

	xPunkte[0] = x1;
	yPunkte[0] = y1;
	xPunkte[1] =
		(int) ((Zeigerlaenge - DiffPfeil) * Math.cos(Winkel
			+ WinkelPfeil));
	yPunkte[1] =
		(int) ((Zeigerlaenge - DiffPfeil) * Math.sin(Winkel
			+ WinkelPfeil));
	xPunkte[2] =
		(int) ((Zeigerlaenge - DiffPfeil) * Math.cos(Winkel
			- WinkelPfeil));
	yPunkte[2] =
		(int) ((Zeigerlaenge - DiffPfeil) * Math.sin(Winkel
			- WinkelPfeil));

	g.drawLine(0, 0, x1, y1);
	g.drawLine(0, 0, x2, y2);
	g.fillPolygon(xPunkte, yPunkte, 3);
	g.fillOval(x2 - 5, y2 - 5, 10, 10);

	// Zeichne Verankerung
	g.fillOval(-8, -8, 16, 16);
	g.setColor(Color.black);
	g.fillOval(-2, -2, 4, 4);
  }
}