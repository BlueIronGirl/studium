package de.w3l.anw.avplus.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SekundaerDialogeGUI {

  JFrame vater;

  public SekundaerDialogeGUI(JFrame vater) {
    this.vater = vater;
  }

  public int zeigeEingabeDialog(String text) {
    String ergebnis =
        JOptionPane.showInputDialog(vater, text);
    if (ergebnis == null)
      return (-2);
    try {
      return Integer.parseInt(ergebnis);
    } catch (Exception ex) {
      return (-1);
    }
  }

  public boolean zeigeBestaetigungsDialog(String text) {
    int ergebnis =
        JOptionPane.showConfirmDialog(vater, text, Messages
                .getString("AV.D_CONFIRM"),
            JOptionPane.YES_NO_OPTION);
    return (ergebnis == JOptionPane.YES_OPTION);
  }

  public void zeigeFehlerDialog(String text) {
    JOptionPane
        .showMessageDialog(vater, text, Messages
                .getString("AV.D_ERROR"),
            JOptionPane.ERROR_MESSAGE);
  }
}
