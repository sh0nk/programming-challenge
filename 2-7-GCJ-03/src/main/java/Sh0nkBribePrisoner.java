import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sh0nkBribePrisoner implements IBribePrisoner {

    class Range {
        int start;
        int end;
        Range(int start, int end) {
            this.start = start;
            this.end  =end;
        }
        int size() {
            return end - start + 1;
        }
        double center() {
            return ((double)end + (double)start) / 2;
        }
        boolean within(int i) {
            if (i <= end && i >= start) {
                return true;
            }
            return false;
        }
    }

    @Override
    public int answer(int P, int Q, int[] A) {
        List<Range> chunks = new ArrayList<>();
        List<Integer> divider = Arrays.stream(A).boxed().collect(Collectors.toList());
        // init
        chunks.add(new Range(1, P));

        int totalCost = 0;
        while (true) {
            Range maxChunk = null;
            for (Range chunk : chunks) {
                if (maxChunk == null) {
                    maxChunk = chunk;
                }
                if (chunk.size() > maxChunk.size()) {
                    maxChunk = chunk;
                }
            }
            chunks.remove(maxChunk);
            System.out.println(String.format("chunk: start %d end %d", maxChunk.start, maxChunk.end));

            int index = -1;
            double minDistance = Integer.MAX_VALUE;
            boolean withinFlag = false;
            for (int j = 0; j < divider.size(); j++) {
                if (!maxChunk.within(divider.get(j))) {
                    continue;
                }
                withinFlag = true;

                //System.out.println(String.format("    center: %f", maxChunk.center()));
                double can = Math.abs(maxChunk.center() - divider.get(j));
                System.out.println(String.format("    can: idx %d, can %f", divider.get(j), can));
                if (can < minDistance) {
                    minDistance = can;
                    index = divider.get(j);
                }
            }
            if (!withinFlag) {
                continue;
            }
            divider.remove(new Integer(index));
            System.out.println(String.format("  divider: %d", index));

            Range lowChunk = new Range(maxChunk.start, index - 1);
            Range highChunk = new Range(index + 1, maxChunk.end);
            totalCost += lowChunk.size();
            totalCost += highChunk.size();
            System.out.println(String.format("  total: %d", totalCost));
            if (divider.size() == 0) {
                break;
            }
            chunks.add(lowChunk);
            chunks.add(highChunk);
        }

        return totalCost;
    }
}
