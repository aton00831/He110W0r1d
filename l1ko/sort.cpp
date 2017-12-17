#include <iostream>     // std::cout
#include <algorithm>    // std::random_shuffle
#include <vector>       // std::vector
#include <ctime>        // std::time
#include <cstdlib>      // std::rand, std::srand
#include <string>
#include <chrono>       // track time with varying degrees of precision
#include <iomanip>      // std::setw
#include <list>
#include <cmath>
using namespace std;

#define ARR_SIZE 80000
#define DEBUG_PRINT 0
#define SWAP(x, y, t) ((t) = (x), (x) = (y), (y) = (t))
/* Great Resource
https://www.toptal.com/developers/sorting-algorithms/
*/

/***
Initial a vector with size : 80000
O(n^2)
Using : [         Bubble Sort] , cost: 48.8749s
Using : [      Insertion Sort] , cost: 25.7667s
Using : [      Selection Sort] , cost: 15.9241s
Using : [          Shell Sort] , cost: 6.79277s

O(n long n)
Using : [          Merge Sort] , cost: 0.09004s
Using : [           Heap Sort] , cost: 0.036019s
Using : [          Quick Sort] , cost: 0.018989s
Using : [             cppsort] , cost: 0.021999s

O(n)
Using : [       Counting Sort] , cost: 0.003s
Using : [          Radix Sort] , cost: 0.014016s
Using : [         Bucket Sort] , cost: 3.58394s

Process returned 0 (0x0)   execution time : 101.177 s
Press any key to continue.
*/

/* ------------------- Linear Time ------------------- */
/* note:
1. Counting sort --
   simple buckets, simple processing, memory overhead
2. Radix sort --
   simple buckets, sophisticated processing, speed overhead (and still need additional static memory)
3. Bucket sort --
   sophisticated buckets, simple processing, requires dynamic memory, good in average
*/
/*** Bucket Sort ***
- regular sort with special bucket
Time Complexity:
    Worse Case: O(n^2) all number put to same index of bucket with reverse order
    Best Case: O(n + k) k is the range of input number
    Average Case: \theta(n+k)
Space Complexity: O(n) and need dynamic memory

Need to design proper bucket hash function (?)
*/
void bucketSort(vector<int> &v) {

    int largest = 0;
    for (const int &i: v) {
        if (i > largest) {
            largest = i;
        }
    }

    // vector<list<int>> bucket(log(largest+2));
    vector<list<int>> bucket(10+1);
    for (const int &i: v) {
        // int n = log(i+2)-1;
        int n = i*10/largest;
        if (bucket[n].size() == 0) {
            bucket[n].push_back(i);
        } else {
            list<int>::iterator itr = bucket[n].begin();
            while(itr != bucket[n].end()) {
                if (i < *itr) {
                    bucket[n].insert(itr, i);
                    break;
                } else {
                    itr++;
                }
            }
            if (itr == bucket[n].end()) {
                bucket[n].push_back(i);
            }
        }
    }

    int i = 0;
    for (int n=0; n<bucket.size(); n++) {
        for (auto itr=bucket[n].begin(); itr != bucket[n].end(); itr++) {
            v[i++] = *itr;
        }
    }

}

/*** Radix Sort ***
= Digit by Digit Sort
Time Complexity:
    Worse Case: O(nk)
    Best Case: O(nk)
    Average Case: \theta(nk) k is the number of max digits
Space Complexity: O(n + k)
*/
void radixSort(vector<int> &v) {
    int n = 1;
    bool sorted = false;

    int largest = 0;
    for (const int &i: v) {
        if (i > largest) {
            largest = i;
        }
    }

    while(largest) {
        largest /= 10;
        vector<int> bucket(10, 0); /* 0,1,2,3,4...,8,9 */
        vector<int> tmp_v(v.size(), 0);

        for (int i=0; i<v.size(); i++) {
            bucket[ v[i] / n % 10 ] += 1;
        }
        for (int i=1; i<bucket.size(); i++) {
            bucket[i] += bucket[i-1];
        }
        /* Must reversed Here !! */
        for (int i=v.size()-1; i>=0; --i) {
            tmp_v[ --bucket[v[i] / n % 10]] = v[i];
        }
        for (int i=0; i<v.size(); i++) {
            v[i] = tmp_v[i];
        }
        n *= 10;
    }
}

/*** Counting Sort ***
Time Complexity: O(n+k) actually bounding on input range `k`
Space Complexity: O(n+k)
Usage: When Range of vector value is not too large
stable,

To solve the large amount memory waste
- 1. sort digit wise -> radix sort
- 2. make buckets more sophisticated -> bucket sort
*/
void countingSort(vector<int> &v) {
    vector<int> bucket(v.size(), 0);
    for (const int &i: v) {
        ++bucket[i];
    }
    for (int j=0, i=0; j<bucket.size(); j++) {
        while(bucket[j]-->0) {
            v[i++]=j;
        }
    }
}

/* ------------------- O(n lg n) ------------------- */
/*** Shell Sort ***
= variance of insertion sort (allow far move)
Time Complexity: (depend on gap design)
    best: O(n * lg n) .. when array is already sorted
    worst: O(N^2 * lg n) => O(N^2) .. (n^2(1 + 1/2 + 1/4 + ...) = 2*n^2
    average: \theta(N^2) (Some say O(N (log N)^2 )
Space Complexity: O(1)
Usage:
    Fast in reversed case (reduce many move comp with insertion sort)
*/
void shellSort(vector<int> &v) {
    int tmp, j;
    for(int k=v.size()/2; k>0; k/=2) { /* k means gap */
        for (int i=k; i<v.size(); i++) {
            tmp = v[i];
            for (j=i; ((j-k)>=0)&&(tmp < v[j-k]); j--) {
                v[j] = v[j-k];
            }
            v[j] = tmp;
        }
    }
}


/*** heap Sort ***
= take use of heap structure
Time Complexity:
    best: O(n * lg n)
    worst:  O(n * lg n)
    average: \theta(n * lg n)
*/
void maxheapify(vector<int> &v, int s, int i) {
    int largest = i;
    int l = 2*i+1, r = 2*i+2;
    if (l<s && v[l] > v[largest]) {
        largest = l;
    }
    if (r<s && v[r] > v[largest]) {
        largest = r;
    }
    if (largest != i) {
        int tmp = v[largest];
        v[largest] = v[i];
        v[i] = tmp;
        maxheapify(v, s, largest);
    }
}
void heapSort(vector<int> &v) {
    for (int i=v.size()/2; i>=0; i--) {
        maxheapify(v, v.size(), i);
    }

    for (int i=v.size()-1; i>0; i--) {
        int tmp = v[i];
        v[i] = v[0];
        v[0] = tmp;
        maxheapify(v, i, 0);
    }
}

/** Quick Sort ***
= sort at divided (actually do nothing at merge)
Time Complexity:
    best: O(n * lg n)
    worst:  O(n^2)
    average: \theta(n * lg n)
*/
void rquickSort(vector<int> &v, int b, int e) {
    if ((e-b)<=1) {
        return;
    }
    int pivot = v[e-1];
    int i=b, tmp;
    for (int j=b; j<e-1; j++) {
        if (v[j] <= pivot) {
            tmp = v[i];
            v[i] = v[j];
            v[j] = tmp;
            ++i;
        }
    }
    tmp = v[i];
    v[i] = v[e-1];
    v[e-1] = tmp;

    rquickSort(v, b, i);
    rquickSort(v, i+1, e);
}

void quickSort(vector<int> &v) {
    int b=0, e=v.size();
    rquickSort(v,b,e);
}

/*** Merge Sort ***
Time Complexity: O(nlgn)
T(n) = 2 * T(n/2) + \theta(n)\
    best: O(n * lg n)
    worst:  O(n * lg n)
    average: \theta(n * lg n)
Stable: Yes
Usage:
    Sort Linked list
    External Sorting
    Count Inversions: http://www.geeksforgeeks.org/counting-inversions/
*/
void rMergeSort(vector<int> &v, int b, int e) {
    int m = b + ((e-b)/2);
    if ((e-b)<=1) {
        return;
    }
    rMergeSort(v, b, m);
    rMergeSort(v, m, e);
    // merge
    vector<int> l(v.begin()+b, v.begin()+m);
    vector<int> r(v.begin()+m, v.begin()+e);
    auto li = l.begin();
    auto ri = r.begin();

    while (li != l.end() && ri != r.end()) {
        if ( *li <= *ri ) {
            v[b++] = *li;
            li++;
        } else {
            v[b++] = *ri;
            ri++;
        }
    }
    while (li != l.end()) {
        v[b++] = *li;
        li++;
    }
    while (ri != r.end()) {
        v[b++] = *ri;
        ri++;
    }
    return;
}

void mergeSort(vector<int> &v) {
    int b=0, e=v.size();// tail exclusive
    rMergeSort(v, b, e);
}

void imergeSort(vector<int> &v) {
    int b=0, e=v.size();// tail exclusive
    rMergeSort(v, b, e);
}



/* Note :
actually The Time Complexity of 3 O(n^2) are
`bubble sort`    >slower then>
`insertion sort` >slower then>
`selection sort`
https://cs.stackexchange.com/questions/13106/why-is-selection-sort-faster-than-bubble-sort
*/

/*** Selection Sort ***
Time Complexity: O(n*n)
Auxiliary Space: O(1)

*/
void selectionSort(vector<int> &v) {
    int m, k;
    for (int i=0; i<v.size(); i++) {
        m = v[i];
        k = i;
        for (int j=i+1; j<v.size(); j++) {
            if (v[j]<m) {
                m = v[j];
                k = j;
            }
        }
        v[k] = v[i];
        v[i] = m;
    }
}

/***Insertion Sort ***
Time Complexity: O(n*n)
Auxiliary Space: O(1)
Boundary Cases:
    takes maximum time to sort if elements are sorted in reverse order.
    takes minimum time (Order of n) when elements are already sorted.
Sorting In Place: Yes
Stable: Yes
Online: Yes
Uses:
    elements is small
    when input array is almost sorted, only few elements are misplaced in complete big array.

Variant:
    Binary Insertion Sort: turn the second for-loop to Binary Search, which worse case from O(i) -> O(log(i)
*/
void insertSort(vector<int> &v) {
    for (int i=1; i<v.size(); i++) {
        int last = v[i];
        for(int j=i-1; j>=0; j--) {
            if (j == 0) {
                v[j] = last;
            }
            if (v[j]>v[j+1]) {
                // Move Back
                v[j+1] = v[j];
            } else {
                // Insert
                v[j+1] = last;
            }
        }
    }
}

/*** Bubble Sort ***
Time Complexity: O(n*n). Worst case occurs when array is reverse sorted.
Auxiliary Space: O(1)
Boundary Cases:
    takes maximum time to sort if elements are sorted in reverse order.
    takes minimum time (Order of n) when elements are already sorted.
Sorting In Place: Yes
Stable: Yes
*/

void bubblesort(vector<int> &v) {
    int tmp;
    bool swaped=false;
    for (int i=0; i<v.size(); i++) {
        for (int j=1; j<v.size()-i; j++) {
            if (v[j-1] > v[j]){
                tmp = v[j-1];
                v[j-1] = v[j];
                v[j] = tmp;
                swaped = true;
            }
        }
        if (!swaped) {
            break;
        } else {
            swaped = false;
        }
    }
}

void cppsort(vector<int> &v) {
    sort(v.begin(), v.end());
}

bool checkSort(vector<int> &v) {
    int last = -1;
    for(int &i: v){
        if( i < last ){
            return false;
        }
        last = i;
    }
    return true;
}

int main()
{
    /* Create random vector */
    cout << "Initial a vector with size : " << ARR_SIZE << endl;
    vector<int> raw(ARR_SIZE);
    for(int i=0; i<ARR_SIZE; i++) {
        raw[i] = i;
    }
    std::srand( unsigned(std::time(0)));
    std::random_shuffle( raw.begin(), raw.end());

    /* Print Created vector */
    if (DEBUG_PRINT) {
        cout << "Origin Vector: "  << endl;
        for(const int &i: raw){
            cout << i << ",";
        }
        cout << endl;
    }

    /* Registered sorted function */
    typedef void sortFunc(vector<int> &);
    struct sFunc {
        sortFunc* psortFunc;
        string name;
        sFunc(sortFunc* f, string s):psortFunc(f),name(s){};
    };

    vector<sFunc> allsortFunc;

    allsortFunc.push_back(sFunc(cppsort, "cppsort"));
    allsortFunc.push_back(sFunc(bubblesort, "Bubble Sort"));
    allsortFunc.push_back(sFunc(insertSort, "Insertion Sort"));
    allsortFunc.push_back(sFunc(selectionSort, "Selection Sort"));
    allsortFunc.push_back(sFunc(mergeSort, "Merge Sort"));
    // allsortFunc.push_back(sFunc(imergeSort, "Iterative(no recursive) Merge Sort"));
    allsortFunc.push_back(sFunc(quickSort, "Quick Sort"));
    allsortFunc.push_back(sFunc(heapSort, "Heap Sort"));
    allsortFunc.push_back(sFunc(shellSort, "Shell Sort"));

    allsortFunc.push_back(sFunc(countingSort, "Counting Sort"));
    allsortFunc.push_back(sFunc(radixSort, "Radix Sort"));
    allsortFunc.push_back(sFunc(bucketSort, "Bucket Sort"));

    /* Run Sorted Function */
    for(auto &f: allsortFunc){
        vector<int> unsortVector(raw);
        auto start = std::chrono::system_clock::now();
        f.psortFunc(unsortVector);
        auto end = std::chrono::system_clock::now();
        std::chrono::duration<double> elapsed = end-start;

        cout << "Using : [" << std::setw(20) << f.name << "] , cost: ";
        if (checkSort(unsortVector)){
            std::cout << elapsed.count() << "s" << endl;
        } else {
            std::cout << "Sorted Failed ... " << endl;
        }
        if (DEBUG_PRINT) {
            if (checkSort(unsortVector)){
                cout << "Well Sorted!" << endl;
            } else {
                cout << "Sort failed!" << endl;
            }
            cout << "Sorted Vector: "  << endl;
            for(const int &i: unsortVector){
                cout << i << ",";
            }
            cout << endl;
        }
    }

    return 0;

    return 0;
}
