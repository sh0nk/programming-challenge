import java.util.Comparator;
import java.util.PriorityQueue;

public class Main implements Interface {

    public int execute(int N, int L, int P, int[] A, int[] B) {

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(N, new Comparator<Integer>() {

            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int sum = 0;
        int step = 0;
        int n = 0;

        while (true) {
            if (step == L) {
                break;
            }
            if (n < N && A[n] == step) {
                pq.add(B[n]);
                n++;
            }
            if (P - step <= 0) {
                if (pq.size() > 0) {
                    P += pq.poll();
                    sum++;
                } else {
                    sum = -1;
                    break;
                }
            }
            step++;
        }
        return sum;
    }

}
