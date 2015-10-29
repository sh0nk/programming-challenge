import java.util.*;

public class AggressiveCowsExample implements AggressiveCows {
    @Override
    public int solve(int N, int M, int[] x) {
    	Arrays.sort(x);
    	int lb = 0;
    	int ub = x[N-1];
    	
    	while (ub - lb > 1) {
    		int mid = (lb + ub) / 2;
    		if (judge(mid, N, M, x)) {
    			lb = mid;
    		} else {
    			ub = mid;
    		}
    	}
    	
    	return lb;
    }
    
    private boolean judge(int d, int N, int M, int[]x) {
    	int last = 0;
    	for (int i = 1; i < M; i++) {
    		int crt = last + 1;
    		while (crt < N && x[crt] - x[last] < d) {
    			crt++;
    		}
    		if (crt == N) {
    			return false;
    		}
    		last = crt;
    	}
    	return true;
    }
}
