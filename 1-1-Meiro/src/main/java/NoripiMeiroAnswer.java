import java.util.*;

public class NoripiMeiroAnswer implements Meiro {
    private int[][] meiro;
    private int startX;
    private int startY;
    private int goalX;
    private int goalY;

    public int bfs(char meiro[][], int numX, int numY, int startX, int startY, int goalX, int goalY) {
        this.meiro = this.addWall(meiro);
        this.startX = startX + 1;
        this.startY = startY + 1;
        this.goalX = goalX + 1;
        this.goalY = goalY + 1;

        this.meiro[this.startX][this.startY] = 0;
        this.updateFromPoints(this.startX, this.startY);

        this.printMeiro();
        return this.meiro[this.goalX][this.goalY];
    }

    private void updateFromPoints(int x, int y) {
        int i = this.meiro[x][y];
        int next = i+1;

        if (this.meiro[x-1][y]==-1 && this.meiro[x+1][y]==-1 && 
            this.meiro[x][y-1]==-1 && this.meiro[x][y+1]==-1) return;

        if (this.meiro[x-1][y]==-2) {
            this.meiro[x-1][y] = next;
            this.updateFromPoints(x-1, y);
        }
        if (this.meiro[x+1][y]==-2) {
            this.meiro[x+1][y] = next;
            this.updateFromPoints(x+1, y);
        }
        if (this.meiro[x][y-1]==-2) {
            this.meiro[x][y-1] = next;
            this.updateFromPoints(x, y-1);
        }
        if (this.meiro[x][y+1]==-2) {
            this.meiro[x][y+1] = next;
            this.updateFromPoints(x, y+1);
        }
    }
    
    private int[][] addWall(char meiro[][]) {
        int[][] data; 
        data = new int[meiro.length+2][meiro[0].length+2];

        for (int i=0; i<data.length; i++) {
            for (int j=0; j<data[i].length; j++) {
                if (i==0 || i==data.length-1 || j==0 || j==data[j].length-1) {
                    data[i][j] = -1;
                } else {
                    data[i][j] = meiro[i-1][j-1]=='#' ? -1 : -2;
                }
            }
        }

        return data;
    }

    private void printMeiro() {
        for (int i=0; i<this.meiro.length; i++) {
            for (int j=0; j<this.meiro[i].length; j++) {
                String output = "";
                switch (this.meiro[i][j]) {
                case -1:
                    output = "xxx";
                    break;
                default:
                    if (i==this.startX && j==this.startY) {
                        output = "  S";
                        break;
                    }
                    if (i==this.goalX && j==this.goalY) {
                        output = "  G";
                        break;
                    }
                    output = "   ";

                    if (this.meiro[i][j] > 0) {
                        output = String.format("%3d", this.meiro[i][j]);
                    }
                }
                System.out.print(output);
            }
            System.out.println();
        }
        System.out.println();
    }
}
