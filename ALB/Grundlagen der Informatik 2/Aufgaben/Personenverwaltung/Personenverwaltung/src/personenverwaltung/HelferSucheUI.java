/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personenverwaltung;

import java.util.ArrayList;

/**
 *
 * @author Alice Bedow
 * @date 8.12.15
 */
public class HelferSucheUI {
  private static Personenverwaltung verwaltung = Personenverwaltung.erstellePersonenverwaltung();
  private static HelferSuche helferSuche = HelferSuche.erstelleHelferSuche();
  public static void main(String[] args) {
    verwaltung.addHelfer(new Helfer(1,"Alice Bedow","Heidehang 33a","Essen",true));
    verwaltung.addHelfer(new Helfer(2, "Helfer1", "Strasse1", "Dortmund", true));
    verwaltung.addHelfer(new Helfer(3, "Helfer2", "Strasse2", "Muenchen", false));
    verwaltung.addPatient(new Patient(1, "Patient1", "Strasse10", "Dortmund", "I"));
    verwaltung.addPatient(new Patient(2, "Patient2", "Strasse20", "Koeln", "II"));
    verwaltung.addPatient(new Patient(3, "Patient3", "Strasse30", "Essen", "III"));
    verwaltung.addPatient(new Patient(4, "Patient4", "Strasse40", "Muenchen", "II"));
//    verwaltung.addHelfer(1,"Alice Bedow","Heidehang 33a","Essen",true);
//    verwaltung.addHelfer(2, "Helfer1", "Strasse1", "Dortmund", true);
//    verwaltung.addHelfer(3, "Helfer2", "Strasse2", "Muenchen", false);
//    verwaltung.addPatient(1, "Patient1", "Strasse10", "Dortmund", "I");
//    verwaltung.addPatient(2, "Patient2", "Strasse20", "Koeln", "II");
//    verwaltung.addPatient(3, "Patient3", "Strasse30", "Essen", "III");
//    verwaltung.addPatient(4, "Patient4", "Strasse40", "Muenchen", "II");
    helferSuche.setHelfer(verwaltung.getPatienten(), verwaltung.getHelfer());
    ausgabePersonen();
    ausgabeHelfer();
  }  
    public static void ausgabePersonen() {
    ArrayList<Patient> patienten = verwaltung.getPatienten();
    System.out.println("Nr\tName\t\tStrasse\t\tOrt\tStufe\t\tNr\tName\tStrasse\t\tOrt\tAusgebildet");
    for (int i = 0; i < patienten.size(); i++) {
      StringBuilder sb = new StringBuilder();
      sb.append(patienten.get(i).getNummer());
      sb.append("\t");
      sb.append(patienten.get(i).getName());
      sb.append("\t");
      sb.append(patienten.get(i).getStrasse());
      sb.append("\t");
      sb.append(patienten.get(i).getOrt());
      sb.append("\t");
      sb.append(patienten.get(i).getPflegestufe());
      sb.append("\t");
      if (patienten.get(i).getHelfer() != null) {
        sb.append(patienten.get(i).getHelfer().getNummer());
        sb.append("\t");
        sb.append(patienten.get(i).getHelfer().getName());
        sb.append("\t");
        sb.append(patienten.get(i).getHelfer().getStrasse());
        sb.append("\t");
        sb.append(patienten.get(i).getHelfer().getOrt());
        sb.append("\t");
        sb.append(patienten.get(i).getHelfer().getAusgebildet());
      }
      System.out.println(sb);
    }
  }

  public static void ausgabeHelfer() {
    ArrayList<Helfer> helfer = verwaltung.getHelfer();
    ArrayList<Patient> patienten = verwaltung.getPatienten();
    System.out.println("Nicht zugeordnete Helfer:");
    SchleifeHelfer:
    for (int i = 0; i < helfer.size(); i++) {
      for (int j = 0; j < patienten.size(); j++) {
        if (patienten.get(j).getHelfer() != null) {
          if (patienten.get(j).getHelfer().equals(helfer.get(i))) {
            continue SchleifeHelfer;
          }
        }
      }
      StringBuilder sb = new StringBuilder();
      sb.append(helfer.get(i).getNummer());
      sb.append("\t");
      sb.append(helfer.get(i).getName());
      sb.append("\t");
      sb.append(helfer.get(i).getStrasse());
      sb.append("\t");
      sb.append(helfer.get(i).getOrt());
      sb.append("\t");
      sb.append(helfer.get(i).getAusgebildet());
      System.out.println(sb);
    }
  }
}
