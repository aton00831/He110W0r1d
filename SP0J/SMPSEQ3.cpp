#include <iostream>
#include <vector>

using namespace std;

int main()
{
	cout << " test " << endl;
	int n, m, t;
	cin >> n;
	vector<int> a, b;
	for (int i = 0; i < n; i++) {
		cin >> t;
		a.push_back(t);
	}
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> t;
		b.push_back(t);
	}

	int w;
	for (int i = 0; i < n; i++) {
		w = 0;
		for (int j = 0; j < n && w == 0; j++) {
			if (a[i] == b[j]) {
				w = 1;
			}
		}
		if (w == 0) {
			cout << a[i] << " ";
		}
	}
	cout << endl;
	return 0;
}