public class SubsequenceSh0nk implements SubsequenceI {
    @Override
    public int solve(int N, int K, int[] L) {
        int start = 0;
        int end = 0;
        int minLength = N + 1;
        int currentSum = 0;

        // start the initial max
        while(end < N) {
            for (int i = end; i < N; i++) {
                currentSum += L[i];
                System.out.println(String.format("s %d %d sum %d", start, i, currentSum));
                if (currentSum >= K) {
                    end = i;
                    break;
                }
            }
            // update the answer
            if (minLength > end - start + 1) {
                minLength = end - start + 1;
                System.out.println(String.format("min %d", minLength));
            }

            for (int i = start; i < N; i++) {
                currentSum -= L[i];
                System.out.println(String.format("e %d %d sum %d", i, end, currentSum));
                if (currentSum < K) {
                    start = i;
                    break;
                }
            }

            currentSum += L[start];
            // update the answer
            if (minLength > end - start + 1) {
                minLength = end - start + 1;
                System.out.println(String.format("min %d", minLength));
            }

            System.out.println(String.format("f %d %d sum %d", start, end, currentSum));
            end++;
        }

        return (minLength == N + 1) ? 0 : minLength;
    }

}
