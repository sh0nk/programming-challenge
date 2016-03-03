import java.util.ArrayList;
import java.util.List;

public class NoripiNumberSets implements NumberSetsInterface {

  @Override
  public int solve(long A, long B, long P) {
    List<List<Long>> unionTree = new ArrayList<>();

    for (long l = A; l <= B; l++) {
      List<Long> union = new ArrayList<>();
      union.add(l);
      unionTree.add(union);
    }

    long l = this.isPrime(P) ? P : this.nextPrime(P);
    while (l <= B) {
      List<List<Long>> newUnionTree = new ArrayList<>();

      List<Long> multiplesList = new ArrayList<>();
      for (List<Long> union : unionTree) {
        boolean canDiv = false;

        // unionTreeのうちlで割れるやつは全て追加
        for (Long num : union) {
          if (num % l == 0) {
            canDiv = true;
            break;
          }
        }

        if (!canDiv) {
          // 割れないやつは別のリストとして追加
          newUnionTree.add(union);
        } else {
          multiplesList.addAll(union);
        }
      }
      if (multiplesList.size() > 0) {
        newUnionTree.add(multiplesList);
      }

      unionTree = newUnionTree;
      l = this.nextPrime(l);
    }

    return unionTree.size();
  }

  private long nextPrime(long num) {
    while (true) {
      if (num % 2 == 0) {
        num++;
      } else {
        num += 2;
      }

      if (this.isPrime(num)) {
        break;
      }
    }

    return num;
  }

  private boolean isPrime(long num) {
    for (long l = 2; l <= Math.sqrt(num); l++) {
      if (num % l == 0) {
        return false;
      }
    }

    return true;
  }

}
