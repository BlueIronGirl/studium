import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Created by Alice on 25.06.2016.
 */
public class MenuBalken extends JMenuBar {
  public MenuBalken() {
    super();
    JMenu einMenue = new JMenu("Bedow");
    JMenuItem menuItem = new JMenuItem("Alice");
    einMenue.add(menuItem);
    add(einMenue);
  }

  public void addMenu(String name) {
    JMenu einMenu = new JMenu(name);
    this.add(einMenu);
    repaint();
  }

  public void addOption(String menu, String name) {
    for (int i = 0; i < getMenuCount(); i++) { //passendes Menu suchen
      if (getMenu(i).getText().equals(menu)) {
        getMenu(i).add(new JMenuItem(name)); //neues MenuItem hinzufuegen
//        getMenu(i).add(name); //alternativ
      }
    }
    repaint();
  }

  public void removeMenu(String name) {
    for (int i = 0; i < getMenuCount(); i++) { //passendes Menu suchen
      if (getMenu(i).getText().equals(name)) {
        remove(i);
      }
    }
    repaint();
  }

  public void removeOption(String menu, String name) {
    for (int i = 0; i < getMenuCount(); i++) { //passende Option suchen
      if (getMenu(i).getText().equals(menu)) {
        for (int j = 0; j < getMenu(i).getItemCount(); j++) {
          if (getMenu(i).getItem(j).getText().equals(name)) {
            getMenu(i).remove(j);
          }
        }
      }
    }
    repaint();
  }

  public String[] getMenus() {
    String[] menues = new String[getMenuCount()];
    for (int i = 0; i < getMenuCount(); i++) {
      menues[i] = getMenu(i).getText();
    }
    return menues;
  }

  public String[] getMenuOption(String menu) {
    JMenu jMenu = null;
    for (int i = 0; i < getMenuCount(); i++) { //passendes Menu suchen
      if (getMenu(i).getText().equals(menu)) {
        jMenu = getMenu(i);
      }
    }
    if (jMenu != null) {
      String[] menues = new String[jMenu.getItemCount()];
      for (int i = 0; i < jMenu.getItemCount(); i++) {
        menues[i] = jMenu.getItem(i).getText();
      }
      return menues;
    }
    return null;
  }
}
