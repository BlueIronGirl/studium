package de.w3l.anw.wetterstation.gui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Anwendungsparameter
{
  // Im Paket-Verzeichnis de.w3l.anw.wetterstation
  // liegen
  // die Property-Dateien mit
  // dem Namen wetterstation_xx_XX-properties.
  private static final String BUNDLE_NAME =
      "de.w3l.anw.wetterstation.wetterstation";

  private static final ResourceBundle RESOURCE_BUNDLE =
      ResourceBundle.getBundle(BUNDLE_NAME);

  private Anwendungsparameter()
  {
  }

  public static String getParameter(String key)
  {
    try
    {
      return RESOURCE_BUNDLE.getString(key);
    }
    catch (MissingResourceException e)
    {
      return '!' + key + '!';
    }
  }
}
