#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <algorithm>
#include <ctime>
using namespace std;

class node {
public:
	node(const char* cop, int value) {
		op = cop;
		result = value;
	}
	node(string &cop, int value, const char _mop) {
		mop = _mop;
		op = cop;
		result = value;

	}
	void calc(node &that) {
		switch (mop) {
		case '+':
			result = that.result + result;
			break;
		case '-':
			result = that.result - result;
			break;
		case '*':
			result = that.result * result;
			break;
		default:
			cout << "error";
		}
		op = that.op + mop + op;
	}
	char mop;
	string op;
	int result;//0+,1*,2-

	vector<node*> static calc(vector<node*> &vn, vector<node*> &that,
			int value, string opstack) {
		for (unsigned i = 0; i < that.size(); i++) {
			node *n = new node(opstack, value, '+');
			n->calc(*that[i]);
			vn.push_back(n);
		}
		for (unsigned i = 0; i < that.size(); i++) {
			node *n = new node(opstack, value, '-');
			n->calc(*that[i]);
			vn.push_back(n);
		}
		for (unsigned i = 0; i < that.size(); i++) {
			node *n = new node(opstack, value, '*');
			n->calc(*that[i]);
			vn.push_back(n);
		}
		return vn;
	}
	vector<node*> static calc(vector<node*> &vn, vector<node*> &that, vector<
			node*> &thiz) {
		for (unsigned j = 0; j < thiz.size(); j++) {
			calc(vn, that, thiz[j]->result, thiz[j]->op);
		}
		return vn;
	}

};

void clean(vector<node*> *v){
	for(unsigned i=0;i<v->size();i++){
		delete v->at(i);
	}
}

int str2int(string &str) {
	//cout << str << "*"<< endl;
	stringstream ss(str);
	int num;
	if ((ss >> num).fail()) {
		cout << "str2int error";
		return 0;
	}
	return num;
}

void pstack(vector<vector<node*>* > &st) {
	for (unsigned i = 0; i < st.size(); i++) {
		cout << "[";
		for (unsigned j = 0; j < st[i]->size(); j++) {
			cout << "(" << st[i]->at(j)->op << "," << st[i]->at(j)->result << ")"
					<< ",";
		}
		cout << "]";
	}
	cout << endl;
}

void parse(string &str) {
	int pos = str.find(" = ");

	string res = str.substr(0, pos);
	string exp = str.substr(pos + 3, str.length());

	vector<vector<node*>*> result_stack;
	int op_stack = 0;
	int pushed = 0;

	size_t start = 0, found = 0;
	found = exp.find_first_of(" ()");
	while (found != string::npos) {
		string tmp;
		if (found == start) {
			tmp = exp[found];
			found++;
			//---
			if (tmp.compare(" ") == 0) {
				op_stack++;
			} else if (tmp.compare("(") == 0) {
				pushed = 1;
			} else if (tmp.compare(")") == 0 && result_stack.size() >= 2) {
				vector<node*> *prev = result_stack.at(result_stack.size() - 1);
				vector<node*> *prev2 = result_stack.at(result_stack.size() - 2);
				vector<node*> *res = new vector<node*> ();
				node::calc(*res, *prev2, *prev);
				clean(prev);
				clean(prev2);
				result_stack.pop_back();
				result_stack.pop_back();
				delete prev;
				delete prev2;
				result_stack.push_back(res);
				pstack(result_stack);
			}
		} else {
			tmp = exp.substr(start, found - start);
			int tmpcast = str2int(tmp);
			//---
			if (result_stack.size() == 0 || pushed > 0) {//build
				vector<node*> *vn = new vector<node*> ();
				node *n = new node("", tmpcast);
				vn->push_back(n);
				result_stack.push_back(vn);
				pstack(result_stack);
				if (pushed > 0) {
					pushed--;
				}
			} else if (result_stack.size() != 0) {
				vector<node*> *prev = result_stack.back();
				vector<node*> *res = new vector<node*> ();
				node::calc(*res, *prev, tmpcast, "");
				clean(prev);
				result_stack.pop_back();
				delete prev;
				result_stack.push_back(res);
				pstack(result_stack);
			}

		}

		//cout << tmp << "!" << endl;


		start = found;
		found = exp.find_first_of(" ()", start);
	}
	if (start != exp.size()) {
		string tmp = exp.substr(start, exp.size() - start);
		int tmpcast = str2int(tmp);
		if (result_stack.size() == 0) {//build
			vector<node*> *vn = new vector<node*> ();
			node *n = new node("", tmpcast);
			vn->push_back(n);
			result_stack.push_back(vn);
			pstack(result_stack);
		} else if (result_stack.size() != 0) {
			vector<node*> *prev = result_stack.back();
			vector<node*> *res = new vector<node*> ();
			node::calc(*res, *prev, tmpcast, "");
			clean(prev);
			result_stack.pop_back();
			delete prev;
			result_stack.push_back(res);
			pstack(result_stack);
		}
		//cout << tmp << endl;
	}

	vector<node*> *final = result_stack.back();
	int castres = str2int(res);
	int got = false;
	string opstack;
	for (unsigned i = 0; i < final->size(); i++) {
		if (castres == final->at(i)->result) {
			opstack = final->at(i)->op;
			got = true;
		}
	}
	if (!got) {
		cout << "Impossible" << endl;
	} else {
		string s;
		s += res;
		s += "=";
		//cout << res << "=";
		int i = 0;
		for (unsigned j = 0; j < exp.length(); j++) {
			if (exp[j] == ' ') {
			//	cout << opstack[i];
				s+= opstack[i];
				i++;
			} else {
				s+= exp[j];
				//cout << exp[j];
			}
		}
		cout << s << endl;
	}

}

/*
 void fn(vector<string*> &v){
 string s("a");
 //string *s = new string("a");
 v.push_back(&s);
 }*/



void fixinput(string &fixed,string &s){
	char last;
	for(unsigned i=0;i<s.size();i++){
		char cur = s[i];
		if(cur == ' ' && last == ' '){//kill double space
			continue;
		}
		if(last == '(' && cur == ' '){//kill   (   (   (
			continue;
		}
		if(cur == ')' && last == ' '){//  a_)
			fixed.erase(fixed.size()-1,1);
		}
		if( (cur == '=' && last != ' ') || (last == '=' && cur != ' ')){
			fixed += ' ';
		}
		if( last == ')' && cur != ' ' && cur != ')'){
			fixed += ' ';
		}


		fixed += s[i];
		last =s[i];
	}
}
int main() {
	clock_t seconds;
	seconds = clock();
	string line = "999 = (((((((((((((((((18 5))))))))) ((450) (2)) (((((((((((((3)))))) 3)))))))))))))))";
	string line2 = "9 = (2(2 3)) 2";
	parse(line);
	//vector<string*> *v = new vector<string*>();
	//string *s = new string("aaa");
	//v->push_back(s);
	//v.pop_back();
	//fn(v);
	//delete v;
	//string *s1 = v.back();
	//delete s;
	//cout << *s;
	//string fix;
	//fixinput(fix,line2);
	//cout << fix;
	//cout << (clock() - seconds) << endl;
	return 0;
}
int main1() {

	int i = 1;
	string line;
	while (true) {
		string fix;
		getline(cin, line);
		if (line.compare("0") == 0) {
			break;
		}

		fixinput(fix,line);
		cout <<fix <<endl;
		cout << "Equation #" << i << ":" << endl;
		//cout << line << endl;
		parse(fix);
		cout << endl;
		i++;

	}

	return 0;
}
