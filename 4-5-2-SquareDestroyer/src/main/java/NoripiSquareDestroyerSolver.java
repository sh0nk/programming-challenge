import java.util.ArrayList;
import java.util.List;

public class NoripiSquareDestroyerSolver implements ISquareDestroyerSolver {

  @Override
  public int solve(int n, int[] excludes) {
    SquareBox squareBox = new SquareBox(n, excludes);

    try {
      return this.breadthFirstSearch(squareBox, 0);
    } catch (CloneNotSupportedException e) {
      return 0;
    }
  }

  private int breadthFirstSearch(SquareBox squareBox, int depth) throws CloneNotSupportedException {
    int currentCount = squareBox.countSquares();
    if (currentCount == 0) {
      return depth;
    }

    int minCount = currentCount;
    List<SquareBox> boxList = new ArrayList<>();

    for (int i = 0; i < squareBox.getDataSize(); i++) {
      if (!squareBox.getDataAt(i)) {
        continue;
      }

      SquareBox clone = (SquareBox) squareBox.clone();
      clone.removeSideAt(i);

      // 辺を削れば最低1個は削れるはずなので減らなかったらskip
      int newCount = clone.countSquares();
      if (newCount == currentCount) {
        continue;
      }

      // いままでで一番小さくなったらリストを初期化
      if (newCount < minCount) {
        minCount = newCount;
        boxList = new ArrayList<>();
      }

      boxList.add(clone);
    }

    // 残ったやつの中からdepthが一番小さいものを求める
    int minDepth = Integer.MAX_VALUE;
    for (int i = 0; i < boxList.size(); i++) {
      minDepth = Math.min(minDepth, this.breadthFirstSearch(boxList.get(i), depth + 1));
    }

    return minDepth;
  }
}
