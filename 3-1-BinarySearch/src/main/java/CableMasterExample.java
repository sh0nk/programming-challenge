public class CableMasterExample implements CableMaster {
    @Override
    public String solve(int N, int K, double[] L) {
    	double lb = 0; // 解の存在範囲:下端
    	double ub = 1000000000; // 解の存在範囲:上端(L_Max * N)
    	
    	for (int i = 0; i < 100; i++) {
    		double mid = (lb + ub) / 2;
    		if (judgeCondition(mid, N, K, L)) {
    			lb = mid;
    		} else {
    			ub = mid;
    		}
    	}
    	
    	return String.format("%.2f", Math.floor(ub * 100) / 100);
    }
    
    private boolean judgeCondition(double x, int N, int K, double[] L) {
    	int num = 0;
    	for (int i = 0; i < N; i++) {
    		num += (int)(L[i] / x);
    	}
    	return num >= K;
    }
}
