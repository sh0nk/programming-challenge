import java.util.Arrays;

/**
 * Copyright (C) 2016 Retty, Inc.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 *
 * @author Noriyuki Ishida
 */

public class KenjiSampleBribePrisoner implements IBribePrisoner {

  @Override
  public int answer(int P, int Q, int[] A) {

    System.out.println("---------------------");
    System.out.println(P);
    System.out.println(Q);
    System.out.println(Arrays.toString(A));
    System.out.println("---------------------");
    int sum_cut = cut(P, A, 0);

    System.out.println(sum_cut);

    return sum_cut;
  }

  public int cut(int P, int[] A, int sum_money) {

    int ret = 0;

    int left_cut = 0;
    for (int i = 0; i < P; i++) {
      int left = i + 1;
      if (A[0] == left) {
        break;
      }
      left_cut++;
    }

    int right_cut = 0;
    for (int i = 0; i < P; i++) {
      int right = P - i;
      if (A[A.length - 1] == right) {
        break;
      }
      right_cut++;
    }

    int[] tmp = new int[A.length - 1];
    int cut = 0;

    if (left_cut > right_cut) {
      for (int i = 0; i < A.length - 1; i++) {
        tmp[i] = A[i + 1] - left_cut;
      }
      cut = left_cut;
    } else {
      for (int i = 0; i < A.length - 1; i++) {
        tmp[i] = A[i];
      }
      cut = right_cut;
    }
    A = tmp;

    System.out.println("P:" + P);
    System.out.println("P-cut-1:" + (P - cut - 1));
    System.out.println("length: " + A.length);
    System.out.println("left: " + left_cut);
    System.out.println("right: " + right_cut);
    System.out.println("sum: " + sum_money);
    System.out.println(Arrays.toString(A));
    System.out.println("---------------------");

    if (A.length == 0) {
      return sum_money + P - 1;
    } else {
      ret = cut(P - cut - 1, A, sum_money + P - 1);
    }

    return ret;
  }

}
