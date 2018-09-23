package de.w3l.anw.wetterstation.applikation;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import de.w3l.anw.wetterstation.gui.Anwendungsparameter;
import de.w3l.anw.wetterstation.xml.adaten.Astronomie;
import de.w3l.anw.wetterstation.xml.wdaten.Weather;

public class Wetterquelle {

  private int abfrageintervallInMinuten;

  /* eine Minute warten */
  private int schlafintervall = 1000 * 60;

  /* Zeitpunkt der letzten Wetter-Abfrage */
  private LocalDateTime letzteAbfrageWetter = null;

  /* Zeitpunkt der letzten Astronomiedaten-Abfrage */
  private LocalDateTime letzteAbfrageAstronomie = null;

  private Astronomie astrodaten = null;

  public Wetterquelle() {
    // Eine neue Wetterquelle erzeugen
    abfrageintervallInMinuten =
        Integer.parseInt(Anwendungsparameter
            .getParameter("ABFRAGEINTERVALL"));
  }

  /**
   * Holt f�r den angegebenen Ort einen neuen Datensatz. Da
   * Wetterquellen wie z.b. Weather Underground in ihren
   * Lizenzbedingungen eine maximale Anzahl von Abfragen
   * vorsehen, wird die Abfrage entsprechend verz�gert, wenn
   * die Mindestpause noch nicht erreicht wurde.
   *
   * @param ort
   * @return Wetter
   * @throws WetterquelleException
   */
  public Wetter holeAktuellesWetter(Ort ort)
      throws WetterquelleException {
    Wetter neuesWetter = null;
    if (letzteAbfrageWetter != null) {
      letzteAbfrageWetter = letzteAbfrageWetter
          .plusMinutes(abfrageintervallInMinuten);
    } else {
      letzteAbfrageWetter = LocalDateTime.now();
    }
    // Jetzt warten wir, bis wir Daten liefern d�rfen
    System.out.println("N�chste Wetterdatenabfrage: "
        + letzteAbfrageWetter);
    while (LocalDateTime.now()
        .isBefore(letzteAbfrageWetter)) {
      try {
        Thread.sleep(schlafintervall);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    // Jetzt d�rfen wir wieder Daten liefern
    try {
      /*
       * Zun�chst muss ein JAXBContext-Objekt erzeugt
       * werden, das die annotierte Klasse kennt. Dies
       * geschieht durch die statische Methode newInstance.
       * Die Klassen, die dem Kontext bekannt sein m�ssen,
       * werden als Parameter hinzugef�gt.
       */
      JAXBContext kontext =
          JAXBContext
              .newInstance(de.w3l.anw.wetterstation.xml.wdaten.Weather.class);

      /*
       * Der Unmarshaller ist der eigentliche "Auspacker".
       * Er kann aus der XML-Datei Objekte erzeugen. Wir
       * erzeugen zun�chst einen Unmarshaller f�r den
       * vorgegebenen Kontext.
       */
      Unmarshaller importierer =
          kontext.createUnmarshaller();

      /*
       * Jetzt kann der Unmarshaller t�tig werden. Wir
       * erhalten ein (Weather-)Objekt von der Wetter-URL.
       */
      Object einObjektGraph =
          importierer.unmarshal(erzeugeWetterURL());

      // Mit xmlWetter erhalten wir ein Weather-Objekt
      Weather xmlWetter = (Weather) einObjektGraph;
      neuesWetter = new Wetter();

      // Wenn die Astronomiedaten heute noch nicht geholt,
      // erfolgt dies jetzt. Um zu �berpr�fen, ob ein neuer
      // Tag vorliegt, muss getestet werden, ob
      // letzteAbfrageWetter schon einen Tag weiter ist als
      // letzteAbfrageAstronomie.
      // Tats�chlich wird getestet, ob nicht gilt,
      // dassdie Astronomiedaten �berhaupt schon einmal
      // abgefragt wurden und dies am selben Tag wie die 
      // letzte Wetterabfrage war.
      if (!((letzteAbfrageAstronomie != null) && (letzteAbfrageAstronomie
          .toLocalDate().equals(letzteAbfrageWetter
              .toLocalDate())))) {
        // Astronomiedaten holen
        letzteAbfrageAstronomie = LocalDateTime.now();
        kontext =
            JAXBContext
                .newInstance(de.w3l.anw.wetterstation.xml.adaten.Astronomie.class);
        importierer = kontext.createUnmarshaller();
        astrodaten =
            (Astronomie) importierer
                .unmarshal(erzeugeAstronomieURL());
      }
      // Sonnenaufgang
      neuesWetter.setSonnenaufgang(LocalTime.of(astrodaten
          .getSunPhase().getSunrise().getHour(), astrodaten
          .getSunPhase().getSunrise().getMinute()));
      // Sonnenuntergang
      neuesWetter
          .setSonnenuntergang(LocalTime.of(astrodaten
                  .getSunPhase().getSunset().getHour(),
              astrodaten.getSunPhase().getSunset()
                  .getMinute()));

      // OrtsID der gelesenen Wetterdaten holen
      short ortsId =
          xmlWetter.getCurrentObservation()
              .getDisplayLocation().getWmo();

      if (ortsId != 0) {
        if (ort != null) {
          /*
           * Wir haben bereits einen Ort und m�ssen nun
           * pr�fen, ob die ID dieses Ortes mit den
           * eingelesenen Daten �bereinstimmt.
           */
          if (!(ort.getId().equals(String.valueOf(ortsId))))
            throw new WetterquelleException(
                "Daten f�r falschen Ort angefordert");
          // Zeitzone sicherheitshalber setzen, da sich
          // die Differenz zu GMT wegen Sommerzeit/
          // Winterzeit ge�ndert haben k�nnte.
          ort.setOffset(xmlWetter.getCurrentObservation()
              .getLocalTzOffset());
          neuesWetter.setOrt(ort);
        } else {
          /*
           * Wir haben noch keinen Ort, nehmen also den
           * gerade gelesenen
           */
          Ort neuerOrt = new Ort();
          neuerOrt.setId(String.valueOf(ortsId));
          neuerOrt.setName(xmlWetter
              .getCurrentObservation().getDisplayLocation()
              .getFull());
          neuerOrt.setLaenge(xmlWetter
              .getCurrentObservation().getDisplayLocation()
              .getLongitude());
          neuerOrt.setBreite(xmlWetter
              .getCurrentObservation().getDisplayLocation()
              .getLatitude());
          neuerOrt.setOffset(xmlWetter
              .getCurrentObservation().getLocalTzOffset());
          neuesWetter.setOrt(neuerOrt);
        }
      } else {
        // In den gelesenen Daten ist kein "id"
        throw new WetterquelleException(
            "<<id>> nicht gefunden!");
      }

      /*
       * Jetzt lesen wir die Zeit der letzten Messung. Diese
       * hat folgendes Format: Wed, 05 Feb 2014 14:10:00
       * +0000
       */
      LocalDateTime zeit =
          LocalDateTime.parse(
              xmlWetter.getCurrentObservation()
                  .getObservationTimeRfc822(),
              DateTimeFormatter.ofPattern(
                  "EEE, dd MMM yyyy HH:mm:ss Z")
                  .withLocale(Locale.ENGLISH));
      neuesWetter.setZeit(zeit);

      // Dann kommt die Temperatur tmp
      neuesWetter.setTemperatur(xmlWetter
          .getCurrentObservation().getTempC());

      // Dann kommt die gef�hlte Temperatur feelslike
      neuesWetter.setGefuehlteTemperatur(xmlWetter
          .getCurrentObservation().getFeelslikeC());

      // URL f�r Icon Wetterzustand holen
      neuesWetter.setWetterzustand(xmlWetter
          .getCurrentObservation().getIconUrl());

      // Luftdruck
      neuesWetter.setLuftdruck(xmlWetter
          .getCurrentObservation().getPressureMb());

      // Luftfeuchtigkeit (Wert kommt als Prozentzahl)
      neuesWetter.setLuftfeuchte(Integer.parseInt(xmlWetter
          .getCurrentObservation().getRelativeHumidity()
          .replace('%', ' ').trim()));

      // Taupunkt
      neuesWetter.setTaupunkt(xmlWetter
          .getCurrentObservation().getDewpointC());

      // Windgeschwindigkeit (km/h) und Windrichtung (Grad)
      neuesWetter.setWindgeschwindigkeit(xmlWetter
          .getCurrentObservation().getWindKph());
      neuesWetter.setWindrichtung(xmlWetter
          .getCurrentObservation().getWindDegrees());

      /*
       * Der UV-Index muss gesondert behandelt werden, da
       * gelegentlich der Wert 10+ geliefert wird. Diesen
       * zeigen wir als 11 an. Manchmal kommen andere
       * nicht-numerische Werte. In diesem Fall wird -1
       * eingetragen.
       */
      String uvIndex =
          xmlWetter.getCurrentObservation().getUV();
      if (uvIndex.equals("10+")) {
        neuesWetter.setUvIndex(11);
      } else {
        neuesWetter.setUvIndex(-1);
        try {
          neuesWetter.setUvIndex(Integer.parseInt(uvIndex));
        } catch (NumberFormatException ex) {
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new WetterquelleException(ex.toString());
    }

    return neuesWetter;
  }

  private URL erzeugeAstronomieURL()
      throws MalformedURLException {
    return new URL(
        Anwendungsparameter
            .getParameter("XMLSERVER_BASE_URL")
            + Anwendungsparameter.getParameter("USER_KEY")
            + "/"
            + Anwendungsparameter
            .getParameter("XMLSERVER_ASTRODIENST")
            + "/lang:DL/q/"
            + Anwendungsparameter
            .getParameter("LAND_UND_ORT") + ".xml");
  }

  private URL erzeugeWetterURL()
      throws MalformedURLException {
    return new URL(
        Anwendungsparameter
            .getParameter("XMLSERVER_BASE_URL")
            + Anwendungsparameter.getParameter("USER_KEY")
            + "/"
            + Anwendungsparameter
            .getParameter("XMLSERVER_WETTERDIENST")
            + "/lang:DL/q/"
            + Anwendungsparameter
            .getParameter("LAND_UND_ORT") + ".xml");
  }
}
