package de.w3l.anw.avplus.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FormularGUI extends javax.swing.JPanel {

  private static final long serialVersionUID = 1L;

  private AVSchaltflaeche btn1OK;

  private AVSchaltflaeche btn2Fort;

  private AVSchaltflaeche btn3Abbr;

  private JPanel zentralPnl;

  private JPanel schaltflaechenPnl;

  private JLabel ueberschriftLbl;

  private JPanel ueberschriftPnl;

  /**
   * Ein GUI braucht f�r die korrekte Ausgabe von Zahlen ein
   * Locale
   */
  @SuppressWarnings("unused")
  private Locale meinLocale;

  public FormularGUI(Locale meinLocale) {
    super();
    this.meinLocale = meinLocale;
    initGUI();
  }

  public FormularGUI() {
    initGUI();
  }

  private void initGUI() {
    try {
      BorderLayout thisLayout = new BorderLayout();
      this.setLayout(thisLayout);
      this.setPreferredSize(new Dimension(590, 320));
      this.setSize(450, 300);
      {
        ueberschriftPnl = new JPanel();
        this.add(ueberschriftPnl, BorderLayout.NORTH);
        {
          ueberschriftLbl = new JLabel("�berschrift");
          ueberschriftPnl.add(ueberschriftLbl);
          ueberschriftLbl.setFont(new java.awt.Font(
              "Dialog", 1, 22));
          ueberschriftLbl
              .setHorizontalAlignment(SwingConstants.CENTER);
        }
      }
      {
        zentralPnl = new JPanel();
        zentralPnl
            .setPreferredSize(new Dimension(590, 340));
        zentralPnl.setMinimumSize(new Dimension(590, 340));
        this.add(zentralPnl, BorderLayout.CENTER);
      }
      {
        schaltflaechenPnl = new JPanel();
        this.add(schaltflaechenPnl, BorderLayout.SOUTH);
        {

          btn1OK = new AVSchaltflaeche();
          schaltflaechenPnl.add(btn1OK);
          btn1OK.setText(Messages
              .getString("AV.BTN_SAVENQUIT"));
          btn1OK
              .setFont(new java.awt.Font("Dialog", 1, 12));
          btn1OK.setActionCommand("SAVENQUIT");
          btn1OK.setToolTipText("OK-Button Tooltiptext");
          btn1OK.setPreferredSize(new java.awt.Dimension(
              140, 30));
        }
        {

          btn2Fort = new AVSchaltflaeche();
          btn2Fort.setText(Messages
              .getString("AV.BTN_SAVE")); //$NON-NLS-1$
          schaltflaechenPnl.add(btn2Fort);
          btn2Fort.setFont(new java.awt.Font("Dialog", 1,
              12));
          btn2Fort.setActionCommand("SAVE");
          btn2Fort.setToolTipText("Tooltip");
          btn2Fort.setPreferredSize(new java.awt.Dimension(
              140, 30));
        }
        {
          btn3Abbr = new AVSchaltflaeche();
          schaltflaechenPnl.add(btn3Abbr);
          btn3Abbr.setText(Messages
              .getString("AV.BTN_CANCEL")); //$NON-NLS-1$
          btn3Abbr.setFont(new java.awt.Font("Dialog", 1,
              12));
          // abbrechenBtn.setSize(100, 23);
          btn3Abbr.setActionCommand("CANCEL");
          btn3Abbr.setPreferredSize(new java.awt.Dimension(
              140, 30));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public JButton getOkBtn() {
    return btn1OK;
  }

  public JButton getUebernehmenBtn() {
    return btn2Fort;
  }

  public JButton getAbbrechenBtn() {
    return btn3Abbr;
  }

  public JPanel getZentralPnl() {
    return zentralPnl;
  }

  public JPanel getSchaltflaechenPnl() {
    return schaltflaechenPnl;
  }

  public JPanel getUeberschriftPnl() {
    return ueberschriftPnl;
  }

  public JLabel getUeberschriftLbl() {
    return ueberschriftLbl;
  }
}
