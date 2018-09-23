package de.w3l.anw.wetterstation.gui;

/* Programmname: Wetterstation
 * GUI-Klasse: Kurvenanzeiger
 * Aufgabe: Kurven werden grafisch dargestellt
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Vector;

public class Kurvenanzeiger extends Messkomponente
{
  private static final long serialVersionUID =
	  -1755345471962627970L;

  // Konstanten fuer das Layout

  public static final int HGroesse = 450;

  public static final int VGroesse = 300;

  private static final int BreiteZeichenbereich = 340;

  private static final int HoeheZeichenbereich = 240;

  private static final int SkalaDist = 40;

  // Attribute
  private Vector<Point2D.Float> werte;

  private String label = "";

  private String einheitX = "";

  private String einheitY = "";

  private float skalierungX;

  private float skalierungY;

  private float minX;

  private float maxX;

  private float minY;

  private float maxY;

  private int haeufigkeitX = 5;

  private int haeufigkeitY = 5;

  // Konstruktoren
  public Kurvenanzeiger()
  {
	setSize(HGroesse, VGroesse);
  }

  public Kurvenanzeiger(float SkalierungX,
	  float SkalierungY, float MinX, float MaxX,
	  float MinY, float MaxY)
  {
	this();
	this.skalierungX = SkalierungX;
	this.skalierungY = SkalierungY;
	this.minX = MinX;
	this.maxX = MaxX;
	this.minY = MinY;
	this.maxY = MaxY;
  }

  // get- und set-Operationen

  /**
     * Übergabe der werte, die der Kurvenanzeiger darstellen
     * soll als Punkte
     * 
     * @param werte:
     * Vector von Punkten
     * @param autorange:
     * Minimum und Maximum für Y-Darstellung wird
     * automatisch berechnet, wenn true.
     */
  public void setWerte(Vector<Point2D.Float> werte,
	  boolean autorange)
  {
	this.werte = werte;
	int anzWerte = werte.size();
	if (autorange && (anzWerte > 0))
	{
	  float max = (werte.get(0)).y;
	  float min = max;
	  for (int i = 1; i < anzWerte; i++)
	  {
		float neuesY = (werte.get(i)).y;
		if (neuesY < min)
		  min = neuesY;
		else if (neuesY > max)
		  max = neuesY;
	  }
	  minY = ((int) (min / haeufigkeitY)) * haeufigkeitY;
	  maxY =
		  (((int) (max / haeufigkeitY)) + 1) * haeufigkeitY;
	}
	repaint();
  }

  public void setLabel(String label)
  {
	this.label = label;
	repaint();
  }

  public void setEinheitX(String einheitX)
  {
	this.einheitX = einheitX;
	repaint();
  }

  public void setEinheitY(String einheitY)
  {
	this.einheitY = einheitY;
	repaint();
  }

  public void setSkalierungX(float skalierungX)
  {
	this.skalierungX = skalierungX;
	repaint();
  }

  public void setSkalierungY(float skalierungY)
  {
	this.skalierungY = skalierungY;
	repaint();
  }

  public void setMinX(float minX)
  {
	this.minX = minX;
	repaint();
  }

  public void setMinY(float minY)
  {
	this.minY = minY;
	repaint();
  }

  public void setMaxX(float maxX)
  {
	this.maxX = maxX;
	repaint();
  }

  public void setMaxY(float maxY)
  {
	this.maxY = maxY;
	repaint();
  }

  public void setHaeufigkeitX(int hx)
  {
	haeufigkeitX = hx;
	repaint();
  }

  public void setHaeufigkeitY(int hy)
  {
	haeufigkeitY = hy;
  }

  public Vector<Point2D.Float> getWerte()
  {
	return werte;
  }

  public String getLabel()
  {
	return label;
  }

  public String getEinheitX()
  {
	return einheitX;
  }

  public String getEinheitY()
  {
	return einheitY;
  }

  public float getSkalierungX()
  {
	return skalierungX;
  }

  public float getSkalierungY()
  {
	return skalierungY;
  }

  public float getMinX()
  {
	return minX;
  }

  public float getMinY()
  {
	return minY;
  }

  public float getMaxX()
  {
	return maxX;
  }

  public float getMaxY()
  {
	return maxY;
  }

  public boolean istImBereich(Point2D.Float P)
  {
	if (P != null)
	{
	  if (minX <= P.x && P.x <= maxX && minY <= P.y
		  && P.y <= maxY)
		return true;
	}
	System.out.println("Ein Wert ist nicht im Bereich!");
	return false;
  }

  public float rechneUm(float Wert, float Grenze)
  {
	return (Wert - Grenze);
  }

  public void zeichneWerte(Graphics g, float MassstabX,
	  float MassstabY)
  {
	if (werte != null && !werte.isEmpty())
	{
	  Point2D.Float P1, P2;
	  float x1, x2, y1, y2;
	  try
	  {
		P1 = werte.elementAt(0);

		for (int i = 1; i < werte.size(); i++)
		{
		  P2 = werte.elementAt(i);
		  if (istImBereich(P1))
		  {
			x1 = rechneUm(P1.x, minX);
			y1 = rechneUm(P1.y, minY);
			x2 = rechneUm(P2.x, minX);
			y2 = rechneUm(P2.y, minY);

			g.drawLine((int) (x1 * MassstabX),
				-(int) (y1 * MassstabY),
				(int) (x2 * MassstabX),
				-(int) (y2 * MassstabY));
		  }
		  P1 = P2;
		}
	  }
	  catch (Exception e)
	  {
		System.out
			.println("Vektor enthaelt ein nicht zulaessiges Element!");
	  }
	}
  }

  // Zeichnen
  public synchronized void paintComponent(Graphics g)
  {
	super.paintComponent(g);

	// Die Schrift setzen
	g.setFont(new Font("SansSerif", Font.BOLD, 10));

	// berechne die Startkoordinaten
	int XStart = (HGroesse - BreiteZeichenbereich) / 2;
	int YStart = (VGroesse - HoeheZeichenbereich) / 2;

	// Die Zeichenflaeche
	g.setColor(Color.white);

	g.fillRect(XStart, YStart, BreiteZeichenbereich,
		HoeheZeichenbereich);
	g.draw3DRect(XStart - 2, YStart - 2,
		BreiteZeichenbereich + 4, HoeheZeichenbereich + 4,
		false);
	g.setColor(Color.black);
	g.drawRect(XStart - 3, YStart - 3,
		BreiteZeichenbereich + 6, HoeheZeichenbereich + 6);

	// Koordinatensystem verschieben
	g.translate(XStart + SkalaDist, YStart
		+ HoeheZeichenbereich - SkalaDist);
	int SkalaLaengeX = BreiteZeichenbereich - 2 * SkalaDist;
	int SkalaLaengeY = HoeheZeichenbereich - 2 * SkalaDist;

	// Skalen zeichnen
	g.drawLine(0, 0, SkalaLaengeX, 0);
	g.drawLine(0, 0, 0, -SkalaLaengeY);
	g
		.drawLine(SkalaLaengeX, 0, SkalaLaengeX,
			-SkalaLaengeY);

	float DivisorX = maxX - minX;
	float DivisorY = maxY - minY;
	float MassstabX = SkalaLaengeX / DivisorX;
	float MassstabY = SkalaLaengeY / DivisorY;
	int AnzahlX = (int) (DivisorX / skalierungX);
	int AnzahlY = (int) (DivisorY / skalierungY);
	int hy = 0;
	String Ausgabe;
	FontMetrics fm = g.getFontMetrics(g.getFont());
	int Breite = 0;

	// Skala in Y-Richtung
	for (int i = 0; i <= AnzahlY; i++)
	{
	  hy = -(int) (i * skalierungY * MassstabY);

	  if (i % haeufigkeitY == 0)
	  {
		Ausgabe = (int) (i * skalierungY + minY) + "";

		Breite = fm.stringWidth(Ausgabe);
		g.drawString((int) (i * skalierungY + minY) + "",
			-SkalaDist, hy - 2);
		g.drawString((int) (i * skalierungY + minY) + "",
			SkalaLaengeX + SkalaDist - Breite - 2, hy - 2);
		g.drawLine(-SkalaDist, hy,
			SkalaLaengeX + SkalaDist, hy);
	  }
	  else
	  {
		g.drawLine(-5, hy, 0, hy);
		g.drawLine(SkalaLaengeX, hy, SkalaLaengeX + 5, hy);
	  }
	}

	// Skala in X-Richtung
	int hx = 0;
	for (int i = 0; i <= AnzahlX; i++)
	{
	  hx = (int) (i * skalierungX * MassstabX);

	  if (i % haeufigkeitX == 0)
	  {
		g.drawString((int) (i * skalierungX + minX) + "",
			hx - 6, 12);
		g.drawLine(hx, 0, hx, -SkalaLaengeY);
	  }
	  else
	  {
		g.drawLine(hx, 5, hx, 0);
		g.drawLine(hx, SkalaLaengeY - 5, hx, SkalaLaengeY);
	  }
	}

	// label zeichnen
	Breite = fm.stringWidth(label);
	hx =
		(BreiteZeichenbereich - 2 * SkalaDist - Breite) / 2;
	g.drawString(label, hx, -HoeheZeichenbereich
		+ SkalaDist + 20);

	// Die Einheiten
	g.drawString(einheitY, -SkalaDist, -SkalaLaengeY - 15);
	g.drawString(einheitX, SkalaLaengeX + SkalaDist
		- fm.stringWidth(einheitX), +12);

	// Die werte einzeichnen
	g.setColor(Color.red);
	zeichneWerte(g, MassstabX, MassstabY);
  }
}
