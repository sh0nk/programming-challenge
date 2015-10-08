import java.util.Arrays;

/**
 * Created by yoshigoe on 10/8/15.
 */
public class MSPGoe implements MSP{

    @Override
    public int solve(int[] v1, int[] v2){
        int N = v1.length;
        if(N != v2.length) return -1;
        float ans = 0;

        Arrays.sort(v1);
        Arrays.sort(v2);

        for(int i =0;i<N;i++){
            ans += v1[i] * v2[N-1-i];
        }
        return (int) ans;

    }

}
