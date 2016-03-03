/**
 * Created by nobuhiro.onishi
 */
public class Gcd {
    // 最大公約数
    static long gcd(long lcm, long l) {
        return l == 0 ? lcm : gcd(l,lcm % l);
    }

    // 最小公倍数
    static long lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
