/**
 * <pre>
 * 連続した整数の区間[A,B]と整数 P が与えられます。はじめは、与えられた区間に含まれるそれぞれの数は
 * 自分自身のみを含む集合に属しています。区間の中の 2 つの数のペアに対し、それらが P 以上の共通素因数
 * を持つ場合に、その 2 つの数を含む集合を合併します。この操作をすべてのペアに対して行った場合、最終
 * 的にいくつの集合になるでしょうか。
 * %制約
 * Small
 * ・1 ≦ A ≦ B ≦ 1000
 * ・2 ≦ P ≦ B
 * Large
 * ・1 ≦ A ≦ B ≦ 10^12
 * ・B ≦ A + 1000000
 * ・2 ≦ P ≦ B
 * </pre>
 * 
 * @author Noriyuki Ishida
 */
public interface NumberSetsInterface {
  public int solve(long A, long B, long P);
}
