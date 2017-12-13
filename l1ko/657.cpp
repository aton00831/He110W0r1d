#include <iostream>

#include <vector>
#include <string>

#include <sstream>

using namespace std;

class Solution {
public:
    bool judgeCircle(string moves) {
        int h=0, v=0;
        for (char c: moves) {
            switch(c){
            case 'D':
                ++v;
                break;
            case 'U':
                --v;
                break;
            case 'L':
                ++h;
                break;
            case 'R':
                --h;
                break;
            default:
                break;
            }
        }
        return !(h || v);
    }
};

int main() {
    cout << "Hello World" << endl;
    Solution a;
    bool ans = a.judgeCircle("LL");

    cout << ans << endl;
    return 0;

}
