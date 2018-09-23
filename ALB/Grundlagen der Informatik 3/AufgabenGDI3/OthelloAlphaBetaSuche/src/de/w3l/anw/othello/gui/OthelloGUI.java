package de.w3l.anw.othello.gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import de.w3l.anw.othello.engine.Othello;
import de.w3l.anw.othello.engine.Zug;

public class OthelloGUI {

  private final String spielregelURL =
      "http://de.wikipedia.org/w/index.php?title=Othello_%28Spiel%29&oldid=35362516#Regeln";

  private JFrame jFrame = null;

  private JPanel jContentPane = null;

  private JMenuBar jJMenuBar = null;

  private JMenu spielMenu = null;

  private JMenu spielstufeMenu = null;

  private JMenu hilfeMenu = null;

  private JMenuItem beendenMenuItem = null;

  private JMenuItem ueberMenuItem = null;

  private JDialog ueberDialog = null;

  private JPanel ueberContentPane = null;

  private JLabel ueberText = null;

  private JMenuItem spielregelnMenuItem = null;

  private JRadioButtonMenuItem einfachJRadioButtonMenuItem =
      null;

  private JRadioButtonMenuItem normalJRadioButtonMenuItem =
      null;

  private JRadioButtonMenuItem schwierigJRadioButtonMenuItem =
      null;

  private JRadioButtonMenuItem sehrSchwierigJRadioButtonMenuItem =
      null;

  private JMenu neuesSpieljMenu = null;

  private JMenuItem menschComputerjMenuItem = null;

  private JMenuItem computerMenschjMenuItem = null;

  private OthelloPanel meinSpielbrett = null;

  private OthelloInfoBoard meinInfoBoard = null;

  private boolean spielLaeuft = false;

  /*
   * Nur wenn der Spieler am Zug ist, kann er auch Felder
   * anklicken
   */
  private boolean spielerAmZug = false;

  private int computer = 0; // Welche Farbe spielt der

  // Computer?

  private String spielername = null; // Wie heisst der

  // Spieler?

  private int spielstufe = 6; // Spielstufe in Halbz�gen

  /*
   * Hilfsgr�sse um doppeltes Ausl�sen beim MouseAdapter zu
   * vermeiden
   */
  private MouseEvent letztesEreignis = null;

  private SpielstufeActionListener spielstufeListener =
      new SpielstufeActionListener(); // @jve:decl-index=0:

  /* Es folgen Initialisierung f�r Oberfl�chen-Elemente */

  /**
   * Initialisierung des grundlegenden jFrame
   *
   * @return javax.swing.JFrame
   */
  private JFrame getJFrame() {
    if (jFrame == null) {
      jFrame = new JFrame();
      jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      jFrame.setJMenuBar(getJJMenuBar());
      jFrame.setSize(840, 700);
      jFrame.setPreferredSize(new Dimension(840, 700));
      jFrame.setTitle("Othello");
    }
    return jFrame;
  }

  /**
   * Initialisierung Menueleiste
   *
   * @return javax.swing.JMenuBar
   */
  private JMenuBar getJJMenuBar() {
    if (jJMenuBar == null) {
      jJMenuBar = new JMenuBar();
      jJMenuBar.add(getSpielMenu());
      jJMenuBar.add(getSpielstufeMenu());
      jJMenuBar.add(getHilfeMenu());
    }
    return jJMenuBar;
  }

  /**
   * Initialisierung Spiel-Menue
   *
   * @return javax.swing.JMenu
   */
  private JMenu getSpielMenu() {
    if (spielMenu == null) {
      spielMenu = new JMenu();
      spielMenu.setText("Spiel");
      spielMenu.add(getNeuesSpieljMenu());
      spielMenu.add(getBeendenMenuItem());
    }
    return spielMenu;
  }

  /**
   * Initialisierung Spielstufe-Menue
   *
   * @return javax.swing.JMenu
   */
  private JMenu getSpielstufeMenu() {
    if (spielstufeMenu == null) {
      spielstufeMenu = new JMenu();
      spielstufeMenu.setText("Spielst�rke");
      spielstufeMenu.add(getEinfachJRadioButtonMenuItem());
      spielstufeMenu.add(getNormalJRadioButtonMenuItem());
      spielstufeMenu
          .add(getSchwierigJRadioButtonMenuItem());
      spielstufeMenu
          .add(getSehrSchwierigJRadioButtonMenuItem());
    }
    return spielstufeMenu;
  }

  /**
   * Initialisierung Hilfe-Menue
   *
   * @return javax.swing.JMenu
   */
  private JMenu getHilfeMenu() {
    if (hilfeMenu == null) {
      hilfeMenu = new JMenu();
      hilfeMenu.setText("Hilfe");
      hilfeMenu.add(getSpielregelnMenuItem());
      hilfeMenu.add(getUeberMenuItem());
    }
    return hilfeMenu;
  }

  /**
   * Initialisierung Beenden-MenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getBeendenMenuItem() {
    if (beendenMenuItem == null) {
      beendenMenuItem = new JMenuItem();
      beendenMenuItem.setText("Beenden");
      beendenMenuItem.addActionListener(e -> {
        programmende();
      });
    }
    return beendenMenuItem;
  }

  /**
   * Initialisierung �ber-MenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getUeberMenuItem() {
    if (ueberMenuItem == null) {
      ueberMenuItem = new JMenuItem();
      ueberMenuItem.setText("�ber Othello");
      ueberMenuItem.addActionListener(e -> {
        JDialog aboutDialog = getUeberDialog();
        aboutDialog.pack();
        Point loc = getJFrame().getLocation();
        loc.translate(20, 20);
        aboutDialog.setLocation(loc);
        aboutDialog.setVisible(true);
      });
    }
    return ueberMenuItem;
  }

  /**
   * Initialisierung ueberDialog
   *
   * @return javax.swing.JDialog
   */
  private JDialog getUeberDialog() {
    if (ueberDialog == null) {
      ueberDialog = new JDialog(getJFrame(), true);
      ueberDialog.setTitle("�ber Othello");
      ueberDialog.setMinimumSize(new Dimension(400, 400));
      ueberDialog.setContentPane(getUeberContentPane());
    }
    return ueberDialog;
  }

  /**
   * Initialisierung ueberContentPane
   *
   * @return javax.swing.JPanel
   */
  private JPanel getUeberContentPane() {
    if (ueberContentPane == null) {
      ueberContentPane = new JPanel();
      ueberContentPane.setLayout(new BorderLayout());
      ueberContentPane.add(getUeberText(),
          BorderLayout.CENTER);
    }
    return ueberContentPane;
  }

  /**
   * Initialisierung ueberText
   *
   * @return javax.swing.JLabel
   */
  private JLabel getUeberText() {
    if (ueberText == null) {
      ueberText = new JLabel();
      ueberText
          .setText("W3L: Java Anwendungen programmieren: Othello");
      ueberText
          .setHorizontalAlignment(SwingConstants.CENTER);
    }
    return ueberText;
  }

  /**
   * Initialisierung spielregelnMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getSpielregelnMenuItem() {
    if (spielregelnMenuItem == null) {
      spielregelnMenuItem = new JMenuItem("Spielregeln");
      spielregelnMenuItem.addActionListener(e -> {
        try {
          Desktop.getDesktop().browse(
              new URI(spielregelURL));
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      });
    }
    return spielregelnMenuItem;
  }

  /**
   * Initialisierung einfachJRadioButtonMenuItem
   *
   * @return javax.swing.JRadioButtonMenuItem
   */
  private JRadioButtonMenuItem getEinfachJRadioButtonMenuItem() {
    if (einfachJRadioButtonMenuItem == null) {
      einfachJRadioButtonMenuItem =
          new JRadioButtonMenuItem();
      einfachJRadioButtonMenuItem.setText("einfach");
      einfachJRadioButtonMenuItem
          .addActionListener(spielstufeListener);
    }
    return einfachJRadioButtonMenuItem;
  }

  /**
   * Initialisierung normalJRadioButtonMenuItem
   *
   * @return javax.swing.JRadioButtonMenuItem
   */
  private JRadioButtonMenuItem getNormalJRadioButtonMenuItem() {
    if (normalJRadioButtonMenuItem == null) {
      normalJRadioButtonMenuItem =
          new JRadioButtonMenuItem();
      normalJRadioButtonMenuItem.setText("normal");
      normalJRadioButtonMenuItem.setSelected(true);
      normalJRadioButtonMenuItem
          .addActionListener(spielstufeListener);
    }
    return normalJRadioButtonMenuItem;
  }

  /**
   * Initialisierung schwierigJRadioButtonMenuItem
   *
   * @return javax.swing.JRadioButtonMenuItem
   */
  private JRadioButtonMenuItem getSchwierigJRadioButtonMenuItem() {
    if (schwierigJRadioButtonMenuItem == null) {
      schwierigJRadioButtonMenuItem =
          new JRadioButtonMenuItem();
      schwierigJRadioButtonMenuItem.setText("schwierig");
      schwierigJRadioButtonMenuItem
          .addActionListener(spielstufeListener);
    }
    return schwierigJRadioButtonMenuItem;
  }

  /**
   * Initialisierung sehrSchwierigJRadioButtonMenuItem
   *
   * @return javax.swing.JRadioButtonMenuItem
   */
  private JRadioButtonMenuItem getSehrSchwierigJRadioButtonMenuItem() {
    if (sehrSchwierigJRadioButtonMenuItem == null) {
      sehrSchwierigJRadioButtonMenuItem =
          new JRadioButtonMenuItem();
      sehrSchwierigJRadioButtonMenuItem
          .setText("Sehr schwierig");
      sehrSchwierigJRadioButtonMenuItem
          .addActionListener(spielstufeListener);
    }
    return sehrSchwierigJRadioButtonMenuItem;
  }

  /**
   * Initialisierung neuesSpieljMenu
   *
   * @return javax.swing.JMenu
   */
  private JMenu getNeuesSpieljMenu() {
    if (neuesSpieljMenu == null) {
      neuesSpieljMenu = new JMenu();
      neuesSpieljMenu.setText("Neues Spiel");
      neuesSpieljMenu.add(getMenschComputerjMenuItem());
      neuesSpieljMenu.add(getComputerMenschjMenuItem());
    }
    return neuesSpieljMenu;
  }

  /**
   * Initialisierung menschComputerjMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getMenschComputerjMenuItem() {
    if (menschComputerjMenuItem == null) {
      menschComputerjMenuItem = new JMenuItem();
      menschComputerjMenuItem
          .setText("Mensch (s) gegen Computer (w)");
      menschComputerjMenuItem
          .addActionListener(e -> {
            if (!spielLaeuft
                || zeigeBestaetigungsDialog("Wollen Sie das laufende Spiel beenden?")) {
              neuesSpiel(Othello.WEISS);
            }
          });
    }
    return menschComputerjMenuItem;
  }

  /**
   * Initialisierung computerMenschjMenuItem
   *
   * @return javax.swing.JMenuItem
   */
  private JMenuItem getComputerMenschjMenuItem() {
    if (computerMenschjMenuItem == null) {
      computerMenschjMenuItem = new JMenuItem();
      computerMenschjMenuItem
          .setText("Computer (s) gegen Mensch (w)");
      computerMenschjMenuItem
          .addActionListener(e -> {
            if (!spielLaeuft
                || zeigeBestaetigungsDialog("Wollen Sie das laufende Spiel beenden?")) {
              neuesSpiel(Othello.SCHWARZ);
            }
          });
    }
    return computerMenschjMenuItem;
  }

  /* Ende der Initialisierungen f�r Oberfl�chen-Elemente */

  private boolean zeigeBestaetigungsDialog(String text) {
    int ergebnis =
        JOptionPane.showConfirmDialog(jFrame, text, "",
            JOptionPane.YES_NO_OPTION);
    return (ergebnis == JOptionPane.YES_OPTION);
  }

  private String spielernamenDialog(String text) {
    String ergebnis =
        JOptionPane.showInputDialog(jFrame, text);
    if ((ergebnis == null)
        || (ergebnis.trim().length() == 0))
      ergebnis = "Mr. X";
    return ergebnis;
  }

  private void zeigeMeldung(String text) {
    JOptionPane.showMessageDialog(jFrame, text);
  }

  /** Der Spieler passt */
  private boolean passen() {
    return (meinSpielbrett.getOthelloEngine().passen());
  }

  /** Der Computer darf ziehen */
  private void computerzug() {
    spielerAmZug = false;
    meinInfoBoard.stoppeUhr(-1 * computer);
    meinInfoBoard.getSpielerAmZug().setInhalt("Computer");
    meinInfoBoard.starteUhr(computer);
    meinInfoBoard.repaint();
    (new Computerzug()).execute();
  }

  /*
   * Der Computer darf ziehen. ACHTUNG: So sollte
   * computerzug() nicht implementiert werden. private void
   * computerzug() { spielerAmZug = false;
   * meinInfoBoard.stoppeUhr(-1 * computer);
   * meinInfoBoard.getSpielerAmZug().setInhalt("Computer");
   * meinInfoBoard.starteUhr(computer);
   * meinInfoBoard.repaint(); // Der folgende Aufruf kann
   * lange dauern und blockiert // daher den EDT Zug
   * computerzug =
   * meinSpielbrett.getOthelloEngine().computerzug(
   * spielstufe); meinInfoBoard.stoppeUhr(computer); if
   * (computerzug != null) {
   * meinInfoBoard.getBewertung().setInhalt( "" +
   * computerzug.getBewertung()); if (computerzug.getZeile()
   * == Othello.PASSEN) { // Der Computer passt
   * zeigeMeldung("Ich passe!"); } } if
   * (meinSpielbrett.getOthelloEngine().spielende())
   * spielende(); else { // Sonst ist der Spieler jetzt
   * dran; spielerzug(); } }
   */
  private void spielerzug() {
    spielerAmZug = true;
    meinInfoBoard.stoppeUhr(computer);
    meinInfoBoard.getSpielerAmZug().setInhalt(spielername);

    /*
     * �berpr�fen, ob es einen m�glichen Zug gibt ausser zu
     * passen
     */
    meinInfoBoard.setPassenMoeglich(meinSpielbrett
        .getOthelloEngine().kannNurPassen(-computer));

    /*
     * Den Spieler durch einen Ton darauf aufmerksam machen,
     * dass er an der Reihe ist.
     */
    Toolkit.getDefaultToolkit().beep();
    meinSpielbrett.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e)
      /*
       * Der Spieler ist am Zug und hat eine Position auf
       * dem Spielbrett angeklickt
       */ {
        if (letztesEreignis != e && spielerAmZug) {
          letztesEreignis = e;
          Point klickstelle =
              meinSpielbrett.getZeileUndSpalte(e);

          if (!meinSpielbrett.getOthelloEngine()
              .spielerzug(klickstelle.x, klickstelle.y))
            zeigeMeldung("Dieser Zug ist nicht m�glich!");
          else {
            meinSpielbrett.repaint();
            if (meinSpielbrett.getOthelloEngine()
                .spielende())
              spielende();
            else
              /* Berechnung des Computerzugs */
              computerzug();
          }
        }
      }
    });
    meinInfoBoard.starteUhr((-1) * computer);
  }

  private void neuesSpiel(int computer) {
    this.computer = computer;
    spielLaeuft = true;
    spielername =
        spielernamenDialog("Bitte geben Sie Ihren Namen ein:");
    jContentPane = new JPanel();
    jContentPane.setLayout(new BorderLayout());
    meinSpielbrett = new OthelloPanel(computer);
    meinInfoBoard = new OthelloInfoBoard();
    jContentPane.add(meinSpielbrett, BorderLayout.CENTER);

    /*
     * ActionListener f�r Passen- und Abbrechen-Button
     * hinzuf�gen; Lambda-Notation
     */
    ActionListener bl =
        e -> {
          if (e.getActionCommand().equals(
              meinInfoBoard.getAbbrechenButton()
                  .getActionCommand())) {
            /* Abbrechen wurde angeklickt */
            spielabbruch();
          } else {
            /* Passen wurde angeklickt */
            if (passen())
              zeigeMeldung("Dieser Zug ist nicht m�glich!");
            else {
              computerzug();
            }
          }
        };
    meinInfoBoard.setAbbrechenMoeglich(true);
    meinInfoBoard.getAbbrechenButton()
        .addActionListener(bl);
    meinInfoBoard.getPassenButton().addActionListener(bl);

    jContentPane.add(meinInfoBoard, BorderLayout.EAST);
    jFrame.setContentPane(jContentPane);
    jFrame.validate();

    if (computer == Othello.SCHWARZ) {
      meinInfoBoard.getWeisserSpieler().setInhalt(
          spielername);
      meinInfoBoard.getSchwarzerSpieler().setInhalt(
          "Computer");
      computerzug();
    } else {
      meinInfoBoard.getSchwarzerSpieler().setInhalt(
          spielername);
      meinInfoBoard.getWeisserSpieler().setInhalt(
          "Computer");
      spielerzug();
    }
    this.jFrame.repaint();
  }

  private void spielabbruch() {
    if (zeigeBestaetigungsDialog("Wollen Sie das laufende Spiel abbrechen?")) {
      spielende();
    }
  }

  private void spielende() {
    /*
     * Das Spiel ist jetzt beendet. Also kann man nicht mehr
     * abbrechen
     */
    meinInfoBoard.setAbbrechenMoeglich(false);
    /* Alle laufenden Uhren stoppen */
    meinInfoBoard.stoppeUhr(computer);
    meinInfoBoard.stoppeUhr(-computer);
    spielLaeuft = false;
    Othello othelloEngine =
        meinSpielbrett.getOthelloEngine();
    int computerSteine =
        othelloEngine.getAnzahlSteine(computer);
    int spielerSteine =
        othelloEngine.getAnzahlSteine(-computer);
    String nachricht = "Das Spiel ist beendet! ";
    if (computerSteine > spielerSteine)
      nachricht =
          nachricht + "Ich habe mit " + computerSteine
              + ":" + spielerSteine + " gewonnen!";
    else if (computerSteine < spielerSteine)
      nachricht =
          nachricht + "Sie haben mit " + spielerSteine
              + ":" + computerSteine + " gewonnen!";
    else
      nachricht =
          nachricht + "Das Spiel endete Unentschieden!";
    zeigeMeldung(nachricht);
  }

  private void programmende() {
    System.exit(0);
  }

  class SpielstufeActionListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      String befehl = e.getActionCommand();
      if (befehl.equals("einfach")) {
        getEinfachJRadioButtonMenuItem().setSelected(true);
        getNormalJRadioButtonMenuItem().setSelected(false);
        getSchwierigJRadioButtonMenuItem().setSelected(
            false);
        getSehrSchwierigJRadioButtonMenuItem().setSelected(
            false);
        spielstufe = 2;
      } else if (befehl.equals("normal")) {
        getEinfachJRadioButtonMenuItem().setSelected(false);
        getNormalJRadioButtonMenuItem().setSelected(true);
        getSchwierigJRadioButtonMenuItem().setSelected(
            false);
        getSehrSchwierigJRadioButtonMenuItem().setSelected(
            false);
        spielstufe = 4;
      } else if (befehl.equals("schwierig")) {
        getEinfachJRadioButtonMenuItem().setSelected(false);
        getNormalJRadioButtonMenuItem().setSelected(false);
        getSchwierigJRadioButtonMenuItem()
            .setSelected(true);
        getSehrSchwierigJRadioButtonMenuItem().setSelected(
            false);
        spielstufe = 6;
      } else {
        getEinfachJRadioButtonMenuItem().setSelected(false);
        getNormalJRadioButtonMenuItem().setSelected(false);
        getSchwierigJRadioButtonMenuItem().setSelected(
            false);
        getSehrSchwierigJRadioButtonMenuItem().setSelected(
            true);
        spielstufe = 8;
      }
      jFrame.setTitle("Othello - " + befehl);
    }
  }

  /**
   * Startet die Applikation im EDT
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      OthelloGUI application = new OthelloGUI();
      application.getJFrame().setVisible(true);
    });
  }

  class Computerzug extends SwingWorker<Zug, Object> {
    protected Zug doInBackground() {
      Zug computerzug =
          meinSpielbrett.getOthelloEngine().computerzug(
              spielstufe);
      return computerzug;
    }

    protected void done() {
      /*
       * Diese Methode wird nach Beendigung der Berechnung
       * im Event Dispatcher Thread (EDT) von Swing
       * aufgerufen. Hier folgen also alle Aktionen nach der
       * Berechnung des Computerzugs.
       */
      try {
        Zug computerzug = get();
        meinInfoBoard.stoppeUhr(computer);
        if (computerzug != null) {
          System.out.println(computerzug);
          meinInfoBoard.getBewertung().setInhalt(
              "" + computerzug.getBewertung());
          if (computerzug.getZeile() == Othello.PASSEN) {
            // Der Computer passt
            zeigeMeldung("Ich passe!");
          }
        }
        meinSpielbrett.repaint();
        if (meinSpielbrett.getOthelloEngine().spielende())
          spielende();
        else {
          // Sonst ist der Spieler jetzt dran;
          spielerzug();
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
