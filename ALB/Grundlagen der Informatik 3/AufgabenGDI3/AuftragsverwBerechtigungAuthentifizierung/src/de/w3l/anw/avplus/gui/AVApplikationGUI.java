package de.w3l.anw.avplus.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class AVApplikationGUI extends JFrame { // erstellt mit WindowBuilder
  protected JMenuBar menuBar;

  protected JMenu mnAVplus;

  protected JMenuItem mntmAVplusDrucken;

  protected JMenuItem mntmAVplusBeenden;

  protected JMenu mnKd;

  protected JMenuItem mntmKdAnlegen;

  protected JMenuItem mntmKdSuchen;

  protected JSeparator separator;

  protected JMenuItem mntmKdListeAuftragssumme;

  protected JMenuItem mntmKdListeDatumAuftrag;

  protected JMenuItem mntmKdOhneAuftraege;

  protected JMenu mnArt;

  protected JMenuItem mntmArtAnlegen;

  protected JMenuItem mntmArtSuchen;

  protected JSeparator separator_1;

  protected JMenuItem mntmArtKleinerMBest;

  protected JMenuItem mntmArtNachAufsumme;

  protected JMenuItem mntmArtNachAufdatum;

  protected JMenuItem mntmArtOhneAuftraege;

  protected JMenu mnAuf;

  protected JMenuItem mntmAufAnlegen;

  protected JMenuItem mntmAufSuchen;

  protected JSeparator separator_2;

  protected JMenuItem mntmAufNachSumme;

  protected JMenuItem mntmAufNachDatum;

  protected JMenuItem mntmAufNachKd;

  protected JMenuItem mntmAufNachArt;

  protected JSeparator separator_3;

  protected JMenuItem mntmAufOffeneNachAufdatum;

  protected JMenuItem mntmAufOffeneNachLDatum;

  protected JMenu mnHilfe;

  protected JMenuItem mntmHilfeHilfe;

  protected JMenuItem mntmHilfeInfo;

  protected JLabel lblStatuszeile;

  // PopUps f�r Sekund�r-Dialoge
  protected SekundaerDialogeGUI sekGui;

  // Panel zur Anzeige des Artikeleingabe-Dialogs
  protected ArtikelBearbeitenGui artikelBearbeitenGui;

  // Panel zur Anzeige des Artikel-Bearbeiten-Dialogs
  protected ArtikelNeuGui artikelNeuGui;

  // Allgemeines Panel zur Anzeige von Listen
  protected ListenGui listenGui;

  // Panel zur Anzeige des Kunden-Eingabe-Dialogs
  protected KundeNeuGui kundeNeuGui;

  // Panel zur Anzeige des Kunden-Bearbeiten-Dialogs
  protected KundeBearbeitenGui kundeBearbeitenGui;

  // Panel zur Anzeige des Auftrag-Eingabe-Dialogs
  protected AuftragNeuGui auftragNeuGui;

  // Welches Formular wird gerade angezeigt?
  private JPanel angezeigtesFormular = null;

  public AVApplikationGUI() {
    initGUI();
  }

  private void initGUI() {
    setSize(new Dimension(600, 450));
    setTitle(Messages.getString("AVApplikationGUI.Titel")); //$NON-NLS-1$

    menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    mnAVplus =
        new JMenu(
            Messages
                .getString("AVApplikationGUI.Anwendung")); //$NON-NLS-1$
    menuBar.add(mnAVplus);

    mntmAVplusDrucken =
        new JMenuItem(
            Messages.getString("AVApplikationGUI.Drucken")); //$NON-NLS-1$
    mnAVplus.add(mntmAVplusDrucken);

    mntmAVplusBeenden =
        new JMenuItem(
            Messages.getString("AVApplikationGUI.Beenden")); //$NON-NLS-1$
    mnAVplus.add(mntmAVplusBeenden);

    mnKd =
        new JMenu(
            Messages.getString("AVApplikationGUI.Kunden")); //$NON-NLS-1$
    menuBar.add(mnKd);

    mntmKdAnlegen =
        new JMenuItem(
            Messages.getString("AVApplikationGUI.Anlegen")); //$NON-NLS-1$
    mnKd.add(mntmKdAnlegen);

    mntmKdSuchen =
        new JMenuItem(
            Messages.getString("AVApplikationGUI.Suchen")); //$NON-NLS-1$
    mnKd.add(mntmKdSuchen);

    separator = new JSeparator();
    mnKd.add(separator);

    mntmKdListeAuftragssumme =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.nachAuftragssumme")); //$NON-NLS-1$
    mnKd.add(mntmKdListeAuftragssumme);

    mntmKdListeDatumAuftrag =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.nachDatumLetzterAuftrag")); //$NON-NLS-1$
    mnKd.add(mntmKdListeDatumAuftrag);

    mntmKdOhneAuftraege =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.KundenOhneAuftraege")); //$NON-NLS-1$
    mnKd.add(mntmKdOhneAuftraege);

    mnArt =
        new JMenu(
            Messages.getString("AVApplikationGUI.Artikel")); //$NON-NLS-1$
    menuBar.add(mnArt);

    mntmArtAnlegen =
        new JMenuItem(
            Messages.getString("AVApplikationGUI.Anlegen")); //$NON-NLS-1$
    mnArt.add(mntmArtAnlegen);

    mntmArtSuchen =
        new JMenuItem(
            Messages.getString("AVApplikationGUI.Suchen")); //$NON-NLS-1$
    mnArt.add(mntmArtSuchen);

    separator_1 = new JSeparator();
    mnArt.add(separator_1);

    mntmArtKleinerMBest =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.BestandKleinerMindestbestand")); //$NON-NLS-1$
    mnArt.add(mntmArtKleinerMBest);

    mntmArtNachAufsumme =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.nachAuftragssumme")); //$NON-NLS-1$
    mnArt.add(mntmArtNachAufsumme);

    mntmArtNachAufdatum =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.NachDatumLetzterArtikel")); //$NON-NLS-1$
    mnArt.add(mntmArtNachAufdatum);

    mntmArtOhneAuftraege =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.ArtikelOhneAuftraege")); //$NON-NLS-1$
    mnArt.add(mntmArtOhneAuftraege);

    mnAuf =
        new JMenu(
            Messages
                .getString("AVApplikationGUI.Auftraege")); //$NON-NLS-1$
    menuBar.add(mnAuf);

    mntmAufAnlegen =
        new JMenuItem(
            Messages.getString("AVApplikationGUI.Anlegen")); //$NON-NLS-1$
    mnAuf.add(mntmAufAnlegen);

    mntmAufSuchen =
        new JMenuItem(
            Messages.getString("AVApplikationGUI.Suchen")); //$NON-NLS-1$
    mnAuf.add(mntmAufSuchen);

    separator_2 = new JSeparator();
    mnAuf.add(separator_2);

    mntmAufNachSumme =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.nachAuftragssumme")); //$NON-NLS-1$
    mnAuf.add(mntmAufNachSumme);

    mntmAufNachDatum =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.nachAuftragsdatum")); //$NON-NLS-1$
    mnAuf.add(mntmAufNachDatum);

    mntmAufNachKd =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.UeberKunde")); //$NON-NLS-1$
    mnAuf.add(mntmAufNachKd);

    mntmAufNachArt =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.ueberArtikel")); //$NON-NLS-1$
    mnAuf.add(mntmAufNachArt);

    separator_3 = new JSeparator();
    mnAuf.add(separator_3);

    mntmAufOffeneNachAufdatum =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.OAnachAuftragsdatum")); //$NON-NLS-1$
    mnAuf.add(mntmAufOffeneNachAufdatum);

    mntmAufOffeneNachLDatum =
        new JMenuItem(
            Messages
                .getString("AVApplikationGUI.OAnachLieferdatum")); //$NON-NLS-1$
    mnAuf.add(mntmAufOffeneNachLDatum);

    mnHilfe =
        new JMenu(
            Messages
                .getString("AVApplikationGUI.menu.Hilfe")); //$NON-NLS-1$
    menuBar.add(mnHilfe);

    mntmHilfeHilfe =
        new JMenuItem(
            Messages.getString("AVApplikationGUI.Hilfe")); //$NON-NLS-1$
    mnHilfe.add(mntmHilfeHilfe);

    mntmHilfeInfo =
        new JMenuItem(
            Messages.getString("AVApplikationGUI.info")); //$NON-NLS-1$
    mnHilfe.add(mntmHilfeInfo);

    lblStatuszeile = new JLabel(" ");
    getContentPane()
        .add(lblStatuszeile, BorderLayout.SOUTH);

    listenGui = new ListenGui(meinLocale);

    // Die GUIs f�r die Unterpunkte erstellen
    kundeNeuGui = new KundeNeuGui(meinLocale);
    kundeBearbeitenGui = new KundeBearbeitenGui(meinLocale);
    artikelNeuGui = new ArtikelNeuGui(meinLocale);
    artikelBearbeitenGui =
        new ArtikelBearbeitenGui(meinLocale);
    auftragNeuGui = new AuftragNeuGui(meinLocale);

    // Default-Close-Operation setzen
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    // Als Vater der Sekund�rdialoge eintragen
    sekGui = new SekundaerDialogeGUI(this);

    // Fenster anzeigen
    setVisible(true);
  }

  private static final long serialVersionUID = 1L;

  /**
   * Ein GUI braucht ein Locale, damit Zahlen korrekt mit
   * Dezimalpunkt oder Dezimalkomma (je nach Land)
   * ausgegeben werden k�nnen
   */
  Locale meinLocale;

  public AVApplikationGUI(Locale meinLocale) {
    this.meinLocale = meinLocale;
    this.setLocale(meinLocale);
    initGUI();
    this.repaint();
  }

  public void zeige(JPanel form) {
    if (angezeigtesFormular != null)
      this.remove(angezeigtesFormular);

    this.add(form, BorderLayout.CENTER);
    angezeigtesFormular = form;
    loescheStatuszeile();
    this.setVisible(true);
    this.repaint();
  }

  public void verberge(JPanel form) {
    this.remove(form);
    this.setVisible(true);
    this.repaint();
  }

  public void setzeStatuszeile(String text) {
    this.lblStatuszeile.setText(text);
  }

  public void loescheStatuszeile() {
    this.lblStatuszeile.setText(" ");
  }

  public KundeNeuGui getKundeNeuGui() {
    return kundeNeuGui;
  }

  public KundeBearbeitenGui getKundeBearbeitenGui() {
    return kundeBearbeitenGui;
  }

  public ArtikelNeuGui getArtikelNeuGui() {
    return artikelNeuGui;
  }

  public ArtikelBearbeitenGui getArtikelBearbeitenGui() {
    return artikelBearbeitenGui;
  }

  public AuftragNeuGui getAuftragNeuGui() {
    return auftragNeuGui;
  }
}
