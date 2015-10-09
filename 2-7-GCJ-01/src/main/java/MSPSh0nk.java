import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MSPSh0nk implements MSP {

    @Override
    public int solve(int[] v1, int[] v2){
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < v1.length; i++) {
            list1.add(v1[i]);
            list2.add(v2[i]);
        }

        Collections.sort(list1);
        Collections.sort(list2);

        long answer = 0;
        for (int i = 0; i < list1.size(); i++) {
            answer += list1.get(i) * list2.get(list1.size() - i - 1);
        }

        return (int) answer;
    }

}
