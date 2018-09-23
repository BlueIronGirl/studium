import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 25.06.2016.
 */
public class MenuGenerator extends JFrame {
  private static MenuBalken menuBalken;
  private static MenuGenerator anwendungsfenster;

  public MenuGenerator(String titel) {
    super(titel);
    getContentPane().setLayout(null); //LayoutManager
    //Fenster schliessen
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
        anzeigenEndeDialog();
      }
    });
  }

  private void anzeigenEndeDialog() {
    int ergebnisEndeDialog = JOptionPane.showConfirmDialog(anwendungsfenster, "Soll die Anwendung beendet werden?", "Frage", JOptionPane.YES_NO_OPTION);
    if (ergebnisEndeDialog == JOptionPane.YES_OPTION) {
      anwendungsfenster.setVisible(false);
      anwendungsfenster.dispose(); //Freigabe der Ressourcen
      System.exit(0); //Beenden
    } else if (ergebnisEndeDialog == JOptionPane.NO_OPTION || ergebnisEndeDialog == JOptionPane.CLOSED_OPTION) {
      anwendungsfenster.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
  }

  public static void init() {
    anwendungsfenster = new MenuGenerator("Menü-Generator");
    menuBalken = new MenuBalken();

    anwendungsfenster.setJMenuBar(menuBalken);

    anwendungsfenster.setSize(650, 400); //Fenstergroesse
    anwendungsfenster.setVisible(true);

    JButton neuMenu = new JButton("Neues Menü");
    neuMenu.setBounds(20, 10, 130, 30);
    anwendungsfenster.add(neuMenu);
    neuMenu.addActionListener(ereignis -> {
      String eingabe = JOptionPane.showInputDialog(anwendungsfenster, "Menütitel", "Eingabe", JOptionPane.PLAIN_MESSAGE);
      if (eingabe != null && !eingabe.isEmpty()) {
        menuBalken.addMenu(eingabe);
        anwendungsfenster.setJMenuBar(menuBalken); //ohne wird das neue Menu nicht angezeigt...
      }
    });

    JButton neuMenuOption = new JButton("Neue Menüoption");
    neuMenuOption.setBounds(155, 10, 150, 30);
    anwendungsfenster.add(neuMenuOption);
    neuMenuOption.addActionListener(ereignis -> {
      String[] menues = menuBalken.getMenus();
      if (menues.length > 0) {
        String init = menues[0];
        String menu = (String) JOptionPane.showInputDialog(anwendungsfenster, "Auswahl des Menüs", "Menütitel",
            JOptionPane.PLAIN_MESSAGE, null, menues, init);
        if (menu != null && !menu.isEmpty()) {
          String name = (String) JOptionPane.showInputDialog(anwendungsfenster, "Optionsname", "Menüname",
              JOptionPane.PLAIN_MESSAGE);
          if (name != null && !name.isEmpty()) {
            menuBalken.addOption(menu, name);
          }
        }
//        MenuNeuEingabe menuNeuEingabe = new MenuNeuEingabe(anwendungsfenster, "Neu Menüoption", menuBalken);
//        menuNeuEingabe.setVisible(true);
      } else {
        JOptionPane.showMessageDialog(anwendungsfenster, "Es sind keine Menüs vorhanden!", "Frage", JOptionPane.ERROR_MESSAGE);
      }
    });

    JButton menuLoeschen = new JButton("Menü löschen");
    menuLoeschen.setBounds(310, 10, 130, 30);
    anwendungsfenster.add(menuLoeschen);
    menuLoeschen.addActionListener(ereignis -> {
      String[] menues = menuBalken.getMenus();
      if (menues.length > 0) {
        String init = menues[0];
        String eingabe = (String) JOptionPane.showInputDialog(anwendungsfenster, "Löschen des Menüs", "Menütitel",
            JOptionPane.PLAIN_MESSAGE, null, menues, init);
        if (eingabe != null && !eingabe.isEmpty()) {
          menuBalken.removeMenu(eingabe);
        }
      } else {
        JOptionPane.showMessageDialog(anwendungsfenster, "Es sind keine Menüs vorhanden!", "Frage", JOptionPane.ERROR_MESSAGE);
      }
    });

    JButton menuOptionLoeschen = new JButton("Menüoption löschen");
    menuOptionLoeschen.setBounds(445, 10, 150, 30);
    anwendungsfenster.add(menuOptionLoeschen);
    menuOptionLoeschen.addActionListener(ereignis -> {
      String[] menues = menuBalken.getMenus();
      if (menues.length > 0) {
        String init = menues[0];
        String menu = (String) JOptionPane.showInputDialog(anwendungsfenster, "Auswahl des Menüs", "Menütitel",
            JOptionPane.PLAIN_MESSAGE, null, menues, init);
        if (menu != null && !menu.isEmpty()) {
          String[] options = menuBalken.getMenuOption(menu);
          if (options.length > 0) {
            init = options[0];
            String name = (String) JOptionPane.showInputDialog(anwendungsfenster, "Optionsname", "Menüname",
                JOptionPane.PLAIN_MESSAGE, null, options, init);
            if (name != null && !name.isEmpty()) {
              menuBalken.removeOption(menu, name);
//              anwendungsfenster.setJMenuBar(menuBalken);
            }
          }
        }
      } else {
        JOptionPane.showMessageDialog(anwendungsfenster, "Es sind keine Menüs vorhanden!", "Frage", JOptionPane.ERROR_MESSAGE);
      }
    });
  }

  public static void menuAuswahl() {

  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        init();
      }
    });
  }
}
