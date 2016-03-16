import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NoripiPopularCows implements PopularCowsInterface {
  public class Node {
    private int value;
    private List<Node> nextNodes;

    public Node(int value) {
      this.value = value;
      this.nextNodes = new ArrayList<>();
    }

    public int getValue() {
      return this.value;
    }

    public void addNextNode(Node n) {
      this.nextNodes.add(n);
    }

    @Override
    public String toString() {
      String str = "" + this.value;
      str += "\t" + this.nextNodes.stream()
          .map(Node::getValue)
          .map(i -> Integer.toString(i))
          .collect(Collectors.joining(","));

      return str;
    }
  }

  @Override
  public int solve(int N, int M, int[] A, int[] B) {
    Node[] nodes = new Node[N];
    for (int i = 0; i < N; i++) {
      nodes[i] = new Node(i + 1);
    }

    for (int i = 0; i < M; i++) {
      nodes[A[i] - 1].addNextNode(nodes[B[i] - 1]);
    }

    for (int i = 0; i < N; i++) {
      System.out.println(nodes[i]);
    }

    return 1;
  }

}
