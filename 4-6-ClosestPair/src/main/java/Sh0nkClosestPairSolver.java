import java.text.MessageFormat;
import java.util.*;

public class Sh0nkClosestPairSolver implements IClosestPairSolver {
    List<Double> xs = new ArrayList<>();
    List<Double> ys = new ArrayList<>();
    List<Integer> globalYsIdx = new ArrayList<>();

    double dist(int leftIdx, int rightIdx) {
        return Math.sqrt(Math.pow(xs.get(leftIdx) - xs.get(rightIdx), 2)
                + Math.pow(ys.get(leftIdx) - ys.get(rightIdx), 2));
    }

    double search(List<Integer> leftXsIdx, List<Integer> rightXsIdx) {
        System.out.println(MessageFormat.format("left {0}", leftXsIdx));
        System.out.println(MessageFormat.format("right {0}", rightXsIdx));
        if (leftXsIdx.size() == 0 || rightXsIdx.size() == 0) {
            return Double.MAX_VALUE;
        }
        if (leftXsIdx.size() == 1 && rightXsIdx.size() == 1) {
            System.out.println(MessageFormat.format("  only l&r, dist {0}", dist(leftXsIdx.get(0), rightXsIdx.get(0))));
            return dist(leftXsIdx.get(0), rightXsIdx.get(0));
        }

        // return 距離
        // TODO: 奇数？
        double left = search(leftXsIdx.subList(0, leftXsIdx.size() / 2),
                leftXsIdx.subList(leftXsIdx.size() / 2, leftXsIdx.size()));
        double right = search(rightXsIdx.subList(0, rightXsIdx.size() / 2),
                rightXsIdx.subList(rightXsIdx.size() / 2, rightXsIdx.size()));

        // またがるケース
        // yでソートされたidxリスト
        System.out.println(MessageFormat.format("cross left {0}", leftXsIdx));
        System.out.println(MessageFormat.format("cross right {0}", rightXsIdx));
        List<Integer> leftYsIdx = new ArrayList<>();
        for (Integer idx : globalYsIdx) {
            if (leftXsIdx.contains(idx)) {
                leftYsIdx.add(idx);
            }
        }
        List<Integer> rightYsIdx = new ArrayList<>();
        List<Double> rightYs = new ArrayList<>();
        for (Integer idx : globalYsIdx) {
            if (rightXsIdx.contains(idx)) {
                rightYsIdx.add(idx);
                rightYs.add(ys.get(idx));
            }
        }

        // 探索
        double center = Double.MAX_VALUE;
        for (Integer idx : leftYsIdx) {
            // 左側を固定した時、右側の中から近い上下2点ずつのみを探す
            // それ以外の場合は分割された左右から既に最小値として検出されているため
            System.out.println(MessageFormat.format("    right Ys idx to search {0}", rightYsIdx));
            System.out.println(MessageFormat.format("    right Ys to search {0}", rightYs));
            System.out.println(MessageFormat.format("    search target value {0}", ys.get(idx)));
            int closestInRightYs = Collections.binarySearch(rightYs, ys.get(idx));
            closestInRightYs = (closestInRightYs >= 0) ? closestInRightYs : ~closestInRightYs;
            System.out.println(MessageFormat.format("    closest idx {0}", closestInRightYs));
            for (int i = closestInRightYs - 2; i < closestInRightYs + 2; i++) {
                if (i >= 0 && i < rightYsIdx.size()) {
                    System.out.println(MessageFormat.format("    center idx (i={2}) {0} {1}", idx, rightYsIdx.get(i), i));
                    center = Math.min(center, dist(idx, rightYsIdx.get(i)));
                }
            }
        }
        System.out.println(MessageFormat.format("  center, dist {0}", center));

        return Math.min(center, Math.min(left, right));
    }

    public double solve(int n, double[] x, double[] y) {
        List<Integer> globalXsIdx = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            xs.add(x[i]);
            ys.add(y[i]);
            globalXsIdx.add(i);
            globalYsIdx.add(i);
        }

        // sort
        globalXsIdx.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return xs.get(o1).compareTo(xs.get(o2));
            }
        });
        globalYsIdx.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return ys.get(o1).compareTo(ys.get(o2));
            }
        });

        return search(globalXsIdx.subList(0, globalXsIdx.size() / 2),
                globalXsIdx.subList(globalXsIdx.size() / 2, globalXsIdx.size()));

    }

}
