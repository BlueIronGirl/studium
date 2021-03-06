import javax.swing.table.AbstractTableModel;

/**
 * Created by Alice on 25.06.2016.
 */
public class RezeptModell extends AbstractTableModel {
  private Container container = Container.getContainer();
  private final String[] spalten = {"Nummer", "Name", "Zubereitung", "Zubereitungszeit", "Schwierigkeit", "Zutaten"};
  @Override
  public int getRowCount() {
    return container.getAnzahl();
  }

  @Override
  public int getColumnCount() {
    return 6;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Rezept rezept = (Rezept) container.get(rowIndex);
    switch(columnIndex) {
      case 0 : return rezept.getNr();
      case 1 : return rezept.getName();
      case 2 : return rezept.getZubereitung();
      case 3 : return rezept.getZubereitungszeit();
      case 4 : return rezept.getSchwierkeitsgradET().toUpperCase();
      case 5 : return rezept.getZutaten();
    }
    return container.get(rowIndex);
  }

  @Override
  public String getColumnName(int spalte) {
    return spalten[spalte];
  }

  public Object getElement(int zeile) {
    return container.get(zeile);
  }
}
