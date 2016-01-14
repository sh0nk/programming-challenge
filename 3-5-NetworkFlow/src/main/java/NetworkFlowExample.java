import java.util.HashMap;

public class NetworkFlowExample implements NetworkFlow {
    @Override
    public int getMaxFow(int S, int T, HashMap<Integer, Edge[]> G) {
		int flow = 0;
		while(true) {
			boolean[] isDfsChecked = new boolean[T];
			int f = dfs(S, T, 100000000, isDfsChecked, G);
			if (f == 0) return flow;
			flow += f;
		}
    }
    
    private int dfs(int v, int t, int f, boolean[] isDfsChecked, HashMap<Integer, Edge[]> G) {
		if (v == t) return f;
		isDfsChecked[v] = true;
		for (int i = 0; i < G.get(v).length; i++) {
			Edge targetEdge = G.get(v)[i];
			if (isDfsChecked[targetEdge.to] && targetEdge.cap > 0) {
				int d = dfs(targetEdge.to, t, Math.min(f, targetEdge.cap), isDfsChecked, G);
				if (d > 0) {
					targetEdge.cap -= d;
					G.get(targetEdge.to)[targetEdge.rev].cap += d;
					return d;
				}
			}
		}
		return 0;
	}
}
