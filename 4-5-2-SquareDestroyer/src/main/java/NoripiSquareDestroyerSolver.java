import java.util.ArrayList;
import java.util.List;

public class NoripiSquareDestroyerSolver implements ISquareDestroyerSolver {

  @Override
  public int solve(int n, int[] excludes) {
    SquareBox squareBox = new SquareBox(n, excludes);

    try {
      return this.breadthFirstSearch(squareBox);
    } catch (CloneNotSupportedException e) {
      return 0;
    }
  }

  private int breadthFirstSearch(SquareBox squareBox) throws CloneNotSupportedException {
    List<SquareBox> squareBoxList = new ArrayList<>();
    squareBoxList.add(squareBox);

    return this.breadthFirstSearch(squareBoxList, 0);
  }

  private int breadthFirstSearch(List<SquareBox> squareBoxList, int depth)
      throws CloneNotSupportedException {
    // 一番数が小さくなってるやつを調べる
    int currentCount = Integer.MAX_VALUE;
    for (SquareBox squareBox : squareBoxList) {
      currentCount = Math.min(currentCount, squareBox.countSquares());
    }

    if (currentCount == 0) {
      return depth;
    }

    int minCount = currentCount;
    List<SquareBox> boxList = new ArrayList<>();

    for (SquareBox squareBox : squareBoxList) {
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
    }

    return this.breadthFirstSearch(boxList, depth + 1);
  }
}
