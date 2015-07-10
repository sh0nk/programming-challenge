maze: main.o knapsack.o
	g++ -o knapsack main.cpp knapsack.cpp

main.o: main.cpp
	g++ -c main.cpp

knapsack.o: knapsack.cpp
	g++ -c knapsack.cpp

clean:
	rm -rf *.o *.gch maze
