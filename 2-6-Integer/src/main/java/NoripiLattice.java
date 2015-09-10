public class NoripiLattice implements Lattice {

  @Override
  public int solve(int aX, int aY, int bX, int bY) {
    int xdiff = Math.abs(aX - bX);
    int ydiff = Math.abs(aY - bY);

    if (xdiff == 0 && ydiff == 0) {
      return 0;
    }
    if (xdiff == 0) {
      return ydiff - 1;
    }
    if (ydiff == 0) {
      return xdiff - 1;
    }

    int gcd = this.euclid(xdiff, ydiff);
    return gcd - 1;
  }

  private int euclid(int x, int y) {
    int larger = Math.max(x, y);
    int smaller = Math.min(x, y);

    int modulo = larger % smaller;
    if (modulo == 0) {
      return smaller;
    }

    return this.euclid(smaller, modulo);
  }
}
