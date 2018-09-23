//Bankverbindung.h
class Bankverbindung
{
	private:
		int blz;
		int kontonummer;
	public:
		Bankverbindung();
		Bankverbindung(int kontonr, int b);
		int getBlz() const {return blz;}
		int getKontonummer() const {return kontonummer;}
		void setBlz(int b) {blz = b;}
		void setKontonummer(int k){kontonummer=k;}		
};
