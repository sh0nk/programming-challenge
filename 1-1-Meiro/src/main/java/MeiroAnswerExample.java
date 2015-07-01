import java.util.*;

public class MeiroAnswerExample implements Meiro {
    private static int NOT_VISITED = 100000;

    public static class Coordinate {
        public int x;
        public int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public int bfs(char meiro[][], int numX, int numY, int startX, int startY, int goalX, int goalY) {
        // var definition (intend to migrate into C lang...)
        Queue<Coordinate> queue = new LinkedList<Coordinate>();
        int[][] distance = new int[numX][numY];
        List<Coordinate> moves = new ArrayList<Coordinate>();
        int cursorX = 0;
        int cursorY = 0;
        Coordinate p;

        // initialize distance
        for (int i = 0; i < numX; i++) {
            for (int j = 0; j < numY; j++) {
                distance[i][j] = NOT_VISITED;
            }
        }

        // initialize move
        moves.add(new Coordinate(1, 0));
        moves.add(new Coordinate(0, 1));
        moves.add(new Coordinate(-1, 0));
        moves.add(new Coordinate(0, -1));

        // set start point
        distance[startX][startY] = 0;
        queue.add(new Coordinate(startX, startY));

        // main loop
        main:
        while(queue.peek() != null) {
            p = queue.poll();

            for (int i = 0; i < 4; i++) {
                cursorX = p.x + moves.get(i).x;
                cursorY = p.y + moves.get(i).y;

                if (cursorX >= 0 && cursorX < numX && cursorY >= 0 && cursorY < numY
                        && meiro[cursorX][cursorY] != '#'
                        && distance[cursorX][cursorY] == NOT_VISITED) {
                    queue.add(new Coordinate(cursorX, cursorY));

                    // advance the distance
                    distance[cursorX][cursorY] = distance[p.x][p.y] + 1;

                    if (cursorX == goalX && cursorY == goalY) {
                        break main;
                    }

                }
            }
        }

        return distance[cursorX][cursorY];
    }
}
