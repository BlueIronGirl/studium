#include <iostream>
#include <string>
#include "Mitglied.h"

using namespace std;

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main() {
	Mitglied *mitglied;
	mitglied = new Mitglied();
	mitglied->setName("Bedow");
	mitglied->setVorname("Alice");
//	mitglied->setBankverbindung(new Bankverbindung(123,456));
//	cout<<mitglied->getBankverbindung()->getBlz()<<"\n";
//	cout<<mitglied->getBankverbindung()->getKontonummer()<<"\n";
	cout<<Mitglied::anzeigeAnzahlMitglieder()<<endl;
	delete mitglied;	
	system("pause");
	return 0;
}
