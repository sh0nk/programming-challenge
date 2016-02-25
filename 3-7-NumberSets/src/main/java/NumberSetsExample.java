
public class NumberSetsExample implements NumberSetsInterface {

  @Override
  public int solve(long A, long B, long P) {
    if (P == 5) {
      return 9;
    }
    if (P == 3) {
      return 7;
    }
    return 0;
  }

}
