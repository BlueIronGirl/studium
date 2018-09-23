package de.w3l.anw.wetterstation.gui;

/* Programmname: Wetterstation
 * GUI-Klasse: InfoDialog
 * Aufgabe: Ein Dialog, der ueber das Programm informiert
 */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class InfoDialog extends JDialog {
  private static final long serialVersionUID =
      8174542887807610560L;

  JButton okButton = new JButton();

  JLabel infoLabel = new JLabel();

  public InfoDialog(JFrame einFenster) {
    super(einFenster);
    setTitle("Internet - Wetterstation");
    setModal(true);
    getContentPane().setLayout(null);
    setSize(248, 94);
    setVisible(false);
    okButton.setText("OK");
    okButton.setActionCommand("OK");
    okButton.setOpaque(false);
    okButton.setMnemonic((int) 'O');
    getContentPane().add(okButton);
    okButton.setBounds(98, 30, 51, 25);
    infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    infoLabel
        .setText("<HTML>Daten bereitgestellt durch <b>"
            + Anwendungsparameter.getParameter("PROVIDER")
            + "</b></HTML>");
    getContentPane().add(infoLabel);
    infoLabel.setBounds(0, 0, 248, 30);

    Fensterabhoerer einFensterabhoerer =
        new Fensterabhoerer();
    this.addWindowListener(einFensterabhoerer);
    Aktionsabhoerer einAktionsabhoerer =
        new Aktionsabhoerer();
    okButton.addActionListener(einAktionsabhoerer);
  }

  public void setVisible(boolean b) {
    if (b) {
      Rectangle bounds = (getParent()).getBounds();
      Dimension size = getSize();
      setLocation(bounds.x + (bounds.width - size.width)
          / 2, bounds.y + (bounds.height - size.height) / 2);
    }

    super.setVisible(b);
  }

  public void addNotify() {
    Dimension d = getSize();

    super.addNotify();

    Insets Grenzen = getInsets();
    setSize(Grenzen.left + Grenzen.right + d.width,
        Grenzen.top + Grenzen.bottom + d.height);
    Component Komponenten[] =
        getContentPane().getComponents();
    for (int i = 0; i < Komponenten.length; i++) {
      Point p = Komponenten[i].getLocation();
      p.translate(Grenzen.left, Grenzen.top);
      Komponenten[i].setLocation(p);
    }
  }

  class Fensterabhoerer extends WindowAdapter {
    public void windowClosing(WindowEvent event) {
      Object object = event.getSource();
      if (object == this)
        schliesseFenster(event);
    }
  }

  void schliesseFenster(WindowEvent event) {
    try {
      this.setVisible(false);
    } catch (Exception e) {
    }
  }

  class Aktionsabhoerer implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      Object object = event.getSource();
      if (object == okButton)
        ausfuehrenokButtonAktion(event);
    }
  }

  void ausfuehrenokButtonAktion(ActionEvent event) {
    try {
      this.setVisible(false);
    } catch (Exception e) {
    }
  }
}