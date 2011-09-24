#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm>
using namespace std;


template <class T>
class node{
public:
	vector<node> children;
	T data;

	node(string &str){

		for(unsigned int i=0;i<str.size();i++){
			cout << str[i];
		}


	}
};

int str2int (const string &str) {
  stringstream ss(str);
  int num;
  if((ss >> num).fail()){
      cout << "str2int error";
      return 0;
  }
  return num;
}

void parse(string &str){

	int pos = str.find(" = ");
	string res = str.substr(0,pos);
	//cout << res <<endl;
	string exp = str.substr(pos+3,str.length());
	node<string> n(exp);
	//cout << exp;
}

int main ()
{
	string line = "18 = 7 (5 3) 2";
	parse(line);

	/*string line;
	while(true){
		getline(cin,line);
		//cout << line << endl;
		if(line.compare("0") == 0){
			break;
		}
	}*/

  return 0;
}
