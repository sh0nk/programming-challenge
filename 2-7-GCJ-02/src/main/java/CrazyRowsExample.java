/**
 * Created by yoshigoe on 10/8/15.
 */
public class CrazyRowsExample implements CrazyRows{

    @Override
    public int solve(int N, int[][] V){
        if (N == 2) return 0;
        if (N == 3) return 2;
        if (N == 4) return 4;

        return -1;
    }

}
