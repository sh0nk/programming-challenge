#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <climits>
#include <chrono>
#include <thread>
#include "knapsack.h"

using namespace std;

const int BUF_SIZE = 1024;

void sleep(int millisec) {
    std::this_thread::sleep_for(std::chrono::milliseconds(millisec));
}

/**
 * Baggage
 */
Baggage::Baggage(int weight, int value) {
    this->weight = weight;
    this->value = value;
}

void Baggage::print() {
    cout << "Baggage<weight=" << this->weight << ", value=" << this->value << ">" << endl;
}

/**
 * Knapsack
 */
Knapsack::Knapsack(char *filename) {
    FILE *fp;

    if ((fp = fopen(filename, "r"))==NULL) return;

    char row[BUF_SIZE];
    int i = 0;
    while(fgets(row, BUF_SIZE, fp)!=NULL) {
        switch (i) {
            case 0:
                this->number = atoi(row);
                this->baggages = (Baggage **)malloc(sizeof(Baggage *) * this->number);
                break;
            case 1:
                this->maxWeight = atoi(row);
                break;
            default:
                int weight = 0;
                int value = 0;

                char *buf;
                buf = strtok(row, ",");
                weight = atoi(buf);
                buf = strtok(NULL, ",");
                value = atoi(buf);

                Baggage *b = new Baggage(weight, value);
                this->baggages[i-2] = b;  
        }

        i++;
    }

    // initialize dp
    int valueSum = 0;
    for (int i=0; i<this->number; i++) {
        valueSum += this->baggages[i]->value;
    }

    this->dp = (int **)malloc(sizeof(int *)*this->number);
    for (int i=0; i<this->number; i++) {
        this->dp[i] = (int *)malloc(sizeof(int)*valueSum);
        for (int j=0; j<valueSum; j++) {
            this->dp[i][j] = INT_MAX>>1;
        }
    }
}

void Knapsack::print() {
    cout << "number: " << this->number << endl;
    cout << "maxWeight: " << this->maxWeight << endl;

    cout << "baggages:" << endl;
    for (int i=0; i<this->number; i++) {
        cout << "\t";
        this->baggages[i]->print();
    }
}

void Knapsack::printDpTable() {
    int valueSum = 0;
    for (int i=0; i<this->number; i++) {
        valueSum += this->baggages[i]->value;
    }

    cout << " i \\ v";
    for (int i=0; i<valueSum; i++) {
        cout << setw(4) << right << i;
    }
    cout << endl;
    cout << "--------";
    for (int i=0; i<valueSum; i++) {
        cout << "----";
    }
    cout << endl;

    for (int i=0; i<this->number; i++) {
        cout << setw(3) << right << i << " |  ";
        for (int j=0; j<valueSum; j++) {
            cout << setw(3) << right;
            if (this->dp[i][j] == INT_MAX>>1) {
                cout << "x";
            } else {
                cout << this->dp[i][j];
            }
            cout << " ";
        }
        cout << endl;
    }
    

}

void Knapsack::putItems() {
    int result = 0;

    int valueSum = 0;
    for (int i=0; i<this->number; i++) {
        valueSum += this->baggages[i]->value;
    }

    cout << endl;
    this->printDpTable();
    for (int itemIdx=0; itemIdx<this->number; itemIdx++) {
        for (int val=0; val<valueSum; val++) {
            if (val==0) {
                this->dp[itemIdx][val] = 0;
                continue;
            }

            Baggage *baggage = this->baggages[itemIdx];
            if (itemIdx==0) {
                if (baggage->value==val) {
                    this->dp[itemIdx][val] = baggage->weight;
                }
            } else {
                if (val-baggage->value>=0) {
                    this->dp[itemIdx][val] = min(this->dp[itemIdx-1][val], this->dp[itemIdx-1][val-baggage->value]+baggage->weight);
                } else {
                    this->dp[itemIdx][val] = this->dp[itemIdx-1][val];
                }
            }

            // prepare answer
            if (this->dp[itemIdx][val] <= this->maxWeight) {
                result = max(result, val);
            }
        }

        printf("\033[%dA", this->number+2);
        this->printDpTable();
        sleep(1000);
    }

    printf("\033[%dD", this->number+2);
    cout << endl;
    cout << "FINISHED. ANSWER IS " << result << endl;
}
