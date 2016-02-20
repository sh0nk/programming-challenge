import java.util.*;

public class Sh0nkJackStraw implements JackStrawInterface {

    class Point {
        float x;
        float y;
    }

    class Line {
        // by = ax + c;
        float a, b, c;
    }

    private static boolean floatEquals(float a, float b) {
        return Math.abs(a - b) < 0.00000001;
    }

    private Line findLineElement(int[] p, int[] q) {
        Line l = new Line();
        // xが同じケース (eg. x=5)
        if (floatEquals(q[0], p[0])) {
            l.a = 1.0f;
            l.b = 0.0f;
            l.c = (float) - q[0];
        } else {
            l.a = (float) (q[1] - p[1]) / (float) (q[0] - p[0]);
            l.b = 1.0f;
            l.c = (float) p[1] - l.a * (float) p[0];
        }
        return l;
    }

    private Point findCrossPoint(Line l0, Line l1) {
        Point p = new Point();
        p.x = - (l0.c - l1.c) / (l0.a - l1.a);
        p.y = (l1.c * l0.a - l1.a * l0.c) / (l0.a - l1.a);
        return p;
    }


    // (p0[0], p0[1]) (q0[0], q0[1])
    private boolean isCrossed(int[] p0, int[] q0, int[] p1, int[] q1) {
        Line l0 = findLineElement(p0, q0);
        Line l1 = findLineElement(p1, q1);
        System.out.println(String.format("(a %.2f b %.2f c %.2f) (a %.2f b %.2f c %.2f)", l0.a, l0.b, l0.c, l1.a, l1.b, l1.c));
        System.out.println(String.format(" (%d,%d)-(%d,%d) (%d,%d)-(%d,%d)",
                p0[0], p0[1], q0[0], q0[1], p1[0], p1[1], q1[0], q1[1]));

        if (l0.b == 0 && l1.b == 0) {
            // どちらもxのみ
            if (floatEquals(l0.c, l1.c)) {
                if (!(Math.min(p0[1], q0[1]) > Math.max(p1[1], q1[1]) || Math.max(p0[1], q0[1]) < Math.min(p1[1], q1[1]))) {
                    return true;
                }
                // 棒が届かない
            }
            // 並行
            return false;
        } else if (l0.a == 0 && l1.a == 0) {
            // どちらもyのみ
            if (floatEquals(l0.c, l1.c)) {
                if (!(Math.min(p0[0], q0[0]) > Math.max(p1[0], q1[0]) || Math.max(p0[0], q0[0]) < Math.min(p1[0], q1[0]))) {
                    return true;
                }
                // 棒が届かない
            }
            // 並行
            return false;
        } else {
            if (floatEquals(l0.a, l1.a)) {
                // 傾き同じ
                if (floatEquals(l0.c, l1.c)) {
                    // 同一直線上
                    if (!(Math.min(p0[0], q0[0]) > Math.max(p1[0], q1[0]) || Math.max(p0[0], q0[0]) < Math.min(p1[0], q1[0]))) {
                        // xだけ同じ値域を持たなければyも持たない
                        return true;
                    }
                    // 棒が届かない
                    return false;
                }
                // 並行
                return false;
            }

            Point p = findCrossPoint(l0, l1);
            System.out.println(String.format("  (%.2f, %.2f) cross point",
                    p.x, p.y));

            float minX0 = Math.min(p0[0], q0[0]);
            float minX1 = Math.min(p1[0], q1[0]);
            float maxX0 = Math.max(p0[0], q0[0]);
            float maxX1 = Math.max(p1[0], q1[0]);
            // xだけ同じ値域を持たなければyも持たない
            if (!(p.x > maxX0) && !(minX0 > p.x) && !(p.x > maxX1) && !(minX1 > p.x)) {
                return true;
            }
            return false;
        }
    }

    private List<List<Integer>> findDirectCrosses(int n, int[][] p, int[][] q) {
        List<List<Integer>> set = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isCrossed(p[i], q[i], p[j], q[j])) {
                    if (i == j) {
                        continue;
                    }
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.add(j);
                    set.add(tmp);
                }
            }
        }

        return set;
    }

    private static Set<Integer> subtract(Set<Integer> orig, Set<Integer> tar) {
        Set<Integer> newSet = new HashSet<>(orig);
        for (Integer one: tar) {
            newSet.remove(one);
        }
        return newSet;
    }

    // 1,3 2,3 3,4
    @Override
    public String[] solve(int numOfSticks, int[][] p, int[][] q, int numOfCandidates, int[][] ab) {
        List<List<Integer>> setAll = findDirectCrosses(numOfSticks, p, q);
        System.out.println(setAll);

        Map<Integer, Set<Integer>> stickToGroup = new HashMap<>();
        Map<Integer, Set<Integer>> groupToStick = new HashMap<>();

        // build maps
        int numSet = 0;
        // まだどのunionにも属してないsticks
        Set<Integer> globalRemainedSticks = new HashSet<>();
        for (List<Integer> set: setAll) {
            for (Integer stick: set) {
                // 2本のペアループ
                Set<Integer> groupStickBelongsTo = stickToGroup.getOrDefault(stick, new HashSet<>());
                groupStickBelongsTo.add(numSet);
                stickToGroup.put(stick, groupStickBelongsTo);

                globalRemainedSticks.add(stick);

                Set<Integer> pair = groupToStick.getOrDefault(numSet, new HashSet<>());
                pair.add(stick);
                groupToStick.put(numSet, pair);
            }
            numSet++;
        }

        Set<Set<Integer>> globalUnion = new HashSet<>();
        while (true) {
            // 新しいunionを見つける
            Iterator<Integer> globalIt = globalRemainedSticks.iterator();
            if (!globalIt.hasNext()) {
                break;
            }
            Integer startStick = globalIt.next();
            System.out.println(String.format("let's start with start stick %d", startStick));
            System.out.println(String.format("  remaining sticks %s", globalRemainedSticks));

            Set<Integer> unionSticks = new HashSet<>();
            Set<Integer> unionGroups = new HashSet<>();
            Set<Integer> visitedSticks = new HashSet<>();
            Set<Integer> visitedGroups = new HashSet<>();
            Integer currentStick = startStick;
            Integer currentGroup = 0;
            for (int i = 0; i < 1000; i++) { // あくまでloopの最大数
                int visitedStickSize = visitedSticks.size();
                int visitedGroupSize = visitedGroups.size();

                visitedSticks.add(currentStick);
                Set<Integer> groups = stickToGroup.get(currentStick);
                unionGroups.addAll(groups);
                System.out.println(String.format("target stick %d", currentStick));
                System.out.println(String.format("  groups %s", groups));

                Set<Integer> notVisitedGroup = subtract(unionGroups, visitedGroups);
                Iterator<Integer> it = notVisitedGroup.iterator();
                if (it.hasNext()) {
                    currentGroup = it.next();
                }

                visitedGroups.add(currentGroup);
                Set<Integer> pair = groupToStick.get(currentGroup);
                unionSticks.addAll(pair);
                System.out.println(String.format("target group %d", currentGroup));
                System.out.println(String.format("  stick pair %s", pair));
                System.out.println(String.format("  current union %s", unionSticks));

                if (visitedStickSize == visitedSticks.size()
                        && visitedGroupSize == visitedGroups.size()) {
                    globalRemainedSticks = subtract(globalRemainedSticks, unionSticks);
                    globalUnion.add(unionSticks);
                    System.out.println(String.format("globalUnion %s", globalUnion));

                    break;
                }

                Set<Integer> notVisitedSticks = subtract(unionSticks, visitedSticks);
                it = notVisitedSticks.iterator();
                if (it.hasNext()) {
                    currentStick = it.next();
                }

            }
        }

        // abを1ずつずらす(0start indexのため)
        for (int i = 0; i < numOfCandidates; i++) {
            for (int j = 0; j < 2; j++) {
                ab[i][j]--;
            }
        }

        // 判定
        String[] retVal = new String[numOfCandidates];
        for (int i = 0; i < numOfCandidates; i++) {
            System.out.println(String.format("candidate %d, %d", ab[i][0], ab[i][1]));
            boolean matched = false;
            for (Set<Integer> set: globalUnion) {
                matched = true;
                for (int j = 0; j < 2; j++) {
                    if (!set.contains(ab[i][j])) {
                        matched = false;
                    }
                }
                if (matched) {
                    break;
                }
            }

            if (matched) {
                retVal[i] = "CONNECTED";
            } else {
                retVal[i] = "NOT CONNECTED";
            }
        }

        System.out.println(Arrays.asList(retVal));

        return retVal;
    }

}
