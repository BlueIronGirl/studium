package de.w3l.anw.wetterstation.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import javax.swing.JLabel;

public class Digitaluhr extends JLabel
    implements ActionListener
{
  private static final long serialVersionUID =
      -2436855307692809518L;

  private ZoneOffset deltaUTC;
  private DateTimeFormatter dtf; 
  private ZoneId zone; 
  
  public Digitaluhr(Locale locale) {
    dtf = DateTimeFormatter.ofLocalizedTime(
            FormatStyle.MEDIUM).withLocale(locale);
  }

  public ZoneOffset getDeltaUTC()
  {
    return deltaUTC;
  }

  public void setDeltaUTC(ZoneOffset deltaUTC)
  {
    this.deltaUTC = deltaUTC;
    zone = ZoneId.ofOffset("UTC", deltaUTC);
  }

  /*
   * Die Methode wird regelmäßig über javax.swing.Timer
   * innerhalb des EDT angestossen
   */
  public void actionPerformed(ActionEvent arg0)
  {
    this.setText(OffsetTime.now(zone).format(dtf));
  }
}
