public class LatticeExample implements Lattice {
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    @Override
    public int solve(int aX, int aY, int bX, int bY) {
        int dx = Math.abs(aX - bX);
        int dy = Math.abs(aY - bY);

        return gcd(dx, dy % dx) - 1;
    }
}
