using System;
namespace OOAnachCS
{
    public class Mitglied : Person
    {
        private int nummer;
        private static int anzahlMitglieder = 0;
        private Bankverbindung verbindung;
        
        public Mitglied(string name, string vorname) : base(name,vorname)
        {
            anzahlMitglieder++;
            nummer = anzahlMitglieder;
        }
        public int Nummer
        {
            get { return nummer; }
            set { this.nummer = value; }
        }
        public static int anzeigeAnzahlMitglieder()
        {
            return anzahlMitglieder;
        }
        public Bankverbindung Verbindung
        {
            get { return verbindung; }
            set { this.verbindung = value; }
        }

    }
}