package de.w3l.anw.othello.engine;

import java.util.Scanner;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class Othello extends AbstractTableModel implements
    Cloneable {
  /*
   * AbstractTableModel wird erweitert, damit �ber
   * getValueAt() die Belegung des Spielbretts abgefragt
   * werden kann.
   */
  private static final long serialVersionUID =
      -1165209844979829465L;

  //Diese Position wird beim Passen �bergeben
  public static final int PASSEN = -1;

  public static final int SCHWARZ = -1;

  public static final int WEISS = 1;

  public static final int LEER = 0;

  static final int ANZ_ZEILEN = 8;

  static final int ANZ_SPALTEN = 8;

  public static int bm[][] =
      new int[ANZ_ZEILEN][ANZ_SPALTEN];

  public static void fuelleBewertungsmatrix() {
    // Bewertung aus
    // http://www.site-constructor.com/othello/Present/BoardLocationValue.html
    // Ersten Quadranten ausf�llen
    bm[0][0] = 50;
    bm[0][1] = -1;
    bm[0][2] = 5;
    bm[0][3] = 2;

    bm[1][0] = -1;
    bm[1][1] = -10;
    bm[1][2] = 1;
    bm[1][3] = 1;

    bm[2][0] = 5;
    bm[2][1] = 1;
    bm[2][2] = 1;
    bm[2][3] = 1;

    bm[3][0] = 2;
    bm[3][1] = 1;
    bm[3][2] = 1;
    bm[3][3] = 0;

    // Zweiten Quadranten symmetrisch anlegen
    for (int zeile = 0; zeile < 4; zeile++) {
      for (int spalte = 4; spalte < 8; spalte++) {
        bm[zeile][spalte] = bm[zeile][7 - spalte];
      }
    }

    // Dritten und vierten Quadranten symmetrisch zu 1 und 2
    for (int zeile = 4; zeile < 8; zeile++) {
      for (int spalte = 0; spalte < 8; spalte++) {
        bm[zeile][spalte] = bm[7 - zeile][spalte];
      }
    }
  }

  // Die beiden folgenden Konstanten werden f�r die Alpha-Beta-Suche ben�tigt
  public static int ALPHA = -1000;
  public static int BETA = +1000;

  private int brett[][];

  // Welche Farbe spielt der Computer?
  private int computerspieler;

  //Wie oft wurde hintereinander gepasst?
  private int anzahlPassen = 0;

  public Othello(int farbeComputer) {
    Othello.fuelleBewertungsmatrix();
    brett = new int[ANZ_ZEILEN][ANZ_SPALTEN];
    for (int zeile = 0; zeile < brett.length; zeile++) {
      for (int spalte = 0; spalte < brett[0].length; spalte++)
        brett[zeile][spalte] = LEER;
    }
    brett[3][3] = WEISS;
    brett[3][4] = SCHWARZ;
    brett[4][3] = SCHWARZ;
    brett[4][4] = WEISS;
    computerspieler = farbeComputer;
  }

  public Othello clone() {
    Othello kopie = new Othello(this.computerspieler);
    for (int zeile = 0; zeile < brett.length; zeile++) {
      for (int spalte = 0; spalte < brett[0].length; spalte++)
        kopie.brett[zeile][spalte] = brett[zeile][spalte];
    }
    return kopie;
  }

  public Vector<Zug> berechneMoeglicheZuege(int spielerAmZug) {
    Vector<Zug> moeglicheZuege = new Vector<Zug>();
    // Zuerst die vier Ecken
    // oben links
    if ((brett[0][0] == LEER)
        && (legalerZug(0, 0, 0, 1, spielerAmZug)
        || legalerZug(0, 0, 1, 0, spielerAmZug) || legalerZug(
        0, 0, 1, 1, spielerAmZug)))
      moeglicheZuege.add(new Zug(0, 0)); // 0*8 + 0;

    // oben rechts
    if ((brett[0][ANZ_SPALTEN - 1] == LEER)
        && (legalerZug(0, ANZ_SPALTEN - 1, 0, -1,
        spielerAmZug)
        || legalerZug(0, ANZ_SPALTEN - 1, 1, 0,
        spielerAmZug) || legalerZug(0,
        ANZ_SPALTEN - 1, 1, -1, spielerAmZug)))
      moeglicheZuege.add(new Zug(0, ANZ_SPALTEN - 1));

    // unten links
    if ((brett[ANZ_ZEILEN - 1][0] == LEER)
        && (legalerZug(ANZ_ZEILEN - 1, 0, 0, 1,
        spielerAmZug)
        || legalerZug(ANZ_ZEILEN - 1, 0, -1, 0,
        spielerAmZug) || legalerZug(ANZ_ZEILEN - 1,
        0, -1, 1, spielerAmZug)))
      moeglicheZuege.add(new Zug(ANZ_ZEILEN - 1, 0));

    // unten rechts
    if ((brett[ANZ_ZEILEN - 1][ANZ_SPALTEN - 1] == LEER)
        && (legalerZug(ANZ_ZEILEN - 1, ANZ_SPALTEN - 1, 0,
        -1, spielerAmZug)
        || legalerZug(ANZ_ZEILEN - 1, ANZ_SPALTEN - 1,
        -1, 0, spielerAmZug) || legalerZug(
        ANZ_ZEILEN - 1, ANZ_SPALTEN - 1, -1, -1,
        spielerAmZug)))
      moeglicheZuege.add(new Zug(ANZ_ZEILEN - 1,
          ANZ_SPALTEN - 1));

    // Jetzt kommen die restlichen Randpunkte
    // obere Zeile bzw. untere Zeile
    for (int spalte = 1; spalte < ANZ_SPALTEN - 1; spalte++) {
      if ((brett[0][spalte] == LEER)
          && (legalerZug(0, spalte, 0, 1, spielerAmZug)
          || legalerZug(0, spalte, 1, 1, spielerAmZug)
          || legalerZug(0, spalte, 1, 0, spielerAmZug)
          || legalerZug(0, spalte, 1, -1, spielerAmZug) || legalerZug(
          0, spalte, 0, -1, spielerAmZug)))
        moeglicheZuege.add(new Zug(0, spalte));

      if ((brett[ANZ_ZEILEN - 1][spalte] == LEER)
          && (legalerZug(ANZ_ZEILEN - 1, spalte, 0, 1,
          spielerAmZug)
          || legalerZug(ANZ_ZEILEN - 1, spalte, 0, -1,
          spielerAmZug)
          || legalerZug(ANZ_ZEILEN - 1, spalte, -1, -1,
          spielerAmZug)
          || legalerZug(ANZ_ZEILEN - 1, spalte, -1, 0,
          spielerAmZug) || legalerZug(
          ANZ_ZEILEN - 1, spalte, -1, 1, spielerAmZug)))
        moeglicheZuege.add(new Zug(ANZ_ZEILEN - 1, spalte));
    }

    // linke bzw. rechte Spalte
    for (int zeile = 1; zeile < ANZ_ZEILEN - 1; zeile++) {
      if ((brett[zeile][0] == LEER)
          && (legalerZug(zeile, 0, -1, 0, spielerAmZug)
          || legalerZug(zeile, 0, -1, 1, spielerAmZug)
          || legalerZug(zeile, 0, 0, 1, spielerAmZug)
          || legalerZug(zeile, 0, 1, 1, spielerAmZug) || legalerZug(
          zeile, 0, 1, 0, spielerAmZug)))
        moeglicheZuege.add(new Zug(zeile, 0));

      if ((brett[zeile][ANZ_SPALTEN - 1] == LEER)
          && (legalerZug(zeile, ANZ_SPALTEN - 1, -1, 0,
          spielerAmZug)
          || legalerZug(zeile, ANZ_SPALTEN - 1, 1, 0,
          spielerAmZug)
          || legalerZug(zeile, ANZ_SPALTEN - 1, 1, -1,
          spielerAmZug)
          || legalerZug(zeile, ANZ_SPALTEN - 1, 0, -1,
          spielerAmZug) || legalerZug(zeile,
          ANZ_SPALTEN - 1, -1, -1, spielerAmZug)))
        moeglicheZuege.add(new Zug(zeile, ANZ_SPALTEN - 1));
    }

    // Jetzt kommen die inneren Felder. Hier gibt es jeweils
    // 8 M�glichkeiten
    for (int zeile = 1; zeile < ANZ_ZEILEN - 1; zeile++) {
      for (int spalte = 1; spalte < ANZ_SPALTEN - 1; spalte++) {
        if ((brett[zeile][spalte] == LEER)
            && (legalerZug(zeile, spalte, -1, -1,
            spielerAmZug)
            || legalerZug(zeile, spalte, -1, 0,
            spielerAmZug)
            || legalerZug(zeile, spalte, -1, 1,
            spielerAmZug)
            || legalerZug(zeile, spalte, 0, -1,
            spielerAmZug)
            || legalerZug(zeile, spalte, 0, 1,
            spielerAmZug)
            || legalerZug(zeile, spalte, 1, -1,
            spielerAmZug)
            || legalerZug(zeile, spalte, 1, 0,
            spielerAmZug) || legalerZug(zeile,
            spalte, 1, 1, spielerAmZug)))
          moeglicheZuege.add(new Zug(zeile, spalte));
      }
    }
    return moeglicheZuege;
  }

  /*
   * Wenn kein Zug m�glich ist, kann der Spieler am Zug nur
   * passen
   */
  public boolean kannNurPassen(int spielerAmZug) {
    return (berechneMoeglicheZuege(spielerAmZug).size() == 0);
  }

  /**
   * Die aktuelle Stellung auf dem Brett bewerten. Die
   * Bewertung erfolgt als Summe �ber die mit dem jeweiligen
   * Gewicht bewerteten Belegungen.
   */
  public int bewertePosition() {
    int bewertung = 0;
    for (int zeile = 0; zeile < ANZ_ZEILEN; zeile++) {
      for (int spalte = 0; spalte < ANZ_SPALTEN; spalte++) {
        bewertung +=
            (brett[zeile][spalte] * computerspieler * bm[zeile][spalte]);
      }
    }
    return bewertung;
  }

  public Vector<Integer> ziehe(int spieler, Zug zug) {
    Vector<Integer> drehsteine = new Vector<Integer>();
    Vector<Integer> neueSteine;

    for (int dz = -1; dz < 2; dz++) {
      for (int ds = -1; ds < 2; ds++) {
        if ((dz == 0) && (ds == 0))
          continue;
        neueSteine =
            berechneDrehsteine(zug.zeile, zug.spalte, dz,
                ds, spieler);
        if (neueSteine != null)
          // Dieser Zug dreht Steine
          drehsteine.addAll(neueSteine);
      }
    }
    if (!drehsteine.isEmpty()) {
      // Drehe die Steine
      brett[zug.zeile][zug.spalte] = spieler;
      for (int i = 0; i < drehsteine.size(); i++) {
        int drehpos = drehsteine.get(i);
        brett[drehpos / ANZ_SPALTEN][drehpos % ANZ_SPALTEN] =
            spieler;
      }
    }
    return drehsteine;
  }

  /**
   * Der Spieler passt. Falls dies nicht m�glich sein
   * sollte, wird FALSE zur�ckgegeben.
   */
  public boolean passen() {
    return (spielerzug(PASSEN, PASSEN));
    // Passen ist nur m�glich, wenn keine anderen Z�ge
    // m�glich sind.
  }

  /**
   * Findet den besten Zug nach dem Mini-Max-Algorithmus)
   * Gesucht wird f�r den am Zug befindlichen Spieler
   * innerhalb der durch tiefe vorgegebenen Anzahl von
   * Halbz�gen
   *
   * @param tiefe:   Noch verbleibende Zahl von Halbz�gen
   * @param spieler: Wer ist am Zug?
   * @return besten Zug
   */
  public Zug findeBestenZug(int tiefe, int spieler) {
    /* Zun�chst alle m�glichen Z�ge berechnen */
    Vector<Zug> moeglicheZuege =
        berechneMoeglicheZuege(spieler);
    int anzahlMoeglicheZuege = moeglicheZuege.size();
    if (anzahlMoeglicheZuege > 0) {
      int maxpos = -1; // enth�lt Index des besten Zugs aus
      // Computersicht
      int minpos = -1; // enth�lt Index des besten Zugs aus
      // Spielersicht
      /*
       * Alle Z�ge werden durchprobiert. Der beste wird
       * zur�ckgeliefert.
       */
      for (int i = 0; i < anzahlMoeglicheZuege; i++) {
        // Mach den Zug auf einer Kopie des Bretts
        Othello brettKopie = this.clone();
        brettKopie.ziehe(spieler, moeglicheZuege.get(i));
        if (tiefe == 1) {
          // Unterste Ebene, jetzt bewerten
          moeglicheZuege.get(i).bewertung =
              brettKopie.bewertePosition();
        } else {
					/*
           * Wir m�ssen noch tiefer im Baum und rufen daher
           * findeBestenZug rekursiv auf.
           */
          Zug besterZug =
              brettKopie.findeBestenZug(tiefe - 1, spieler
                  * (-1));
          moeglicheZuege.get(i).bewertung =
              besterZug.bewertung;
        }
        if (i == 0) {
          // Wir sind beim ersten m�glichen Zug
          maxpos = 0;
          minpos = 0;
        } else {
          int bewertung =
              moeglicheZuege.get(i).bewertung.intValue();
          if (bewertung > moeglicheZuege.get(maxpos).bewertung
              .intValue())
            maxpos = i; // neue Maximalposition
          else if (bewertung < moeglicheZuege.get(minpos).bewertung
              .intValue())
            minpos = i; // neue Minimalposition
        }
      }
      if (spieler == computerspieler)
        // Wir brauchen den maximalen Zug
        return moeglicheZuege.get(maxpos);
      else
        return moeglicheZuege.get(minpos);
    } else {
      // Kein Zug ausser Passen m�glich
      Zug ergebnis = new Zug(PASSEN, PASSEN);
      if (tiefe == 1) {
        // Wir sind bei den Bl�ttern angekommen
        ergebnis.bewertung = bewertePosition();
      } else {
        ergebnis.bewertung =
            findeBestenZug(tiefe - 1, spieler * (-1)).bewertung;
      }
      return ergebnis;
    }
  }

  /**
   * findeBestenZug in der Variante f�r Alpha-Beta-Suche
   * Durch die Parameter Alpha und Beta wird jeweils ein
   * Intervall aufgespannt, innerhalb dessen sich Z�ge
   * befinden m�ssen, um in Betracht zu kommen.
   */
  public Zug findeBestenZug(int tiefe, int spieler,
                            int alpha, int beta) {
    /* Zun�chst alle m�glichen Z�ge berechnen */
    Vector<Zug> moeglicheZuege =
        berechneMoeglicheZuege(spieler);
    int anzahlMoeglicheZuege = moeglicheZuege.size();
    if (anzahlMoeglicheZuege > 0) {
      int maxpos = -1; // enth�lt Index des besten Zugs aus Computersicht
      int minpos = -1; // enth�lt Index des besten Zugs aus Spielersicht
      for (int i = 0; i < anzahlMoeglicheZuege; i++) {
        Othello brettKopie = this.clone(); // Mach den Zug auf einer Kopie des Bretts
        brettKopie.ziehe(spieler, moeglicheZuege.get(i));
        if (tiefe == 1) {
          moeglicheZuege.get(i).bewertung = brettKopie.bewertePosition(); // Unterste Ebene, jetzt bewerten
        } else {
          //rekursiv tiefer im Baum
          Zug besterZug = brettKopie.findeBestenZug(tiefe - 1, spieler * (-1), alpha, beta);
          moeglicheZuege.get(i).bewertung = besterZug.bewertung;
          if ((spieler == computerspieler) && (besterZug.bewertung.intValue() > alpha)) //maximaler Wert des eigenen Zuges
            alpha = besterZug.bewertung.intValue();
          if ((spieler != computerspieler) && (besterZug.bewertung.intValue() < beta)) //minimaler Wert des Gegenspielers
            beta = besterZug.bewertung.intValue();
        }
        if (i == 0) { //erster Zug
          maxpos = 0;
          minpos = 0;
        } else {
          int bewertung = moeglicheZuege.get(i).bewertung.intValue();
          if (bewertung > moeglicheZuege.get(maxpos).bewertung.intValue())
            maxpos = i; // neue Maximalposition
          else if (bewertung < moeglicheZuege.get(minpos).bewertung.intValue())
            minpos = i; // neue Minimalposition
        }
        if (alpha >= beta) { //Zug lohnt sich, wenn eigener Zugwert maximiert wird und Zug des Gegenspielers minimiert wird
          break; //abbrechen
        }
      } // Ende der Schleife, die �ber alle Z�ge geht
      if (spieler == computerspieler)
        // Wir brauchen den maximalen Zug
        return moeglicheZuege.get(maxpos);
      else
        return moeglicheZuege.get(minpos);
    } else {
      // Kein Zug ausser Passen m�glich
      Zug ergebnis = new Zug(PASSEN, PASSEN);
      if (tiefe == 1) {
        // Wir sind bei den Bl�ttern angekommen
        ergebnis.bewertung = bewertePosition();
      } else {
        ergebnis.bewertung =
            findeBestenZug(tiefe - 1, spieler * (-1), alpha, beta).bewertung;
      }
      return ergebnis;
    }
  }


  public int getAnzahlSteine(int spieler) {
    int anzahl = 0;
    for (int zeile = 0; zeile < ANZ_ZEILEN; zeile++) {
      for (int spalte = 0; spalte < ANZ_SPALTEN; spalte++) {
        if (brett[zeile][spalte] == spieler)
          anzahl++;
      }
    }
    return anzahl;
  }

  public void druckeBrett() {
    System.out.println("  0 1 2 3 4 5 6 7");
    System.out.println("=================");
    for (int zeile = 0; zeile < ANZ_ZEILEN; zeile++) {
      System.out.print(zeile + ":");
      for (int spalte = 0; spalte < ANZ_SPALTEN; spalte++) {
        if (brett[zeile][spalte] == WEISS)
          System.out.print("O ");
        else if (brett[zeile][spalte] == SCHWARZ)
          System.out.print("X ");
        else
          System.out.print("  ");
      }
      System.out.println();
    }
  }

  /**
   * @param zeile   :
   *                Ursprungszeile
   * @param spalte  :
   *                Ursprungsspalte
   * @param dz      :
   *                Delta Zeile
   * @param ds      :
   *                Delta Spalte
   * @param spieler :
   *                Wer zieht?
   * @return : true, wenn Zug m�glich
   */
  private boolean legalerZug(int zeile, int spalte, int dz,
                             int ds, int spieler) {
    // Der Zug ist legal, wenn er Steine dreht
    return (berechneDrehsteine(zeile, spalte, dz, ds,
        spieler) != null);
  }

  private Vector<Integer> berechneDrehsteine(int zeile,
                                             int spalte, int dz, int ds, int spieler) {
    Vector<Integer> drehsteine = null;
    int gegenspieler = spieler * (-1);
    int neueZeile = zeile + dz;
    int neueSpalte = spalte + ds;
    if ((neueZeile >= 0) && (neueZeile < ANZ_ZEILEN)
        && (neueSpalte >= 0) && (neueSpalte < ANZ_SPALTEN)) {
      // 1. Bedingung: Auf dem Nachbarfeld muss ein Stein
      // des Gegners sein
      if (brett[neueZeile][neueSpalte] != gegenspieler)
        return null;

      drehsteine = new Vector<Integer>();
      drehsteine.add(new Integer(neueZeile * ANZ_SPALTEN
          + neueSpalte));
      // den Stein k�nnen wir vielleicht drehen

      // 2. Bedingung: Vor dem Ende oder einem leeren
      // Stein muss noch
      // ein eigener Stein folgen
      neueZeile = neueZeile + dz;
      neueSpalte = neueSpalte + ds;
      while ((neueZeile < ANZ_ZEILEN) && (neueZeile >= 0)
          && (neueSpalte < ANZ_SPALTEN)
          && (neueSpalte >= 0)) {
        if (brett[neueZeile][neueSpalte] == spieler)
          return drehsteine; // Wir k�nnen tats�chlich
        // drehen
        if (brett[neueZeile][neueSpalte] == LEER)
          return null; // Da geht nichts!
        drehsteine.add(new Integer(neueZeile * ANZ_SPALTEN
            + neueSpalte));
        // den Stein k�nnen wir vielleicht drehen
        neueZeile = neueZeile + dz;
        neueSpalte = neueSpalte + ds;
      }
    }
    // Falls wir hier ankommen, ist es kein legaler Zug!
    return null;
  }

  public boolean spielende() {
    if (anzahlPassen > 1)
      return true; // Nach zweimal Passen ist das Spiel
    // beendet.

    // Das Spiel ist auch beendet, wenn das Brett voll ist.
    for (int zeile = 0; zeile < ANZ_ZEILEN; zeile++) {
      for (int spalte = 0; spalte < ANZ_SPALTEN; spalte++) {
        if (brett[zeile][spalte] == 0)
          return false;
      }
    }
    return true;
  }

  /**
   * Ein Computerzug wird durchgef�hrt. Sollte kein Zug mehr
   * m�glich sein, wird null zur�ckgeliefert. Sonst wird der
   * Zug mit Bewertung zur�ckgeliefert.
   */
  public Zug computerzug(int suchtiefe) {

    // Variante f�r Minimax-Suche
//    Zug computerzug =
//        findeBestenZug(suchtiefe, computerspieler);

//    // Variante f�r Alpha-Beta-Suche
    Zug computerzug =
      findeBestenZug(suchtiefe,computerspieler,
          ALPHA, BETA);
    System.out.println(computerzug);
    if (computerzug == null)
      return null;
    if (computerzug.zeile == PASSEN) {
      anzahlPassen++;
      return computerzug;
    }
    anzahlPassen = 0;
    this.ziehe(computerspieler, computerzug);
    return computerzug;
  }

  public boolean spielerzug(int zeile, int spalte) {
    Vector<Zug> moeglicheZuege =
        berechneMoeglicheZuege(-1 * computerspieler);
    Zug spielerzug = new Zug(zeile, spalte);
    if (moeglicheZuege.contains(spielerzug)) {
      if (spielerzug.zeile == PASSEN)
        anzahlPassen++;
      else
        anzahlPassen = 0;
      ziehe(-computerspieler, spielerzug);
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    Othello.fuelleBewertungsmatrix();
    Othello obj = new Othello(SCHWARZ);
    obj.druckeBrett();
    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);
    while (true) {
      Zug meinZug =
          obj.findeBestenZug(7, obj.computerspieler, -200, +200);
      System.out.println("Ziehe (" + meinZug.zeile + ", "
          + meinZug.spalte + ") mit Bewertung "
          + meinZug.bewertung);
      obj.ziehe(obj.computerspieler, meinZug);
      obj.druckeBrett();
      Vector<Zug> gegenzuege =
          obj.berechneMoeglicheZuege(obj.computerspieler
              * (-1));
      System.out
          .println("F�r Sie sind folgende Z�ge m�glich:");
      for (int i = 0; i < gegenzuege.size(); i++) {
        System.out.print("(" + gegenzuege.get(i).zeile
            + ", " + gegenzuege.get(i).spalte + ") ");
      }
      System.out.println();
      System.out.println("Geben Sie Ihren Zug an: ");
      // Zugkontrolle muss noch eingebaut werden!!!
      int zeile = input.nextInt();
      int spalte = input.nextInt();
      obj.ziehe(obj.computerspieler * (-1), new Zug(zeile,
          spalte));
      obj.druckeBrett();
    }
  }

  @Override
  public int getColumnCount() {
    return ANZ_SPALTEN;
  }

  @Override
  public int getRowCount() {
    return ANZ_ZEILEN;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    int inhalt = brett[rowIndex][columnIndex];
    switch (inhalt) {
      case (0):
        return null;
      case (-1):
        return new Integer(SCHWARZ);
      case (1):
        return new Integer(WEISS);
    }
    return null;
  }
}
