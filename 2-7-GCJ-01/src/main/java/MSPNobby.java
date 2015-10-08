/**
 * Created by Nobby on 10/8/15.
 */
import java.util.Arrays;
public class MSPNobby implements MSP{

    @Override
    public int solve(int[] v1, int[] v2){
    	// v1を昇順, v2を降順にソートしたときに内積は最小となる
    	Arrays.sort(v1);
    	Arrays.sort(v2);
    	int answer = 0;
    	int lastIndex = v2.length - 1;
    	for (int i = 0; i < lastIndex + 1; i++) {
    		System.out.println("v1 : " + v1[i] + ", v2 : " + v2[lastIndex - i]);
    		answer += v1[i] * v2[lastIndex - i];
    	}
    	return answer;
    }

}
