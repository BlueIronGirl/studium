package de.w3l.anw.avplus.gui;

import java.awt.Color;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class AuftragFormular extends javax.swing.JPanel {

  private static final long serialVersionUID = 1L;

  private JTextField gesamtpreisTf;

  private JTextField einzelpreisTf;

  private JTextField mengeTf;

  private JTextField bezeichnungTf;

  private JTextField artikelnrTf;

  private JLabel gesamtpreisLbl;

  private JLabel einzelpreisLbl;

  private JLabel mengeLbl;

  private JLabel bezeichnungLbl;

  private JLabel artikelnrLbl;

  private JTextField auftragssummeTf;

  private JTextField nameTf;

  private JFormattedTextField lieferdatumTf;

  private JComboBox<String> waehrungCb;

  private JTextField kundennrTf;

  private JFormattedTextField auftragsdatumTf;

  private JTextField auftragsnrTf;

  private JLabel auftragssummeLbl;

  private JLabel nameLbl;

  private JLabel lieferdatumLbl;

  private JLabel waehrungLbl;

  private JLabel kundennrLbl;

  private JPanel inhaltPnl;

  private JScrollPane auftragspositionenSPnl;

  private JLabel auftragsdatumLbl;

  private JLabel auftragsnrLbl;

  private JTable auftragspositionenTbl;

  private JPanel aufposEingabePnl;

  /**
   * Ein GUI braucht ein Locale, wenn Betr�ge korrekt mit
   * Dezimalpunkt oder Dezimalkomma ausgegeben werden
   * sollen.
   */
  @SuppressWarnings("unused")
  private Locale meinLocale;

  private JButton hinzufuegenBtn;

  public AuftragFormular(Locale meinLocale) {
    super();
    this.meinLocale = meinLocale;
    initGUI();
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private void initGUI() {
    try {
      FormLayout felderPnlLayout =
          new FormLayout(
              "5dlu, max(p;5dlu), 5dlu, max(p;25dlu), max(p;40dlu), 5dlu, max(p;5dlu), max(p;5dlu), 65dlu, max(p;20dlu):grow, 5dlu",
              "5dlu, max(p;5dlu), 5dlu, max(p;15dlu), 5dlu, max(p;10dlu), 5dlu, max(p;10dlu), 5dlu");
      this.setLayout(felderPnlLayout);
      this.setPreferredSize(new java.awt.Dimension(511, 238));
      auftragsnrLbl = new JLabel();
      this.add(getAuftragsnrLbl(), new CellConstraints(
          "2, 2, 1, 1, default, default"));
      auftragsnrLbl.setText(Messages
          .getString("AV.ORD_ORDNR"));
      auftragsnrLbl.setFont(new java.awt.Font("Dialog", 1,
          12));
      auftragsnrLbl
          .setHorizontalAlignment(SwingConstants.RIGHT);
      auftragsdatumLbl = new JLabel();
      this.add(auftragsdatumLbl, new CellConstraints(
          "2, 4, 1, 1, default, default"));
      auftragsdatumLbl.setText(Messages
          .getString("AV.ORD_ORDDAT"));
      auftragsdatumLbl.setFont(new java.awt.Font("Dialog",
          1, 12));
      auftragsdatumLbl
          .setHorizontalAlignment(SwingConstants.RIGHT);
      kundennrLbl = new JLabel();
      this.add(kundennrLbl, new CellConstraints(
          "2, 6, 1, 1, default, default"));
      kundennrLbl.setText(Messages.getString("AV.CUSTNR"));
      kundennrLbl
          .setFont(new java.awt.Font("Dialog", 1, 12));
      kundennrLbl
          .setHorizontalAlignment(SwingConstants.RIGHT);
      waehrungLbl = new JLabel();
      this.add(waehrungLbl, new CellConstraints(
          "2, 8, 1, 1, default, default"));
      waehrungLbl
          .setText(Messages.getString("AV.CURRENCY"));
      waehrungLbl
          .setFont(new java.awt.Font("Dialog", 1, 12));
      waehrungLbl
          .setHorizontalAlignment(SwingConstants.RIGHT);
      lieferdatumLbl = new JLabel();
      this.add(lieferdatumLbl, new CellConstraints(
          "7, 4, 1, 1, default, default"));
      lieferdatumLbl.setText(Messages
          .getString("AV.ORD_DELDAT"));
      lieferdatumLbl.setFont(new java.awt.Font("Dialog", 1,
          12));
      lieferdatumLbl
          .setHorizontalAlignment(SwingConstants.RIGHT);
      nameLbl = new JLabel();
      this.add(nameLbl, new CellConstraints(
          "7, 6, 1, 1, default, default"));
      nameLbl.setText(Messages.getString("AV.CUSTNAME"));
      nameLbl.setFont(new java.awt.Font("Dialog", 1, 12));
      nameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
      auftragssummeLbl = new JLabel();
      this.add(auftragssummeLbl, new CellConstraints(
          "7, 8, 1, 1, default, default"));
      auftragssummeLbl.setText(Messages
          .getString("AV.ORD_AMOUNT"));
      auftragssummeLbl.setFont(new java.awt.Font("Dialog",
          1, 12));
      auftragssummeLbl
          .setHorizontalAlignment(SwingConstants.RIGHT);
      auftragsnrTf = new JTextField();
      this.add(auftragsnrTf, new CellConstraints(
          "4, 2, 2, 1, default, default"));
      auftragsnrTf
          .setHorizontalAlignment(SwingConstants.RIGHT);
      auftragsnrTf.setEnabled(false);
      auftragsnrTf.setDisabledTextColor(new Color(0, 0, 0));
      auftragsdatumTf = new JFormattedTextField();
      this.add(auftragsdatumTf, new CellConstraints(
          "4, 4, 2, 1, default, default"));
      auftragsdatumTf
          .setHorizontalAlignment(SwingConstants.RIGHT);
      auftragsdatumTf.setFocusTraversalKeysEnabled(false);
      kundennrTf = new JTextField();
      this.add(kundennrTf, new CellConstraints(
          "4, 6, 2, 1, default, default"));
      kundennrTf
          .setHorizontalAlignment(SwingConstants.RIGHT);
      waehrungCb = new JComboBox(new String[]{"EUR"});
      this.add(waehrungCb, new CellConstraints(
          "4, 8, 1, 1, default, default"));
      waehrungCb.setFont(new java.awt.Font("DialogInput",
          0, 12));
      lieferdatumTf = new JFormattedTextField();
      this.add(lieferdatumTf, new CellConstraints(
          "9, 4, 1, 1, default, default"));
      lieferdatumTf
          .setHorizontalAlignment(SwingConstants.RIGHT);
      nameTf = new JTextField();
      this.add(nameTf, new CellConstraints(
          "9, 6, 2, 1, default, default"));
      nameTf.setEnabled(false);
      nameTf.setDisabledTextColor(new Color(0, 0, 0));
      auftragssummeTf = new JTextField();
      this.add(auftragssummeTf, new CellConstraints(
          "9, 8, 1, 1, default, default"));
      auftragssummeTf
          .setHorizontalAlignment(SwingConstants.RIGHT);
      auftragssummeTf.setEnabled(false);
      auftragssummeTf.setDisabledTextColor(new Color(0, 0,
          0));
      aufposEingabePnl = new JPanel();
      FormLayout aufposEingabePnlLayout =
          new FormLayout(
              "max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu):grow, max(p;1dlu), max(p;5dlu):grow, max(p;1dlu), max(p;5dlu):grow, max(p;1dlu), max(p;5dlu):grow, max(p;1dlu), max(p;5dlu):grow, max(p;5dlu)",
              "5dlu, max(p;5dlu), 5dlu, max(p;5dlu), 5dlu");
      aufposEingabePnl.setLayout(aufposEingabePnlLayout);
      artikelnrLbl = new JLabel();
      aufposEingabePnl.add(getArtikelnrLbl(),
          new CellConstraints(
              "4, 2, 1, 1, default, default"));
      artikelnrLbl.setText(Messages.getString("AV.ARTNR"));
      artikelnrLbl.setFont(new java.awt.Font("Dialog", 1,
          12));
      artikelnrLbl
          .setHorizontalAlignment(SwingConstants.CENTER);
      bezeichnungLbl = new JLabel();
      aufposEingabePnl.add(getBezeichnungLbl(),
          new CellConstraints(
              "6, 2, 1, 1, default, default"));
      bezeichnungLbl.setText(Messages
          .getString("AV.ARTNAME"));
      bezeichnungLbl.setFont(new java.awt.Font("Dialog", 1,
          12));
      bezeichnungLbl
          .setHorizontalAlignment(SwingConstants.CENTER);
      mengeLbl = new JLabel();
      aufposEingabePnl.add(getMengeLbl(),
          new CellConstraints(
              "8, 2, 1, 1, default, default"));
      mengeLbl.setText(Messages.getString("AV.ART_AMOUNT"));
      mengeLbl.setFont(new java.awt.Font("Dialog", 1, 12));
      mengeLbl
          .setHorizontalAlignment(SwingConstants.CENTER);
      einzelpreisLbl = new JLabel();
      aufposEingabePnl.add(getEinzelpreisLbl(),
          new CellConstraints(
              "10, 2, 1, 1, default, default"));
      einzelpreisLbl.setText(Messages
          .getString("AV.ART_SPRICE"));
      einzelpreisLbl.setFont(new java.awt.Font("Dialog", 1,
          12));
      einzelpreisLbl
          .setHorizontalAlignment(SwingConstants.CENTER);
      gesamtpreisLbl = new JLabel();
      aufposEingabePnl.add(gesamtpreisLbl,
          new CellConstraints(
              "12, 2, 1, 1, default, default"));
      gesamtpreisLbl.setText(Messages
          .getString("AV.ART_TPRICE"));
      gesamtpreisLbl.setFont(new java.awt.Font("Dialog", 1,
          12));
      gesamtpreisLbl
          .setHorizontalAlignment(SwingConstants.RIGHT);
      hinzufuegenBtn = new JButton();
      aufposEingabePnl.add(getHinzufuegenBtn(),
          new CellConstraints(
              "2, 4, 1, 1, default, default"));
      hinzufuegenBtn.setText("+");
      artikelnrTf = new JTextField();
      aufposEingabePnl.add(getArtikelnrTf(),
          new CellConstraints(
              "4, 4, 1, 1, default, default"));
      artikelnrTf.setFont(new java.awt.Font("DialogInput",
          0, 12));
      artikelnrTf
          .setHorizontalAlignment(SwingConstants.RIGHT);
      bezeichnungTf = new JTextField();
      aufposEingabePnl.add(getBezeichnungTf(),
          new CellConstraints(
              "6, 4, 1, 1, default, default"));
      bezeichnungTf.setFont(new java.awt.Font(
          "DialogInput", 0, 12));
      bezeichnungTf.setEnabled(false);
      bezeichnungTf
          .setDisabledTextColor(new Color(0, 0, 0));
      mengeTf = new JTextField();
      aufposEingabePnl.add(getMengeTf(),
          new CellConstraints(
              "8, 4, 1, 1, default, default"));
      mengeTf.setFont(new java.awt.Font("DialogInput", 0,
          12));
      mengeTf.setHorizontalAlignment(SwingConstants.RIGHT);
      einzelpreisTf = new JTextField();
      aufposEingabePnl.add(getEinzelpreisTf(),
          new CellConstraints(
              "10, 4, 1, 1, default, default"));
      einzelpreisTf.setFont(new java.awt.Font(
          "DialogInput", 0, 12));
      einzelpreisTf.setEnabled(false);
      einzelpreisTf
          .setDisabledTextColor(new Color(0, 0, 0));
      einzelpreisTf
          .setHorizontalAlignment(SwingConstants.RIGHT);
      gesamtpreisTf = new JTextField();
      aufposEingabePnl.add(getGesamtpreisTf(),
          new CellConstraints(
              "12, 4, 1, 1, default, default"));
      gesamtpreisTf.setFont(new java.awt.Font(
          "DialogInput", 0, 12));
      gesamtpreisTf.setEnabled(false);
      gesamtpreisTf
          .setDisabledTextColor(new Color(0, 0, 0));
      gesamtpreisTf
          .setHorizontalAlignment(SwingConstants.RIGHT);
      auftragspositionenSPnl = new JScrollPane();
      inhaltPnl.add(auftragspositionenSPnl);
      TableModel auftragspositionenTblModel =
          new DefaultTableModel(new String[][]{
              {"One", "Two"}, {"Three", "Four"}},
              new String[]{"Column 1", "Column 2"});
      auftragspositionenTbl = new JTable();
      auftragspositionenSPnl
          .setViewportView(getAuftragspositionenTbl());
      auftragspositionenTbl
          .setModel(auftragspositionenTblModel);
      this.add(aufposEingabePnl);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public JTable getAuftragspositionenTbl() {
    return auftragspositionenTbl;
  }

  public JLabel getAuftragsnrLbl() {
    return auftragsnrLbl;
  }

  public JTextField getAuftragsnrTf() {
    return auftragsnrTf;
  }

  public JFormattedTextField getAuftragsdatumTf() {
    return auftragsdatumTf;
  }

  public JComboBox<String> getWaehrungCb() {
    return waehrungCb;
  }

  public JFormattedTextField getLieferdatumTf() {
    return lieferdatumTf;
  }

  public JTextField getNameTf() {
    return nameTf;
  }

  public JTextField getAuftragssummeTf() {
    return auftragssummeTf;
  }

  public JLabel getArtikelnrLbl() {
    return artikelnrLbl;
  }

  public JLabel getBezeichnungLbl() {
    return bezeichnungLbl;
  }

  public JLabel getMengeLbl() {
    return mengeLbl;
  }

  public JLabel getEinzelpreisLbl() {
    return einzelpreisLbl;
  }

  public JButton getHinzufuegenBtn() {
    return hinzufuegenBtn;
  }

  public JTextField getArtikelnrTf() {
    return artikelnrTf;
  }

  public JTextField getBezeichnungTf() {
    return bezeichnungTf;
  }

  public JTextField getMengeTf() {
    return mengeTf;
  }

  public JTextField getEinzelpreisTf() {
    return einzelpreisTf;
  }

  public JTextField getGesamtpreisTf() {
    return gesamtpreisTf;
  }

  /* Hilfsmethoden */
  public void loescheKopf() {
    auftragsdatumTf.setText("");
    lieferdatumTf.setText("");
    auftragsnrTf.setText("");
    nameTf.setText("");
    kundennrTf.setText("");
    auftragssummeTf.setText("");
  }

  public void loescheAposEingabezeile() {
    artikelnrTf.setText("");
    bezeichnungTf.setText("");
    mengeTf.setText("");
    einzelpreisTf.setText("");
    gesamtpreisTf.setText("");
  }

  public void loeschen() {
    loescheKopf();
    loescheAposEingabezeile();
  }

  public JTextField getKundennrTf() {
    return kundennrTf;
  }
}