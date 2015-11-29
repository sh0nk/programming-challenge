package main;

import java.util.Arrays;

import noripi.Angle;
import noripi.PolarPoint;

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
    this.printTree(tree);

    /***********************************************************************************
     * execute operations
     ***********************************************************************************/
    double[][] results = new double[S.length][2];

    for (int i = 0; i < S.length; i++) {
      int targetIndex = S[i];
      Angle newAngle = Angle.byRadians((A[i] - 180.0) / 180.0 * Math.PI);
      Angle subtractedAngle = newAngle.getSubtractedAngle(tree[0][targetIndex].getAngle());
      int index = targetIndex;

      tree[0][index] = new PolarPoint(tree[0][index].getLength(), newAngle);

      // update tree
      for (int level = 1; level < rootLevel + 1; level++) {
        index = (int) Math.floor(index / 2.0);
        this.updateNode(tree, level, index, subtractedAngle);
      }

      this.printTree(tree);
      results[i] = tree[rootLevel][0].toRectangular();
      System.out.println(Arrays.toString(tree[rootLevel][0].toRectangular()));
    }

    return results;
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
          double rad = (i == 0) ? 0.5 * Math.PI : 0;
          tree[level][i] = new PolarPoint(L[i], Angle.byRadians(rad));
        } else {
          this.updateNode(tree, level, i, Angle.byRadians(0));
        }
      }

      level++;
      length = length == 1 ? 0 : (int) Math.ceil(length / 2.0);
    }

    return tree;
  }

  private void updateNode(PolarPoint[][] tree, int level, int index, Angle rotation) {
    int firstChildIndex = index * 2;
    int secondChildIndex = firstChildIndex + 1;

    if (tree[level - 1].length <= secondChildIndex
        || tree[level - 1][firstChildIndex + 1].getLength() == 0) {

      if (tree[level][index] == null) {
        // if not set, set new value
        tree[level][index] = new PolarPoint(tree[level - 1][firstChildIndex].getLength(),
            tree[level - 1][firstChildIndex].getAngle());
      } else {
        System.out.println(rotation);
        // if set, just rotate
        Angle newAngle = tree[level][index].getAngle().getRotatedAngle(rotation);
        tree[level][index] = new PolarPoint(tree[level - 1][firstChildIndex].getLength(), newAngle);
      }
      return;
    }

    PolarPoint firstChild = tree[level - 1][firstChildIndex];
    PolarPoint secondChild = tree[level - 1][secondChildIndex];

    // cos formula: a^2 = b^2 + c^2 - 2bc * cosA
    double segLength = Math.sqrt(Math.pow(firstChild.getLength(), 2)
        + Math.pow(secondChild.getLength(), 2) - 2 * firstChild.getLength()
            * secondChild.getLength() * -secondChild.getAngle().getCosine());

    // sin formula: a/sinA = b/sinB = c/sinC
    double firstChildDiffSin =
        secondChild.getLength() / segLength * secondChild.getAngle().getSine();
    double firstChildDiffCos = Math.sqrt(1 - Math.pow(firstChildDiffSin, 2))
        * Math.signum(Math.pow(firstChild.getLength(), 2) + Math.pow(segLength, 2)
            - Math.pow(secondChild.getLength(), 2));

    // seg angle = second angle + diff angle
    Angle rotatedAngle =
        firstChild.getAngle().getRotatedAngle(firstChildDiffCos, firstChildDiffSin);
    tree[level][index] = new PolarPoint(segLength, rotatedAngle);

    // if next segment exists, update angle
    if (tree[level].length > index + 1 && tree[level][index + 1] != null) {
      PolarPoint nextSegment = tree[level][index + 1];

      double nextSegDiffSin = firstChild.getLength() * firstChildDiffSin / secondChild.getLength();
      double nextSegDiffCos = Math.sqrt(1 - Math.pow(nextSegDiffSin, 2))
          * Math.signum(Math.pow(secondChild.getLength(), 2) + Math.pow(segLength, 2)
              - Math.pow(firstChild.getLength(), 2));

      Angle nextRotatedAngle =
          nextSegment.getAngle().getRotatedAngle(nextSegDiffCos, nextSegDiffSin);

      tree[level][index + 1] = new PolarPoint(nextSegment.getLength(), nextRotatedAngle);
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
  }


}
