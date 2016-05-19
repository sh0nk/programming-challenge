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

/**
 * 次図のような、部屋が一列にP個並んだ牢屋があります。左の部屋から順に1, 2,...,Pと番号が付いています。はじめはすべての部屋に一人の囚人が暮らしています。隣り合う部屋の間には窓があって、隣の部屋の
 * 囚人と会話することができます。
 *
 * 囚人を解放していくことを考えます。ある部屋の囚人が解放されるとき、その隣の部屋の囚人にわかってしまうため、隣の囚人は怒り、暴れてしまいます。そこで、ある部屋の囚人を解放する際には、両隣の部屋の
 * 囚人に賄賂として金貨1枚を渡さなければなりません。また、解放されることは隣り合う部屋どうしで伝聞されるため、直接の両隣だけでなく、伝わり得るすべての囚人、すなわち空き部屋か端に到達するまでにい
 * る、すべての囚人に金貨を渡す必要があります。
 *
 * a1, a2,...,aQ 番の部屋の、Q人の囚人を解放したいのですが、その順番は決まっていません。必要な金貨の枚数ができるだけ少なくなるような順番で解放していったとき、何枚の金貨が必要になるでしょうか?
 * 制約 ・1≦N≦100 ・Q≦P small ・1≦P≦100 ・1≦Q≦5 large ・1≦P≦10000 ・1≦Q≦100
 */
public interface IBribePrisoner {

  public int answer(int P, int Q);
}
