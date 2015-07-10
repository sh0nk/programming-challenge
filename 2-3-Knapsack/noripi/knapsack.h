class Baggage {
    public:
        int weight;
        int value;
        Baggage(int weight, int value);
        void print();
};

class Knapsack {
    private:
        int number;
        int maxWeight;
        int **dp;
        Baggage **baggages;

    public:
        Knapsack(char *filename);
        void print();
        void printDpTable();
        void putItems();
};
