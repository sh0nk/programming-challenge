
/**
 * <pre>
 * 二人で次のようなゲームを行います。
 * w × h の格子に区切られた長方形の紙を用意し、交互に紙を切断します。
 * 切断は格子の境界に沿って、水平 または垂直に一直線に切らなければなりません。
 * n 回の切断の後には n+1 個の紙に分かれており、次の切断 では、それらのうちどれか 1 つの紙を選んで切断をします。
 * 1 つの格子しか含まない紙(1 × 1 の格子からな る紙)を最初に切り出した人が勝ちです。
 * 両者が最善を尽くす場合、先行のプレイヤーは勝つでしょうか、 負けるでしょうか?
 *  
 *  <p> 制約
 *  ・2≦w, h≦200
 * </pre>
 *
 */
public interface CuttingGameInterface {
  public String solve(int w, int h);
}