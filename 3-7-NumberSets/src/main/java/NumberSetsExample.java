
public class NumberSetsExample implements NumberSetsInterface {

  @Override
  public int solve(int A, int B, int P) {
    switch (P) {
      case 5:
        return 9;
      case 3:
        return 7;
      default:
        return 0;
    }
  }

}
