package de.w3l.anw.avplus.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;

import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import de.w3l.anw.avplus.applikationslogik.Artikel;
import de.w3l.anw.avplus.applikationslogik.ArtikelTableModel;
import de.w3l.anw.avplus.applikationslogik.Artikelverwaltung;
import de.w3l.anw.avplus.applikationslogik.Auftrag;
import de.w3l.anw.avplus.applikationslogik.AuftragException;
import de.w3l.anw.avplus.applikationslogik.AuftragTableModel;
import de.w3l.anw.avplus.applikationslogik.Auftragsposition;
import de.w3l.anw.avplus.applikationslogik.AuftragspositionTableModel;
import de.w3l.anw.avplus.applikationslogik.Auftragsverwaltung;
import de.w3l.anw.avplus.applikationslogik.Kunde;
import de.w3l.anw.avplus.applikationslogik.Kundenverwaltung;
import de.w3l.anw.avplus.berechtigungssystem.Benutzer;
import de.w3l.anw.avplus.berechtigungssystem.BenutzerException;
import de.w3l.anw.avplus.berechtigungssystem.Benutzerverwaltung;
import de.w3l.anw.utility.Geld;

/**
 * Controller- und Starter-Klasse f�r die Applikation
 */
public class AVApplikation {
  /*
   * Haupt-View-Komponente erzeugen; Die gesamte Anwendung
   * arbeitet standardm��ig mit dem DefaultLocale
   */
  private Locale meinLocale;

  // GUI-Komponente
  private AVApplikationGUI view;

  // Model-Komponenten
  private Artikelverwaltung av;

  private Kundenverwaltung kv;

  private Auftragsverwaltung aufv;

  private Benutzerverwaltung benv;

  // Table-Model f�r Auftragspositionen bei
  // Auftragseingabe
  private AuftragspositionTableModel aposTM;

  // Listener f�r verschiedene Funktionen
  // aus den einzelnen Menues
  // Der erstene Listener (f�r die Anlage neuer Artikel)
  // wird �ber eine anonyme Klasse per Lambda-Ausdruck
  // erstellt
  private ActionListener artikelNeuListener =
      e -> {
        String befehl = e.getActionCommand();
        ArtikelNeuGui panel = view.getArtikelNeuGui();
        if (befehl.equals("SAVE") || befehl.equals("SAVENQUIT")) { //$NON-NLS-1$
          try {
            Artikel neuerArtikel =
                view.artikelNeuGui.einlesen();
            av.hinzufuegen(neuerArtikel);
            info(Messages.getString("AV.I_ART_ADD_SUCC") + neuerArtikel.getArtikelnr()); //$NON-NLS-1$
            panel.loeschen();
            if (befehl.equals("SAVENQUIT"))
              view.verberge(panel);
          } catch (Exception ex) {
            view.setzeStatuszeile(ex.toString());
          }
        }
        if (e.getActionCommand().equals("CANCEL")) { //$NON-NLS-1$
          panel.loeschen();
          view.loescheStatuszeile();
          view.verberge(panel);
        }
      };

  // Die Erstellung �ber innere Klassen ist
  // aber in diesen F�llen �bersichtlicher
  private ArtikelBearbeitenListener abl =
      new ArtikelBearbeitenListener();

  private KundeNeuListener knl = new KundeNeuListener();

  private KundenBearbeitenListener kbl =
      new KundenBearbeitenListener();

  private AuftragNeuListener aufnl =
      new AuftragNeuListener();

  // Listener f�r noch nicht implementierte Funktionen
  private NichtImplementiertListener ni =
      new NichtImplementiertListener();

  public AVApplikation() {
    meinLocale = Locale.getDefault();
    view = new AVApplikationGUI(meinLocale);

    // Model-Komponenten erzeugen
    av = Artikelverwaltung.getInstance();
    kv = Kundenverwaltung.getInstance();
    aufv = Auftragsverwaltung.getInstance();
    benv = Benutzerverwaltung.getInstance();

    boolean benutzerOK = false;
    do {
      /*
       * Die Applikation startet in jedem Fall mit der
       * Authentifizierung (Benutzername/Passwort) des
       * Benutzers.
       */
      PasswortGui pg = new PasswortGui(view);
      if (pg.getAbbruch()) {
        /*
         * Benutzer- und Passworteingabe durch Benutzer
         * abgebrochen. Anwendung beenden
         */
        pg.dispose();
        anwendungBeenden();
      }
      /*
       * Nachschauen, ob Benutzername und Passwort
       * zusammenpassen
       */
      benutzerOK =
          authentifizierungOK(pg.getUsername(),
              pg.getPasswort());
      pg.dispose();

      if (!benutzerOK)
        fehler(Messages.getString("AV.E_USERNAMEPASSWORD"));
    }
    while (!benutzerOK);

    /*
     * Es folgt die Registrierung von Listenern f�r die
     * oberste Menuestufe
     */

    // Schliessen des Applikationsfensters
    view.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent ev) {
        anwendungBeenden();
      }
    });

    // Menuepunkt AV --> Beenden
    view.mntmAVplusBeenden.addActionListener(e -> {
      anwendungBeenden();
    });

    // Menupunkt Artikel --> Neu
    view.mntmArtAnlegen.addActionListener(e -> {
      view.zeige(view.artikelNeuGui);
    });

    // Menuepunkt Artikel --> Bearbeiten
    view.mntmArtSuchen.addActionListener(e -> {
      artikelBearbeiten();
    });

    /*
     * Menuepunkt Artikel --> Liste Bestand kleiner
     * Mindestbestand
     */
    view.mntmArtKleinerMBest.addActionListener(e -> {
      try {
        view.listenGui
            .listeAnzeigen(
                "AV.H_ARTSHORT",
                new ArtikelTableModel(av
                    .findeArtikelMitUnterdeckung(),
                    meinLocale));
        view.zeige(view.listenGui);
      } catch (Exception ex) {
        fehler(Messages.getString(Messages
            .getString("AV.E_ARTLIST"))
            + " "
            + ex.toString());
      }
    });

    // Menuepunkt Kunde --> Anlegen
    view.mntmKdAnlegen.addActionListener(e -> {
      view.zeige(view.getKundeNeuGui());
    });

    // Menuepunkt Kunde --> Bearbeiten
    view.mntmKdSuchen.addActionListener(e -> {
      kundenBearbeiten();
    });

    // Menuepunkt Auftrag --> Anlegen
    view.mntmAufAnlegen.addActionListener(e -> {
      view.auftragNeuGui.loescheKopf();
      view.auftragNeuGui.loescheAposEingabezeile();
      view.zeige(view.auftragNeuGui);
      // N�chste Auftragsnummer holen und in Maske
      // eintragen
      try {
        int auftragsnr = aufv.naechsteAuftragsnr();
        view.auftragNeuGui.getAuftragsnrTf().setText(
            "" + auftragsnr); //$NON-NLS-1$
      } catch (Exception ex) {
        fehler(Messages.getString("AV.E_ORDNUM")); //$NON-NLS-1$
      }

        /*
         * Aktuelles Datum passend f�r Benutzer-Locale in
         * Kurzform bei Auftragsdatum eintragen
         */
      DateTimeFormatter dtf =
          DateTimeFormatter.ofLocalizedDate(
              FormatStyle.SHORT).withLocale(meinLocale);
      view.auftragNeuGui.getAuftragsdatumTf().setText(
          LocalDate.now().format(dtf));

      // Neues leeres Table-Model f�r
      // Auftragspositionen setzen
      aposTM = new AuftragspositionTableModel(meinLocale);
      aposTM.addTableModelListener(aufnl);
      // ... und dem GUI bekannt geben
      view.auftragNeuGui.getAuftragspositionenTbl()
          .setModel(aposTM);
    });

    /*
     * Registrierung von Listenern f�r tiefere
     * Anwendungsstufen
     */
    // Listener bei Artikel neu Formular
    view.artikelNeuGui.getOkBtn().addActionListener(
        artikelNeuListener);
    // �bernehmen-Button in Anlage Neuer Artikel
    view.artikelNeuGui.getUebernehmenBtn()
        .addActionListener(artikelNeuListener);
    // Abbrechen: Felder l�schen
    view.artikelNeuGui.getAbbrechenBtn().addActionListener(
        artikelNeuListener);

    // Listener bei Artikel bearbeiten Formular
    view.artikelBearbeitenGui.getOkBtn().addActionListener(
        abl);
    view.artikelBearbeitenGui.getUebernehmenBtn()
        .addActionListener(abl);
    view.artikelBearbeitenGui.getAbbrechenBtn()
        .addActionListener(abl);
    view.artikelBearbeitenGui.getLoeschenBtn()
        .addActionListener(abl);

    // Listener bei Kunde neu Formular
    view.getKundeNeuGui().getAbbrechenBtn()
        .addActionListener(knl);
    view.getKundeNeuGui().getUebernehmenBtn()
        .addActionListener(knl);
    view.getKundeNeuGui().getOkBtn().addActionListener(knl);

    // Listener bei Kunde bearbeiten Formular
    view.kundeBearbeitenGui.getAbbrechenBtn()
        .addActionListener(kbl);
    view.kundeBearbeitenGui.getLoeschenBtn()
        .addActionListener(kbl);
    view.kundeBearbeitenGui.getUebernehmenBtn()
        .addActionListener(kbl);
    view.kundeBearbeitenGui.getOkBtn().addActionListener(
        kbl);

    // Listener bei Auftrag neu Formular
    view.auftragNeuGui.getKundennrTf().addFocusListener(
        aufnl);
    view.auftragNeuGui.getMengeTf().addFocusListener(aufnl);
    view.auftragNeuGui.getArtikelnrTf().addFocusListener(
        aufnl);
    view.auftragNeuGui.getHinzufuegenBtn()
        .addActionListener(aufnl);
    view.auftragNeuGui.getOkBtn().addActionListener(aufnl);
    view.auftragNeuGui.getUebernehmenBtn()
        .addActionListener(aufnl);
    view.auftragNeuGui.getAbbrechenBtn().addActionListener(
        aufnl);

    /*
     * Registrierung der Listener f�r noch nicht
     * implementierte Funktionen
     */
    view.mntmKdListeAuftragssumme.addActionListener(ni);
    view.mntmKdListeDatumAuftrag.addActionListener(ni);
    view.mntmKdOhneAuftraege.addActionListener(ni);

    view.mntmArtNachAufdatum.addActionListener(ni);
    view.mntmArtNachAufsumme.addActionListener(ni);
    view.mntmArtOhneAuftraege.addActionListener(ni);

    view.mntmAufSuchen.addActionListener(ni);
    view.mntmAufNachArt.addActionListener(ni);
    view.mntmAufNachDatum.addActionListener(ni);
    view.mntmAufNachKd.addActionListener(ni);
    view.mntmAufNachSumme.addActionListener(ni);
    view.mntmAufOffeneNachAufdatum.addActionListener(ni);
    view.mntmAufOffeneNachLDatum.addActionListener(ni);

    view.mntmHilfeHilfe.addActionListener(ni);
    view.mntmHilfeInfo.addActionListener(ni);
  }

  /**
   * Beenden der Anwendung. Hier m�ssen alle
   * "Aufr�umarbeiten" (Schlie�en der DB...) erledigt
   * werden.
   */
  private void anwendungBeenden() {
    if (view.sekGui.zeigeBestaetigungsDialog(Messages
        .getString("AV.Q_FINISH"))) //$NON-NLS-1$
      System.exit(0);
  }

  /**
   * Hier findet die Zugangs�berpr�fung statt.
   */
  private boolean authentifizierungOK(String user,
                                      char[] password) {
    System.out.println("Anmeldeversuch " + user
        + " mit Kennwort " + new String(password));
    try {
      Benutzer benutzer = benv.findUserName(user); //Benutzer suchen
      if (benutzer != null) { //Benutzer gefunden
        if (benutzer.passwortOk(password.toString())) { //Passwort pruefen
          return true;
        }
      }
    } catch (BenutzerException e) {
    }
    return false;
  }

  private void artikelBearbeiten() {
    int eingabe =
        view.sekGui.zeigeEingabeDialog(Messages
            .getString("AV.Q_ARTNUM")); //$NON-NLS-1$
    if (eingabe > 0) {
      // Nach dem entsprechenden Artikel suchen
      try {
        Artikel art = av.finden(eingabe);
        if (art != null) {
          // Fenster zur Bearbeitung anzeigen
          view.zeige(view.artikelBearbeitenGui);
          view.artikelBearbeitenGui
              .setTableModel(new AuftragTableModel(aufv
                  .findeAuftraegeZuArtikel(art), meinLocale));
          view.artikelBearbeitenGui.setArtikel(art);
        } else {
          /* Diesen Artikel gibt es nicht. */
          fehler(Messages.getString("AV.E_ARTNOTFOUND")
              + eingabe);
        }
      } catch (Exception ex) {
        fehler(Messages.getString("AV.E_ARTNOTFOUND") + eingabe); //$NON-NLS-1$
      }
    }
  }

  private void kundenBearbeiten() {
    int eingabe =
        view.sekGui.zeigeEingabeDialog(Messages
            .getString("AV.Q_CUSTNUM")); //$NON-NLS-1$
    if (eingabe > 0) {
      // Nach dem entsprechenden Kunden suchen
      try {
        view.zeige(view.kundeBearbeitenGui);
        Kunde kd = kv.finden(eingabe);
        if (kd != null) {
          view.kundeBearbeitenGui
              .setTableModel(new AuftragTableModel(aufv
                  .findeAuftraegeZuKunde(kd), meinLocale));
          view.kundeBearbeitenGui.kundeAnzeigen(kd);
        }
      } catch (Exception ex) {
        fehler(Messages.getString("AV.E_CUSTNOTFOUND") + eingabe); //$NON-NLS-1$
      }
    }
  }

  // Allgemeine Hilfsmethoden f�r die Ausgabe von
  // Infomeldungen, Warnungen
  // und Fehlern
  private void fehler(String msg) {
    view.sekGui.zeigeFehlerDialog(msg);
  }

  @SuppressWarnings("unused")
  private void warnung(String msg) {
    view.setzeStatuszeile(msg);
  }

  private void info(String msg) {
    view.setzeStatuszeile(msg);
  }

  public static void main(String[] args) {
    /*
     * Wir initialisieren im Event Dispatcher Thread, da die
     * Oberfl�che mit Swing realisiert wird.
     */
    Runnable initialisierer = () -> new AVApplikation();
    EventQueue.invokeLater(initialisierer);
  }

  /*
   * ========================================================
   * Es folgen innere Klassen zur Realisierung von Listenern
   * f�r verschiedene Dialogsituationen
   * ======================
   * ==================================
   */

  class ArtikelBearbeitenListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      ArtikelBearbeitenGui panel =
          view.getArtikelBearbeitenGui();
      String befehl = e.getActionCommand();
      Artikel art = null;

      // Aktualisieren
      if (befehl.equals("SAVE")
          || (befehl.equals("SAVENQUIT"))) {
        try {
          art = panel.getArtikel();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        if (art != null) {
          try {
            av.aktualisieren(art);
            info(Messages.getString("AV.I_UPDSUCC") + art.getArtikelnr()); //$NON-NLS-1$
            if (befehl.equals("SAVENQUIT")) {
              panel.loeschen();
              view.verberge(panel);
            }
          } catch (Exception ex) {
            fehler(Messages.getString("AV.E_UPDERROR") + " " + ex.toString()); //$NON-NLS-1$
          }
        } else { // Artikel konnte nicht vom GUI gelesen werden
          fehler(Messages.getString("AV.E_ART_INP_ERR2")); //$NON-NLS-1$
        }
      }

      // L�schen
      if (e.getActionCommand().equals("DELETE")) { //$NON-NLS-1$
        try {
          art = view.artikelBearbeitenGui.getArtikel();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
        if (art != null) {
          try {
            if (aufv.findeAuftraegeZuArtikel(art).size() > 0) {
              // Es gibt Auftr�ge zum Artikel. Dieser kann
              // daher nicht gel�scht werden.
              fehler(Messages.getString("AV.E_DEL_ART_IMP"));
            } else {
              av.entfernen(art);
              info(Messages.getString("AV.I_DELSUCC") + art.getArtikelnr()); //$NON-NLS-1$
              panel.loeschen();
              view.verberge(panel);
            }
          } catch (AuftragException ex) {
            fehler(Messages.getString("AV.E_INTERR")
                + ex.toString());
          } catch (Exception ex) {
            fehler(Messages.getString("AV.E_DEL_ERROR") + ex.toString()); //$NON-NLS-1$
          }
        } else { // Artikel konnte nicht vom GUI gelesen werden
          fehler(Messages.getString("AV.E_ART_INP_ERR2")); //$NON-NLS-1$
        }
      }

      // Abbrechen
      if (e.getActionCommand().equals("CANCEL")) { //$NON-NLS-1$
        panel.loeschen();
        view.verberge(panel);
      }
    }
  }

  class KundeNeuListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      KundeNeuGui panel = view.getKundeNeuGui();
      String befehl = e.getActionCommand();
      if ((befehl.equals("SAVE") || befehl.equals("SAVENQUIT"))) { //$NON-NLS-1$
        try {
          Kunde k = panel.einlesen();
          kv.hinzufuegen(k);
          info(Messages.getString("AV.I_CUST_INS_SUCC") + k.getKundennr()); //$NON-NLS-1$
          panel.loeschen();
          if (befehl.equals("SAVENQUIT"))
            view.verberge(panel);
        } catch (Exception ex) {
          fehler(Messages.getString("AV.E_CUST_ERR"));
        }
      }
      ;
      if (e.getActionCommand().equals("CANCEL")) { //$NON-NLS-1$
        panel.loeschen();
        view.loescheStatuszeile();
        view.verberge(panel);
      }
    }
  }

  class KundenBearbeitenListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      KundeBearbeitenGui panel =
          view.getKundeBearbeitenGui();
      String befehl = e.getActionCommand();
      // Aktualisieren
      if (befehl.equals("SAVE")
          || (befehl.equals("SAVENQUIT"))) {
        Kunde kd = panel.getKunde();
        if (kd != null) {
          try {
            kv.aktualisieren(kd);
            info(Messages.getString("AV.I_CUST_UPD_SUCC")
                + kd.getKundennr());
            if (befehl.equals("SAVENQUIT")) {
              panel.loeschen();
              view.verberge(panel);
            }
          } catch (Exception ex) {
            fehler(Messages.getString("AV.E_CUST_UPD_ERR")
                + ex.toString());
          }
        } else { // Kunde konnte nicht vom GUI gelesen werden
          fehler(Messages.getString("AV.E_CUST_INP_ERR2")); //$NON-NLS-1$
        }
      }

      // L�schen
      if (e.getActionCommand().equals("DELETE")) { //$NON-NLS-1$
        Kunde kd = panel.getKunde();
        if (kd != null) {
          try {
            kv.entfernen(kd);
            info(Messages.getString("AV.I_CUST_DEL_SUCC")
                + kd.getKundennr());
            panel.loeschen();
            view.verberge(panel);
          } catch (Exception ex) {
            fehler(Messages.getString("AV.E_CUST_DEL_ERR")
                + ex.toString());
          }
        } else { // Kunde konnte nicht vom GUI gelesen werden
          fehler(Messages.getString("AV.E_CUST_INP_ERR2")); //$NON-NLS-1$
        }
      }

      // Abbrechen: Fenster ohne �nderungen verlassen
      if (e.getActionCommand().equals("CANCEL")) { //$NON-NLS-1$
        panel.loeschen();
        view.verberge(panel);
      }
    }
  }

  class AuftragNeuListener implements ActionListener,
      FocusListener, TableModelListener {

    public void actionPerformed(ActionEvent arg0) {
      String actionCommand = arg0.getActionCommand();
      if (actionCommand.equals("+")) { //$NON-NLS-1$
        // Auftragsposition hinzuf�gen
        Auftragsposition neueApos =
            aposDatenLesenUndPruefen();
        if (neueApos != null) {
          if (aposTM.addAuftragsposition(neueApos)) {

            // Auftragsgesamtsumme aktualisieren
            view.auftragNeuGui
                .getAuftragssummeTf()
                .setText(
                    Geld.getBetragMitKomma(
                        aposTM
                            .getTotalAmount((String) view.auftragNeuGui
                                .getWaehrungCb()
                                .getSelectedItem()),
                        meinLocale));

            // Eingabezeile bereit machen f�r n�chste
            // Auftragsposition
            view.auftragNeuGui.loescheAposEingabezeile();
          } else {
            fehler(Messages
                .getString("AV.E_ORDPOS_INS_ERR")); //$NON-NLS-1$
          }
        }
      }

      if ((actionCommand.equals("SAVE") || (actionCommand.equals("SAVENQUIT")))) { //$NON-NLS-1$
        if (aposTM.getRowCount() < 1) {
          fehler(Messages.getString("AV.E_ORD_1POS_ERR")); //$NON-NLS-1$
        } else {
          Auftrag neuerAuf = auftragDatenLesenUndPruefen();
          if (neuerAuf != null) {
            // Nacheinander alle Auftragspositionen zum
            // Auftrag hinzuf�gen
            for (int i = 0; i < aposTM.getRowCount(); i++) {
              neuerAuf.einfuegenPos(aposTM
                  .getAuftragsposition(i));
            }

            // Auftrag an die Auftragsverwaltung weitergeben
            try {
              aufv.hinzufuegen(neuerAuf);
              info(Messages.getString("AV.I_ORD_ADD_SUCC")); //$NON-NLS-1$
              view.auftragNeuGui.loeschen();
              if (actionCommand.equals("SAVENQUIT"))
                view.verberge(view.auftragNeuGui);
            } catch (Exception ex) {
              fehler(Messages.getString("AV.E_ORD_ADD_ERR")); //$NON-NLS-1$
            }
          }
        }
      }

      if (actionCommand.equals("CANCEL")) { //$NON-NLS-1$
        view.auftragNeuGui.loeschen();
        view.verberge(view.auftragNeuGui);
      }
    }

    public void focusGained(FocusEvent arg0) {
      // Erst beim Focusverlust werden wir aktiv.
    }

    public void focusLost(FocusEvent arg0) {
      JTextField werWarEs =
          (JTextField) arg0.getComponent();
      int eingabe;
      if (werWarEs == view.auftragNeuGui.getKundennrTf()) {
        try {
          eingabe = Integer.parseInt(werWarEs.getText());
          // Kundennummer wurde eingegeben
          Kunde k1 = kv.finden(eingabe);
          view.auftragNeuGui.getNameTf().setText(
              k1.getName());
        } catch (Exception ex) {
          view.auftragNeuGui.getNameTf().setText("");
          fehler(Messages.getString("AV.E_CUST_INP_ERR"));
        }
      } else {
        if (werWarEs == view.auftragNeuGui.getArtikelnrTf()) {
          try {
            eingabe = Integer.parseInt(werWarEs.getText());
            // Artikelnummer wurde eingegeben:
            // Artikel suchen ...
            Artikel a1 = av.finden(eingabe);
            // ... und Artikelbezeichnung und Einzelpreis
            // eintragen
            view.auftragNeuGui.getBezeichnungTf().setText(
                a1.getArtikelbezeichnung());
            view.auftragNeuGui.getEinzelpreisTf().setText(
                Geld.getBetragMitKomma(a1.getPreis(),
                    meinLocale));
          } catch (Exception ex) {
            view.auftragNeuGui.getBezeichnungTf().setText(
                ""); //$NON-NLS-1$
            fehler(Messages.getString("AV.E_ART_INP_ERR"));
          }
        } else {
          if (werWarEs == view.auftragNeuGui.getMengeTf()) {
            try {
              eingabe =
                  Integer.parseInt(view.auftragNeuGui
                      .getMengeTf().getText());
              Geld einzelpreis =
                  new Geld(view.auftragNeuGui
                      .getEinzelpreisTf().getText(),
                      meinLocale,
                      (String) view.auftragNeuGui
                          .getWaehrungCb()
                          .getSelectedItem());
              if (einzelpreis != null)
                view.auftragNeuGui.getGesamtpreisTf()
                    .setText(
                        Geld.getBetragMitKomma(einzelpreis
                                .multiplizieren(eingabe),
                            meinLocale));
            } catch (Exception ex) {
              fehler(Messages.getString("AV.E_QUANT_ERR"));
            }
          }
        }
      }
    }

    public void tableChanged(TableModelEvent arg0) {
      int column = arg0.getColumn();
      int row = arg0.getFirstRow();
      if (column == 0) {
        // In der L�schen-Spalte wurde geklickt
        Auftragsposition a =
            aposTM.getAuftragsposition(row);
        if (a != null) {
          if (view.sekGui.zeigeBestaetigungsDialog(Messages
              .getString("AV.Q_ORD_POS_DEL"))) { //$NON-NLS-1$
            // Auftragsposition soll gel�scht werden
            aposTM.removeAuftragsposition(row);
            // Auftragsgesamtsumme aktualisieren
            view.auftragNeuGui
                .getAuftragssummeTf()
                .setText(
                    Geld.getBetragMitKomma(
                        aposTM
                            .getTotalAmount((String) view.auftragNeuGui
                                .getWaehrungCb()
                                .getSelectedItem()),
                        meinLocale));
          }
        }
        view.repaint();
      }
    }

    // Es folgen Hilfsmethoden zum �berp�fen der eingegeben
    // Daten zur
    // Auftragsposition

    private Auftragsposition aposDatenLesenUndPruefen() {
      Auftragsposition ergebnis = null;
      Artikel artikel = artikelnrLesenUndPruefen();
      if (artikel != null) {
        int menge = mengeLesenUndPruefen();
        if (menge > 0) {
          try {
            ergebnis =
                new Auftragsposition(aposTM.getRowCount(),
                    artikel, menge, new Geld(
                    view.auftragNeuGui
                        .getEinzelpreisTf().getText(),
                    meinLocale,
                    (String) view.auftragNeuGui
                        .getWaehrungCb()
                        .getSelectedItem()), null);
          } catch (AuftragException ex) {
            fehler(Messages.getString("AV.E_ORDPOS_ERR"));
          }
        }
      }
      return ergebnis;
    }

    private Artikel artikelnrLesenUndPruefen() {
      Artikel ergebnis = null;
      int artikelnr = -1;
      try {
        artikelnr =
            Integer.parseInt(view.auftragNeuGui
                .getArtikelnrTf().getText());
      } catch (Exception ex) {
        fehler(Messages.getString("AV.E_ARTNUM_ERR")); //$NON-NLS-1$
      }
      if (artikelnr > 0) {
        try {
          ergebnis = av.finden(artikelnr);
        } catch (Exception ex) {
          fehler(Messages.getString("AV.E_ARTNOTFOUND")); //$NON-NLS-1$
        }
      }
      return ergebnis;
    }

    private int mengeLesenUndPruefen() {
      int ergebnis = -1;
      try {
        ergebnis =
            Integer.parseInt(view.auftragNeuGui
                .getMengeTf().getText());
      } catch (Exception ex) {
        fehler(Messages.getString("AV.E_QUANT_ERR")); //$NON-NLS-1$
      }
      if (ergebnis <= 0)
        fehler(Messages.getString("AV.E_QUANT_ERR")); //$NON-NLS-1$
      return ergebnis;
    }

    private LocalDate lieferdatumLesenUndPruefen(
        LocalDate auftragsdatum) {
      LocalDate lieferdatum =
          datumLesenUndPruefen(view.auftragNeuGui
              .getLieferdatumTf().getText());
      if (lieferdatum == null) {
        fehler(Messages.getString("AV.E_LDATE_ERR")); //$NON-NLS-1$
        return null;
      }

      if (lieferdatum.isBefore(auftragsdatum)) {
        fehler(Messages
            .getString("AV.E_DELDAT_BEFORE_ORDAT")); //$NON-NLS-1$
        return null;
      }

      return lieferdatum;
    }

    private Auftrag auftragDatenLesenUndPruefen() {
      Auftrag ergebnis = null;
      int auftragsnr = 0;
      try {
        auftragsnr =
            Integer.parseInt(view.auftragNeuGui
                .getAuftragsnrTf().getText());
      } catch (Exception ex) {
        fehler(Messages.getString("AV.E_ORDNUM_ERR")); //$NON-NLS-1$
      }

      if (auftragsnr > 0) {
        LocalDate auftragsdatum =
            datumLesenUndPruefen(view.auftragNeuGui
                .getAuftragsdatumTf().getText());
        if (auftragsdatum == null) {
          fehler(Messages.getString("AV.E_ORDDAT_ERR")); //$NON-NLS-1$
        } else {
          LocalDate lieferdatum =
              lieferdatumLesenUndPruefen(auftragsdatum);
          if (lieferdatum != null) {
            Kunde kunde = kundeLesenUndPruefen();
            if (kunde != null) {
              Currency waehrung =
                  Currency
                      .getInstance((String) view.auftragNeuGui
                          .getWaehrungCb()
                          .getSelectedItem());
              try {
                ergebnis =
                    new Auftrag(auftragsnr, auftragsdatum,
                        lieferdatum, kunde, waehrung);
              } catch (AuftragException ex) {
                fehler(Messages.getString("AV.E_ORD_ERR")); //$NON-NLS-1$
              }
            }
          }
        }
      }

      return ergebnis;
    }

    private Kunde kundeLesenUndPruefen() {
      Kunde ergebnis = null;
      int kundennr = 0;
      try {
        kundennr =
            Integer.parseInt(view.auftragNeuGui
                .getKundennrTf().getText());
        ergebnis = kv.finden(kundennr);
      } catch (Exception ex) {
        fehler(Messages
            .getString("AV.E_CUSTNUM_CUSTFOUND_ERR")); //$NON-NLS-1$
      }
      return ergebnis;
    }

    // Es folgen allgemeine Hilfsmethoden

    /**
     * Datumstext in LocalDate umwandeln
     */
    private LocalDate datumLesenUndPruefen(String datumstext) {
      LocalDate ergebnis = null;
      try {
        ergebnis =
            LocalDate.parse(datumstext, DateTimeFormatter
                .ofLocalizedDate(FormatStyle.SHORT)
                .withLocale(meinLocale));
      } catch (Exception ex) {
        // Nichts tun
      }
      return ergebnis;
    }
  }

  class NichtImplementiertListener implements
      ActionListener {
    public void actionPerformed(ActionEvent e) {
      fehler("Funktion ist noch nicht implementiert!");
    }
  }
}