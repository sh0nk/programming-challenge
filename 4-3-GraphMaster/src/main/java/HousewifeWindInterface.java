import java.util.List;

/**
 * <pre>
 * XX 村には n 個の小屋があり、いくつかの小屋の間は両方向に通行可能な道路で結ばれていて、
 * そのグラフ は木となっています。各道路iはai 番とbi 番の小屋を結んでおり、
 * その道路を使って移動するにはwi の時間 がかかります。
 * あなたははじめ s 番の小屋にいます。次のようなクエリを q 個処理しなさい。
 *
 * A :現在位置から頂点 x へ移動し、かかった時間を出力する。
 * B : x 番の道路を使って移動するのにかかる時間が、t に変化する。
 *
 *  <p> 制約
 *  ・1≦n≦100000,0≦q≦100000, 1≦a,b≦n
 * </pre>
 *
 */

/**
 * query１個ずつの解答を
 */
public interface HousewifeWindInterface {
  public String solve(int n, int q, int s, List<int[]> abw, List<List> query);
}
