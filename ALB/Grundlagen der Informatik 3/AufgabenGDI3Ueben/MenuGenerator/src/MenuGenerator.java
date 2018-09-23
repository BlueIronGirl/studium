import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Created by Alice on 23.07.2016.
 */
public class MenuGenerator extends JFrame {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new MenuGenerator();
      }
    });
  }

  public MenuGenerator() {
    initGUI();
  }

  public void initGUI() {
    setTitle("Menü-Generator");
    setSize(500, 300);
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JMenuBar menuBar = new JMenuBar();
    JMenu initial = new JMenu("Bedow");
    JMenuItem initialItem = new JMenuItem("Alice");
    initial.add(initialItem);
    menuBar.add(initial);
    add(menuBar);
    setJMenuBar(menuBar);

    final JButton neuesMenu = new JButton("Neues Menü");
    neuesMenu.setBounds(20, 10, 130, 30);
    add(neuesMenu);
    neuesMenu.addActionListener(ae -> {
          String neuesMenuName = JOptionPane.showInputDialog(this,
              "Neues Menüs", "Eingabe", JOptionPane.PLAIN_MESSAGE);
          if (neuesMenuName != null && !neuesMenuName.isEmpty()) {
            menuBar.add(new JMenu(neuesMenuName)); //neues Menu hinzufuegen
            setJMenuBar(menuBar); //Menubar aktualisieren
          }
        }
    );

    JButton neueMenuOption = new JButton("Neue Menüoption");
    neueMenuOption.setBounds(150, 10, 130, 30);
    add(neueMenuOption);
    neueMenuOption.addActionListener(ae -> {
          if (menuBar.getMenuCount() > 0) {
            String[] auswahl = menuesErmitteln(menuBar);
            String menuName = (String) JOptionPane.showInputDialog(this, "Menüauswahl", "Eingabe",
                JOptionPane.QUESTION_MESSAGE, null, auswahl, auswahl[0]);
            if (menuName != null && !menuName.isEmpty()) {
              JMenu jMenu = menuErmitteln(menuBar, menuName);
              if (jMenu != null) {
                String neueMenuOptionName = JOptionPane.showInputDialog(this, "Neue Menüoption",
                    "Eingabe", JOptionPane.PLAIN_MESSAGE);
                if (neueMenuOptionName != null && !neueMenuOptionName.isEmpty()) {
                  jMenu.add(new JMenuItem(neueMenuOptionName));
                }
              }
            }
          } else {
            JOptionPane.showMessageDialog(this, "Es sind keine Menüs vorhanden!",
                "Error", JOptionPane.ERROR_MESSAGE);
          }
        }
    );

    JButton menuLoeschen = new JButton("Menü löschen");
    menuLoeschen.setBounds(280, 10, 130, 30);
    add(menuLoeschen);
    menuLoeschen.addActionListener(ae ->
    {
      if (menuBar.getMenuCount() > 0) {
        String[] auswahl = menuesErmitteln(menuBar);
        String menuName = (String) JOptionPane.showInputDialog(this, "Wählen Sie das Menü aus",
            "Eingabe", JOptionPane.QUESTION_MESSAGE, null, auswahl, auswahl[0]);
        if (menuName != null && !menuName.isEmpty()) {
          menuLoeschen(menuBar, menuName);
          setJMenuBar(menuBar);
        }
      } else {
        JOptionPane.showMessageDialog(this, "Es sind keine Menüs vorhanden!",
            "Error", JOptionPane.ERROR_MESSAGE);
      }
    });

    JButton menuLoeschenItem = new JButton("Menüoption löschen");
    menuLoeschenItem.setBounds(410, 10, 130, 30);
    add(menuLoeschenItem);
    menuLoeschenItem.addActionListener(ae -> {
      if (menuBar.getMenuCount() > 0) {
        String[] auswahl = menuesErmitteln(menuBar);
        String menuName = (String) JOptionPane.showInputDialog(this, "Menüauswahl", "Eingabe",
            JOptionPane.QUESTION_MESSAGE, null, auswahl, auswahl[0]);
        if (menuName != null && !menuName.isEmpty()) {
          JMenu auswahlMenu = menuErmitteln(menuBar, menuName);
          if (auswahlMenu.getItemCount() > 0) {
            String[] itemauswahl = menuItemsErmitteln(auswahlMenu);
            String itemName = (String) JOptionPane.showInputDialog(this, "Menüoption Auswahl",
                "Eingabe", JOptionPane.QUESTION_MESSAGE, null, itemauswahl, itemauswahl[0]);
            menuItemLoeschen(auswahlMenu, itemName);
            setJMenuBar(menuBar);
          }
        }
      } else {
        JOptionPane.showMessageDialog(this, "Es sind keine Menüs vorhanden!", "Error",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    setVisible(true);
  }

  public static void menuLoeschen(JMenuBar menuBar, String menuName) {
    for (int i = 0; i < menuBar.getMenuCount(); i++) {
      JMenu jMenu = menuBar.getMenu(i);
      if (jMenu.getText().equals(menuName)) {
        menuBar.remove(i); //loeschen
      }
    }
  }

  public static void menuItemLoeschen(JMenu menu, String menuItemName) {
    for (int i = 0; i < menu.getItemCount(); i++) {
      JMenuItem jMenuItem = menu.getItem(i);
      if (jMenuItem.getText().equals(menuItemName)) {
        menu.remove(i);
      }
    }
  }

  public static JMenu menuErmitteln(JMenuBar jMenuBar, String menuName) {
    for (int i = 0; i < jMenuBar.getMenuCount(); i++) {
      JMenu jMenu = jMenuBar.getMenu(i);
      if (jMenu.getText().equals(menuName)) {
        return jMenu;
      }
    }
    return null;
  }

  public static String[] menuesErmitteln(JMenuBar jMenuBar) {
    String[] menues = new String[jMenuBar.getMenuCount()];
    for (int i = 0; i < jMenuBar.getMenuCount(); i++) {
      menues[i] = jMenuBar.getMenu(i).getText();
    }
    return menues;
  }

  public static String[] menuItemsErmitteln(JMenu jMenu) {
    String[] menuItems = new String[jMenu.getItemCount()];
    for (int i = 0; i < jMenu.getItemCount(); i++) {
      menuItems[i] = jMenu.getItem(i).getText();
    }
    return menuItems;
  }
}
