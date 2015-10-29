public class Sh0nkCableMaster implements CableMaster {
    @Override
    public String solve(int N, int K, double[] L) {
        double start = 0;
        double end = 0;

        // init the range max with the max of L because some rope is possibly not used
        for (int i = 0; i < N; i++) {
            if (L[i] > end) {
                end = L[i];
            }
        }

        double current = 0;
        for (int i = 0; i < 100; i++) {
            int honsu = 0;
            double half = (end - start) / 2;

            current = start + half;
            for (int j = 0; j < N; j++) {
                honsu += Math.floor(L[j] / current);
            }

            System.out.println(String.format("honsu %d current %.2f start %.2f end %.2f", honsu, current, start, end));

            if (honsu >= K) {
                start += half;
            } else {
                end -= half;
            }
        }

        // floor by floating 2 digits
        return String.format("%.2f", Math.floor(current * 100) / 100);
    }
}
