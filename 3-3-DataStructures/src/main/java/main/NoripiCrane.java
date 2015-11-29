package main;

import java.util.Arrays;

public class NoripiCrane implements CraneInterface {
  @Override
  public double[][] solve(int N, int C, int[] L, int[] S, int[] A) {
    PolarPoint[][] tree = this.setupInitialTree(N, L);
    int rootLevel = 0;
    for (int level = 0; level < tree.length; level++) {
      if (tree[level].length == 1 || tree[level][1] == null) {
        rootLevel = level;
        break;
      }
    }

    /***********************************************************************************
     * execute operations
     ***********************************************************************************/
    int i = 0;
    int targetIndex = S[i];
    double newAngleRadian = (A[i] - 180.0) / 180.0 * Math.PI;

    // update tree
    int index = targetIndex;
    tree[0][index] =
        new PolarPoint(tree[0][index].length, Math.cos(newAngleRadian), Math.sin(newAngleRadian));
    for (int level = 1; level < rootLevel + 1; level++) {
      index = (int) Math.floor(index / 2.0);
      this.updateNode(tree, level, index);
    }

    this.printTree(tree);
    System.out.println(Arrays.toString(tree[rootLevel][0].toRectangular()));

    return null;
  }

  private PolarPoint[][] setupInitialTree(int N, int[] L) {
    /***********************************************************************************
     * setup vectors by polar coordinates system (Angle starts from Math.PI)
     ***********************************************************************************/
    PolarPoint[][] tree = new PolarPoint[(int) Math.ceil(Math.log(N) / Math.log(2)) + 1][N];

    int level = 0;
    int length = N;
    while (length > 0) {
      for (int i = 0; i < length; i++) {

        if (level == 0) {
          double angle = i == 0 ? 0.5 * Math.PI : 0;
          tree[level][i] = new PolarPoint(L[i], Math.cos(angle), Math.sin(angle));
        } else {
          this.updateNode(tree, level, i);
        }
      }

      level++;
      length = length == 1 ? 0 : (int) Math.ceil(length / 2.0);
    }

    return tree;
  }

  private void updateNode(PolarPoint[][] tree, int level, int index) {
    int firstChildIndex = index * 2;
    int secondChildIndex = firstChildIndex + 1;

    if (tree[level - 1].length <= secondChildIndex
        || tree[level - 1][firstChildIndex + 1].length == 0) {

      tree[level][index] = new PolarPoint(tree[level - 1][firstChildIndex].length,
          tree[level - 1][firstChildIndex].cosine, tree[level - 1][firstChildIndex].sine);
      return;
    }

    PolarPoint firstChild = tree[level - 1][firstChildIndex];
    PolarPoint secondChild = tree[level - 1][secondChildIndex];

    // if sin=0, length is just sum
    if (secondChild.sine == 0) {
      tree[level][index] = new PolarPoint(firstChild.length + secondChild.length, firstChild.cosine,
          firstChild.sine);
      return;
    }

    // cos formula: a^2 = b^2 + c^2 - 2bc * cosA
    double segLength = Math.sqrt(Math.pow(firstChild.length, 2) + Math.pow(secondChild.length, 2)
        - 2 * firstChild.length * secondChild.length * -secondChild.cosine);

    // sin formula: a/sinA = b/sinB = c/sinC
    double firstChildDiffSin = secondChild.length / segLength * secondChild.sine;
    double firstChildDiffCos = Math.sqrt(1 - Math.pow(firstChildDiffSin, 2)) * Math.signum(
        Math.pow(firstChild.length, 2) + Math.pow(segLength, 2) - Math.pow(secondChild.length, 2));

    // seg angle = second angle - diff angle
    double segCos = firstChild.cosine * firstChildDiffCos - firstChild.sine * firstChildDiffSin;
    double segSin = firstChild.sine * firstChildDiffCos + firstChild.cosine * firstChildDiffSin;
    tree[level][index] = new PolarPoint(segLength, segCos, segSin);

    // if next segment exists, update angle
    if (tree[level].length > index + 1 && tree[level][index + 1] != null) {
      PolarPoint nextSegment = tree[level][index + 1];

      double nextSegDiffSin = firstChild.length * firstChildDiffSin / secondChild.length;
      double nextSegDiffCos =
          Math.sqrt(1 - Math.pow(nextSegDiffSin, 2)) * Math.signum(Math.pow(secondChild.length, 2)
              + Math.pow(segLength, 2) - Math.pow(firstChild.length, 2));

      double nextSegCos = nextSegment.cosine * nextSegDiffCos - nextSegment.sine * nextSegDiffSin;
      double nextSegSin = nextSegment.sine * nextSegDiffCos + nextSegment.cosine * nextSegDiffSin;

      tree[level][index + 1] = new PolarPoint(nextSegment.length, nextSegCos, nextSegSin);
    }

  }

  private void printTree(PolarPoint[][] tree) {
    for (int i = 0; i < tree.length; i++) {
      for (int j = 0; j < tree[i].length; j++) {
        if (tree[i][j] == null) {
          break;
        }

        System.out.print(tree[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }

  private class PolarPoint {
    private static final int PRECISION = 4;
    private double length;
    private double cosine;
    private double sine;

    public PolarPoint(double length, double cosine, double sine) {
      this.length = length;
      this.cosine = Math.round(cosine * Math.pow(10, PRECISION + 1)) / Math.pow(10, PRECISION + 1);
      this.sine = Math.round(sine * Math.pow(10, PRECISION + 1)) / Math.pow(10, PRECISION + 1);
    }

    @Override
    public String toString() {
      return "[Len:" + this.length + " Angle:" + this.cosine + "/" + this.sine + "] ";
    }

    public double[] toRectangular() {
      return new double[] {
          Math.round(this.length * this.cosine * Math.pow(10, PRECISION)) / Math.pow(10, PRECISION),
          Math.round(this.length * this.sine * Math.pow(10, PRECISION)) / Math.pow(10, PRECISION)};
    }
  }
}
