/**
 * Copyright (C) 2016 Retty, Inc.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 *
 * @author Noriyuki Ishida
 */

public class NoripiBribePrisoner implements IBribePrisoner {

  @Override
  public int answer(int P, int Q, int[] A) {
    // 壁付き配列
    int[] withBoundary = new int[A.length + 2];
    withBoundary[0] = 0;
    for (int i = 0; i < A.length; i++) {
      withBoundary[i + 1] = A[i];
    }
    withBoundary[A.length + 1] = P + 1;

    int wholeCost = 0;
    while (withBoundary.length > 2) {
      // 一番コストが低い人を探す
      int minIdx = -1;
      int minCost = Integer.MAX_VALUE;
      for (int i = 0; i < withBoundary.length - 2; i++) {
        int backwardCost = withBoundary[i + 1] - withBoundary[i] - 1;
        int forwardCost = withBoundary[i + 2] - withBoundary[i + 1] - 1;

        if (minCost > backwardCost + forwardCost) {
          minCost = backwardCost + forwardCost;
          minIdx = i;
        }
      }

      // その部屋に収容する
      int[] newDataWithBoundary = new int[withBoundary.length - 1];
      for (int i = 0; i < newDataWithBoundary.length; i++) {
        int realIdx = minIdx + 1 > i ? i : i + 1;
        newDataWithBoundary[i] = withBoundary[realIdx];
      }

      withBoundary = newDataWithBoundary;

      // costを計算
      wholeCost += minCost;
    }

    return wholeCost;
  }
}
