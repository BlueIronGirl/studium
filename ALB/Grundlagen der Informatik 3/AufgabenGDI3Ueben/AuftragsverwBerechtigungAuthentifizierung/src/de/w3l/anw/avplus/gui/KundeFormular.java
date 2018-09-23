package de.w3l.anw.avplus.gui;

import java.awt.Dimension;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import de.w3l.anw.avplus.applikationslogik.Kunde;

public class KundeFormular extends javax.swing.JPanel {
  private static final long serialVersionUID = 1L;

  private JComboBox<String> anredeCBx;

  private JLabel anredeLbl;

  private JTextField debnrTf;

  private JLabel debnrLbl;

  private JTextField strasseTf;

  private JLabel strasseLbl;

  private JTextField ortTf;

  private JTextField plzTF;

  private JLabel plzLbl;

  private JTextField kVNameTF;

  private JTextField knameTF;

  private JLabel knameLbL;

  private JTextField kdnrTxtf;

  private JLabel kdnrLbl;

  private JPanel felderPnl;

  /**
   * Ein GUI braucht f�r die korrekte Ausgabe von Zahlen ein
   * Locale
   */
  @SuppressWarnings("unused")
  private Locale meinLocale;

  public KundeFormular(Locale meinLocale) {
    super();
    this.meinLocale = meinLocale;
    initGUI();
  }

  public KundeFormular() {
    initGUI();
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private void initGUI() {
    try {
      FormLayout felderPnlLayout =
          new FormLayout(
              "5dlu, max(p;5dlu), 5dlu, 40dlu, 5dlu, 25dlu:grow, 5dlu, 40dlu, 25dlu:grow, 5dlu, 5dlu",
              "max(p;10dlu), max(p;5dlu), 5dlu, max(p;15dlu), 5dlu, max(p;10dlu), 5dlu, max(p;10dlu), 5dlu, max(p;10dlu), 5dlu, 5dlu, max(p;10dlu), max(p;5dlu)");
      this.setLayout(felderPnlLayout);
      this.setBorder(new LineBorder(new java.awt.Color(0,
          0, 0), 1, false));
      this.setPreferredSize(new Dimension(590, 210));
      this.setMinimumSize(new Dimension(590, 210));

      kdnrLbl = new JLabel();
      this.add(kdnrLbl, new CellConstraints(
          "2, 2, 1, 1, default, default"));
      kdnrLbl.setText(Messages.getString("AV.CUSTNR"));
      kdnrLbl.setFont(new java.awt.Font("Dialog", 1, 12));
      kdnrLbl.setHorizontalAlignment(SwingConstants.RIGHT);

      kdnrTxtf = new JTextField();
      this.add(kdnrTxtf, new CellConstraints(
          "4, 2, 1, 1, fill, default"));
      kdnrTxtf.setFont(new java.awt.Font("DialogInput", 0,
          12));
      kdnrTxtf.setHorizontalAlignment(SwingConstants.RIGHT);
      kdnrTxtf.setName("KNR");

      anredeLbl = new JLabel();
      this.add(anredeLbl, new CellConstraints(
          "2, 4, 1, 1, default, default"));
      anredeLbl.setText(Messages.getString("AV.TITLE"));
      anredeLbl.setFont(new java.awt.Font("Dialog", 1, 12));
      anredeLbl
          .setHorizontalAlignment(SwingConstants.RIGHT);

      knameLbL = new JLabel();
      this.add(knameLbL, new CellConstraints(
          "2, 6, 1, 1, default, default"));
      knameLbL.setText(Messages
          .getString("AV.NAME_FIRSTNAME"));
      knameLbL.setFont(new java.awt.Font("Dialog", 1, 12));
      knameLbL.setHorizontalAlignment(SwingConstants.RIGHT);

      knameTF = new JTextField();
      this.add(knameTF, new CellConstraints(
          "4, 6, 3, 1, fill, default"));
      knameTF.setFont(new java.awt.Font("DialogInput", 0,
          12));
      kVNameTF = new JTextField();
      this.add(kVNameTF, new CellConstraints(
          "8, 6, 3, 1, fill, default"));
      kVNameTF.setFont(new java.awt.Font("DialogInput", 0,
          12));

      plzLbl = new JLabel();
      this.add(plzLbl, new CellConstraints(
          "2, 8, 1, 1, default, default"));
      plzLbl.setText(Messages.getString("AV.ZIPCITY"));
      plzLbl.setFont(new java.awt.Font("Dialog", 1, 12));
      plzLbl.setHorizontalAlignment(SwingConstants.RIGHT);

      plzTF = new JTextField();
      this.add(plzTF, new CellConstraints(
          "4, 8, 1, 1, fill, default"));
      plzTF
          .setFont(new java.awt.Font("DialogInput", 0, 12));
      plzTF.setHorizontalAlignment(SwingConstants.RIGHT);

      ortTf = new JTextField();
      this.add(ortTf, new CellConstraints(
          "6, 8, 5, 1, fill, default"));
      ortTf
          .setFont(new java.awt.Font("DialogInput", 0, 12));

      strasseLbl = new JLabel();
      this.add(strasseLbl, new CellConstraints(
          "2, 10, 1, 1, default, default"));
      strasseLbl.setText(Messages.getString("AV.STREET"));
      strasseLbl
          .setFont(new java.awt.Font("Dialog", 1, 12));
      strasseLbl
          .setHorizontalAlignment(SwingConstants.RIGHT);

      strasseTf = new JTextField();
      this.add(strasseTf, new CellConstraints(
          "4, 10, 7, 1, fill, default"));
      strasseTf.setFont(new java.awt.Font("DialogInput", 0,
          12));

      debnrLbl = new JLabel();
      this.add(debnrLbl, new CellConstraints(
          "2, 13, 1, 1, default, default"));
      debnrLbl.setText(Messages.getString("AV.DEBTORNR"));
      debnrLbl.setFont(new java.awt.Font("Dialog", 1, 12));
      debnrLbl.setHorizontalAlignment(SwingConstants.RIGHT);

      debnrTf = new JTextField();
      this.add(debnrTf, new CellConstraints(
          "4, 13, 1, 1, fill, default"));
      debnrTf.setHorizontalAlignment(SwingConstants.RIGHT);
      debnrTf.setFont(new java.awt.Font("DialogInput", 0,
          12));

      anredeCBx =
          new JComboBox(new String[]{
              Messages.getString("AV.CUST_FRAU"),
              Messages.getString("AV.CUST_HERR"),
              Messages.getString("AV.CUST_FRAUDR"),
              Messages.getString("AV.CUST_HERRDR")});
      this.add(getAnredeCBx(), new CellConstraints(
          "4, 4, 1, 1, default, default"));
      anredeCBx.setFont(new java.awt.Font("DialogInput", 0,
          12));
      anredeCBx
          .setMinimumSize(new java.awt.Dimension(6, 21));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public JTextField getDebnrTf() {
    return debnrTf;
  }

  public JTextField getKdnrTxtf() {
    return kdnrTxtf;
  }

  public JTextField getKnameTF() {
    return knameTF;
  }

  public JTextField getKVNameTF() {
    return kVNameTF;
  }

  public JTextField getOrtTf() {
    return ortTf;
  }

  public JTextField getPlzTF() {
    return plzTF;
  }

  public JTextField getStrasseTf() {
    return strasseTf;
  }

  public JComboBox<String> getAnredeCBx() {
    return anredeCBx;
  }

  /* Methoden zur Kommunikation zwischen Model und View */

  /**
   * Anzeigen eines Kunden
   *
   * @param kd : Anzuzeigender Kunde
   */
  public void setKunde(Kunde kd) {
    getKdnrTxtf().setText("" + kd.getKundennr());
    getAnredeCBx().setSelectedItem(kd.getAnrede());
    getKnameTF().setText(kd.getName());
    getKVNameTF().setText(kd.getVorname());
    getPlzTF().setText(kd.getPlz());
    getOrtTf().setText(kd.getOrt());
    getStrasseTf().setText(kd.getStrasse());
    getDebnrTf().setText(kd.getDebitorennr());
  }

  /**
   * Einlesen eines Kunden aus dem View
   *
   * @return neuer Kunde
   */
  public Kunde getKunde() {
    int kundennr =
        Integer.parseInt(getKdnrTxtf().getText());
    Kunde k =
        new Kunde(kundennr, (String) getAnredeCBx()
            .getSelectedItem(), getKnameTF().getText(),
            getKVNameTF().getText(), getStrasseTf()
            .getText(), getPlzTF().getText(),
            getOrtTf().getText());
    k.setDebitorennr(getDebnrTf().getText());
    return k;
  }

  /*
   * Hilfsmethoden, um den Umgang mit dem GUI zu erleichtern
   */

  /**
   * Eingabebildschirm Kundeneingabe in Ausgangszustand
   * versetzen.
   */
  public void loeschen() {
    getKdnrTxtf().setText("");
    getAnredeCBx().setSelectedIndex(0);
    getKnameTF().setText("");
    getKVNameTF().setText("");
    getPlzTF().setText("");
    getOrtTf().setText("");
    getStrasseTf().setText("");
    getDebnrTf().setText("");
  }

  public JPanel getFelderPnl() {
    return felderPnl;
  }
}
