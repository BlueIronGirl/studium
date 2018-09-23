using System;
namespace OOAnachCS
{
    public class Bankverbindung
    {
        private int blz;
        private int kontonummer;
        public Bankverbindung(int blz, int kontonummer)
        {
            this.blz = blz;
            this.kontonummer = kontonummer;
        }
        public int Blz
        {
            get { return blz; }
            set { this.blz = value; }
        }
        public int Kontonummer
        {
            get { return kontonummer; }
            set { this.kontonummer = value; }
        }
    }
}