/**
 * Created by onishinobuhiro on 2016/01/14.
 * 辺を表すクラス（行き先、容量、逆辺）
 */
public class Edge {
    public int to;
    public int cap;
    public int rev;

    Edge(int t, int c, int r) {
        this.to = t;
        this.cap = c;
        this.rev = r;
    }
}
