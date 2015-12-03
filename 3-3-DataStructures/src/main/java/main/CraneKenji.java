package main;

public class CraneKenji implements CraneInterface {

    class Obj {
        // absolute
        double aX;
        double aY;
        // relative
        double rX;
        double rY;
        int uAngle; // unit angle relative to previous
        int rAngle; // angle relative to previous
        Obj next;
        Obj prev;

        Obj(Obj prev, double rX, double rY, int uAngle) {
            this.prev = prev;
            this.rX = rX;
            this.rY = rY;
            this.uAngle = uAngle;
            if (prev != null) {
                this.aX = prev.aX + rX;
                this.aY = prev.aY + rY;
            }
            this.rAngle = uAngle + 90;
        }

        public Obj leaf() {
            Obj o = this;
            while (o.next != null) {
                o = o.next;
                // System.out.println("(" + round(o.aX, 2) + ", " + round(o.aY, 2) + ")");
            }
            return o;
        }

        public void operate(int start, int angle, int N) {
            Obj o = this;
            for (int i = 0; i < start; i++) {
                o = o.next;
            }
            int dRAngle = angle - o.rAngle;
            o.rAngle = angle;
            for (int i = 0; i < N - start; i++) {
                o.uAngle = dRAngle + o.uAngle;
                double dx = Math.cos(Math.toRadians(o.uAngle));
                double dy = Math.sin(Math.toRadians(o.uAngle));
                o = o.next;
                double norm = Math.sqrt(o.rX * o.rX + o.rY * o.rY);
                o.rX = norm * dx;
                o.rY = norm * dy;
                o.aX = o.prev.aX + o.rX;
                o.aY = o.prev.aY + o.rY;
                // System.out.println(o.prev.uAngle + ", " + o.prev.uAngle);
            }
            // System.out.println("(" + round(leaf().aX, 2) + ", " + round(leaf().aY, 2) + ")");
        }
    }

    /** http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places */
    public static double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public double[][] solve(int N, int C, int[] L, int[] S, int[] A) {

        Obj obj = new Obj(null, 0, 0, 90);
        initialize(obj, L, 0);

        double[][] ans = new double[C][2];
        for (int c = 0; c < C; c++) {
            obj.operate(S[c], A[c], N);
            Obj leaf = obj.leaf();
            ans[c][0] = leaf.aX;
            ans[c][1] = leaf.aY;
            System.out.println("(" + round(leaf.aX, 2) + ", " + round(leaf.aY, 2) + ")");
        }

        return ans;
    }

    void initialize(Obj obj, int[] L, int deep) {
        if (deep == L.length)
            return;
        obj.next = new Obj(obj, 0, L[deep], 90);
        initialize(obj.next, L, deep + 1);
    }

}
