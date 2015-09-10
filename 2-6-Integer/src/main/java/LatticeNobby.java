public class LatticeNobby implements Lattice {
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

        if (dx == 0 && dy == 0) {
        	return 0;
        }
        return gcd(dx, dy) - 1;
    }
}
