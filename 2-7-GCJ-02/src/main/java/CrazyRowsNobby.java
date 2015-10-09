/**
 * Created by Nobby on 10/8/15.
 */
import java.util.ArrayList;
public class CrazyRowsNobby implements CrazyRows{

    @Override
    public int solve(int N, int[][] V){    	
        // 各行で最後に1が出る位置をArrayListに保存する
    	ArrayList<Integer> lastOnePosArray = getLastOnePosArrayList(N, V);
    	int answer = 0;
    	for (int i = 0; i < N; i++) {
    		answer += getNumberForFixRow(i, lastOnePosArray);
    	}
        return answer;
    }
    
    /**
     * 引数で指定した行の決定に必要な入れ替え回数を取得する
     * @param rowNumber
     * @param list
     * @return
     */
    private int getNumberForFixRow(int rowNumber, ArrayList<Integer> list) {
    	for (int i = 0; i < list.size(); i++) {
    		if (list.get(i) == rowNumber || list.get(i) == rowNumber + 1) {
    			list.remove(i);
    			return i;
    		}    		
    	}
    	return 0;
    }
    
    private ArrayList<Integer> getLastOnePosArrayList(int N, int[][] V) {
    	ArrayList<Integer> retArray = new ArrayList<Integer>();
    	for (int i = 0; i < N; i++) {
    		Integer lastOnePos = 0; // 1が存在しない行は0を返す
    		for (int j = 0; j < N; j++) {
    			if (V[i][N-1-j] == 1) {
    				lastOnePos = N-j; // index+1をposとして返す
    				break;
    			}
    		}
    		retArray.add(lastOnePos);
    	}
    	return retArray;
    }

}
