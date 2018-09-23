/**
 * Created by Alice on 25.06.2016.
 */
public class Zutat {
  private String name;
  private int menge;
  private String groesse;

  public Zutat(String name, String concat) {
    this.name = name;
    updateMengeConcat(concat);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMenge() {
    return menge;
  }

  public void setMenge(int menge) {
    this.menge = menge;
  }

  public String getGroesse() {
    return groesse;
  }

  public void setGroesse(String groesse) {
    this.groesse = groesse;
  }

  public String getMengeConcat() {
    return this.menge + this.groesse;
  }

  public void setMengeConcat(String mengeConcat) {
    updateMengeConcat(mengeConcat);
  }

  public boolean isNumber(String value) {
    return value.matches("[0-9]+]");
  }

  public void updateMengeConcat(String mengeConcat) {
    String zahl;
    String menge;
    for (int i = 0; i < mengeConcat.length(); i++) {
      if (!isNumber(mengeConcat.substring(i))) {
        menge = mengeConcat.substring(i);
        zahl = mengeConcat.substring(0,i);
        this.groesse = zahl;
//        System.out.println(zahl);
        if(isNumber(zahl)) {
          this.menge = Integer.parseInt(zahl);
          break;
        }
      }
    }
  }
}
