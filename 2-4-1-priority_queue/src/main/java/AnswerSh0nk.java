import java.util.Collections;
import java.util.PriorityQueue;

public class AnswerSh0nk implements Interface {
    private int currentGas;
    private int count = 0;
    private PriorityQueue<Integer> heap;

    private boolean fillGas(int idx) {
        System.out.println(String.format("%d: %d", idx, heap.peek()));
        if (heap.size() == 0) {
            return false;
        }

        currentGas += heap.poll();
        System.out.println(String.format("current: %d", currentGas));
        count++;
        return true;
    }

    @Override
    public int execute(int N, int L, int P, int[] A, int[] B) {
        currentGas = P;
        heap = new PriorityQueue<>(N, Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            heap.add(B[i]);
            while (currentGas < A[i]) {
                if (!fillGas(i)) {
                    return -1;
                }
            }
        }

        while (currentGas < L) {
            if (!fillGas(N)) {
                return -1;
            }
        }

        return count;
    }
}
