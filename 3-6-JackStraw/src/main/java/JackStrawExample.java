
public class JackStrawExample implements JackStrawInterface {

  @Override
  public String[] solve(int n, int[][] p, int[][] q, int m, int[][] ab) {
    return new String[] {
        "CONNECTED",
        "NOT CONNECTED",
        "CONNECTED",
        "NOT CONNECTED"
    };
  }
}
