public class NoripiSquareDestroyerSolver implements ISquareDestroyerSolver {

  @Override
  public int solve(int n, int[] excludes) {
    SquareBox squareBox = new SquareBox(n, excludes);

    try {
      return this.depthFirstSearch(squareBox, 0);
    } catch (CloneNotSupportedException e) {
      return 0;
    }
  }

  private int depthFirstSearch(SquareBox squareBox, int depth) throws CloneNotSupportedException {
    int currentCount = squareBox.countSquares();
    if (currentCount == 0) {
      return depth;
    }

    int minDepth = Integer.MAX_VALUE;
    for (int i = 0; i < squareBox.getDataSize(); i++) {
      if (!squareBox.getDataAt(i)) {
        continue;
      }

      SquareBox clone = (SquareBox) squareBox.clone();
      clone.removeSideAt(i);

      // 辺を削れば最低1個は削れるはずなので減らなかったらskip
      if (clone.countSquares() == currentCount) {
        continue;
      }

      minDepth = Math.min(this.depthFirstSearch(clone, depth + 1), minDepth);
    }

    return minDepth;
  }
}
