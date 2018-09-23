package de.w3l.anw.avplus.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class PasswortGui extends JDialog {
  private static final long serialVersionUID = 1L;

  private JLabel benutzerLbl;

  private JTextField benutzernameTf;

  private AVSchaltflaeche abbrechenSchaltflaeche;

  private AVSchaltflaeche okSchaltflaeche;

  private JPanel schaltflaechenPnl;

  private JPanel inhalt;

  private JPasswordField passwortTf;

  private JLabel passwortLbl;

  protected boolean abbruch;

  public PasswortGui(JFrame frame) {
    super(frame, true); // Wichtig: Der Dialog muss
    // modal sein!
    initGUI();
  }

  private void initGUI() {
    try {
      this.setTitle(Messages.getString("AV.NAME"));
      BorderLayout thisLayout = new BorderLayout();
      getContentPane().setLayout(thisLayout);
      this.setResizable(false);
      inhalt = new JPanel();
      FormLayout inhaltLayout =
          new FormLayout(
              "max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu):grow, max(p;5dlu)",
              "5dlu, max(p;5dlu), 5dlu, max(p;5dlu), 5dlu");
      inhalt.setLayout(inhaltLayout);
      getContentPane().add(inhalt, BorderLayout.CENTER);
      benutzerLbl = new JLabel();
      inhalt.add(benutzerLbl, new CellConstraints(
          "2, 2, 1, 1, default, default"));
      benutzerLbl.setText(Messages
          .getString("AV.D_USERNAME"));
      benutzerLbl
          .setFont(new java.awt.Font("Dialog", 1, 12));
      benutzernameTf = new JTextField();
      inhalt.add(benutzernameTf, new CellConstraints(
          "4, 2, 1, 1, default, default"));
      passwortLbl = new JLabel();
      inhalt.add(passwortLbl, new CellConstraints(
          "2, 4, 1, 1, default, default"));
      passwortLbl.setText(Messages
          .getString("AV.D_PASSWORD"));
      passwortLbl
          .setFont(new java.awt.Font("Dialog", 1, 12));
      passwortTf = new JPasswordField();
      inhalt.add(passwortTf, new CellConstraints(
          "4, 4, 1, 1, default, default"));
      passwortTf.setFont(new java.awt.Font("DialogInput",
          0, 12));
      schaltflaechenPnl = new JPanel();
      getContentPane().add(schaltflaechenPnl,
          BorderLayout.SOUTH);
      okSchaltflaeche = new AVSchaltflaeche();
      okSchaltflaeche.addActionListener(e -> {
        abbruch = false;
        setVisible(false);
      });
      okSchaltflaeche.setSize(140, 30);
      okSchaltflaeche
          .setPreferredSize(new java.awt.Dimension(140, 30));
      okSchaltflaeche.setFont(new Font("Dialog", Font.BOLD,
          12));

      schaltflaechenPnl.add(okSchaltflaeche);
      okSchaltflaeche.setText(Messages
          .getString("AV.D_OKBUTTON"));
      okSchaltflaeche.setSize(66, 21);
      abbrechenSchaltflaeche = new AVSchaltflaeche();
      abbrechenSchaltflaeche.addActionListener(e -> {
        abbruch = true;
        setVisible(false);
      });
      abbrechenSchaltflaeche.setSize(140, 30);
      abbrechenSchaltflaeche
          .setPreferredSize(new java.awt.Dimension(140, 30));
      abbrechenSchaltflaeche.setFont(new Font("Dialog",
          Font.BOLD, 12));

      schaltflaechenPnl.add(abbrechenSchaltflaeche);
      abbrechenSchaltflaeche.setText(Messages
          .getString("AV.D_CANCELBUTTON"));
      this.setSize(300, 150);
      this.setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean getAbbruch() {
    return abbruch;
  }

  public String getUsername() {
    return benutzernameTf.getText();
  }

  public char[] getPasswort() {
    return passwortTf.getPassword();
  }
}