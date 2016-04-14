import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SquareBox implements Cloneable {
  private boolean[] data;
  private int squareSize;

  public SquareBox(int n, int[] excludes) {
    // 棒の総数 (n+1)*n*2
    this.data = new boolean[(n + 1) * n * 2];

    List<Integer> excludeList = Arrays.stream(excludes).boxed().collect(Collectors.toList());
    for (int i = 0; i < this.data.length; i++) {
      this.data[i] = !excludeList.contains(i + 1);
    }

    this.squareSize = n;
  }

  private SquareBox(boolean[] data, int size) {
    this.data = data;
    this.squareSize = size;
  }

  public int countSquares() {
    int result = 0;
    for (int i = 0; i < this.squareSize; i++) {
      result += this.countSquares(i + 1);
    }

    return result;
  }

  public int getDataSize() {
    return this.data.length;
  }

  private int countSquares(int size) {
    int count = 0;
    for (int i = 0; i < this.data.length; i++) {
      boolean isSquare = true;
      for (int j = 0; j < size; j++) {
        int upperSide = i + j;
        int leftSide = i + this.squareSize + j * (this.squareSize * 2 + 1);
        int lowerSide = upperSide + size * (this.squareSize * 2 + 1);
        int rightSide = leftSide + size;

        isSquare &= this.getDataAt(upperSide) && this.isHorizontal(upperSide);
        isSquare &= this.getDataAt(lowerSide) && this.isHorizontal(lowerSide);
        isSquare &= this.getDataAt(leftSide) && !this.isHorizontal(leftSide);
        isSquare &= this.getDataAt(rightSide) && !this.isHorizontal(rightSide);
      }

      if (isSquare) {
        count++;
      }
    }

    return count;
  }

  public boolean getDataAt(int i) {
    return i < this.data.length ? this.data[i] : false;
  }

  private boolean isHorizontal(int i) {
    return i % (this.squareSize * 2 + 1) < this.squareSize;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return new SquareBox(this.data.clone(), this.squareSize);
  }

  public void removeSideAt(int i) {
    this.data[i] = false;
  }
}
