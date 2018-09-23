using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OOAnachCS
{
    class Program
    {
        static void Main(string[] args)
        {
            Mitglied einMitglied = new Mitglied("Bedow", "Alice");
            einMitglied.Verbindung = new Bankverbindung(12345,98765);
            Console.WriteLine(Mitglied.anzeigeAnzahlMitglieder());
            Console.Write(einMitglied.Verbindung.Blz);
            Console.WriteLine(einMitglied.Verbindung.Kontonummer);
            Console.Read(); //Fenster offen halten
        }
    }
}
