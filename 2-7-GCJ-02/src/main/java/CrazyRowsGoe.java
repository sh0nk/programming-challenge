import java.util.Arrays;

/**
 * Created by yoshigoe on 10/8/15.
 */
public class CrazyRowsGoe implements CrazyRows{

    @Override
    public int solve(int N, int[][] V){
        int ans = 0;
        int[] pos = new int[N]; //position of right 1
        int row; // position of row

        for (int i=0;i<N;i++){
            pos[i] = -1;
            for(int j=0;j<N;j++){
                if(V[i][j]==1) pos[i] = j;
            }
        }
        System.out.println(Arrays.toString(pos));

        for (int i=0;i<N;i++){
            row = -1;
            for(int j=0;j<N;j++){
                if(pos[j] <= i){
                    row = j;
                    break;
                }
            }
            for(int j=row;j>i;j--){
                int swap = pos[j];
                pos[j] = pos[j-1];
                pos[j-1] = swap;
                ans++;
            }
        }
        return ans;
    }

}
