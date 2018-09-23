	
package inout;
   
import java.util.Scanner;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.DateFormat;

/** Diese Klasse stellt Methoden zur Verfügung, <br/>
 * um Texte und einfache Typen von der Konsole einzulesen.<br/>
 * Die Ausnahmebehandlung ist Aufgabe des Aufrufers.<hr/>
 * @author Helmut Balzert
 * @version 2.1 / 1.11.2012
 */
public class Console
{
 private static Scanner sc;
 
 //Unterdrückung des default-Konstruktor,
 //um eine Objekterzeugung zu verhindern
 private Console()
 {
  //Dieser Konstruktor wird nie aufgerufen
 }
 
 /**Liest eine Zeile von der Konsole
  * @return Eingelesene Zeile vom Typ String.
  * @exception NoSuchElementException: 
  *    Es wurde keine Eingabezeile gefunden.
  * @exception IllegalStateException: 
  *    Die verwendete Methode ist nicht geöffnet.
  */
 public static String readString() 
  throws NoSuchElementException, IllegalStateException
 {
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
 }
 
 /**Liest eine Zeile von der Konsole
  * @return Eingelesene Zeile vom Typ char[].
  * @exception NoSuchElementException: 
  *    Es wurde keine Eingabezeile gefunden.
  * @exception IllegalStateException: 
  *    Die verwendete Methode ist nicht geöffnet.
  */
 public static char[] readCharArray() 
  throws NoSuchElementException, IllegalStateException
 {
    sc = new Scanner(System.in);
    String text = sc.nextLine();
    return text.toCharArray();
 }
        
 /**Liest einen booleschen Wert von der Konsole
  * @return Boolescher Wert true oder false.
  * @exception NoSuchElementException: 
  *    Es wurde keine Eingabezeile gefunden.
  * @exception IllegalStateException: 
  *    Die verwendete Methode ist nicht geöffnet.
  * @exception InputMismatchException: 
  *    Die Eingabe entspricht nicht dem Typ.
  */
 public static boolean readBoolean() throws 
   InputMismatchException, NoSuchElementException, 
   IllegalStateException
 {
    sc = new Scanner(System.in);
    return sc.nextBoolean();
 }
 
 /**Liest eine ganze Zahl vom Typ int von der Konsole
  * @return Ganze Zahl vom Typ int.
  * @exception InputMismatchException: 
  *    Die Eingabe entspricht nicht dem Typ.
  * @exception NoSuchElementException: 
  *    Es wurde keine Eingabezeile gefunden.
  * @exception IllegalStateException: 
  *    Die verwendete Methode ist nicht geöffnet.
  */
 public static int readInt() throws 
   InputMismatchException, NoSuchElementException, 
   IllegalStateException
 {
    return new Scanner(System.in).nextInt();
 }
 
 /**Liest eine ganze Zahl vom Typ long von der Konsole
  * @return Ganze Zahl vom Typ long
  * @exception InputMismatchException: 
  *    Die Eingabe entspricht nicht dem Typ.
  * @exception NoSuchElementException: 
  *    Es wurde keine Eingabezeile gefunden.
  * @exception IllegalStateException: 
  *    Die verwendete Methode ist nicht geöffnet.
  */
 public static long readLong() throws 
   InputMismatchException, NoSuchElementException, 
   IllegalStateException
 {
    return new Scanner(System.in).nextLong();
 }

 /**Liest eine Gleitpunktzahl vom Typ float von der Konsole
  * Englische Notation: Trennung der 
  * Nachkommastellen durch Punkt
  * @return Gleitpunktzahl vom Typ float
  * @exception InputMismatchException: 
  *    Die Eingabe entspricht nicht dem Typ.
  * @exception NoSuchElementException: 
  *    Es wurde keine Eingabezeile gefunden.
  * @exception IllegalStateException: 
  *    Die verwendete Methode ist nicht geöffnet.
  */
  public static float readFloatPoint() throws 
   InputMismatchException, NoSuchElementException, 
   IllegalStateException
 {
    Locale.setDefault(Locale.ENGLISH);
    return new Scanner(System.in).nextFloat();
 }
 
 /**Liest eine Gleitpunktzahl vom Typ float von der Konsole
  * Deutsche Notation: Trennung der 
  * Nachkommastellen durch Komma
  * @return Gleitpunktzahl vom Typ float
  * @exception InputMismatchException: 
  *    Die Eingabe entspricht nicht dem Typ.
  * @exception NoSuchElementException: 
  *    Es wurde keine Eingabezeile gefunden.
  * @exception IllegalStateException: 
  *    Die verwendete Methode ist nicht geöffnet.
  */
 public static float readFloatComma() throws 
   InputMismatchException, NoSuchElementException, 
   IllegalStateException
 {
    Locale.setDefault(Locale.ENGLISH);
    return new Scanner(System.in).nextFloat();
 }

 /**Liest eine Gleitpunktzahl vom Typ double von der Konsole
  * Englische Notation: Trennung der 
  * Nachkommastellen durch Punkt
  * @return Gleitpunktzahl vom Typ double
  * @exception InputMismatchException: 
  *    Die Eingabe entspricht nicht dem Typ.
  * @exception NoSuchElementException: 
  *    Es wurde keine Eingabezeile gefunden.
  * @exception IllegalStateException: 
  *    Die verwendete Methode ist nicht geöffnet.
  */
 public static double readDoublePoint() throws 
   InputMismatchException, NoSuchElementException, 
   IllegalStateException
 {
    Locale.setDefault(Locale.ENGLISH);
    return new Scanner(System.in).nextDouble();
 }
 
 /**Liest eine Gleitpunktzahl vom Typ double von der Konsole
  * Deutsche Notation: Trennung der 
  * Nachkommastellen durch Komma
  * @return Gleitpunktzahl vom Typ double
  * @exception InputMismatchException: 
  *    Die Eingabe entspricht nicht dem Typ.
  * @exception NoSuchElementException: 
  *    Es wurde keine Eingabezeile gefunden.
  * @exception IllegalStateException: 
  *    Die verwendete Methode ist nicht geöffnet.
  */
 public static double readDoubleComma() throws 
   InputMismatchException, NoSuchElementException, 
   IllegalStateException
 {
    Locale.setDefault(Locale.ENGLISH);
    return new Scanner(System.in).nextDouble();
 }  

/**Liest ein Zeichen vom Typ char von der Konsole
 * @return Erstes eingegebene Zeichen vom Typ char.
 * @exception NoSuchElementException:
 *    Es wurde keine Eingabezeile gefunden.
 */
 public static char readChar() throws 
   NoSuchElementException,IllegalStateException
 {
    String s = new Scanner(System.in).next();
    return s.charAt(0);
 }

}
