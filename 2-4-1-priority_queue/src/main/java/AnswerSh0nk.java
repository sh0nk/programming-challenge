import java.util.*;

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
        int[] extendedA = Arrays.copyOf(A, N + 1);
        extendedA[N] = L;
        int[] extendedB = Arrays.copyOf(B, N + 1);
        extendedB[N] = 0;

        currentGas = P;
        heap = new PriorityQueue<>(N, Collections.reverseOrder());

        for (int i = 0; i < N + 1; i++) {
            while (currentGas < extendedA[i]) {
                if (!fillGas(i)) {
                    return -1;
                }
            }
            heap.add(extendedB[i]);
        }

        return count;
    }
}
