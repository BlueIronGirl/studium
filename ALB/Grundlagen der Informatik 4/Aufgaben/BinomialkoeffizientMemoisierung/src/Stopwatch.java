/**
 * Created by Alice on 22.07.2016.
 */
public class Stopwatch {
  private long startZeit, stoppZeit;
  private boolean istAktiv;

  public void starten(){
    if (!istAktiv){
      istAktiv = true;
      startZeit = System.currentTimeMillis();
    }
  }

  public void stoppen(){
    if (istAktiv){
      istAktiv = false;
      stoppZeit = System.currentTimeMillis();
    }
  }

  public long getLaufzeit(){
    return (istAktiv ? System.currentTimeMillis() : stoppZeit) - startZeit;
  }

  public boolean istAktiv(){
    return istAktiv;
  }
}
