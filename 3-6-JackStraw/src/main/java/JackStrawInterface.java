
/***
 * 机の上に n 本の棒が置かれており、各棒 i の端点の座標は(pix, piy)と(qix, qiy)です。指定された m 個の棒のペア (ai, bi)について、それらが連結であるかを判定しなさい。2
 * つの棒が共有点を持つとき、それらは連結であ り、また連結な棒によって間接的につながっている場合も連結であるとします。 制約
 * <li>2≦n≦12
 * <li>0≦p_ix, p_iy, q_ix, q_iy ≦100
 * <li>0≦m≦10000, 1≦a_i, b_i ≦n
 */
interface JackStrawInterface {

    public String[] solve(int n, int[][] p, int[][] q, int m, int ab[][]);

}
