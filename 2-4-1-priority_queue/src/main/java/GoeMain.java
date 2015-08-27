import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yoshigoe on 8/27/15.
 */
public class GoeMain implements Interface {

    public int execute(int N, int L, int P, int[] A, int[] B){
        if(N <1 || N>10000) return -1;
        if(L <1 || L>1000000) return -1;
        if(P <1 || P>1000000) return -1;

        int distance;
        int count = 0;
        int position = 0;
        int tank = P;
        ArrayList<Integer> newA = new ArrayList<Integer>();
        ArrayList<Integer> newB = new ArrayList<Integer>();
        for (int a : A) newA.add(a);
        for (int b : B) newB.add(b);

        newA.add(L);
        newB.add(0);

        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(N, new Comparator<Integer>(){
            public int compare (Integer o1, Integer o2){
                return o2 - o1;
            }
        });

        for (int i=0; i<N+1; i++){
            distance = newA.get(i) - position;
            while (tank - distance < 0){
                if(pQueue.isEmpty()){
                    return -1;
                }
                tank += pQueue.peek();
                pQueue.poll();
                count++;
            }
            tank -= distance;
            position = newA.get(i);
            pQueue.add(newB.get(i));
        }
        return count;
    }
}
