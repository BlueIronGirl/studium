package de.w3l.anw.othello.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import de.w3l.anw.othello.engine.Othello;

public class OthelloInfoBoard extends JPanel {

  private static final long serialVersionUID = 1L;

  /*
   * Hier kommen die Buttons zum Passen und Abbrechen rein.
   */
  private JPanel buttonjPanel = new JPanel();

  private JButton passenButton = new JButton("Passen");

  private JButton abbrechenButton =
      new JButton("Spiel abbrechen");

  private InfoElement weisserSpieler =
      new InfoElement("Wei�er Spieler:", Color.WHITE);

  private InfoElement schwarzerSpieler =
      new InfoElement("Schwarzer Spieler:",
          Color.LIGHT_GRAY);

  private InfoElement spielerAmZug =
      new InfoElement("Spieler am Zug:", Color.BLUE);

  private UhrInfoElement weisseUhr =
      new UhrInfoElement("Verbrauchte Zeit wei�:",
          Color.WHITE);

  private InfoElement bewertung =
      new InfoElement("Bewertung:", Color.BLUE);

  private UhrInfoElement schwarzeUhr =
      new UhrInfoElement("Verbrauchte Zeit schwarz:",
          Color.LIGHT_GRAY);

  public OthelloInfoBoard() {
    GridLayout gridLayout = new GridLayout();
    gridLayout.setRows(7);
    this.setLayout(gridLayout);
    this.setPreferredSize(new Dimension(200, 640));
    this.add(weisserSpieler);
    this.add(schwarzerSpieler);
    this.add(spielerAmZug);
    this.add(weisseUhr);
    this.add(schwarzeUhr);
    this.add(bewertung);
    this.add(buttonjPanel);
    buttonjPanel.setLayout(new GridLayout(2, 1));
    setPassenMoeglich(false);
    buttonjPanel.add(passenButton);
    buttonjPanel.add(abbrechenButton);
    /*
     * Die Timer f�r die Zeitanzeige starten und alle 950 ms
     * ein Event ausl�sen
     */
    new Timer(950, weisseUhr).start();
    new Timer(950, schwarzeUhr).start();
  }

  public void setPassenMoeglich(boolean b) {
    passenButton.setEnabled(b);
  }

  public void setAbbrechenMoeglich(boolean b) {
    abbrechenButton.setEnabled(b);
  }

  public InfoElement getBewertung() {
    return bewertung;
  }

  public InfoElement getSchwarzerSpieler() {
    return schwarzerSpieler;
  }

  public UhrInfoElement getSchwarzeUhr() {
    return schwarzeUhr;
  }

  public InfoElement getSpielerAmZug() {
    return spielerAmZug;
  }

  public InfoElement getWeisserSpieler() {
    return weisserSpieler;
  }

  public UhrInfoElement getWeisseUhr() {
    return weisseUhr;
  }

  public void starteUhr(int farbe) {
    UhrInfoElement uhr;
    if (farbe == Othello.WEISS)
      uhr = weisseUhr;
    else
      uhr = schwarzeUhr;
    uhr.start();
  }

  public void stoppeUhr(int farbe) {
    UhrInfoElement uhr;
    if (farbe == Othello.WEISS)
      uhr = weisseUhr;
    else
      uhr = schwarzeUhr;
    uhr.stop();
  }

  public JButton getAbbrechenButton() {
    return abbrechenButton;
  }

  public JButton getPassenButton() {
    return passenButton;
  }
}
