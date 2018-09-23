package de.w3l.anw.avplus.gui;

import java.awt.Font;

import javax.swing.JButton;

public class AVSchaltflaeche extends JButton {
  private static final long serialVersionUID = 1L;

  public AVSchaltflaeche() {
    setSize(140, 30);
    setPreferredSize(new java.awt.Dimension(140, 30));
    setFont(new Font("Dialog", Font.BOLD, 12));
  }

  public AVSchaltflaeche(String text, String actionCommand) {
    this();
    this.setText(text);
    this.setActionCommand(actionCommand);
  }
}
