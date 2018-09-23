package de.w3l.anw.avplus.gui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
  // Im Paket-Verzeichnis
  // de.w3l.anw.avplus.gui liegen
  // die Property-Dateien mit
  // dem Namen messages_xx_XX-properties.
  private static final String BUNDLE_NAME =
      "de.w3l.anw.avplus.gui.messages";

  private static final ResourceBundle RESOURCE_BUNDLE =
      ResourceBundle.getBundle(BUNDLE_NAME);

  private Messages() {
  }

  public static String getString(String key) {
    try {
      return RESOURCE_BUNDLE.getString(key);
    } catch (MissingResourceException e) {
      return '!' + key + '!';
    }
  }
}
