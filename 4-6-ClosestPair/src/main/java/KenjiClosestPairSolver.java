import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KenjiClosestPairSolver implements IClosestPairSolver {

  class Pair {
    Pair(double x, double y) {
      this.x = x;
      this.y = y;
    }

    double x;
    double y;

    public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }

  double closestPair(List<Pair> pairs, int n) {
    if (n <= 1) {
      return Integer.MAX_VALUE;
    }
    int m = n / 2;
    System.out.println(pairs);
    System.out.println("m: " + m);
    System.out.println("n: " + n);
    double x = pairs.get(m - 1).x;
    List<Pair> left = pairs.subList(0, m);
    List<Pair> right = pairs.subList(m, n);
    double d = Math.min(closestPair(left, m), closestPair(right, m));
    System.out.println("d: " + d);

    List<Pair> a = pairs.subList(0, n);
    a.sort(new Comparator<Pair>() {
      @Override
      public int compare(Pair o1, Pair o2) {
        if (o1.y > o2.y) {
          return 1;
        } else if (o1.y < o2.y) {
          return -1;
        } else {
          return 0;
        }
      }
    });

    List<Pair> b = new ArrayList<Pair>();
    for (int i = 0; i < n; i++) {
      if (Math.abs(a.get(i).x - x) >= d)
        continue;
      for (int j = 0; j < b.size(); j++) {
        double dx = a.get(i).x - b.get(b.size() - j - 1).x;
        double dy = a.get(i).y - b.get(b.size() - j - 1).y;
        if (dy >= d)
          break;
        d = Math.min(d, Math.sqrt(dx * dx + dy * dy));
      }
      b.add(a.get(i));
    }

    System.out.println(a);

    return d;
  }

  @Override
  public double solve(int n, double[] x, double[] y) {
    List<Pair> pairs = new ArrayList<Pair>();
    for (int i = 0; i < x.length; i++) {
      pairs.add(new Pair(x[i], y[i]));
    }
    pairs.sort(new Comparator<Pair>() {
      @Override
      public int compare(Pair o1, Pair o2) {
        if (o1.x > o2.x) {
          return 1;
        } else if (o1.x < o2.x) {
          return -1;
        } else {
          return 0;
        }
      }
    });

    return closestPair(pairs, n);
  }

}
