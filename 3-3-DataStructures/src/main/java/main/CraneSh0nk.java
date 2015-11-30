package main;

import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class CraneSh0nk implements CraneInterface {

    private static double TO_RAD = Math.PI / 180.0;

    private Node[] nodes;
    private int maxN;
    private int[] lengths;

    private class Node {
        double x;
        double y;
        double length;
        int angle;
    }

    private void init(int n) {
        nodes = new Node[2 * maxN - 1];
        for (int i = 0; i < 2 * maxN - 1; i++) {
            nodes[i] = new Node();
            nodes[i].x = 0.0;
            nodes[i].y = 0.0;
            nodes[i].length = 0.0;
            nodes[i].angle = 180;
        }

        // overwrite
        for (int i = 0; i < n; i++) {
            nodes[i + maxN - 1].y = lengths[i];
            nodes[i + maxN - 1].length = lengths[i];
        }
        merge(0);
    }

    //1:    0
    //2:   1      2
    //4:  3 4    5    6
    //8: 78 910 1112 1314
    private void merge(int idx) {
        if (idx >= maxN - 1) {
            System.out.println(String.format("idx %d x %f y %f", idx, nodes[idx].x, nodes[idx].y));
        } else {
            merge(idx * 2 + 1);
            merge(idx * 2 + 2);
            nodes[idx].x = nodes[idx * 2 + 1].x + nodes[idx * 2 + 2].x;
            nodes[idx].y = nodes[idx * 2 + 1].y + nodes[idx * 2 + 2].y;
            nodes[idx].length = nodes[idx * 2 + 1].length + nodes[idx * 2 + 2].length;
            System.out.println(String.format("idx %d x %f y %f", idx, nodes[idx].x, nodes[idx].y));
        }
    }

    private void update(int craneIdx, int angle) {
        int idx = craneIdx + maxN - 1;
        System.out.println(String.format("start update for craneIdx %d node idx %d angle %d", craneIdx, idx, angle));
        nodes[idx].angle = angle;
        while (idx > 0) {
            System.out.println(String.format("update for node idx %d", idx));
            idx = (idx - 1) / 2;
            nodes[idx].angle = nodes[idx * 2 + 2].angle + nodes[idx * 2 + 1].angle - 180;
            nodes[idx].x = nodes[idx * 2 + 1].x + nodes[idx * 2 + 2].length * Math.sin(nodes[idx].angle * TO_RAD);
            nodes[idx].y = nodes[idx * 2 + 1].y - nodes[idx * 2 + 2].length * Math.cos(nodes[idx].angle * TO_RAD);
            System.out.println(String.format("updated for node idx %d x %f y %f angle %d left %d right %d",
                    idx, nodes[idx].x, nodes[idx].y, nodes[idx].angle,
                    nodes[idx * 2 + 1].angle, nodes[idx * 2 + 2].angle));
        }

    }

    private void findMaxN(int n) {
        for (int i = 1; i < Integer.MAX_VALUE; i = i * 2) {
            if (n <= i) {
                this.maxN = i;
                break;
            }
        }
    }

    @Override
    public double[][] solve(int N, int C, int[] L, int[] S, int[] A) {
        lengths = L;

        double[][] answers = new double[N][2];

        // 2のべき乗になるNより大きい数をさがす
        findMaxN(N);
        System.out.println(String.format("maxN is %d", maxN));
        init(N);

        for (int i = 0; i < C; i++) {
            update(S[i], A[i]);
            answers[i] = new double[]{
                    Double.valueOf(String.format("%.2f", nodes[0].x)),
                    Double.valueOf(String.format("%.2f", nodes[0].y))};
        }

        return answers;
    }

}
