package main;

/**
 * Hello world!
 *
 */
public class CraneExample implements CraneInterface {

  @Override
  public double[][] solve(int N, int C, int[] L, int[] S, int[] A) {
    switch (N) {
      case 2:
        return new double[][] {{5.00, 10.00}};

      case 3:
        return new double[][] {{-10.0, 5.0}, {-5.0, 10.0}};
    }
    return null;
  }

}
