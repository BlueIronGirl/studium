import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Created by Alice on 25.06.2016.
 */
public class MenuNeuEingabe extends JDialog {
  private MenuBalken menuBalken;

  public MenuNeuEingabe(JFrame anwendungsfenster, String titel, MenuBalken menuBalken) {
    super(anwendungsfenster, titel);
    this.menuBalken = menuBalken;
    setSize(200, 200);
    setLayout(null);
    anzeigeDialog();
    this.addWindowListener
        (
            new WindowAdapter() {
              public void windowClosing(WindowEvent event) {
                setVisible(false);
                dispose();
              }
            }
        );
  }

  public void anzeigeDialog() {
    String[] menues = menuBalken.getMenus();
    JComboBox comboBox = new JComboBox(menues);
    comboBox.setBounds(10,10, 80, 30);
    add(comboBox);
    if (menues.length > 0) {
      String init = menues[0];
      String menu = (String) JOptionPane.showInputDialog(this, "Auswahl des Menüs", "Menütitel",
          JOptionPane.PLAIN_MESSAGE, null, menues, init);
      if (menu != null && !menu.isEmpty()) {
        String name = (String) JOptionPane.showInputDialog(this, "Menüname", "Menüname",
            JOptionPane.PLAIN_MESSAGE);
        menuBalken.addOption(menu, name);
      }


    } else {
      JOptionPane.showMessageDialog(this, "Es sind keine Menüs vorhanden!", "Frage", JOptionPane.ERROR_MESSAGE);
    }
  }
}
