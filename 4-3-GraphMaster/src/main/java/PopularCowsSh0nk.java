import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class PopularCowsSh0nk implements PopularCowsInterface {
    private static Logger LOGGER = LogManager.getLogger(PopularCowsSh0nk.class);

    private Map<Integer, List<Integer>> G = new HashMap<>();
    private Map<Integer, List<Integer>> ReversedG = new HashMap<>();
    private List<Integer> orders = new ArrayList<>();
    private Set<Integer> visited = new HashSet<>();
    private List<Set<Integer>> grouped = new ArrayList<>();

    private void initNeighboredRepresentation(int M, int[] A, int[] B, Map<Integer, List<Integer>> dest) {
        for (int i = 0; i < M; i++) {
            List<Integer> direction = dest.get(A[i]);
            if (direction == null) {
                direction = new LinkedList<>();
                dest.put(A[i], direction);
            }
            direction.add(B[i]);
        }

        LOGGER.info(dest);
    }

    private void dfs(int index) {
        visited.add(index);
        LOGGER.debug("visited {}", index);
        if (G.get(index) != null) {
            for (int i : G.get(index)) {
                if (!visited.contains(i)) {
                    visited.add(i);
                    dfs(i);
                }
            }
        }

        // 帰りがけ
        orders.add(index);
    }

    private void rdfs(int index, int group, Set<Integer> groupSet) {
        visited.add(index);
        if (groupSet != null) {
            groupSet.add(index);
        }
        LOGGER.debug("visited {}", index);
        if (ReversedG.get(index) != null) {
            for (int i : ReversedG.get(index)) {
                if (!visited.contains(i)) {
                    rdfs(i, group, groupSet);
                }
            }
        }
    }



    @Override
    public int solve(int N, int M, int[] A, int[] B) {
        initNeighboredRepresentation(M, A, B, G);
        initNeighboredRepresentation(M, B, A, ReversedG);

        // DFS
        for (int i = 1; i < N + 1; i++) {
            if (!visited.contains(i)) {
                dfs(i);
            }
        }

        LOGGER.info("dfs {}", orders);
        visited.clear();

        // Reversed DFS
        int group = 0;
        Collections.reverse(orders);
        for (int i: orders) {
            if (!visited.contains(i)) {
                Set<Integer> groupSet = new HashSet<>();
                rdfs(i, group++, groupSet);
                grouped.add(groupSet);
                LOGGER.info("group {} from {} done: {}", group, i, groupSet);
            }
        }

        visited.clear();
        // 解答になりうる強連結集合 (一番ordersが若いノードが含まれる集合)
        Set<Integer> candidateSet = grouped.get(grouped.size() - 1);
        LOGGER.info("candidate set <{}>", candidateSet);
        if (candidateSet.size() == 0) {
            return 0;
        }
        // 全ノード訪問できるかチェック
        rdfs(candidateSet.iterator().next(), 0, null);

        if (visited.size() == N) {
            return candidateSet.size();
        } else {
            return 0;
        }

    }
}
