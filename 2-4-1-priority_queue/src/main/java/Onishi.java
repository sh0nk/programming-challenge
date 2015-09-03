import java.util.Comparator;
import java.util.PriorityQueue;

public class Onishi implements Interface {

    public int execute(int N, int L, int P, int[] A, int[] B) {
    	PriorityQueue<Integer> pq = new PriorityQueue<Integer>(N, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    	
    	int progress = 0; // 進んだ距離
    	int currentGas = P;
    	int addNum = 0; // 補給した回数
    	
    	for (int i = 0; i < N; i++) {
    		int diff = A[i] - progress;
    		if (currentGas >= diff) {
    			// 次のガソリンスタンドまで到達可
    			currentGas -= diff;
    			pq.add(B[i]);
    			progress = A[i];
    		} else {
    			while (pq.size() > 0) {
    				currentGas += pq.poll();
    				addNum++;
    				System.out.println("add!!");
    				if (currentGas >= diff) {
    					break;
    				}
    			}
    			if (currentGas < diff) {
    				// Goal不可
    				return -1;
    			}

    			progress = A[i];
    			currentGas -= diff;
    			pq.add(B[i]);
    		}
    	}
    	
    	// 最後のガソリンスタンドからGoalまで
    	int last = L - progress;
    	if (currentGas < last) {
    		while (pq.size() > 0) {
				currentGas += pq.poll();
				addNum++;
				System.out.println("add!!");
				if (currentGas >= last) {
					break;
				}
			}

			if (currentGas < last) {
				// Goal不可
				return -1;
			}

    	}
    	
    	return addNum;
    }

}
