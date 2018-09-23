package de.w3l.anw.othello.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * stellt einen Uhrenelement auf der Benutzungsoberfl�che
 * dar. Um die SingleThreadRule f�r Swing nicht zu
 * verletzen, wird die Uhr jeweils durch einen
 * javax.swing.Timer angesto�en und muss daher die
 * Schnittstelle ActionListener implementieren.
 */
public class UhrInfoElement extends InfoElement implements
    ActionListener {

  private static final long serialVersionUID =
      4681312459092883496L;

  private long startTime = 0;

  private long stopTime = 0;

  private long elapsed = 0;

  private boolean running = false;

  public UhrInfoElement(String ueberschrift,
                        Color hintergrund) {
    super(ueberschrift, hintergrund);
  }

  public void start() {
    if (!running) {
      this.startTime = System.currentTimeMillis();
      this.running = true;
    }
  }

  public void stop() {
    if (running) {
      this.stopTime = System.currentTimeMillis();
      this.running = false;
      elapsed += (stopTime - startTime);
    }
  }

  // elaspsed time in milliseconds
  public long getElapsedTime() {
    if (running) {
      return (System.currentTimeMillis() - startTime + elapsed);
    }
    return elapsed;
  }

  // elaspsed time in seconds
  public long getElapsedTimeSecs() {
    return getElapsedTime() / 1000;
  }

  public String getElapsedTimeMin() {
    long elapsed = getElapsedTime();
    long min = elapsed / 60000;
    long sec = (elapsed / 1000) % 60;
    if (sec < 10)
      return "" + min + ":0" + sec;
    else
      return "" + min + ":" + sec;
  }

  public void actionPerformed(ActionEvent e) {
    if (running) {
      this.setInhalt(getElapsedTimeMin());
      repaint();
    }
  }
}
