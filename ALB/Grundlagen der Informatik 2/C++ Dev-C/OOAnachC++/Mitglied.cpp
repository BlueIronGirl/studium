//Mitglied.cpp
#include "Mitglied.h"
int Mitglied::anzahlMitglieder = 0;
Mitglied::Mitglied()
{
	anzahlMitglieder++;
}
int Mitglied::anzeigeAnzahlMitglieder()
{
	return anzahlMitglieder;
}
Mitglied::~Mitglied()
{
	delete bank;
}
