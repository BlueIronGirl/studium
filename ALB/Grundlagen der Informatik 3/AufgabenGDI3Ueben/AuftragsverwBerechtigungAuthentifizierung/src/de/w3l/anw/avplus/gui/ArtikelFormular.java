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
import de.w3l.anw.avplus.applikationslogik.Artikel;
import de.w3l.anw.utility.Geld;

public class ArtikelFormular extends javax.swing.JPanel {

  private static final long serialVersionUID = 1L;

  // Formularfelder f�r Artikel
  private JTextField aktBestandTf;

  private JLabel aktBestandLbl;

  private JTextField mindestbestandTf;

  private JLabel mindestbestandLbl;

  private JComboBox<String> waehrungCb;

  private JTextField preisTF;

  private JLabel preisLbl;

  private JTextField artnameTF;

  private JLabel artnameLbl;

  private JTextField artnrTf;

  private JLabel artnrLbl;

  private JLabel ueberschriftLbl;

  private JPanel ueberschriftPnl;

  /**
   * Der GUI braucht ein Locale, damit Betr�ge korrekt
   * ausgegeben werden (mit Dezimalpunkt oder Dezimalkomma)
   */
  private Locale meinLocale;

  public ArtikelFormular(Locale locale) {
    super();
    this.meinLocale = locale;
    initGUI();
  }

  public ArtikelFormular() {
    super();
    initGUI();
  }

  /**
   * Aufbau der Benutzungsoberfl�che: Belegen des JPanels
   * mit verschiedenen Oberfl�chenelementen. Da
   * WindowBuilder nicht mit Generics umgehen kann, wird das
   * SuppressWarnings ben�tigt.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  private void initGUI() {
    try {
      /*
       * ArtikelFormular besteht aus 12 Spalten (1.
       * Parameter des Konstruktors) und aus 7 Zeilen (2.
       * Parameter des Konstruktors). Eine zus�tzliche
       * Angabe von "grow" bedeutet, dass die Zeilen bzw.
       * Spalten wachsen k�nnen, wenn die zur Verf�gung
       * stehende Fl�che gr��er wird. Der Aufbau der Zeilen
       * ist wie folgt: leer, F�hrungstext 1, leer,
       * Eingabefeld 1 (40, 1, 24), leer, F�hrungstext 2,
       * leer, Eingabefeld 2 (65, 65), leer Die
       * Eingabefelder f�r Artikelnummer und Mindestbestand
       * nehmen den gesamten Raum von Eingabefeld 1 ein. Das
       * Eingabefeld f�r den Preis nimmt nur den ersten Teil
       * des ersten Eingabefelds ein. Teil 2 bleiben f�r
       * Leerstelle und Drop-Down-Liste. Aktueller Bestand
       * nimmt nur den ersten Teil von Eingabefeld 2 ein,
       * w�hrend Artikelbezeichnung beide Teile einnimmt.
       */
      FormLayout felderPnlLayout =
          new FormLayout(
              /* Angabe der Spalten */
              "5dlu, max(p;5dlu), 5dlu, 40dlu, 1dlu, 24dlu, 5dlu, "
                  + "max(p;5dlu), 5dlu, 65dlu, min(p;65dlu):grow, 5dlu",
              /* Angabe der Zeilen */
              "10dlu, max(p;5dlu), 5dlu, max(p;5dlu), 5dlu, "
                  + "max(p;5dlu), 10dlu");
      this.setLayout(felderPnlLayout);
      this.setBorder(new LineBorder(new java.awt.Color(0,
          0, 0), 1, false));
      this.setPreferredSize(new Dimension(590, 200));
      this.setMinimumSize(new Dimension(590, 200));

      artnrLbl = new JLabel();
      /*
       * Das JLabel artnrLbl wird auf das felderPnl gesetzt
       * und zwar in die zweite Spalte und die zweite Zeile.
       * Dabei nimmt es genau eine Zelle in x-Richtung und
       * eine Zelle in y-Richtung ein.
       */
      this.add(artnrLbl, new CellConstraints(
          "2, 2, 1, 1, default, default"));
      artnrLbl.setText(Messages.getString("AV.ARTNR"));
      artnrLbl.setFont(new java.awt.Font("Dialog", 1, 12));
      artnrLbl.setHorizontalAlignment(SwingConstants.RIGHT);
      artnrTf = new JTextField();
      this.add(artnrTf, new CellConstraints(
          "4, 2, 3, 1, fill, default"));
      artnrTf.setFont(new java.awt.Font("DialogInput", 0,
          12));
      artnrTf.setHorizontalAlignment(SwingConstants.RIGHT);
      artnrTf.setName("KNR");
      artnameLbl = new JLabel();
      this.add(artnameLbl, new CellConstraints(
          "8, 2, 1, 1, default, default"));
      artnameLbl.setText("Bezeichnung");
      artnameLbl
          .setFont(new java.awt.Font("Dialog", 1, 12));
      artnameLbl
          .setHorizontalAlignment(SwingConstants.RIGHT);
      artnameTF = new JTextField();
      this.add(artnameTF, new CellConstraints(
          "10, 2, 2, 1, fill, default"));
      artnameTF.setFont(new java.awt.Font("DialogInput", 0,
          12));
      preisLbl = new JLabel();
      this.add(preisLbl, new CellConstraints(
          "2, 4, 1, 1, default, default"));
      preisLbl.setText(Messages.getString("AV.ART_PRICE"));
      preisLbl.setFont(new java.awt.Font("Dialog", 1, 12));
      preisLbl.setHorizontalAlignment(SwingConstants.RIGHT);
      preisTF = new JTextField();
      this.add(preisTF, new CellConstraints(
          "4, 4, 1, 1, fill, default"));
      preisTF.setFont(new java.awt.Font("DialogInput", 0,
          12));
      preisTF.setHorizontalAlignment(SwingConstants.RIGHT);
      waehrungCb = new JComboBox(new String[]{"EUR"});
      this.add(waehrungCb, new CellConstraints(
          "6, 4, 1, 1, fill, default"));
      waehrungCb.setFont(new java.awt.Font("DialogInput",
          0, 12));
      waehrungCb.setSize(43, 21);
      mindestbestandLbl = new JLabel();
      this.add(mindestbestandLbl, new CellConstraints(
          "2, 6, 1, 1, default, default"));
      mindestbestandLbl.setText(Messages
          .getString("AV.ART_MIN_STOCK"));
      mindestbestandLbl.setFont(new java.awt.Font("Dialog",
          1, 12));
      mindestbestandLbl
          .setHorizontalAlignment(SwingConstants.RIGHT);
      mindestbestandTf = new JTextField();
      this.add(mindestbestandTf, new CellConstraints(
          "4, 6, 3, 1, fill, default"));
      mindestbestandTf.setFont(new java.awt.Font(
          "DialogInput", 0, 12));
      mindestbestandTf
          .setHorizontalAlignment(SwingConstants.RIGHT);
      aktBestandLbl = new JLabel();
      this.add(aktBestandLbl, new CellConstraints(
          "8, 6, 1, 1, default, default"));
      aktBestandLbl.setText("Akt. Bestand");
      aktBestandLbl.setFont(new java.awt.Font("Dialog", 1,
          12));
      aktBestandLbl
          .setHorizontalAlignment(SwingConstants.RIGHT);
      aktBestandTf = new JTextField();
      this.add(aktBestandTf, new CellConstraints(
          "10, 6, 1, 1, fill, default"));
      aktBestandTf
          .setHorizontalAlignment(SwingConstants.RIGHT);
      aktBestandTf.setFont(new java.awt.Font("DialogInput",
          0, 12));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public JLabel getAktBestandLbl() {
    return aktBestandLbl;
  }

  public JTextField getAktBestandTf() {
    return aktBestandTf;
  }

  public JLabel getArtnameLbl() {
    return artnameLbl;
  }

  public JTextField getArtnameTF() {
    return artnameTF;
  }

  public JLabel getArtnrLbl() {
    return artnrLbl;
  }

  public JTextField getArtnrTf() {
    return artnrTf;
  }

  public JLabel getMindestbestandLbl() {
    return mindestbestandLbl;
  }

  public JTextField getMindestbestandTf() {
    return mindestbestandTf;
  }

  public JLabel getPreisLbl() {
    return preisLbl;
  }

  public JTextField getPreisTF() {
    return preisTF;
  }

  public JLabel getUeberschriftLbl() {
    return ueberschriftLbl;
  }

  public JPanel getUeberschriftPnl() {
    return ueberschriftPnl;
  }

  public JComboBox<String> getWaehrungCb() {
    return waehrungCb;
  }

  /**
   * Eingabebildschirm Kundeneingabe in Ausgangszustand
   * versetzen.
   */
  public void loeschen() {
    getArtnrTf().setText("");
    getArtnameTF().setText("");
    getPreisTF().setText("");
    getMindestbestandTf().setText("");
    getAktBestandTf().setText("");
  }

  /*
   * Methoden f�r die Kommunikation zwischen Model und View
   */

  /**
   * Anzeigen eines Model-Objekts im View
   *
   * @param art : Anzuzeigender Artikel
   */
  public void setArtikel(Artikel art) {
    getArtnrTf().setText("" + art.getArtikelnr());
    getArtnameTF().setText(art.getArtikelbezeichnung());
    getPreisTF().setText(
        Geld.getBetragMitKomma(art.getPreis(), meinLocale));
    getMindestbestandTf().setText(
        "" + art.getMindestbestand());
    getAktBestandTf().setText("" + art.getBestand());
  }

  /**
   * R�ckgabe der Daten vom View als Model-Objekt
   *
   * @return neuer Artikel
   * @throws java.text.ParseException
   */
  public Artikel getArtikel()
      throws java.text.ParseException {
    int artikelnr =
        Integer.parseInt(getArtnrTf().getText());
    return new Artikel(artikelnr, getArtnameTF().getText(),
        new Geld(getPreisTF().getText(), meinLocale,
            (String) getWaehrungCb().getSelectedItem()),
        Integer.parseInt(getMindestbestandTf().getText()),
        Integer.parseInt(getAktBestandTf().getText()));
  }
}