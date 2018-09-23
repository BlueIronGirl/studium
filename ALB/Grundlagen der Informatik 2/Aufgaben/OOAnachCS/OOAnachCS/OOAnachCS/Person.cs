using System;
namespace OOAnachCS
{
    public class Person
    {
        private string name;
        private string vorname;

        public Person(string name, string vorname)
        {
            this.name = name;
            this.vorname = vorname;
        }
        public string Name
        {
            get { return name; }
            set { name = value; }
        }
        public string Vorname
        {
            get { return vorname; }
            set { vorname = value; }
        }
    }
}