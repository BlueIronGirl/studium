package de.w3l.anw.othello.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoElement extends JPanel {

  private static final long serialVersionUID = 1L;

  private JLabel inhalt = null;

  public InfoElement() {
    super();
  }

  public InfoElement(String ueberschrift) {
    inhalt = new JLabel();
    inhalt.setFont(new Font("SansSerif", Font.BOLD, 20));
    GridLayout gridLayout = new GridLayout();
    gridLayout.setRows(1);
    this.setLayout(gridLayout);
    this.setBorder(BorderFactory
        .createTitledBorder(ueberschrift));
    this.add(inhalt);
  }

  public InfoElement(String ueberschrift, Color hintergrund) {
    this(ueberschrift);
    this.setBackground(hintergrund);
  }

  public String getInhalt() {
    return inhalt.getText();
  }

  public void setInhalt(String inhalt) {
    this.inhalt.setText(inhalt);
    this.inhalt.repaint();
  }
}
