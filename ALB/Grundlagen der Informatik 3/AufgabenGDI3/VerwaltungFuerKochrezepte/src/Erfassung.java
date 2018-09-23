import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * Created by Alice on 25.06.2016.
 */
public class Erfassung extends JDialog {
  private Rezept rezept;
  private JPanel inhaltsfeld = new JPanel();
  private JTextField tNummer = new JTextField();
  private JTextField tName = new JTextField();
  private JComboBox tSchwierigkeit = new JComboBox();
  private JTextArea tZubereitung = new JTextArea();
  private JTextField tZubereitungszeit = new JTextField();
  private JTextField tMenge = new JTextField();
  private JTextField tZutat = new JTextField();
  private JButton hinzuefuegen = new JButton("Hinzufügen");
  private JTable zutaten = new JTable();
  private ListSelectionModel selectionModel;
  private ZutatenModell zutatenModell;
  private JButton speichern = new JButton("Speichern");
  private JButton abbrechen = new JButton("Abbrechen");
  private JPanel jPanel;
  private Container container = Container.getContainer();
  private JFrame anwendungsfenster;

  public Erfassung(JFrame anwendungsfenster, String titel, Rezept rezept) {
    super(anwendungsfenster, titel);
    setSize(anwendungsfenster.getWidth(), 800);
    setVisible(true);
    setBackground(Color.gray);
    getContentPane().add(inhaltsfeld);
    this.anwendungsfenster = anwendungsfenster;
    this.rezept = rezept;
    if (rezept != null) {
      fuellen();
    } else {
      this.rezept = new Rezept("", "", 0, "");
    }
    zutatenModell = new ZutatenModell(this.rezept);
    JLabel lNummer = new JLabel("Nummer");
    lNummer.setBounds(10, 10, 100, 40);
    JLabel lName = new JLabel("Name");
    lName.setBounds(10, 50, 100, 40);
    JLabel lSchwierigkeit = new JLabel("Schwierigkeit");
    lSchwierigkeit.setBounds(10, 90, 100, 40);
    JLabel lZubereitung = new JLabel("Zubereitung");
    lZubereitung.setBounds(10, 150, 100, 40);
    JLabel lZubereitungszeit = new JLabel("Zubereitungszeit");
    lZubereitungszeit.setBounds(10, 210, 100, 40);
    JLabel lZutaten = new JLabel("Zutaten");
    lZutaten.setBounds(10, 250, 100, 40);
    JLabel lMenge = new JLabel("Menge");
    lMenge.setBounds(120, 290, 100, 40);
    JLabel lZutat = new JLabel("Zutat");
    lZutat.setBounds(120, 330, 100, 40);

    tNummer.setBounds(110, 10, 400, 40);
    tName.setBounds(110, 50, 400, 40);
    tSchwierigkeit.setBounds(110, 90, 400, 40);
    tZubereitung.setBounds(110, 130, 400, 80);
    tZubereitungszeit.setBounds(110, 210, 400, 40);
    tMenge.setBounds(230, 290, 400, 40);
    tZutat.setBounds(230, 330, 400, 40);
    hinzuefuegen.setBounds(230, 370, 360, 40);
    hinzuefuegen.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        zutatSpeichern();
      }
    });
    tZubereitung.setText("[...]");

    for (int i = 0; i < SchwierigkeitsgradET.getSchwierigkeitsgrade().size(); i++) {
      System.out.println(SchwierigkeitsgradET.getSchwierigkeitsgrade().get(i));
      tSchwierigkeit.addItem(SchwierigkeitsgradET.getSchwierigkeitsgrade().get(i));
    }

    add(lNummer);
    add(lName);
    add(lSchwierigkeit);
    add(lZubereitung);
    add(lZubereitungszeit);
    add(lZutaten);
    add(lMenge);
    add(lZutat);
    add(tNummer);
    add(tName);
    add(tSchwierigkeit);
    add(tZubereitung);
    add(tZubereitungszeit);
    add(tMenge);
    add(tZutat);
    add(hinzuefuegen);

    zutaten.setModel(zutatenModell);
    zutaten.setBounds(110, 410, 400, 70);
    selectionModel = zutaten.getSelectionModel();
    selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    speichern.setBounds(10, 480, 360, 40);
    abbrechen.setBounds(380, 480, 360, 40);
    speichern.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        speichern();
      }
    });

    abbrechen.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        abbrechen();
      }
    });
    add(speichern);
    add(abbrechen);

    jPanel = new JPanel();
    jPanel.setBounds(110, 410, 400, 70);
    jPanel.setBackground(Color.white);
    jPanel.add(zutaten);
    getContentPane().add(jPanel);

    JPanel jPanel1 = new JPanel();
    jPanel1.setBounds(10, 480, 50, 50);
//    jPanel1.setBackground(Color.red);
    getContentPane().add(jPanel1);
    getContentPane().setSize(anwendungsfenster.getWidth(), 800);
  }

  public void fuellen() {
    if (rezept != null) {
      tNummer.setText(String.valueOf(rezept.getNr()));
      tName.setText(rezept.getName());
      tSchwierigkeit.setSelectedItem(rezept.getSchwierkeitsgradET());
      tZubereitung.setText(rezept.getZubereitung());
      tZubereitungszeit.setText(String.valueOf(rezept.getZubereitungszeit()));
    }
  }

  public void speichern() {
    if (isNumber(tNummer.getText(), tZubereitungszeit.getText())) {
      rezept.setNr(Integer.parseInt(tNummer.getText()));
      rezept.setName(tName.getText());
      rezept.setSchwierkeitsgradET(tSchwierigkeit.getSelectedItem().toString());
      rezept.setZubereitung(tZubereitung.getText());
      rezept.setZubereitungszeit(Integer.parseInt(tZubereitungszeit.getText()));
      container.add(rezept);
      JOptionPane.showMessageDialog(anwendungsfenster, "Rezept gespeichert.", "Frage", JOptionPane.PLAIN_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(anwendungsfenster, "Bitte Eingabe überprüfen.", "Frage", JOptionPane.ERROR_MESSAGE);
    }
  }

  public void zutatSpeichern() {
    Zutat zutat = new Zutat(tZutat.getText(), tMenge.getText());
    rezept.addZutat(zutat);
    zutaten.repaint();
    zutaten.updateUI();
    repaint();
  }

  public void abbrechen() {
    this.dispose();
  }

  public boolean isNumber(String... texte) {
    for (String text : texte) {
      if (!text.matches("[0-9]+")) {
        return false;
      }
    }
    return true;
  }
}
