package meinkraftsport;

/* Programmname: Objektspeicherung
 * Datenhaltungs-Klasse: ObjektDatei
 * Aufgabe: Eine Objekt serialisieren. 
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjektDatei
{
    private String einDateiname;
    
    /**
     * Konstruktor
     * @param einDateiname 
     */
    public ObjektDatei(String einDateiname)
    {
       this.einDateiname = einDateiname;
    }
    
    /**
     * Objekt in Datei speichern
     * @param einObjekt 
     */
    public void speichereObjekt(Object einObjekt)
    {
        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(einDateiname)))
        {
            writer.writeObject(einObjekt);
        }
        catch (Exception ex)
        {
            System.out.println("Fehler in speichereObjekt: " + ex);
        }
    }
    
    /**
     * Objekt aus Datei einlesen
     * @return 
     */
    public Object leseObjekt()
    {
        Object einObject = null;
      
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(einDateiname)))
        {
            einObject = reader.readObject();
        }
        catch (Exception ex)
        {
            System.out.println("Fehler in leseObjekt: " + ex); 
        }
        return einObject;
    }
}

