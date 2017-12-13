#include <iostream>

#include <vector>
#include <string>

#include <sstream>

using namespace std;

class Solution {
public:
    int hammingDistance(int x, int y) {
        int n = x ^ y;
        int ans = 0;

        while(n) {
            ans += (n & 1);
            n >>= 1;
        }

        return ans;
    }
};

int main() {
    cout << "Hello World" << endl;
    Solution a;
    int ans = a.hammingDistance(5,1);

    cout << ans << endl;
    return 0;

}
