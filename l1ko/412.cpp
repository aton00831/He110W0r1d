#include <iostream>

#include <vector>
#include <string>

#include <sstream>

using namespace std;

class Solution {
public:
    vector<string> fizzBuzz(int n) {
        vector<string> ans;
        stringstream ss;

        for (int i=1; i<=n; i++) {
            if (i%15 == 0){
                ans.push_back("FizzBuzz");
            } else if (i%3 == 0) {
                ans.push_back("Fizz");
            } else if (i%5 == 0) {
                ans.push_back("Buzz");
            } else {
                ss << i;
                ans.push_back(ss.str());
                ss.str("");

            }
        }
        return ans;

    }
};

int main() {
    cout << "Hello World" << endl;
    Solution a;
    vector<string> ans = a.fizzBuzz(1);

    for (string s: ans){
        cout << s << endl;
    }
    return 0;

}
