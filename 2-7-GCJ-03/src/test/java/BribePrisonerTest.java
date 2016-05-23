import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

public class BribePrisonerTest {

  private long startTime;

  private IBribePrisoner yourImplementation = new NoripiBribePrisoner();

  @Before
  public void setUp() {
    this.startTime = System.nanoTime();
  }

  @After
  public void tearDown() {
    double timeElapsed = (System.nanoTime() - this.startTime) / 1_000_000f;
    System.out.println(
        String.format("Finished execution in: %f millis.", timeElapsed));
  }

  @Test
  public void test1() {
    assertEquals(7, this.yourImplementation.answer(8, 1, new int[]{3}));
  }

  @Test
  public void test2() {
    assertEquals(35, this.yourImplementation.answer(20, 3, new int[]{3, 6, 14}));
  }

  @Test
  public void test3() {
    assertEquals(102, this.yourImplementation.answer(50, 4, new int[]{3, 8, 23, 40}));
  }

  @Test
  public void test4() {
    assertEquals(241, this.yourImplementation.answer(100, 5, new int[]{13, 20, 48, 56, 77}));
  }

  @Test
  public void test4_1() {
    assertEquals(1119, this.yourImplementation
        .answer(300, 13, new int[]{13, 20, 48, 56, 77, 106, 129, 150, 190, 211, 233, 254, 289}));
  }

  @Test
  public void test5() {
    assertEquals(15809, this.yourImplementation.answer(3000, 50,
        new int[]{88, 100, 122, 133, 168, 185, 279, 285, 317, 349, 410, 453, 636, 689, 723, 729,
                  768, 811, 835, 948, 963, 976, 1189, 1225, 1237, 1299, 1358, 1375, 1467, 1637,
                  1683, 1687, 1782, 1831, 1878, 1893, 1954, 1954, 1962, 2200, 2267, 2481, 2514,
                  2519, 2600, 2626, 2714, 2774, 2798, 2808
        }));
  }

  @Test
  public void test6() {
    assertEquals(62520, this.yourImplementation.answer(10000, 100,
        new int[]{83, 101, 163, 171, 312, 328, 442, 471, 730, 1022, 1063, 1225, 1354, 1483, 1584,
                  1931, 1966, 1966, 2158, 2311, 2453, 2520, 2544, 3210, 3313, 3329, 3386, 3670,
                  3674, 3716, 3731, 3898, 3935, 4278, 4282, 4391, 4406, 4425, 4442, 4516, 4637,
                  4683, 4791, 4969, 4982, 5105, 5387, 5570, 5629, 5812, 5819, 5875, 5921, 6262,
                  6334, 6383, 6396, 6449, 6550, 6606, 6721, 6802, 6948, 6956, 6991, 7052, 7167,
                  7182, 7270, 7452, 7573, 7595, 7684, 7781, 7974, 7975, 8063, 8127, 8205, 8538,
                  8594, 8600, 8645, 8663, 8806, 8951, 8975, 9045, 9387, 9402, 9467, 9505, 9577,
                  9584, 9663, 9680, 9700, 9759, 9821, 9963
        }));
  }
}
