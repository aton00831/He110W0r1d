#include <iostream>
#include <algorithm>

using namespace std;

void orderr(long long &sum, long long a) {
	while (a>1) {
		sum *= a;
		a -= 1;
		while (sum>=1000000007LL ) {
			sum -= 1000000007LL ;
		}
	}
}
int main () 
{
	int n,m,s,t,seed;
	cin >> n >> m;
	long score[m];
	for (int i=0; i<m; i++){
		score[i] = 0;
	}

	while (n--) {
		cin >> s;
		seed = (rand()%100+1);
		while (s--) {
			cin >> t;
			score[t-1] += (1+seed);
		}
	}
	sort(score, score+m);

	long long sum =1;
	int count=0, last=-1;
	
	int count0=0;
	for (int i=0; i<m; i++) {
		if (score[i] == 0) {
			count0 += 1;
			continue;
		}
		if (last!=score[i]) {
			if (count>1) {
				orderr(sum, count);
			}
			count = 1;
			last = score[i];
		} else {
			count += 1;
		}
	}
	if (count>1) {
		orderr(sum, count);
	}
	if (count0>1) {
		cout << count0 << endl;
		cout << sum << endl;
		orderr(sum, count0);
	}
	cout << sum << endl;
}