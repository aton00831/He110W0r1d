#include <iostream>
#include <thread>
#include <vector>
#include <mutex>
#include <atomic>
using namespace std;
class notSingleton {
public:
    notSingleton():empty(false),boiled(false){
        cout << "Inside init notSingleton()" << endl;
    };
    void fill() {empty=false;};
    void drain() {empty=true;boiled=false;};
    void boil() {boiled=true;};
private:
    bool empty;
    bool boiled;
};
/* ---------------------------------------- */
class EagerInitializedSingleton {
public:
    void fill() {empty=false;};
    void drain() {empty=true;boiled=false;};
    void boil() {boiled=true;};

    static EagerInitializedSingleton* get() {
        return instance;
    }
private:
    EagerInitializedSingleton():empty(false),boiled(false){
        cout << "Inside init EagerInitializedSingleton()" << endl;
    };
    static EagerInitializedSingleton* instance;

    bool empty;
    bool boiled;
};
EagerInitializedSingleton* EagerInitializedSingleton::instance = new EagerInitializedSingleton;
/* ---------------------------------------- */
class LazyInitializedSingleton {
public:
    void fill() {empty=false;};
    void drain() {empty=true;boiled=false;};
    void boil() {boiled=true;};

    static LazyInitializedSingleton* get() {
        if (instance == NULL) {
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
private:
    LazyInitializedSingleton():empty(false),boiled(false){
        cout << "Inside init LazyInitializedSingleton()" << endl;
    };
    static LazyInitializedSingleton* instance;

    bool empty;
    bool boiled;
};
LazyInitializedSingleton* LazyInitializedSingleton::instance = nullptr;
/* ---------------------------------------- */
mutex mtx;
class PessimisticThreadSafeSingleton {
public:
    void fill() {empty=false;};
    void drain() {empty=true;boiled=false;};
    void boil() {boiled=true;};

    static PessimisticThreadSafeSingleton* get() {
        mtx.lock;
        if (instance == NULL) {
            instance = new PessimisticThreadSafeSingleton();
        }
        mtx.unlock();
        return instance;
    }
private:
    PessimisticThreadSafeSingleton():empty(false),boiled(false){
        cout << "Inside init LazyInitializedSingleton()" << endl;
    };
    static PessimisticThreadSafeSingleton* instance;

    bool empty;
    bool boiled;
};
PessimisticThreadSafeSingleton* PessimisticThreadSafeSingleton::instance = nullptr;

void try_to_get_object_pessimistic() {
    PessimisticThreadSafeSingleton* P=PessimisticThreadSafeSingleton::get();
}
/* ---------------------------------------- */
class OptimisticThreadSafeSingleton {
public:
    atomic<bool> optlock(false);
    void fill() {empty=false;};
    void drain() {empty=true;boiled=false;};
    void boil() {boiled=true;};

    static OptimisticThreadSafeSingleton* get() {
        mtx.lock;
        if (instance == NULL) {
            while(atomic_exchange(optlock, true));
            if (instance == NULL) {
                instance = new OptimisticThreadSafeSingleton();
            }
            atomic_store_explicit(optlock, false);
        }
        mtx.unlock();
        return instance;
    }
private:
    OptimisticThreadSafeSingleton():empty(false),boiled(false){
        cout << "Inside init LazyInitializedSingleton()" << endl;
    };
    static OptimisticThreadSafeSingleton* instance;

    bool empty;
    bool boiled;
};
OptimisticThreadSafeSingleton* OptimisticThreadSafeSingleton::instance = nullptr;

void try_to_get_object_optimistic() {
    OptimisticThreadSafeSingleton* P=OptimisticThreadSafeSingleton::get();
}
/* ---------------------------------------- */
int main() {
    cout << "Init notSingleton" << endl;
    notSingleton test;

    cout << "Init EagerInitializedSingleton" << endl;
    EagerInitializedSingleton* E = EagerInitializedSingleton::get();

    cout << "Init LazyInitializedSingleton" << endl;
    LazyInitializedSingleton* L = LazyInitializedSingleton::get();

    cout << "Init PessimisticThreadSafeSingleton" << endl;
    vector<thread> thread_pool(10, try_to_get_object);
    for(thread t: thread_pool) {
        t.join();
        cout << "Done PessimisticThreadSafeSingleton" << endl;
    }

    cout << "Init OptimisticThreadSafeSingleton" << endl;
    vector<thread> thread_pool(10, try_to_get_object_optimistic);
    for(thread t: thread_pool) {
        t.join();
        cout << "Done OptimisticThreadSafeSingleton" << endl;
    }
    return 0;
}
