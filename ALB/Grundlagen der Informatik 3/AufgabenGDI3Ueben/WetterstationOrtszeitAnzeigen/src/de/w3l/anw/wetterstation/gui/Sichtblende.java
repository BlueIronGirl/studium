package de.w3l.anw.wetterstation.gui;

/* Programmname: Wetterstation
 * GUI-Klasse: Sichtblende
 * Aufgabe: Dient als Blindbeschlag fuer freie Flaechen
 */

import java.awt.Graphics;

public class Sichtblende extends Messkomponente
{
  private static final long serialVersionUID =
      376977643964906986L;

  // Konstanten fuer das Layout
  public static final int HGroesse = 150;

  public static final int VGroesse = 75;

  // Konstruktoren
  public Sichtblende()
  {
    setSize(HGroesse, VGroesse);
  }

  public synchronized void paint(Graphics g)
  {
    super.paintComponent(g);
  }
}