package main;

/**
 * 1~nの数を並び替えた数列a0, a1,...,an-1が与えられます。この数列をバブルソートでソートするのに必要なスワップ回数を求めなさい<br>
 * (バブルソートとは、ai>ai+1であるようなiを見つけてスワップすることを繰り返すアルゴリズムのことを言います)。<br>
 * 制約
 * <li>1≦n≦100000
 */
public interface SwapCountInterface {
  public int solve(int n, int[] a);
}
