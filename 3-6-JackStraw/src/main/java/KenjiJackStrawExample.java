import java.util.ArrayList;
import java.util.List;

public class KenjiJackStrawExample implements JackStrawInterface {

  class Line {
    Point p;
    Point q;
    int num;
    List<Line> connectedLists = new ArrayList<Line>();

    Line(Point p, Point q, int num) {
      this.p = p;
      this.q = q;
      this.num = num + 1;
    }

    @Override
    public String toString() {
      return num + " (" + p + ", " + q + ")";
    }

  }

  class Point {
    int x;
    int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }

  @Override
  public String[] solve(int n, int[][] p, int[][] q, int m, int[][] ab) {
    System.out.println("========================");
    String[] answers = new String[m];

    // Represent given int array with Line class.
    List<Line> lines = new ArrayList<Line>();
    for (int i = 0; i < n; i++) {
      int px = p[i][0];
      int py = p[i][1];

      int qx = q[i][0];
      int qy = q[i][1];

      Line line = new Line(new Point(px, py), new Point(qx, qy), i);
      lines.add(line);
    }

    // Create connected lists with each other
    for (Line line1 : lines) {
      for (Line line2 : lines) {
        if (line1.equals(line2))
          continue;
        if (isIntersected(line1, line2)) {
          line1.connectedLists.add(line2);
        }
      }
    }

    for (int i = 0; i < m; i++) {
      Line aLine = lines.get(ab[i][0] - 1);
      Line bLine = lines.get(ab[i][1] - 1);
      List<Integer> checkedNum = new ArrayList<>();
      if (isConnected(aLine, bLine, checkedNum)) {
        answers[i] = "CONNECTED";
        System.out.println(answers[i]);
        continue;
      }
      answers[i] = "NOT CONNECTED";
      System.out.println(answers[i]);
    }

    return answers;
  }

  /**
   * Check if the two lines are connected considering indirect connection.
   * 
   * @param start the start line to be scanned from
   * @param target the target line
   * @param checkedNum the stored number already scanned
   * @return true if the two lines are connected, false if not.
   */
  public boolean isConnected(Line start, Line target, List<Integer> checkedNum) {
    if (start.connectedLists.isEmpty()) {
      return false;
    }
    if (start.connectedLists.contains(target)) {
      return true;
    }
    checkedNum.add(start.num);
    for (Line next : start.connectedLists) {
      if (checkedNum.contains(next.num)) {
        continue;
      }
      if (isConnected(next, target, checkedNum)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Check if the two lines are intersected.
   * 
   * @see http://sampleyy.hatenablog.com/entry/2015/03/28/104040
   * 
   * @param a the line to be checked
   * @param b the line to be checked
   * @return true if the two lines are intersected, false if not.
   */
  public boolean isIntersected(Line a, Line b) {
    if (((a.p.x - a.q.x) * (b.p.y - a.p.y) + (a.p.y - a.q.y) * (a.p.x - b.p.x)) *
        ((a.p.x - a.q.x) * (b.q.y - a.p.y) + (a.p.y - a.q.y) * (a.p.x - b.q.x)) <= 0) {
      if (((b.p.x - b.q.x) * (a.p.y - b.p.y) + (b.p.y - b.q.y) * (b.p.x - a.p.x)) *
          ((b.p.x - b.q.x) * (a.q.y - b.p.y) + (b.p.y - b.q.y) * (b.p.x - a.q.x)) <= 0) {
        return true;
      }
    }
    return false;
  }

}
