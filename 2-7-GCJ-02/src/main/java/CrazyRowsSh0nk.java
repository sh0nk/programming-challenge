import java.util.Arrays;

public class CrazyRowsSh0nk implements CrazyRows{
    private int count = 0;

    private void swap(int index[], int dst, int org) {
        // x, y, z -> z, x, y
        // 0, 1, 2 -> 2, 0, 1
        for (int i = org; i > dst; i--) {
            int tmp = index[i];
            index[i] = index[i - 1];
            index[i - 1] = tmp;
            count++;
        }
        System.out.println(Arrays.toString(index));
    }

    @Override
    public int solve(int N, int[][] V){
        int[] index = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j >= 0; j--) {
                if (V[i][j] == 1) {
                    index[i] = j;
                    break;
                }
            }
        }

        System.out.println(Arrays.toString(index));

        for (int i = 0; i < N / 2 + 1; i++) {
            if (index[i] > i) {
                for (int j = i + 1; j < N; j++) {
                    if (index[j] <= i) {
                        swap(index, i, j);
                        System.out.println(String.format("%d %d count %d", i, j, count));
                        break;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(index));

        return count;
    }

}
