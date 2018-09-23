import javax.swing.table.AbstractTableModel;

/**
 * Created by Alice on 26.06.2016.
 */
public class ZutatenModell extends AbstractTableModel {
  private Rezept rezept = null;

  public ZutatenModell(Rezept rezept) {
    this.rezept = rezept;
  }

  @Override
  public int getRowCount() {
    return rezept.getAnzahl();
  }

  @Override
  public int getColumnCount() {
    return 2;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Zutat temp = (Zutat) rezept.get(rowIndex);
    switch(columnIndex) {
      case 0 : return temp.getMengeConcat();
      case 1 : return temp.getName();
    }
    return rezept.get(rowIndex);
  }

  public Object getElement(int zeile) {
    return rezept.get(zeile);
  }
}
