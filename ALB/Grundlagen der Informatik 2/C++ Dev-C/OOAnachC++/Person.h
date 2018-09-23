//Person.h
#include <string>
using namespace std;
class Person
{
	private:
		string name;
		string vorname;
	public:
		string getName() const {return name;}
		string getVorname() const {return vorname;}
		void setName(string n){name = n;}
		void setVorname(string v){vorname = v;}	
};
