/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personenverwaltung;

/**
 *
 * @author Alice Bedow
 * @date 8.12.15
 */
public class Patient extends Person<Patient> {
  //private enum pflegestufe{I,II,III};
  //private pflege = pflegestufe.I;
  private int pflegestufe;
  private Helfer helfer;
  public enum Pflegestufe{
    I(1),II(2),III(3);
    private final int wert;
    Pflegestufe(int wert)
    {
      this.wert = wert;
    }
    public int getWert()
    {
      return wert;
    }
  }
  public Patient(int nummer, String name, String strasse,String ort, String pflegestufe)
  {
    super(nummer,name,strasse,ort);
    for(Pflegestufe p : Pflegestufe.values())
      if(p.toString().equals(pflegestufe)){
        this.pflegestufe = p.getWert();
      }  
  }
  public int getPflegestufe()
  {
    return this.pflegestufe;
  }
  public Helfer getHelfer()
  {
    return this.helfer;
  }
  public void setPflegestufe(int pflegestufe)
  {
    this.pflegestufe = pflegestufe;
  }
  public void setHelfer(Helfer helfer)
  {
    this.helfer = helfer;
  }
  
}
