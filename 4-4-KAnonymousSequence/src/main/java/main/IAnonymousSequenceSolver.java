package main;

/**
 * 長さ n の単調非減少数列 a0,a1,...,an-1 が与えられます。1 回の操作で数列の 1 つの項の値を 1 だけ小さくする
 * ことができます。数列の各項について、同じ値が少なくとも他にk-1個存在するようにするために必要な最 小の操作回数を求めなさい。
 * 
 * @author Noriyuki Ishida
 */
public interface IAnonymousSequenceSolver {
  public int solve(int n, int k, int[] a);
}
