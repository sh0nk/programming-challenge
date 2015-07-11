import java.util.*;

public class AnswerExample implements Answer {
    private static void printDp(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
    }

    public int solve(int[][] candidates, int n, int weight) {
        int maxValueSum = 0, weightSum = 0;
        for (int i = 0; i < n; i++) {
            maxValueSum += candidates[i][1];
            weightSum += candidates[i][0];
        }
        System.out.println("value sum = " + maxValueSum);

        int[][] dp = new int[n + 1][maxValueSum + 1];
        for (int valueSum = 0; valueSum < maxValueSum + 1; valueSum++) {
            dp[0][valueSum] = weightSum + 1;
        }
        dp[0][0] = 0;
        printDp(dp);

        for (int idx = 0; idx < n; idx++) {
            for (int valueSum = 0; valueSum < maxValueSum + 1; valueSum++) {
                if (valueSum < candidates[idx][1]) {
                    dp[idx + 1][valueSum] = dp[idx][valueSum];
                } else {
                    dp[idx + 1][valueSum] = Math.min(dp[idx][valueSum],
                            (dp[idx][valueSum - candidates[idx][1]] + candidates[idx][0]));
                }
            }
        }
        printDp(dp);

        int answer = 0;
        for (int valueSum = 0; valueSum < maxValueSum + 1; valueSum++) {
            if (dp[n][valueSum] <= weight) {
                answer = valueSum;
            }
        }

        return answer;
    }
}
