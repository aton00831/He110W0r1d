#include <iostream>

#include <vector>
#include <string>

#include <sstream>

using namespace std;

class Solution {
public:
    int hammingWeight(uint32_t n) {
        int ans = 0;
        while(n) {
            ++ans;
            n &= (n-1);
        }
        return ans;
    }
};

int main() {
    cout << "Hello World" << endl;
    Solution a;
    int ans = a.hammingWeight(11);

    cout << ans << endl;
    return 0;

}
