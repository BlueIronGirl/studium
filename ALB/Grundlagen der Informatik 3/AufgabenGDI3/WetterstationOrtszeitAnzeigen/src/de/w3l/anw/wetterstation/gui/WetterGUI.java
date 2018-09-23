package de.w3l.anw.wetterstation.gui;

/* Programmname: Wetterstation
 * GUI-Klasse: WetterGUI
 * Aufgabe: Realisiert das Anwendungsfenster
 */

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

import de.w3l.anw.wetterstation.applikation.Wetter;
import de.w3l.anw.wetterstation.applikation.WetterContainer;

public class WetterGUI extends JFrame {
  private static final long serialVersionUID =
      -3836994649164782141L;

  // Konstanten fuer das Layout
  // Wo wird mit dem Anordnen der Komponenten begonnen
  private static final int XStart = 0, YStart = 1;

  // Die minimale Temperatur fuer alle Temperaturanzeigen
  private static final int TempMin = -20;

  // Die maximale Temperatur fuer alle Temperaturanzeigen
  private static final int TempMax = 40;

  // Attribute

  // Der Container fuer die Wetterdatensaetze
  private WetterContainer einWetterContainer;

  // Der aktuell selektierte Wetter-Datensatz
  private int wetterAktuell;

  // Locale und Formatierer f�r die Anzeige von
  // Datum und Zeit
  Locale meinLocale = Locale.getDefault();

  DateTimeFormatter dtfDatum = DateTimeFormatter
      .ofLocalizedDate(FormatStyle.SHORT).withLocale(
          meinLocale);

  DateTimeFormatter dtfZeit = DateTimeFormatter
      .ofLocalizedTime(FormatStyle.SHORT).withLocale(
          meinLocale);

  // die Instrumente
  // Messinstrument fuer die Temperatur
  private Messinstrument einThermometer =
      new Messinstrument(TempMax, TempMin, 1, "C",
          "Temperatur");

  // Messinstrument fuer den Taupunkt
  private Messinstrument einTaupunktThermometer =
      new Messinstrument(TempMax, TempMin, 1, "�C",
          "Taupunkt");

  // Messinstrument fuer die gef�hlte Temperatur
  private Messinstrument einGefuehlteTempThermometer =
      new Messinstrument(TempMax, TempMin, 1, "�C",
          "Gef�hlte Temp.");

  // Messinstrument fuer die Luftfeuchte
  private Messinstrument einLuftfeuchteMesser =
      new Messinstrument(100, 0, 2, "%", "Luftfeuchte");

  // Ein Windrichtungsanzeiger
  private Windmesser einWindrichtungsmesser =
      new Windmesser();

  // Digitalanzeige fuer die Windgeschwindigkeit
  private Digitalanzeige eineWindanzeige =
      new Digitalanzeige("km/h", "Windgeschwindigkeit");

  // Digitalanzeige f�r UV-Index
  private Digitalanzeige eineUvIndexanzeige =
      new Digitalanzeige("", "UV-Index");

  /*
   * private Sichtblende eineSichtblende = new
   * Sichtblende();
   */
  // Komponente zum anzeigen der Luftdruckkurve.
  private Kurvenanzeiger einKurvenanzeiger =
      new Kurvenanzeiger();

  // Eine Fl�che zum Aufnehmen der Zeitauswahl.
  private Messkomponente eineZeitauswahl =
      new Bedienungsflaeche();

  // Fl�che zum Anzeigen der Ortsinformationen
  private Messkomponente eineOrtsinformation =
      new Messkomponente();

  private JComboBox<String> tagesauswahl =
      new JComboBox<String>();

  private JComboBox<String> zeitauswahl =
      new JComboBox<String>();

  private JButton plusKnopf = new JButton("+");

  private JButton minusKnopf = new JButton("-");

  private JLabel bereitgestelltText = new JLabel(
      "<HTML><B>Daten bereit-<p/>gestellt durch</B>",
      JLabel.CENTER);

  private JButton bilderflaeche; // Da kommt das

  // Providerbild hin

  private ImageIcon providerBild;

  private JLabel dargestellterOrt = new JLabel("",
      JLabel.LEFT);

  private JLabel laengeText = new JLabel("L�nge:");

  private JLabel laengeInfo = new JLabel();

  private JLabel breiteText = new JLabel("Breite:");

  private JLabel breiteInfo = new JLabel();

  private JLabel sonnenaufText = new JLabel("Sonnenauf.:");

  private JLabel sonnenaufInfo = new JLabel();

  private JLabel sonnenuntText =
      new JLabel("Sonnenunter.:");

  private JLabel sonnenuntInfo = new JLabel();

  private JLabel wetterbildflaeche = new JLabel();

  private ImageIcon wetterBild;

  private Digitaluhr uhrflaeche;

  // die benutzten Swing GUI-Elemente
  JMenuBar einMenuebalken = new JMenuBar();

  JMenu dateiMenue = new JMenu();

  JMenuItem beendenOption = new JMenuItem();

  JMenu hilfeMenue = new JMenu();

  JMenuItem infoOption = new JMenuItem();

  public WetterGUI() {
    // Container erzeugen
    einWetterContainer =
        new WetterContainer(
            Anwendungsparameter.getParameter("ORT_ID")
                + "."
                + Anwendungsparameter
                .getParameter("FILEEXT"));
    try {
      providerBild =
          new ImageIcon(
              Anwendungsparameter.getParameter("PROV_BILD"));
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    // Allgemeine Einstellungen
    setJMenuBar(einMenuebalken);
    setTitle("Internet-Wetterstation");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    getContentPane().setLayout(null);
    setResizable(false);
    setVisible(false);

    // Menue aufbauen
    dateiMenue.setText("Datei");
    dateiMenue.setActionCommand("File");
    dateiMenue.setMnemonic((int) 'D');
    einMenuebalken.add(dateiMenue);
    beendenOption.setText("Beenden");
    beendenOption.setActionCommand("Beenden");
    beendenOption.setMnemonic((int) 'B');
    dateiMenue.add(beendenOption);
    hilfeMenue.setText("Hilfe");
    hilfeMenue.setActionCommand("Help");
    hilfeMenue.setMnemonic((int) 'H');
    einMenuebalken.add(hilfeMenue);
    infoOption
        .setHorizontalTextPosition(SwingConstants.RIGHT);
    infoOption.setText("Info...");
    infoOption.setActionCommand("Info...");
    infoOption.setMnemonic((int) 'I');
    hilfeMenue.add(infoOption);

    // die Instrumente einfuegen
    // Thermometer (Durchschnitt)
    getContentPane().add(einThermometer);
    einThermometer.setLocation(XStart, YStart);
    einThermometer.setBalkenfarbe(Color.green);

    // Luftfeuchtigkeit
    getContentPane().add(einLuftfeuchteMesser);
    einLuftfeuchteMesser.setLocation(XStart
        + Messinstrument.HGroesse, YStart);
    einLuftfeuchteMesser.setBalkenfarbe(Color.blue);

    // Taupunkt
    getContentPane().add(einTaupunktThermometer);
    einTaupunktThermometer.setLocation(XStart + 2
        * Messinstrument.HGroesse, YStart);

    // Gef�hlte Temperatir
    getContentPane().add(einGefuehlteTempThermometer);
    einGefuehlteTempThermometer.setLocation(XStart + 3
        * Messinstrument.HGroesse, YStart);
    einGefuehlteTempThermometer
        .setBalkenfarbe(Color.yellow);

    // Windmesser
    getContentPane().add(einWindrichtungsmesser);
    einWindrichtungsmesser.setLocation(XStart, YStart
        + Messinstrument.VGroesse);

    getContentPane().add(eineWindanzeige);
    eineWindanzeige.setLocation(XStart, YStart
        + Messinstrument.VGroesse + Windmesser.VGroesse);

    // UV-Index
    getContentPane().add(eineUvIndexanzeige);
    eineUvIndexanzeige.setLocation(XStart, YStart
        + Messinstrument.VGroesse + Windmesser.VGroesse
        + Digitalanzeige.VGroesse);

    // Kurvenanzeiger
    getContentPane().add(einKurvenanzeiger);
    einKurvenanzeiger.setLocation(XStart
        + Messinstrument.HGroesse, YStart
        + Messinstrument.VGroesse);
    einKurvenanzeiger.setEinheitX("Uhr");
    einKurvenanzeiger.setMinX(0);
    einKurvenanzeiger.setMaxX(24);
    einKurvenanzeiger.setSkalierungX(0.5f);
    einKurvenanzeiger.setHaeufigkeitX(4);
    einKurvenanzeiger.setEinheitY("mBar");
    einKurvenanzeiger.setMinY(960);
    einKurvenanzeiger.setMaxY(1040);
    einKurvenanzeiger.setHaeufigkeitY(5);
    einKurvenanzeiger.setSkalierungY(1);
    einKurvenanzeiger.setLabel("Luftdruck");

    // Auswahl (Tag und Stunde)
    getContentPane().add(eineZeitauswahl);
    eineZeitauswahl.setBounds(
        XStart + Messinstrument.HGroesse
            + Kurvenanzeiger.HGroesse, YStart,
        Messinstrument.HGroesse, Messinstrument.VGroesse);
    eineZeitauswahl.setLayout(null);

    eineZeitauswahl.add(tagesauswahl);
    tagesauswahl.setBounds(15, 30, 120, 25);
    tagesauswahl.setEditable(false);

    eineZeitauswahl.add(zeitauswahl);
    zeitauswahl.setBounds(15, 70, 120, 25);
    zeitauswahl.setEditable(false);

    eineZeitauswahl.add(minusKnopf);
    minusKnopf
        .setFont(new Font("SansSerif", Font.BOLD, 14));
    minusKnopf.setBounds(15, 110, 50, 30);

    eineZeitauswahl.add(plusKnopf);
    plusKnopf.setFont(new Font("SansSerif", Font.BOLD, 14));
    plusKnopf.setBounds(70, 110, 50, 30);

    // "Daten bereitgestellt durch"
    eineZeitauswahl.add(bereitgestelltText);
    bereitgestelltText.setFont(new Font("SansSerif",
        Font.PLAIN, 12));
    bereitgestelltText.setBounds(15, 150, 120, 50);

    // Bild einfuegen
    bilderflaeche = new JButton(providerBild);
    bilderflaeche.setBorderPainted(false);
    bilderflaeche.setSize(100, 60);
    eineZeitauswahl.add(bilderflaeche);
    bilderflaeche.setBounds(25, 200, 100, 60);

    // Informationen zur Stadt anzeigen
    getContentPane().add(eineOrtsinformation);
    eineOrtsinformation.setBounds(
        XStart + Messinstrument.HGroesse
            + Kurvenanzeiger.HGroesse, XStart
            + Messinstrument.VGroesse,
        Messinstrument.HGroesse, Messinstrument.VGroesse);
    eineOrtsinformation.setLayout(null);

    eineOrtsinformation.add(dargestellterOrt);
    dargestellterOrt.setBounds(15, 30, 120, 25);
    dargestellterOrt.setFont(new Font("SansSerif",
        Font.BOLD, 12));

    eineOrtsinformation.add(laengeText);
    laengeText.setBounds(15, 60, 60, 25);
    eineOrtsinformation.add(laengeInfo);
    laengeInfo.setBounds(90, 60, 50, 25); // 3 war 40
    laengeInfo.setHorizontalAlignment(JLabel.RIGHT);

    eineOrtsinformation.add(breiteText);
    breiteText.setBounds(15, 85, 60, 25);
    eineOrtsinformation.add(breiteInfo);
    breiteInfo.setBounds(90, 85, 50, 25); // 3 war 40
    breiteInfo.setHorizontalAlignment(JLabel.RIGHT);

    eineOrtsinformation.add(sonnenaufText);
    sonnenaufText.setBounds(15, 110, 80, 25);
    eineOrtsinformation.add(sonnenaufInfo);
    sonnenaufInfo.setBounds(90, 110, 40, 25);
    sonnenaufInfo.setHorizontalAlignment(JLabel.RIGHT);

    eineOrtsinformation.add(sonnenuntText);
    sonnenuntText.setBounds(15, 135, 80, 25);
    eineOrtsinformation.add(sonnenuntInfo);
    sonnenuntInfo.setBounds(90, 135, 40, 25);
    sonnenuntInfo.setHorizontalAlignment(JLabel.RIGHT);

    // Wetterbild einf�gen
    wetterbildflaeche.setSize(64, 64);
    wetterbildflaeche.setIcon(wetterBild);
    eineOrtsinformation.add(wetterbildflaeche);
    wetterbildflaeche.setBounds(40, 160, 64, 64);

    // Uhr als Timer hinzuf�gen
    uhrflaeche = new Digitaluhr(meinLocale);

    ZoneId system = ZoneId.systemDefault();
    ZoneOffset offset = ZoneOffset.ofHours(2);
    String prefix = "GMT";  // Greenwich Mean Time
    ZoneId gmt = ZoneId.ofOffset(prefix, offset);
    uhrflaeche.setDeltaUTC(offset);
    uhrflaeche
        .setFont(new Font("SansSerif", Font.BOLD, 24));
    uhrflaeche.setHorizontalAlignment(Digitaluhr.CENTER);
    eineOrtsinformation.add(uhrflaeche);
    uhrflaeche.setBounds(15, 230, 120, 40);
    uhrflaeche.setBorder(BorderFactory
        .createLineBorder(Color.black));

    /*
     * Neuen Timer f�r uhrflaeche erzeugen, der alle 980 ms
     * angestossen wird.
     */
    new Timer(980, uhrflaeche).start();
    anzeigeInitialisieren();

    Fensterabhoerer einFensterabhoerer =
        new Fensterabhoerer();
    addWindowListener(einFensterabhoerer);
    Aktionsabhoerer einAktionsabhoerer =
        new Aktionsabhoerer();
    infoOption.addActionListener(einAktionsabhoerer);
    beendenOption.addActionListener(einAktionsabhoerer);
    tagesauswahl.addActionListener(einAktionsabhoerer);
    zeitauswahl.addActionListener(einAktionsabhoerer);
    // Kurvenauswahl.addActionListener(einAktionsabhoerer);
    plusKnopf.addActionListener(einAktionsabhoerer);
    minusKnopf.addActionListener(einAktionsabhoerer);
    bilderflaeche.addActionListener(einAktionsabhoerer);
    autoAktualisiere();
  }

  // Konstruktor
  public WetterGUI(String sTitle) {
    this();
    setTitle(sTitle);
  }

  // Die main-Operation.
  // Setzt das Look and Feel auf das System Look and Feel.
  // Erzeugt eine Instanz von WetterGUI und macht sie
  // sichtbar.
  public static void main(String args[]) {
    try {
      // Das Look and Feel auf das System Look and Feel
      // setzen.
      try {
        UIManager.setLookAndFeel(UIManager
            .getSystemLookAndFeelClassName());
      } catch (Exception e) {
      }

      // Eine Instanz von WetterGUI erzeugen und sichtbar
      // machen.
      (new WetterGUI()).setVisible(true);
    } catch (Throwable t) {
      t.printStackTrace();
      // Die Anwendung wird mit einem Fehlercode
      // verlassen.
      System.exit(1);
    }
  }

  /*
   * Diese Operation wird aufgerufen, wenn eine Komponente
   * in einen Container eingefuegt wurde. Sie wird niemals
   * direkt im user code verwendet. In der Operation wird
   * die Groesse des Fensters richtig gesetzt.
   */
  public void addNotify() {
    super.addNotify();

    // Bestimme die Groesse des Fensters in Abhaengigkeit
    // seines Rahmens, seines Menuebalkens und der in ihm
    // enthaltenen Komponenten.
    JMenuBar MenueBalken = getRootPane().getJMenuBar();
    int MenueBalkenGroesse =
        MenueBalken.getPreferredSize().height;
    // die Ausdehnungen des Fensterrahmens
    Insets Grenzen = getInsets();
    setSize(Grenzen.left + Grenzen.right + 2 * XStart + 5
        * Messinstrument.HGroesse, Grenzen.top
        + Grenzen.bottom + MenueBalkenGroesse + 2 * YStart
        + 2 * Messinstrument.VGroesse);
  }

  void beendeAnwendung() {
    // Wetterdaten speichern
    einWetterContainer.endeAnwendung();

    try {
      // Beep
      Toolkit.getDefaultToolkit().beep();
      // den Beendendialog zeigen
      int Antwort =
          JOptionPane.showConfirmDialog(this,
              "Wollen Sie die Anwendung wirklich beenden?",
              "Ende Dialog", JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE);

      if (Antwort == JOptionPane.YES_OPTION) {
        this.setVisible(false); // Fenster unsichtbar
        this.dispose(); // Systemressoucen freigeben
        System.exit(0); // Anwendung beenden
      }
    } catch (Exception e) {
    }
  }

  class Fensterabhoerer extends
      java.awt.event.WindowAdapter {
    public void windowClosing(
        java.awt.event.WindowEvent event) {
      Object object = event.getSource();
      if (object == WetterGUI.this)
        schliesseFenster(event);
    }
  }

  void schliesseFenster(WindowEvent event) {
    try {
      this.beendeAnwendung();
    } catch (Exception e) {
    }
  }


  class Aktionsabhoerer implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      Object object = event.getSource();
      if (object == infoOption)
        ausfuehrenInfoItemAktion(event);
      else if (object == beendenOption)
        ausfuehrenBeendenItemAktion(event);
      else if (object == tagesauswahl)
        ausfuehrenTagesauswahlAktion(event);
      else if (object == zeitauswahl)
        ausfuehrenZeitauswahlAktion(event);
      else if (object == minusKnopf)
        ausfuehrenMinusKnopfAktion(event);
      else if (object == plusKnopf)
        ausfuehrenPlusKnopfAktion(event);
      else if (object == bilderflaeche)
        ausfuehrenBilderflaecheAktion(event);
    }
  }

  void ausfuehrenInfoItemAktion(ActionEvent event) {
    try {
      InfoDialog einInfoDialog = new InfoDialog(this);
      einInfoDialog.setModal(true);
      einInfoDialog.setVisible(true);
    } catch (Exception e) {
    }
  }

  void ausfuehrenBeendenItemAktion(ActionEvent event) {
    try {
      beendeAnwendung();
    } catch (Exception e) {
    }
  }

  void ausfuehrenTagesauswahlAktion(ActionEvent event) {
    try {
      // Wir holen uns das erste Wetter für den
      // ausgewählten Tag
      Wetter w =
          einWetterContainer.getWetterFuerDatum(
              LocalDate.parse((String) tagesauswahl.getSelectedItem(), dtfDatum))
              .firstElement();

      wetterAktuell = einWetterContainer.findeWetterIndex(w);
      guiAktualisieren(w);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void ausfuehrenZeitauswahlAktion(ActionEvent event) {
    String tag = (String) tagesauswahl.getSelectedItem();
    String zeit = (String) zeitauswahl.getSelectedItem();
    try {
      LocalDateTime zeitpunkt = LocalDateTime.parse(tag + " " + zeit, DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
      Wetter w =
          einWetterContainer
              .getWetterFuerZeitpunkt(zeitpunkt);
      wetterAktuell = einWetterContainer.findeWetterIndex(w);
      guiAktualisieren(w);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void ausfuehrenMinusKnopfAktion(ActionEvent event) {
    if (wetterAktuell > 0) {
      guiAktualisieren(einWetterContainer
          .getWetter(--wetterAktuell));
    }
  }

  void ausfuehrenPlusKnopfAktion(ActionEvent event) {
    if ((wetterAktuell + 1) < einWetterContainer
        .getAnzahlDatensaetze()) {
      guiAktualisieren(einWetterContainer
          .getWetter(++wetterAktuell));
    }
  }

  /**
   * Aufruf des Standard-Webbrowsers der verwendeten
   * Plattform für die Internetseite des
   * Wetterdatenlieferanten. Funktioniert ab Java 6
   */
  private void ausfuehrenBilderflaecheAktion_(
      ActionEvent event) {
    try {
      Desktop.getDesktop().browse(
          new URI(Anwendungsparameter
              .getParameter("HTTPSERVER_URL")));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /*
   * Aufruf des Standard-Webbrowsers für die Plattform
   * Windows für die Internetseite des
   * Wetterdatenlieferanten. Funktioniert auch für frühere
   * Java-Versionen.
   */
  private void ausfuehrenBilderflaecheAktion(
      ActionEvent event) {
    try {
      Runtime.getRuntime().exec(
          "rundll32 url.dll,FileProtocolHandler "
              + Anwendungsparameter
              .getParameter("HTTPSERVER_URL"));
    } catch (Exception ex) {
      System.err.println("Browserstart nicht möglich!");
    }
  }

  public void anzeigeInitialisieren() {
    LocalDate tag;
    tagesauswahl.setModel(new DefaultComboBoxModel(
        einWetterContainer.getVorhandeneTage(dtfDatum)));
    // Auf letztes Element setzen
    tagesauswahl.setSelectedIndex(tagesauswahl
        .getItemCount() - 1);
    try {
      tag = LocalDate.parse((String) tagesauswahl.getSelectedItem(), dtfDatum);

      zeitauswahl.setModel(new DefaultComboBoxModel(
          einWetterContainer.getVorhandeneZeiten(tag, dtfZeit)));
      zeitauswahl.setSelectedIndex(zeitauswahl
          .getItemCount() - 1);

      anzeigeAktualisieren(einWetterContainer
          .getWetter(einWetterContainer
              .getAnzahlDatensaetze() - 1));
      kurvenanzeigerAktualisieren(tag);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void anzeigeAktualisieren(Wetter aktuellesWetter) {
    System.out.println("Anzeige aktualisieren auf "
        + aktuellesWetter.getZeit());
    System.out.println(aktuellesWetter);
    try {
      uhrflaeche.setDeltaUTC(aktuellesWetter.getOrt().getOffset());
      einThermometer.setWert(aktuellesWetter
          .getTemperatur());
      einLuftfeuchteMesser.setWert(aktuellesWetter
          .getLuftfeuchte());
      einTaupunktThermometer.setWert(aktuellesWetter
          .getTaupunkt());
      einGefuehlteTempThermometer.setWert(aktuellesWetter
          .getGefuehlteTemperatur());
      eineWindanzeige.setWert(aktuellesWetter
          .getWindgeschwindigkeit());
      einWindrichtungsmesser
          .setWindrichtung(aktuellesWetter
              .getWindrichtung());
      eineUvIndexanzeige.setWert(aktuellesWetter
          .getUvIndex());

      // Ortsinformationen einblenden
      dargestellterOrt.setText(aktuellesWetter.getOrt()
          .getName());
      laengeInfo.setText(aktuellesWetter.getOrt()
          .getLaengeWE());
      breiteInfo.setText(""
          + aktuellesWetter.getOrt().getBreiteNS());
      sonnenaufInfo.setText(dtfZeit.format(aktuellesWetter.getSonnenaufgang()));
      sonnenuntInfo.setText(dtfZeit.format(aktuellesWetter.getSonnenuntergang()));
      wetterBild =
          new ImageIcon(""
              + aktuellesWetter.getWetterzustand() + ".png");
      wetterbildflaeche.setIcon(wetterBild);

      // Die Auswahlboxen für Tag und Stunde auf den
      // letzten Messwert
      // setzen
      tageslisteAktualisieren(aktuellesWetter.getZeit().toLocalDate());
      stundenlisteAktualisieren(aktuellesWetter.getZeit());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void kurvenanzeigerAktualisieren(LocalDate tag) {
    Vector<Wetter> wetter =
        einWetterContainer.getWetterFuerDatum(tag);
    int anzDaten = wetter.size();
    Vector<Point2D.Float> punkte =
        new Vector<Point2D.Float>(anzDaten);
    for (int i = 0; i < anzDaten; i++) {
      Wetter w = wetter.elementAt(i);

      int stunde = w.getZeit().getHour();
      int minute = w.getZeit().getMinute();

      float punktx = stunde + minute / 60f; // + 0.5f;
      punkte.add(new Point2D.Float(punktx, (float) w
          .getLuftdruck()));
    }
    einKurvenanzeiger.setWerte(punkte, true);
  }

  private void tageslisteAktualisieren(LocalDate tag) {
    String tagString = dtfDatum.format(tag);
    DefaultComboBoxModel model =
        new DefaultComboBoxModel(einWetterContainer
            .getVorhandeneTage(dtfDatum));
    // ACHTUNG: Der nächste Schritt ist entscheidend:
    // Selektieren Sie den
    // passenden Eintrag bevor(!) Sie das Model in die
    // Combo-Box eintragen.
    // Andernfalls wird beim Selektieren ein zweites
    // Ereignis ausgelöst, das
    // wiederum zur Aktualisierung der Benutzeroberfläche
    // führt!
    model.setSelectedItem(tagString);
    tagesauswahl.setModel(model);
    /*
     * Den folgenden Befehl auf keinen Fall anwenden, siehe
     * oben! tagesauswahl.setSelectedItem(tagString);
     */
  }

  private void stundenlisteAktualisieren(LocalDateTime datum) {
    String stundeString = dtfZeit.format(datum);
    DefaultComboBoxModel model =
        new DefaultComboBoxModel(einWetterContainer
            .getVorhandeneZeiten(datum.toLocalDate(), dtfZeit));
    // Hier gilt dieselbe Aussage wie für die entsprechende
    // Stelle in der
    // Methode tageslisteAktualisieren
    model.setSelectedItem(stundeString);
    zeitauswahl.setModel(model);
  }

  /**
   * Diese Methode implementiert eine Endlosschleife, in der
   * in periodischen Abständen neue Wetterdaten abgefragt
   * werden.
   */
  private void autoAktualisiere() {
    /*
     * Einen Thread aus einer neuen anonymen Klasse, die
     * Runnable implementiert erzeugen. In der run-Methode
     * werden neue Wetterdaten abgerufen.
     */
    Thread t1 = new Thread(new Runnable() {
      public void run() {
        while (true) {
          try {
            Thread.sleep(10000);
            /*
             * mindestens 10 Sekunden schlafen, damit
             * Wetterdaten nicht zu oft abgerufen werden
             */
          } catch (Exception ex) {
          }
          /*
           * datensatzHinzufügen überwacht selbsttätig, wann
           * der nächste Datensatz abgerufen werden darf und
           * blockiert solange.
           */
          einWetterContainer.datensatzHinzufuegen();
          wetterAktuell =
              einWetterContainer.getAnzahlDatensaetze() - 1;
          guiAktualisieren(einWetterContainer
              .getWetter(wetterAktuell));
        }
      }
    });
    t1.start();
  }

  private void guiAktualisieren(Wetter neuesWetter) {
    anzeigeAktualisieren(neuesWetter);
    kurvenanzeigerAktualisieren(neuesWetter.getZeit().toLocalDate());
  }
}

  