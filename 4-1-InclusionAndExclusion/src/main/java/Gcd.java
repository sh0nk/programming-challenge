/**
 * Created by nobuhiro.onishi
 */
public class Gcd {
    // 最大公約数
    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b,a % b);
    }

    // 最小公倍数
    static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
