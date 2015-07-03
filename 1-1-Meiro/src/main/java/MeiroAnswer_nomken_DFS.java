
public class MeiroAnswer_nomken_DFS implements Meiro {

    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, -1, 0, 1 };

    @Override
    public int bfs(char[][] meiro, int numX, int numY, int startX, int startY, int goalX, int goalY) {

        int[][] passed = new int[numX][numY];

        passed[startX][startY] = 1;

        int ans = 0;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + startX;
            int ny = dy[i] + startY;
            if (0 <= nx && nx < numX && 0 <= ny && ny < numY && meiro[nx][ny] == '.') {
                System.out.printf("nx=%d, ny=%d, goalX=%d, goalY=%d, c=%d \n", nx, ny, goalX, goalY, 0);
                ans = test(meiro, passed, numX, numY, nx, ny, goalX, goalY, 1);
            }
        }

        return ans;
    }

    private int test(char[][] meiro, int[][] passed, int numX, int numY, int x, int y, int goalX, int goalY, int count) {

        passed[x][y] = 1;

        if (count == 0) {
            return 0;
        }

        if (x == goalX && y == goalY) {
            return count;
        }

        count++;

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if (0 <= nx && nx < numX && 0 <= ny && ny < numY && passed[nx][ny] == 0 && meiro[nx][ny] == '.') {
                System.out.printf("nx=%d, ny=%d, goalX=%d, goalY=%d, c=%d \n", nx, ny, goalX, goalY, count);
                int ans = test(meiro, passed, numX, numY, nx, ny, goalX, goalY, count);
                if (ans != 0) {
                    return ans;
                }
            }
        }

        return 0;
    }

}
