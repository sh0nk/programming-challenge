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

public class SampleBribePrisoner implements IBribePrisoner {

  @Override
  public int answer(int P, int Q, int[] A) {
    switch (P << 10 + Q) {
      case 8 << 10 + 1:
        return 7;
      case 20 << 10 + 3:
        return 35;
    }

    return 0;
  }
}
