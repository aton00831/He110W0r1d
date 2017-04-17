#include <iostream>
#include <string>

using namespace std;

#define AZ 52
int nu(char c) {
	if (c>=(int)'a') {
		return c - 'a';
	} else {
		return c - (int)'A' + 26;
	}
}

int main ()
{
	string in;
	cin >> in;

	int alb[AZ] = { 0 };

	//for(char &c :in) {
	for(int i=0; i<in.length(); i++) {
		alb[nu(in[i])] += 1;
	}

	int win = 9487945;

	if ( alb[nu('B')] < win ) {
		win = alb[nu('B')];
	}
	if ( (alb[nu('u')]/2) < win ) {
		win = alb[nu('u')]/2;
	}
	if ( (alb[nu('a')]/2) < win ) {
		win = alb[nu('a')]/2;
	}
	if ( alb[nu('l')] < win ) {
		win = alb[nu('l')];
	}
	if ( alb[nu('b')] < win ) {
		win = alb[nu('b')];
	}
	if ( alb[nu('s')] < win ) {
		win = alb[nu('s')];
	}
	if ( alb[nu('r')] < win ) {
		win = alb[nu('r')];
	}
	cout << win << endl;
	
}