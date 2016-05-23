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
    System.out.println("P: " + P);
    System.out.println("Q: " + Q);
    System.out.println(Arrays.toString(A));
    System.out.println("---------------------");

    for (int i = 0; i < A.length; i++) {
      // A[i] = A[i] - 1;
    }

    int sum_cut = cut(1, P, 0, Q - 1, A);

    System.out.println(sum_cut);

    return sum_cut;
  }

  public int cut(int roomStart, int roomEnd, int prisonerStart, int prisonerEnd, int[] A) {

    System.out.println("-------------");
    System.out.println("roomStart: " + roomStart);
    System.out.println("roomEnd: " + roomEnd);
    System.out.println("prisonerStart: " + prisonerStart);
    System.out.println("prisonerEnd: " + prisonerEnd);

    int min_cost = Integer.MAX_VALUE;
    int min_left = Integer.MAX_VALUE;
    int min_right = Integer.MAX_VALUE;
    int minPrisoner = -1;
    int minRoom = -1;
    for (int i = prisonerStart; i <= prisonerEnd; i++) {
      int left = A[i] - 1;
      int right = roomEnd - A[i];
      int min = Math.abs(right - left);
      System.out.println(left + "," + right + ": " + min);
      if (min < min_cost) {
        minPrisoner = i;
        minRoom = A[minPrisoner];
        min_cost = min;
        min_left = left;
        min_right = right;
      }
    }

    if (minPrisoner == -1) {
      return 0;
    }

    int money_left = minRoom - roomStart;
    int money_right = roomEnd - minRoom;

    System.out.println("minPrisoner: " + minPrisoner);
    System.out.println("minRoom: " + minRoom);
    System.out.println("min_cost: " + min_cost);
    System.out.println("money_left: " + (minRoom - roomStart));
    System.out.println("money_right: " + (roomEnd - minRoom));

    int left_cost = money_left + cut(roomStart, minRoom - 1, prisonerStart, minPrisoner - 1, A);
    System.out.println("left: " + left_cost);
    
    int right_cost = money_right + cut(minRoom + 1, roomEnd, minPrisoner + 1, prisonerEnd, A);
    System.out.println("right: " + right_cost);


    return left_cost + right_cost;
  }

}
