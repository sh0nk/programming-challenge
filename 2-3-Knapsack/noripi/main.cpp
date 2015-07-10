#include <iostream>
#include "knapsack.h"

using namespace std;

int main(int argc, char *argv[]) 
{
    if (argc<2) {
        cout << "Usage: knapsack <filename>" << endl;
        return -1;
    }
   
    Knapsack *knapsack = new Knapsack(argv[1]);
    knapsack->print();
    knapsack->putItems();
}

