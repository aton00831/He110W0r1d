#include <iostream>

#include <vector>
#include <string>

#include <sstream>

using namespace std;

class Solution {
public:
    bool isSDV(int i) {
        stringstream ss;
        ss << i;
        for (char c : ss.str()) {
            int d = (int)(c-'0');
            if (d==0 || i%d != 0){
                return false;
            }
        }
        return true;
    }

    vector<int> selfDividingNumbers(int left, int right) {
        vector<int> sol;
        for (int i=left; i<=right; i++) {
            if (isSDV(i)){
                sol.push_back(i);
            }
        }
        return sol;
    }
};

int main() {
    cout << "Hello World" << endl;
    Solution a;
    vector<int> ans = a.selfDividingNumbers(1,22);

    for (int s: ans){
        cout << s << endl;
    }
    return 0;

}
