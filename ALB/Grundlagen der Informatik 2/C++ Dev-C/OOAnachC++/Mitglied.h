//Mitglied.h
#include "Person.h"
#include "Bankverbindung.h"
#include <string>
using namespace std;
class Mitglied : public Person
{
	private:
		int nummer;
		Bankverbindung *bank;
		static int anzahlMitglieder; //Klassenattribut
	public:
		Mitglied();
		static int anzeigeAnzahlMitglieder();	//Klassenoperation
		int getNummer()const {return nummer;}
		Bankverbindung* getBankverbindung() const{return bank;}
		void setBankverbindung(Bankverbindung *b){bank = b;}
		~Mitglied();
};
