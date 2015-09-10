
public class LatticeKenji implements Lattice {

    int yu(int max, int min) {
        int m = max % min;
        if (m == 0) {
            return min;
        }
        return yu(min, m);
    }

    @Override
    public int solve(int aX, int aY, int bX, int bY) {
        int x = Math.abs(aX - bX);
        int y = Math.abs(aY - bY);

        return yu(Math.max(x, y), Math.min(x, y)) - 1;
    }

}
