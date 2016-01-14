import java.util.HashMap;

public interface NetworkFlow {
	// S : source (スタート地点)
	// T : terminal (終点)
	public int getMaxFow(int S, int T, int[][] G);
}
